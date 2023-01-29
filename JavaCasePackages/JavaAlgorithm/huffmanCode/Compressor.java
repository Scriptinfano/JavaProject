package JavaAlgorithm.huffmanCode;

import DataStructure.tree.nodes.BinaryTreeNode;
import org.jetbrains.annotations.NotNull;

import java.util.*;

class HuffmanNode extends BinaryTreeNode<Integer> implements Comparable<HuffmanNode> {
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
        return this.getValue() - o.getValue();
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

public class Compressor {
    private static HashMap<Byte, String> huffmanByteTable = new HashMap<>();
    private static int endLength;

    private static String getHuffmanCode(byte[] preBytes) {
        ArrayList<HuffmanNode> huffmanNodes = statisticalCharacter(preBytes);//统计字节数组各字节出现次数，并构造哈夫曼叶子节点，返回保存叶子节点的容器
        HuffmanNode root = createHuffmanTree(huffmanNodes);//根据哈夫曼叶子节点构建哈夫曼树，返回树的根节点
        processTreeNode(root, new StringBuilder(), null);
        return generateHuffmanCode(preBytes);
    }

    private static String generateHuffmanCode(byte[] preBytes) {
        StringBuilder huffmanCodeBuilder = new StringBuilder();
        for (Byte by : preBytes) {
            huffmanCodeBuilder.append(huffmanByteTable.get(by));
        }
        return huffmanCodeBuilder.toString();
    }

    private static void processTreeNode(HuffmanNode node, StringBuilder builder, Boolean leftOrRight) {
        if (node != null) {
            if (node.getByteCharacter() == null) {
                //该节点为非叶子节点
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
                huffmanByteTable.put(node.getByteCharacter(), builder.toString());
            }
        }
    }

    private static HuffmanNode createHuffmanTree(ArrayList<HuffmanNode> huffmanNodes) {
        HuffmanNode parentNode = null;
        while (huffmanNodes.size() > 1) {
            Collections.sort(huffmanNodes);
            HuffmanNode leftNode = huffmanNodes.get(0);
            HuffmanNode rightNode = huffmanNodes.get(1);
            parentNode = new HuffmanNode((Character) null, leftNode.getValue() + rightNode.getValue());
            parentNode.setLeftChild(leftNode);
            parentNode.setRightChild(rightNode);
            huffmanNodes.remove(leftNode);
            huffmanNodes.remove(rightNode);
            huffmanNodes.add(parentNode);
        }
        return parentNode;
    }

    private static ArrayList<HuffmanNode> statisticalCharacter(byte[] preBytes) {
        Map<Byte, Integer> counts = new HashMap<>();
        for (byte b : preBytes) {
            counts.merge(b, 1, Integer::sum);
        }
        ArrayList<HuffmanNode> huffmanNodes = new ArrayList<>();
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            huffmanNodes.add(new HuffmanNode(entry.getKey(), entry.getValue()));
        }
        return huffmanNodes;
    }

    public static ZipPackage compress(byte[] preBytes) {
        String huffmanCode = getHuffmanCode(preBytes);
        ZipPackage newPackage = new ZipPackage(endLength, new HashMap<>(huffmanByteTable), getHuffmanCodeBytes(huffmanCode));
        endLength = 0;
        huffmanByteTable.clear();
        return newPackage;
    }


    private static byte[] getHuffmanCodeBytes(String huffmanCode) {
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

    private static String byteToBitString(boolean flag, byte b) {
        int temp = b;//先将byte转换成int
        if (flag)
            temp |= 256;
        String str = Integer.toBinaryString(temp);
        return str.substring(str.length() - 8);
    }

    /**
     * 将压缩包还原回原本的字节数组
     *
     * @param compressedPackage 压缩包
     * @return {@link byte[]} 返回压缩之前的字节数组
     */
    public static byte[] depress(ZipPackage compressedPackage) {
        byte[] compressedBytes = compressedPackage.huffmanCodeBytes();
        int endLengths = compressedPackage.endLength();
        Map<Byte, String> huffmanTable = compressedPackage.huffmanByteTable();

        StringBuilder huffmanCodeBuilder = new StringBuilder();
        //这一段循环是将每一个byte还原成二进制字符串，也就是哈夫曼编码
        for (int i = 0; i < compressedBytes.length; i++) {
            if (i == compressedBytes.length - 1) {
                //对于最后一个要单独处理，长度最后一定要等于endLength
                int temp = compressedBytes[i];
                String tempString = Integer.toBinaryString(temp);
                StringBuilder tempBuilder = new StringBuilder(tempString);
                while (tempBuilder.length() < endLengths)
                    tempBuilder.insert(0, '0');
                huffmanCodeBuilder.append(tempBuilder);
                break;
            }
            huffmanCodeBuilder.append(byteToBitString(compressedBytes[i] >= 0, compressedBytes[i]));
        }

        //反转哈夫曼映射表，原本的映射表是从字符到哈夫曼编码，我们现在已知哈夫曼编码，要将哈夫曼编码变回字符，就需要反转映射表
        Map<String, Byte> decodeMap = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanTable.entrySet()) {
            decodeMap.put(entry.getValue(), entry.getKey());
        }
        List<Byte> decodeList = new ArrayList<>();//保存由哈夫曼编码转化之后的字符

        while (!huffmanCodeBuilder.isEmpty()) {
            int count = 1;
            Byte newByte = null;
            while (newByte == null) {
                String tempStr = huffmanCodeBuilder.substring(0, count);
                newByte = decodeMap.get(tempStr);
                count++;
            }
            decodeList.add(newByte);
            huffmanCodeBuilder.delete(0, count - 1);
        }

        byte[] newBytes = new byte[decodeList.size()];
        int i = 0;
        for (Byte by : decodeList)
            newBytes[i++] = by;
        return newBytes;
    }


}

class TestHuffmanGenerator {
    public static void main(String[] args) {
        testMain();
    }

    public static void testMain() {
        String str = "I want to make love with y";
        System.out.println("压缩之前的字符串：" + str);
        ZipPackage compressedPackage = Compressor.compress(str.getBytes());
        System.out.println("压缩之后的byte[]数组：" + Arrays.toString(compressedPackage.huffmanCodeBytes()));
        byte[] strbytes = Compressor.depress(compressedPackage);
        String resultStr = new String(strbytes);
        System.out.println(resultStr);
    }

}
