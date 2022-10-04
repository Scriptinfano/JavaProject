package printerManagement;

public class PrintTask {
    String text;//要打印的内容
    private int printId;//打印任务的标识号码

    public PrintTask(int printId, String text) {
        this.printId = printId;
        this.text = text;
    }

    public int getPrintId() {
        return printId;
    }

    public String getText() {
        return text;
    }
}
