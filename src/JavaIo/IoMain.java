package JavaIo;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class IoMain {
    public static void main(String[] args) {
        try {
            testPrintStream();
        }catch (FileNotFoundException exception)
        {
            String message=exception.getMessage();
            System.err.println(message);
            exception.printStackTrace();
        }
    }

    public static void  testScannerNext() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (scanner.hasNext()) {
                String str1 = scanner.next();
                System.out.println("输入的字符串：" + str1);
            } else break;

        }
    }

    public static void testPrintStream() throws FileNotFoundException {
        FileOutputStream fileOut=new FileOutputStream("D:\\JavaProjects\\JavaProjects\\testFile\\test.txt");
        PrintStream p=new PrintStream(fileOut);
        System.setOut(p);
        System.out.println("写入文件");
    }

}
