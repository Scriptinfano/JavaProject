package CourseDesign.pratice2;

import viewManagerPack.ViewManager;

import java.util.Objects;


/**
 * 二叉计算树的管理系统类，维护管理二叉计算树并向用户提供交互操作界面
 *
 * @author localuser
 */
public class CalculateTreeSystem extends ViewManager {
    //printer和scanner是ViewManger已经封装好的输入输出对象
    private final BinaryCalculateTree tree = new BinaryCalculateTree();


    /**
     * 显示菜单
     */
    @Override
    protected void showMenu() {
        printer.println("**********欢迎使用表达式计算交互系统************");
        printer.println("*         1、设定新表达式                    *");
        printer.println("*         2、计算当前表达式并输出结果          *");
        printer.println("*         3、查看当前表达式                   *");
        printer.println("*         4、退出程序                        *");
        printer.println("********************************************");
    }


    /**
     * 运行整个系统，启动交互
     */
    @Override
    protected void run() {
        while (true) {
            String choice;
            showMenu();
            System.out.print("请输入你的选择：");
            choice = scanner.nextSelectionByString(1, 4);
            switch (choice) {
                case "1" -> setNewExpression();
                case "2" -> calculateExpression();
                case "3" -> outputExpression();
                case "4" -> exitProgram();//该接口的默认实现已经被ViewManager封装，此处直接调用表示退出程序
                default -> printer.println("你的输入不合法，请重新输入");
            }
        }
    }

    /**
     * 输出表达式
     */
    private void outputExpression() {
        String theExpression = tree.getExpression();
        printer.println(Objects.requireNonNullElse(theExpression, "表达式为空，请先设定表达式"));//Objects.requireNonNullElse判断第一个参数是否为空，若不为空则返回第一个参数，否则返回第二个参数
    }

    /**
     * 计算表达式的值，输出计算结果
     */
    private void calculateExpression() {
        double result;
        try {
            result = tree.calculate();
        } catch (RuntimeException e) {
            printer.println(e.getMessage());
            return;
        }
        assert tree.getExpression() != null;
        printer.println("表达式" + tree.getExpression() + "的运算结果=" + result);

    }

    /**
     * 设立新表达式
     */
    private void setNewExpression() {
        while (true) {
            printer.print("输入新的待计算的表达式（每个符号用空格分开）：");
            String expressionStr = scanner.nextLine();
            String[] expression = expressionStr.split(" ");//用空格分割字符串
            if (tree.setExpression(expression)) {
                //设置成功的情况
                printer.println("新表达式设置成功");
                break;
            } else {
                //表达式设置失败，说明表达式有问题，需要重新输入
                printer.println("表达式设置失败，你的输入不符合要求，请重新输入表达式");
            }
        }
    }
}

class TestBinaryCalculateTree {
    public static void main(String[] args) {
        test2();
    }

    /**
     * test1直接使用给定的表达式进行计算
     */
    private static void test1() {
        BinaryCalculateTree calculateTree = new BinaryCalculateTree();
        String[]expression4={"12","3","4"};
        String[] expression = {"2", "+", "3", "-", "4", "*", "3", "/", "4", "+", "7"};
        String[] expression2 = {"85", "+", "14", "*", "(", "15", "+", "8", "*", "9", "/", "2", "+", "14", "/", "208", "+", "26", ")", "*", "21", "+", "(", "327", "-", "23", ")", "/", "19"};
        String[] expression3 = {"1.2", "+", "(", "2.7", "-", "3.4", ")", "*", "4.4", "+", "4.1", "/", "2.8"};
        if (calculateTree.setExpression(expression4)) {
            double result = calculateTree.calculate();
            System.out.println(result);
        }
    }

    /**
     * test2使用交互界面录入表达式
     */
    private static void test2() {
        CalculateTreeSystem calculateTreeSystem = new CalculateTreeSystem();
        calculateTreeSystem.run();
    }
}