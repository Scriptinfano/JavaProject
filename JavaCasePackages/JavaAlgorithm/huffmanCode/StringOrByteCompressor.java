package JavaAlgorithm.huffmanCode;

import DataStructure.tree.nodes.BinaryTreeNode;
import exceptions.RequiredActionNotExcuteException;
import org.jetbrains.annotations.NotNull;

import java.util.*;

class HuffmanNode extends BinaryTreeNode implements Comparable<HuffmanNode> {
    private final Character character;

    private final Byte byteCharacter;

    public HuffmanNode(Character ch, Integer value) {
        super(value);
        character = ch;
        byteCharacter = null;
    }

    public HuffmanNode(Byte by, Integer value) {
        super(value);
        byteCharacter = by;
        character = null;
    }

    @Override
    public int compareTo(@NotNull HuffmanNode o) {
        return this.getElement() - o.getElement();
    }

    public Character getCharacter() {
        return character;
    }

    public Byte getByteCharacter() {
        return byteCharacter;
    }

    @Override
    public String toString() {
        return "HuffmanNode{" +
                "character=" + character +
                ", byteCharacter=" + byteCharacter +
                '}';
    }

    public void preOrder() {
        System.out.println(this);
        if (getLeftChild() != null)
            getLeftChild().preOrder();
        if (getRightChild() != null)
            getRightChild().preOrder();
    }

    @Override
    public HuffmanNode getLeftChild() {
        return (HuffmanNode) super.getLeftChild();
    }

    @Override
    public HuffmanNode getRightChild() {
        return (HuffmanNode) super.getRightChild();
    }
}

/**
 * 字符串或字节数组压缩机，先将原字符串转换为二进制的哈夫曼编码，再将该哈夫曼编码转换为对应的byte[]数组，达到压缩字符串的目的，还可以根据压缩之后的byte[]数组
 * 将其还原为原本的字符串，对于字节数组也可以执行相同的操作
 */
public class StringOrByteCompressor {
    /**
     * 存放各哈夫曼树各叶子节点的容器，构建哈夫曼树时需要用到该容器，两个模式下生成的哈夫曼树叶子节点都保存在该容器当中
     */
    private final ArrayList<HuffmanNode> nodes = new ArrayList<>();
    /**
     * 一号霍夫曼表，保存键值对，键是Character，值是Character对应的霍夫曼编码
     */
    private final Map<Character, String> huffmanTable = new HashMap<>();
    /**
     * 二号霍夫曼表，保存键值对，键是Byte，值是Byte对应的霍夫曼编码
     */
    private final Map<Byte, String> huffmanTable2 = new HashMap<>();
    /**
     * 根据原字符串出现次数构建好的哈夫曼树的根节点，两个模式下生成的霍夫曼树的根节点都保存在这个变量中
     */
    private HuffmanNode root;
    /**
     * 模式一要处理的原本的字符串
     */
    private String preString;

    /**
     * 保存两个运行模式下生成的二进制霍夫曼编码
     */
    private String huffmanCode;

    /**
     * 模式二要处理的原始的待压缩字节数组
     */
    private byte[] preBytes;

    /**
     * 在将哈夫曼编码转化为字节数组的时候，因为是每8位算一个byte，但不能保证哈夫曼编码一定是8的
     * 倍数，所以最后留下几位不够8位，这个endLength就是记录最后是几位不够凑齐8位，以便在译码时
     * 将缺的0补齐
     */
    private int endLength;
    /**
     * 模式选择，A模式必须调用{@link StringOrByteCompressor#setPreString(String)}之后使用，该模式以字符串中字符的出现次数来作为
     * 哈夫曼树节点的权值；B模式必须调用{@link StringOrByteCompressor#setPreBytes(byte[])}，该模式以字节数组中某byte出现的次数
     * 作为哈夫曼树节点的权值
     */
    private Mode mode;

    /**
     * 计算运行模式A下的计算压缩比
     *
     * @param preStr 压缩之前的原本的字符串
     * @param arr    压缩之后得到的字节数组
     * @return double 返回的压缩率，越高越好
     */


    public static double compressionRatio(String preStr, byte[] arr) {
        return (1 - (13 + arr.length) / (double) (40 + 2 * preStr.length())) * 100;
    }

    public static Mode modeA() {
        return Mode.A;
    }

    /**
     * 计算运行模式B下的压缩比
     *
     * @param preBytes   压缩之前的字节数组
     * @param laterBytes 压缩之后的字节数组
     * @return double 返回的压缩率，越高越好
     */
    public static double compressionRatio(byte[] preBytes, byte[] laterBytes) {
        return (1 - (laterBytes.length) / (double) (preBytes.length)) * 100;
    }

