package JavaAlgorithm.huffmanCode;

import java.io.Serializable;
import java.util.HashMap;

public record ZipPackage(int endLength, HashMap<Byte, String> huffmanByteTable, byte[] huffmanCodeBytes) implements Serializable {
}
