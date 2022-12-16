package viewManagerPack;

import myScannerAndPrinter.ScannerPlus;

import java.io.PrintStream;

public abstract class ViewManager {
    protected static final ScannerPlus scanner = new ScannerPlus();
    protected static final PrintStream printer = new PrintStream(System.out);

    protected void showMenu() {
    }

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

    protected void run() {
    }

    protected static void pause() {
        ScannerPlus.pause();
    }
}