    public static Mode modeB() {
        return Mode.B;
    }

    /**
     * 得到根据霍夫曼树所得到的霍夫曼编码
     *
     * @return {@link String} 返回由1和0组成的霍夫曼编码
     */
    public String getHuffmanCode() {
        if (huffmanCode == null || huffmanCode.isEmpty())
            throw new RequiredActionNotExcuteException("未运行压缩，无法从内部取得哈夫曼编码或运行之后暂未生成哈夫曼编码，检查调用时所处条件");
        return huffmanCode;
    }

    /**
     * 清空该对象内部的所有状态除了运行模式，运行模式可以随时更改
     */
    public void clear() {
        preBytes = null;
        preString = null;
        huffmanCode = null;
        huffmanTable.clear();
        huffmanTable2.clear();
        nodes.clear();
        root = null;
    }

    /**
     * 得到哈夫曼编码的流程控制函数，将结果保存在类内部变量huffmanCode中
     */
    private void loadCompressedCode() {
        switch (mode) {
            case A -> statisticalCharacter();//统计原字符串中各字符出现的次数
            case B -> statisticalCharacter2();
        }
        createHuffmanTree();//根据各字符出现的次数构建哈夫曼树
        //开始处理哈夫曼树，为每个叶子节点根据到达该叶子节点的路径生成哈夫曼编码，并将该叶子节点所对应的字符以及哈夫曼编码存储在HuffmanTable中
        processTreeNode(root, new StringBuilder(), null);
        huffmanCode = generateHuffmanCode();
    }

