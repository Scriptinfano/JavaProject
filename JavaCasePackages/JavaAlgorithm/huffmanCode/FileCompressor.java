package JavaAlgorithm.huffmanCode;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class FileCompressor {
    /**
     * 要压缩文件的地址
     */
    private String sourceFile;

    /**
     * 要输出压缩文件的目标地址
     */
    private String destinationFile;

    /**
     * 构造器
     *
     * @param srcFile  源文件地址
     * @param destFile 要输出文件的地址
     */
    public FileCompressor(String srcFile, String destFile) {
        sourceFile = srcFile;
        destinationFile = destFile;
    }

    public void zipFile() {
        try (FileInputStream inStream = new FileInputStream(sourceFile);
             FileOutputStream outStream = new FileOutputStream(destinationFile);
             ObjectOutputStream objectOutStream = new ObjectOutputStream(outStream)) {

            byte[] byteArr = new byte[inStream.available()];//调用available()方法来获取还有多少字节可以读取，根据该数值创建固定大小的byte数组，从而读取输入流的信息
            inStream.read(byteArr);
            StringOrByteCompressor compressor = new StringOrByteCompressor();
            compressor.setMode(StringOrByteCompressor.modeB());
            compressor.setPreBytes(byteArr);
            byte[] compressedBytes = compressor.compress();

            objectOutStream.writeObject(compressedBytes);//将压缩好的字节数组作为对象写入对象输出流
            objectOutStream.writeObject(compressor);//将解码器本身也作为对象传入，方便在读取该文件时获取该对象并利用该对象在内部保存的状态来解压缩文件


        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        System.out.println("压缩完成");
    }
}

class TestFileCompressor {
    public static void main(String[] args) {
        String srcFile = "D:\\JavaProjects\\JavaProjects\\JavaCasePackages\\JavaAlgorithm\\huffmanCode\\testfile.bmp";
        String destFile = "D:\\JavaProjects\\JavaProjects\\JavaCasePackages\\JavaAlgorithm\\huffmanCode\\dest.zip";
        FileCompressor compressor = new FileCompressor(srcFile, destFile);
        compressor.zipFile();
    }
}
