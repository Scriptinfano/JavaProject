package printerManagement;

import myscan.ScannerPlus;

public class PrintManagerSolver {
    private final PrintTaskManager manager = new PrintTaskManager();
    private ScannerPlus scanner = new ScannerPlus();

    public static void main(String[] args) {
        PrintManagerSolver solver = new PrintManagerSolver();
        solver.run();
    }

    public void run() {
        String userSelection;
        while (true) {
            showMenu();
            userSelection = scanner.nextLine();
            switch (userSelection) {
                case "1":
                    printFirstTask();
                    break;
                case "2":
                    addNewTask();
                    break;

                case "3":
                    printAllTask();
                    break;
                case "4":
                    clearAllTask();
                    break;

                case "5":
                    exitProgram();
                    break;
                default:
                    System.out.println("请输入正确的选项");
            }
        }

    }

    /**
     * 退出程序
     */
    private void exitProgram() {
        System.out.println("确认是否退出程序？（Y/N）");
        if (readConfirmSelection())
            System.exit(0);
    }


    /**
     * 读取确认信息
     *
     * @return boolean
     */
    private boolean readConfirmSelection() {
        while (true) {
            String userInput = scanner.nextLine();
            if (!userInput.equals("Y") && !userInput.equals("N") && !userInput.equals("y") && !userInput.equals("n")) {
                System.out.println("选择错误，请重新输入");
            } else {
                return userInput.equals("Y") || userInput.equals("y");
            }
        }
    }

    /**
     * 清除所队列中所有打印任务
     */
    private void clearAllTask() {
        manager.clearPrintTask();
    }

    /**
     * 打印所有任务
     */
    private void printAllTask() {
        manager.printAllTask();
    }

    /**
     * 添加新的任务
     */
    private void addNewTask() {
        System.out.println("请输入打印任务的id号码:");
        int id = scanner.nextInt();
        System.out.println("请输入打印内容:");
        String text = scanner.nextLine();
        manager.addTask(id, text);
    }

    /**
     * 打印队头的任务
     */
    private void printFirstTask() {
        manager.printTask();

    }

    /**
     * 显示菜单
     */
    private void showMenu() {
        System.out.println("============打印任务管理器============");
        System.out.println("            1、打印当前任务           ");
        System.out.println("            2、添加新的打印任务        ");
        System.out.println("            3、打印所有任务           ");
        System.out.println("            4、清除所有打印任务        ");
        System.out.println("            5、退出程序              ");
        System.out.println("====================================");
        System.out.println("请选择<1-5>:");

    }
}
