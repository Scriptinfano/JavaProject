package myScannerAndPrinter;

import java.io.PrintStream;

/**
 * 输入输出转换器，便于直接使用输入输出对象
 *
 * @author localuser
 * @date 2022/12/05
 */
public class IOTransformer {
    public static final ScannerPlus scanner=new ScannerPlus();
    public static final PrintStream printer=new PrintStream(System.out);
}
