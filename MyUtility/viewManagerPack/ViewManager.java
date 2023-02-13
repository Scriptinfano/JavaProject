package viewManagerPack;

import myScannerAndPrinter.ScannerPlus;

import java.io.PrintStream;

/**
 * 表示视图管理器的抽象类，封装了输入输出对象以及是否退出程序的代码，规定了几个要设计交互系统必须要重写的函数
 */
public abstract class ViewManager {
    protected static final ScannerPlus scanner = new ScannerPlus();
    protected static final PrintStream printer = new PrintStream(System.out);
    protected abstract void showMenu();
    /**
     * 询问用户是否退出，并执行退出程序的代码
     */
    protected void exitProgram() {
        String choice;
        printer.println("确定退出吗？输入(y/n):");
        while (true) {
            choice = scanner.nextLine();
            if (choice.equals("y") || choice.equals("Y")) {
                System.exit(0);
                pause();
            } else if (choice.equals("n") || choice.equals("N")) {
                return;
            } else {
                System.out.print("输入内容不正确，请重新输入:");
            }
        }
    }

    /**
     * 表示运行此交互系统
     */
    protected abstract void run();
    /**
     * 暂停程序的运行，按任意键继续
     */
    protected static void pause() {
        ScannerPlus.pause();
    }
}
