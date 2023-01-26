package JavaAlgorithm.huffmanCode;

import java.io.*;

public class FileCompressor {
    public static void zipFile(String sourceAddress, String compressAddress) {
        try (FileInputStream inStream = new FileInputStream(sourceAddress);
             FileOutputStream outStream = new FileOutputStream(compressAddress);
             ObjectOutputStream objectOutStream = new ObjectOutputStream(outStream)) {

            byte[] byteArr = new byte[inStream.available()];//调用available()方法来获取还有多少字节可以读取，根据该数值创建固定大小的byte数组，从而读取输入流的信息
            inStream.read(byteArr);//从输入流中读取文件，以字节的方式存放在字节数组中
            ZipPackage CompressedPackage = Compressor.compress(byteArr);//压缩字节数组
            objectOutStream.writeObject(CompressedPackage);//将压缩好的压缩包写入对象流
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        System.out.println("压缩完成");
    }

    public static void unZipFile(String sourceFile, String depressAddress) {
        try (
                InputStream is = new FileInputStream(sourceFile);
                ObjectInputStream ois = new ObjectInputStream(is)
        ) {
            ZipPackage compressedPackage = (ZipPackage) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            //两种要捕捉的异常没有共同的基类但有相同的处理操作时使用多重捕捉
            System.err.println(e.getMessage());
        }
    }
}

class TestFileCompressor {
    public static void main(String[] args) {
        String srcFile = "D:\\证件照片\\202107070707黄铭翔.jpg";
        String destFile = "D:\\证件照片\\photo.zip";
        FileCompressor.zipFile(srcFile, destFile);

    }
}