    private void statisticalCharacter2() {
        if (preBytes == null) throw new RequiredActionNotExcuteException("必要的字节数组未设定");
        Map<Byte, Integer> counts = new HashMap<>();
        for (byte b : preBytes) {
            counts.merge(b, 1, Integer::sum);
        }

        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            nodes.add(new HuffmanNode(entry.getKey(), entry.getValue()));
        }
    }

    /**
     * 设置要压缩的字节数组或者已经设定好字节数组的情况下可以重新设定字节数组（自动清空内部的状态）
     *
     * @param preBytes 要压缩的字节数组
     */
    public void setPreBytes(byte[] preBytes) {
        clear();
        this.preBytes = preBytes;
    }

    /**
     * 统计原字符串的字符的出现次数，将统计结果存入nodes
     */
    private void statisticalCharacter() {
        if (preString.isEmpty()) throw new RequiredActionNotExcuteException("必要的字符串未设定");
        //利用Map的特性统计preStr中每个字符出现的次数
        char[] chars = preString.toCharArray();
        Map<Character, Integer> counts = new HashMap<>();//每一个键值对的键就是字符，值就是字符在字符串中出现的次数
        for (char b : chars) {
            //试图查找编号为b的键值对，如果没有找到则会返回null，说明目前拿到的是一个新字符需要做新的统计，如果拿到了则更新他的出现次数
            //如果键值为b的键值对已经存在了，则将其旧值替换为新的值
            counts.merge(b, 1, Integer::sum);//若键为b的键值对不存在，则将新的键值对<b,1>放入HashMap容器；若存在，则将旧值和指定的值作为指定映射函数的两个参数得到一个新的值，再将新的值赋给指定key的value
        }
        //把每个键值对转换成哈夫曼节点HuffmanNode，并加入到nodes集合
        for (Map.Entry<Character, Integer> entry : counts.entrySet()) {
            nodes.add(new HuffmanNode(entry.getKey(), entry.getValue()));
        }
    }

    /**
     * 根据nodes统计的结果创建哈夫曼树
     */
    private void createHuffmanTree() {
        HuffmanNode parentNode = null;
        while (nodes.size() > 1) {
            Collections.sort(nodes);
            HuffmanNode leftNode = nodes.get(0);
            HuffmanNode rightNode = nodes.get(1);
            parentNode = new HuffmanNode((Character) null, leftNode.getElement() + rightNode.getElement());
            parentNode.setLeftChild(leftNode);
            parentNode.setRightChild(rightNode);
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parentNode);
        }
        root = parentNode;
    }

    /**
     * 设定运行模式
     *
     * @param mode 具体的模式
     * @see Mode
     */
    public void setMode(Mode mode) {
        this.mode = mode;
    }

    /**
     * 设定原字符串或者已经设定好字符串的情况下可以重新设定字符串（自动清空内部的状态）
     *
     * @param preStr 要压缩的字符串
     */
    public void setPreString(String preStr) {
        clear();
        preString = preStr;
    }

    /**
     * 根据已经构建好的哈夫曼编码表，将原字符串的各个字符按照编码表中的编码转化成哈夫曼编码
     *
     * @return {@link String} 转换后的哈夫曼编码，是一段由0和1组成的二进制字符串
     */
    private String generateHuffmanCode() {
        StringBuilder huffmanCodeBuilder = new StringBuilder();
        if (mode == Mode.A) {
            for (Character ch : preString.toCharArray()) {
                huffmanCodeBuilder.append(huffmanTable.get(ch));
            }
        } else {
            for (Byte by : preBytes) {
                huffmanCodeBuilder.append(huffmanTable2.get(by));
            }
        }

        return huffmanCodeBuilder.toString();
    }

    private static String byteToBitString(boolean flag, byte b) {
        int temp = b;//先将byte转换成int
        if (flag)
            temp |= 256;
        String str = Integer.toBinaryString(temp);
        return str.substring(str.length() - 8);
    }

    /**
     * 压缩字符串或字节数组，根据由0和1组成的二进制编码，将其转换为压缩之后的byte数组。在设定要压缩的字符串或者字节数组之后调用此接口得到压缩之后的byte[]数组
     * <br/>用法：在调用{@link StringOrByteCompressor#setPreBytes}或{@link StringOrByteCompressor#setPreString(String)}设置好要压缩的字符串或
     * 者字节数组并调用{@link StringOrByteCompressor#setMode(Mode)}设定好相应的运行模式之后调用该接口执行压缩操作
     *
     * @return {@link byte[]} 返回压缩之后的字节数组
     */
    public byte[] compress() {
        if (mode == null) throw new RequiredActionNotExcuteException("运行模式未设定，无法执行压缩");
        if (((mode == Mode.A) && (preString == null || preString.isEmpty())) || ((mode == Mode.B) && (preBytes == null || preBytes.length == 0)))
            throw new RequiredActionNotExcuteException("运行模式已设定，请设定要压缩处理的字符串或字节数组");
        loadCompressedCode();//生成压缩之后的二进制哈夫曼编码，保存在内部变量huffmanCode中
        //System.out.println("压缩之后得到的哈夫曼编码：" + huffmanCode);
        int arrayLength = (int) Math.ceil(huffmanCode.length() / 8d);
        byte[] huffmanCodeBytes = new byte[arrayLength];
        int index = 0;
        for (int i = 0; i < huffmanCode.length(); i += 8) {
            String strByte;
            //注意下面的sunString中的i+8可能会越界，因为无法保证生成的哈夫曼编码一定是8的倍数，所以做下面的分情况讨论
            if (i + 8 > huffmanCode.length()) {
                endLength = huffmanCode.length() - i;//因为不能保证转化之后的哈夫曼编码是8的倍数，所以最后有几位凑不齐8位，所以要把最后剩下的位数记下来，以便在解码时候使用
                strByte = huffmanCode.substring(i);//如果最后8位不够取，则从当前字符一直截取到最后的字符即可，当subString只有一个参数时，则截取该字符串时从该参数起一直截取到结尾
            } else
                strByte = huffmanCode.substring(i, i + 8);//注意subString截取的是字符串[begin,end)的子字符串
            huffmanCodeBytes[index] = (byte) Integer.parseInt(strByte, 2);//将二进制的字符串转化成十进制的数字
            index++;
        }
        return huffmanCodeBytes;
    }

    /*
     * 如果运行模式是B，则调用此接口将压缩之后的字节数组还原回压缩之前的字节数组
     *
     * @param byteArr 压缩之后的字节数组
     * @return {@link byte[]} 压缩之前的字节数组
     */
    /*public byte[] depressByteArrToByteArray(byte[] byteArr) {

    }*/

    private enum Mode {
        A,
        B
    }

    /**
     * 如果运行模式是A，解压压缩之后的字节数组，转换为原本的字节数组
     *
     * @param byteArr 压缩之后的字节数组
     * @return {@link String} 压缩之前的字符串
     */
    public String depressbyteArrToString(byte[] byteArr) {
        if (mode == null) throw new RequiredActionNotExcuteException("运行模式未设定，无法执行任何操作");
        if (mode == Mode.B) throw new UnsupportedOperationException("当前设置的是运行模式B，不支持将byte[]转化为String，模式B只能将byte[]转化为压缩之前的byte[]");
        if (huffmanTable.isEmpty())
            throw new RequiredActionNotExcuteException("请先执行模式A的压缩操作，再执行解压缩操作");
        StringBuilder builder = new StringBuilder();
        //这一段循环是将每一个byte还原成二进制字符串，也就是哈夫曼编码
        for (int i = 0; i < byteArr.length; i++) {
            if (i == byteArr.length - 1) {
                //对于最后一个要单独处理，长度最后一定要等于endLength
                int temp = byteArr[i];
                String tempString = Integer.toBinaryString(temp);
                StringBuilder tempBuilder = new StringBuilder(tempString);
                while (tempBuilder.length() < endLength)
                    tempBuilder.insert(0, '0');
                builder.append(tempBuilder);
                break;
            }
            builder.append(byteToBitString(byteArr[i] >= 0, byteArr[i]));
        }
        //System.out.println("解压时得到的哈夫曼编码：" + builder);

        //反转哈夫曼映射表，原本的映射表是从字符到哈夫曼编码，我们现在已知哈夫曼编码，要将哈夫曼编码变回字符，就需要反转映射表
        Map<String, Character> decodeMap = new HashMap<>();
        for (Map.Entry<Character, String> entry : huffmanTable.entrySet()) {
            decodeMap.put(entry.getValue(), entry.getKey());
        }
        List<Character> decodeList = new ArrayList<>();//保存由哈夫曼编码转化之后的字符

        while (!builder.isEmpty()) {
            int count = 1;
            Character character = null;
            while (character == null) {
                String tempStr = builder.substring(0, count);
                character = decodeMap.get(tempStr);
                count++;
            }
            decodeList.add(character);
            builder.delete(0, count - 1);
        }
        decodeList.forEach(builder::append);
        return builder.toString();
    }

    /**
     * 递归遍历哈夫曼树，将到叶子节点路径转化为二进制串
     *
     * @param node        初次调用传入哈夫曼树根节点
     * @param builder     记录到叶节点路径的字符串拼接器，走左节点拼接0，走右节点拼接1
     * @param leftOrRight 正在处理的节点是父节点的左节点还是右节点
     */
    private void processTreeNode(HuffmanNode node, StringBuilder builder, Boolean leftOrRight) {
        if (node != null) {
            if ((mode == Mode.A && node.getCharacter() == null) || (mode == Mode.B && node.getByteCharacter() == null)) {
                if (leftOrRight != null) {
                    //如果leftOrRight==null，则说明node是根节点，没有父节点，此时继续递归即可
                    if (leftOrRight) {
                        //该节点是父节点的左子树
                        builder.append("0");
                    } else {
                        //该节点是父节点的右子树
                        builder.append("1");
                    }
                }
                //该节点不是叶子节点，继续递归
                processTreeNode(node.getLeftChild(), new StringBuilder(builder), true);
                processTreeNode(node.getRightChild(), new StringBuilder(builder), false);
            } else {
                if (leftOrRight != null) {
                    if (leftOrRight) {
                        //该节点是父节点的左子树
                        builder.append("0");
                    } else {
                        //该节点是父节点的右子树
                        builder.append("1");
                    }
                }
                if (mode == Mode.A)
                    huffmanTable.put(node.getCharacter(), builder.toString());
                else huffmanTable2.put(node.getByteCharacter(), builder.toString());
            }
        }
    }
}

