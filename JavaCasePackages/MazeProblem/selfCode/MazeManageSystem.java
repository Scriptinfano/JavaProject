package MazeProblem.selfCode;

import viewManagerPack.ViewManager;
/**
 * 迷宫求解类
 *
 * @author localuser
 */
class MazeSolver {
    /**
     * 迷宫
     */
    private Maze maze;
    /**
     * 负责调用迷宫生成算法，返回迷宫给maze引用
     */
    private final MazeCreator creator=new MazeCreator();

    public void generateNewMaze(int length,int width){
        maze=creator.getNewMaze(length,width);
    }
}

/**
 * 求解迷宫的用户交互系统类
 *
 * @author localuser
 */
public class MazeManageSystem extends ViewManager {
    /**
     * 迷宫求解对象，封装迷宫对象并调用内置迷宫求解算法求解迷宫
     */
    private MazeSolver solver;

    /**
     * 显示菜单
     */
    @Override
    protected void showMenu() {
        printer.println("**********欢迎使用迷宫求解系统****************");
        printer.println("*         1、生成新迷宫                     *");
        printer.println("*         2、查看当前生成的迷宫（*表示迷宫的墙）*");
        printer.println("*         3、求解当前迷宫并输出迷宫走法        *");
        printer.println("*         4、退出程序                       *");
        printer.println("*******************************************");
    }

    /**
     * 运行迷宫求解系统，初始化该对象之后请首先调用此接口
     */
    @Override
    protected void run() {
        while (true) {
            String choice;
            showMenu();
            printer.print("请输入你的选择：");
            choice = scanner.nextSelectionByString(1, 4);
            switch (choice) {
                case "1" -> generateNewMaze();
                case "2" -> showMaze();
                case "3" -> solveMaze();
                case "4" -> exitProgram();
                default -> printer.println("你的输入不合法，请重新输入");
            }
        }
    }

    /**
     * 求解迷宫的用户接口
     */
    private void solveMaze() {
        if (solver == null)
            printer.println("迷宫未生成，请先生成迷宫再执行其他操作");
        else {

        }
    }

    /**
     * 显示迷宫
     */
    private void showMaze() {
        if (solver == null)
            printer.println("迷宫未生成，请先生成迷宫再执行其他操作");
        else {

        }
    }

    /**
     * 生成新迷宫
     */
    private void generateNewMaze() {
        if (solver == null) {
            //执行生成新迷宫的接口
            System.out.print("请输入迷宫的长度(长度至少为10)：");
            int length = scanner.nextIntWithLimit(false, 0, "迷宫的长不能为负数");
            System.out.print("请输入迷宫的宽度（宽度至少为10）：");
            int width = scanner.nextIntWithLimit(false, 0, "迷宫的宽不能为负数");
            solver.generateNewMaze(length, width);
        } else {
            printer.print("迷宫已生成，是否重新生成（输入0表示不再设定，输入1表示继续设定：）");
            int choice = scanner.nextSelectionByInt(0, 1);//要求用户只能输入两个数字表示确认还是否定
            if (choice == 1) {
                solver = null;//解除图当前的引用绑定，重新设定
                generateNewMaze();//重新调用本函数设置新图
            }
        }
    }
}

class MazeTest {
    public static void main(String[] args) {
        MazeManageSystem system = new MazeManageSystem();
        system.run();
    }
}