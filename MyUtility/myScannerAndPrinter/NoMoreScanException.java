package myScannerAndPrinter;

/**
 * 当用户不再需要输入时，ScannerPlus会抛出这个异常指示外界不需要再输入数据
 *
 * @author localuser
 * @date 2022/12/06
 */
public class NoMoreScanException extends Exception{
    public NoMoreScanException() {}
    public NoMoreScanException(String msg){
        super(msg);
    }
}