class TestHuffmanGenerator {
    public static void main(String[] args) {
        test();
    }

    public static void testMain() {
        StringOrByteCompressor compresser = new StringOrByteCompressor();
        String str = "I want to make love with y";
        System.out.println("压缩之前的字符串：" + str);
        compresser.setMode(StringOrByteCompressor.modeA());
        compresser.setPreString(str);
        byte[] compressedArr = compresser.compress();
        System.out.println("压缩之后的byte[]数组：" + Arrays.toString(compressedArr));
        String result = compresser.depressbyteArrToString(compressedArr);
        System.out.println("解码之后的结果：" + result);
        System.out.printf("压缩率%.2f\n", StringOrByteCompressor.compressionRatio(str, compressedArr));
    }

    public static void test() {
        String testStr = "hello world, I like java, do you like java.";
        System.out.println("压缩之前的字节数组" + Arrays.toString(testStr.getBytes()));
        byte[] byteArr = testStr.getBytes();
        StringOrByteCompressor compressor = new StringOrByteCompressor();
        compressor.setMode(StringOrByteCompressor.modeB());
        compressor.setPreBytes(byteArr);
        byte[] compressedBytes = compressor.compress();
        System.out.printf("压缩之后的字节数组" + Arrays.toString(compressedBytes));
    }
}