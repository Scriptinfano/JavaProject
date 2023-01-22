package JavaAlgorithm.huffmanCode;

import DataStructure.tree.nodes.BinaryTreeNode;
import exceptions.RequiredActionNotExcuteException;
import org.jetbrains.annotations.NotNull;

import java.util.*;

class HuffmanNode extends BinaryTreeNode implements Comparable<HuffmanNode> {
    private final Character character;

    public HuffmanNode(Integer value, Character ch) {
        super(value);
        character = ch;
    }

    @Override
    public int compareTo(@NotNull HuffmanNode o) {
        return this.getElement() - o.getElement();
    }

    public Character getCharacter() {
        return character;
    }

    @Override
    public String toString() {
        return "HuffmanNode{" + "character=" + character + '}';
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
 * 字符串压缩机，先将原字符串转换为二进制的哈夫曼编码，再将该哈夫曼编码转换为对应的byte[]数组，达到压缩字符串的目的
 */
public class StringCompressor {
    /**
     * 存放各哈夫曼树各叶子节点的容器，构建哈夫曼树时需要用到该容器
     */
    private final ArrayList<HuffmanNode> nodes = new ArrayList<>();

    /**
     * 根据原字符串出现次数构建好的哈夫曼树的根节点
     */
    private HuffmanNode root;

    /**
     * 哈夫曼编码表，Map容器，存储的是键值对，键是字符串中的字符，值是该字符对应的哈夫曼编码
     */
    private final Map<Character, String> huffmanTable = new HashMap<>();

    /**
     * 要处理的原本的字符串
     */
    private String preString;

    /**
     * 统计原字符串的字符的出现次数，将统计结果存入nodes
     */
    private void statisticalCharacter() {
        //利用Map的特性统计preStr中每个字符出现的次数
        byte[] bytes = preString.getBytes();//将字符串都转换成byte类型的数据，方便在Map中作为键值对的键存储
        Map<Byte, Integer> counts = new HashMap<>();//每一个键值对的键就是字符的编码，值就是字符在字符串中出现的次数
        for (byte b : bytes) {
            //试图查找编号为b的键值对，如果没有找到则会返回null，说明目前拿到的是一个新字符需要做新的统计，如果拿到了则更新他的出现次数
            //如果键值为b的键值对已经存在了，则将其旧值替换为新的值
            counts.merge(b, 1, Integer::sum);//若键为b的键值对不存在，则将新的键值对<b,1>放入HashMap容器；若存在，则将旧值和指定的值作为指定映射函数的两个参数得到一个新的值，再将新的值赋给指定key的value
        }

        //把每个键值对转换成哈夫曼节点HuffmanNode，并加入到nodes集合
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            nodes.add(new HuffmanNode(entry.getValue(), (char) (byte) entry.getKey()));
        }
    }

    /**
     * 设定原字符串或者已经设定好字符串的情况下可以重新设定字符串
     *
     * @param preStr 要压缩的字符串
     */
    public void setPreString(String preStr) {
        if (!nodes.isEmpty() || root != null || !huffmanTable.isEmpty()) {
            //如果这些内部容器有一个或多个不为空，说明这是上一次处理残留下来的数据，若想设定新字符串，必须先清空这些残留数据
            nodes.clear();
            root = null;
            huffmanTable.clear();
        }
        preString = preStr;
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
            parentNode = new HuffmanNode(leftNode.getElement() + rightNode.getElement(), null);
            parentNode.setLeftChild(leftNode);
            parentNode.setRightChild(rightNode);
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parentNode);
        }
        root = parentNode;
    }

    /**
     * 得到哈夫曼编码的流程控制函数
     *
     * @return {@link String} 返回的由0和1组成的二进制哈夫曼编码
     */
    private String getCompressedCode() {
        if (preString == null || preString.isEmpty())
            throw new RuntimeException("请先调用setPreString(String)接口设定要压缩处理的字符串再调用此接口");
        statisticalCharacter();//统计原字符串中各字符出现的次数
        createHuffmanTree();//根据各字符出现的次数构建哈夫曼树
        //开始处理哈夫曼树，为每个叶子节点根据到达该叶子节点的路径生成哈夫曼编码，并将该叶子节点所对应的字符以及哈夫曼编码存储在HuffmanTable中
        processTreeNode(root, new StringBuilder(), null);
        return generateHuffmanCode();
    }


    /**
     * 根据由0和1组成的二进制编码，将其转换为压缩之后的byte数组。在调用{@link StringCompressor#setPreString(String)}之后调用此接口得到压缩之后的byte[]数组
     */
    public byte[] getHuffmanCodeBytes() {
        String huffmanCode = getCompressedCode();
        System.out.println("转换原始字符串之后的哈夫曼编码" + huffmanCode);
        int arrayLength = (int) Math.ceil(huffmanCode.length() / 8d);
        byte[] huffmanCodeBytes = new byte[arrayLength];
        int index = 0;
        for (int i = 0; i < huffmanCode.length(); i += 8) {
            String strByte;
            //注意下面的sunString中的i+8可能会越界，因为无法保证生成的哈夫曼编码一定是8的倍数，所以做下面的分情况讨论
            if (i + 8 > huffmanCode.length())
                strByte = huffmanCode.substring(i);//如果最后8位不够取，则从当前字符一直截取到最后的字符即可，当subString只有一个参数时，则截取该字符串时从该参数起一直截取到结尾
            else
                strByte = huffmanCode.substring(i, i + 8);//注意subString截取的是字符串[begin,end)的子字符串
            huffmanCodeBytes[index] = (byte) Integer.parseInt(strByte, 2);
            index++;
        }
        return huffmanCodeBytes;
    }

    /**
     * 根据已经构建好的哈夫曼编码表，将原字符串的各个字符按照编码表中的编码转化成哈夫曼编码
     *
     * @return {@link String} 转换后的哈夫曼编码，是一段由0和1组成的二进制字符串
     */
    private String generateHuffmanCode() {
        StringBuilder huffmanCodeBuilder = new StringBuilder();
        for (Character ch : preString.toCharArray()) {
            huffmanCodeBuilder.append(huffmanTable.get(ch));
        }
        return huffmanCodeBuilder.toString();
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
            StringBuilder newBuilder = new StringBuilder(builder);
            if (node.getCharacter() == null) {
                if (leftOrRight != null) {
                    if (leftOrRight) {
                        //该节点是父节点的左子树
                        newBuilder.append("0");
                    } else {
                        //该节点是父节点的右子树
                        newBuilder.append("1");
                    }
                }
                //该节点不是叶子节点，继续递归
                processTreeNode(node.getLeftChild(), newBuilder, true);
                processTreeNode(node.getRightChild(), newBuilder, false);
            } else {
                //该节点是叶节点，此时newBuilder中存储的字符串就是该叶节点代表的字符的哈夫曼编码
                huffmanTable.put(node.getCharacter(), newBuilder.toString());
            }
        }
    }

    private static String byteToBitString(boolean flag, byte b) {
        int temp = b;//先将byte转换成int
        if (flag)
            temp |= 256;
        String str = Integer.toBinaryString(temp);
        return str.substring(str.length() - 8);
    }

    public void decode(byte[] byteArr) {
        if (huffmanTable.isEmpty())
            throw new RequiredActionNotExcuteException("请先执行压缩操作，再执行解压缩操作");
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < byteArr.length; i++) {
            if (i == byteArr.length - 1) {
                //对于最后一个
                int temp = byteArr[i];
                builder.append(Integer.toBinaryString(temp));
                break;
            }
            builder.append(byteToBitString(byteArr[i] >= 0, byteArr[i]));
        }

        Map<String, Character> decodeMap = new HashMap<>();
        for (Map.Entry<Character, String> entry : huffmanTable.entrySet()) {
            decodeMap.put(entry.getValue(), entry.getKey());
        }
        List<Character> decodeList = new ArrayList<>();
        for (int i = 0; i < builder.length(); i++) {
            int count = 0;
            Character character = null;
            while (character == null) {
                decodeMap.get(builder.substring(count, i + 1));
                count++;
            }

        }
    }
}

class TestHuffmanGenerator {
    public static void main(String[] args) {
        StringCompressor generator = new StringCompressor();
        String str = "holy shit, mother fucker!";
        generator.setPreString(str);
        byte[] compressedArr = generator.getHuffmanCodeBytes();
        System.out.println(Arrays.toString(compressedArr));
        generator.decode(compressedArr);

    }

    public static void test() {
        String str = "011010001000100101111011011110100010111001100101011110001110111111001011101";
        String str2 = "011010001000100101111011011110100010111001100101011110001110111111001011101";
        System.out.println(str.equals(str2));


    }
}