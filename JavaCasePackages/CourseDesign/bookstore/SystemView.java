package CourseDesign.bookstore;

import viewManagerPack.ViewManager;

import java.sql.SQLException;

public class SystemView extends ViewManager {

    public static void main(String[] args) throws SQLException {
        System.out.println("是否以测试模式登录？输入(0或1)：");
        boolean testMode = scanner.nextSelectionByInt(0, 1) == 1;
        String passWard;
        String user;

        String ipAddress;
        String port;
        String databaseName;
        if (testMode) {
            user = "root";
            passWard = "200329";

            ipAddress = "localhost";
            port = "3306";
            databaseName = "newcompany";
        } else {
            System.out.print("请输入数据库的ip地址：");
            ipAddress = scanner.nextLineNoEmpty();
            System.out.print("请输入账号：");
            user = scanner.nextLineNoEmpty();
            System.out.print("请输入密码：");
            passWard = scanner.nextLineNoEmpty();
            System.out.print("请输入数据库的端口号：");
            port = scanner.nextLineNoEmpty();
            System.out.print("请输入数据库的名字：");
            databaseName = scanner.nextLineNoEmpty();
        }


        DatabaseDao dao = new DatabaseDao(passWard, user, ipAddress, port, databaseName);

    }

    /**
     * 系统管理员主界面1
     */
    @Override
    protected void showMenu() {
        printer.println("**********欢迎使用书店管理系统************");
        printer.println("*         1、编辑图书库（在售书籍信息）                    *");
        printer.println("*         2、编辑库存          *");
        printer.println("*         4、编辑用户                   *");
        printer.println("*         4、编辑批发商                   *");
        printer.println("*         4、退出程序                        *");
        printer.println("********************************************");
    }

    /**
     * 显示menu1 1
     */
    public void showMenu1_1() {
        printer.println("***************************");
        printer.println("*         1、添加新图书       *");
        printer.println("*         2、删除图书          *");
        printer.println("*         3、          *");
        printer.println("*         4、返回       *");
        printer.println("***************************");
    }

    @Override
    protected void run() {

    }
}
