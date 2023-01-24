package JavaAlgorithm.huffmanCode;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class FileCompressor {
    /**
     * 要压缩文件的地址
     */
    private String sourceFile;

    /**
     * 要输出压缩文件的目标地址
     */
    private String destinationFile;

    private ArrayList<HuffmanNode> huffmanNodes = new ArrayList<>();

    public FileCompressor(String srcFile, String destFile) {
        sourceFile = srcFile;
        destinationFile = destFile;
    }

    public void zipFile() {
        try (FileInputStream inputStream = new FileInputStream(sourceFile)) {
            byte[] byteArr = new byte[inputStream.available()];
            inputStream.read(byteArr);

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }


    }
}
