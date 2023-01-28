package JavaAlgorithm.huffmanCode;

import java.io.*;

/**
 * 文件压缩工具
 *
 * @author Mingxiang
 */
public class FileCompressor {
    public static void zipFile(String sourceAddress, String compressAddress) {
        System.out.println("压缩文件开始");
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

    /**
     * 解压缩zip文件
     *
     * @param sourceFile     待解压的文件
     * @param depressAddress 解压之后文件的输出地址
     */
    public static void unZipFile(String sourceFile, String depressAddress) {
        System.out.println("解压文件开始");
        try (
                InputStream is = new FileInputStream(sourceFile);//创建文件输入流
                ObjectInputStream ois = new ObjectInputStream(is);//创建与文件输入流相关联的对象输入流，如此以来，就可以把对象从文件里读取出来
                FileOutputStream fos = new FileOutputStream(depressAddress)
        ) {
            byte[] preBytes = Compressor.depress((ZipPackage) ois.readObject());//从对象输入流中得到压缩包然后扔给工具类去处理解压
            fos.write(preBytes);
        } catch (IOException | ClassNotFoundException e) {
            //两种要捕捉的异常没有共同的基类但有相同的处理操作时使用多重捕捉
            System.err.println(e.getMessage());
        }
        System.out.println("解压文件结束");
    }
}

class TestFileCompressor {
    public static void main(String[] args) {
        String srcFile = " ";
        String destFile = "";
        String depressAddress = "";
        FileCompressor.zipFile(srcFile, destFile);
        FileCompressor.unZipFile(destFile, depressAddress);

    }
}
