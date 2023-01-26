package JavaAlgorithm.huffmanCode;

import java.util.HashMap;

public class ZipPackage {
    private int endLength;
    private HashMap<Byte, String> huffmanByteTable;
    private byte[] huffmanCodeBytes;

    public ZipPackage(int endLength, HashMap<Byte, String> huffmanByteTable, byte[] huffmanCodeBytes) {
        this.endLength = endLength;
        this.huffmanByteTable = huffmanByteTable;
        this.huffmanCodeBytes = huffmanCodeBytes;
    }

    public int getEndLength() {
        return endLength;
    }

    public HashMap<Byte, String> getHuffmanByteTable() {
        return huffmanByteTable;
    }

    public byte[] getHuffmanCodeBytes() {
        return huffmanCodeBytes;
    }
}
