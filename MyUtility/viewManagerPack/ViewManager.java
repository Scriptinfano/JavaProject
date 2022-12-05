package viewManagerPack;

import myScannerAndPrinter.ScannerPlus;

public interface ViewManager {
    ScannerPlus scanner=new ScannerPlus();

    void showMenu();

    default void exitProgram(){
        String choice = null;
        System.out.println("确定退出吗？输入(y/n):");
        while (true) {
            choice = scanner.nextLine();
            if (choice.equals("y") || choice.equals("Y"))
                System.exit(0);
            else if (choice.equals("n") || choice.equals("N")) {
                return;
            } else {
                System.out.print("输入内容不正确，请重新输入:");
            }
        }

    }

    void viewInteraction();
}
