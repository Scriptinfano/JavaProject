package inputOutput;

import java.io.*;
import java.util.Scanner;

public class IoMain {
    public static void main(String[] args) {
        try {
            FileOutputStream fileOut = new FileOutputStream("D:\\JavaProjects\\JavaProjects\\testFile\\test.txt");
            testOutputStream(fileOut);
        } catch (IOException exception) {
            String message = exception.getMessage();
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
        FileOutputStream fileOut = new FileOutputStream("D:\\JavaProjects\\JavaProjects\\testFile\\test.txt");
        PrintStream p = new PrintStream(fileOut);
        System.setOut(p);
        System.out.println("写入文件");
    }

    public static void testOutputStream(OutputStream out) throws IOException {
        int firstPrintableCharacter = 33;
        int numberOfPrintableCharacter = 94;
        int numberOfCharacterPerLine = 72;
        int start = firstPrintableCharacter;
        byte[] line = new byte[numberOfCharacterPerLine + 2];
        for (int j = 0; j < 1000; j++) {
            for (int i = start; i < start + numberOfCharacterPerLine; i++) {
                line[i - start] = (byte) ((i - firstPrintableCharacter) % numberOfPrintableCharacter + firstPrintableCharacter);
            }
            line[72] = (byte) '\r';
            line[73] = (byte) '\n';
            out.write(line);
            start = ((start + 1) - firstPrintableCharacter) % numberOfPrintableCharacter + firstPrintableCharacter;
        }
    }
}
