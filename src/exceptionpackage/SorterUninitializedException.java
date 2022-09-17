package exceptionpackage;

public class SorterUninitializedException extends Throwable {
    private String message = "排序器未初始化，无法执行排序功能";

    public SorterUninitializedException(String theMessage) {
        this.message = theMessage;
    }

    public SorterUninitializedException() {
    }

    public void what() {
        System.err.println(message);
    }

}
