package MazeProblem.selfCode;

import viewManagerPack.ViewManager;


/**
 * 迷宫管理类，内部封装迷宫生成器和迷宫以及球，负责三者之间的数据交换
 */
class MazeManager {
    /**
     * 迷宫
     */
    private Maze maze;

    /**
     * 负责调用迷宫生成算法，返回迷宫给maze引用
     */
    private final MazeCreator creator = new MazeCreator();

    /**
     * 生成新迷宫
     *
     * @param mazeSize 正方形迷宫的边长
     * @throws MazeCreateErrorException 迷宫创建错误异常
     */
    public void generateNewMaze(int mazeSize) throws MazeCreateErrorException {
        maze = creator.getNewMaze(mazeSize);
    }

    /**
     * 求解迷宫，调用迷宫maze的求解迷宫起点到终点的最短路径的接口
     *
     * @throws MazeNotFoundException       迷宫尚未创建异常
     * @throws Maze.MazeHasSolvedException 迷宫无法重复求解异常
     */
    public void solveMaze() throws MazeNotFoundException, Maze.MazeHasSolvedException {
        if (hasMaze()) {

            maze.solveMaze();
        } else throw new MazeNotFoundException();
    }

    /**
     * 在屏幕上输出迷宫的形状，墙点用*标识，迷宫中起点到终点的唯一一条路径用+号标识，通路不用任何符号标识（用空格标识）
     *
     * @throws MazeNotFoundException 迷宫尚未创建异常
     */
    public void showMaze() throws MazeNotFoundException {
        if (hasMaze()) {
            maze.showMaze();
        } else throw new MazeNotFoundException();
    }

    /**
     * 判断内部的迷宫是否已经生成
     *
     * @return boolean
     */
    public boolean hasMaze() {
        return maze != null;
    }

    /**
     * 清除当前迷宫以便生成新迷宫
     */
    public void clearMaze() {
        maze = null;
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
    private final MazeManager mazeManager = new MazeManager();

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
        try {
            mazeManager.solveMaze();
        } catch (MazeNotFoundException | Maze.MazeHasSolvedException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * 显示迷宫
     */
    private void showMaze() {
        try {
            mazeManager.showMaze();
        } catch (MazeNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * 生成新迷宫
     */
    private void generateNewMaze() {
        if (mazeManager.hasMaze()) {
            printer.print("迷宫已生成，是否重新生成（输入0表示不再设定，输入1表示继续设定：）");
            int choice = scanner.nextSelectionByInt(0, 1);//要求用户只能输入两个数字表示确认还是否定
            if (choice == 1) {
                mazeManager.clearMaze();
                generateNewMaze();
            }
        } else {
            //执行生成新迷宫的接口
            while (true) {
                System.out.print("请输入正方形迷宫的边长(大小至少为11且必须是奇数)：");
                int mazeSize = scanner.nextInt();
                try {
                    mazeManager.generateNewMaze(mazeSize);
                } catch (MazeCreateErrorException e) {
                    System.err.println(e.getMessage());
                    System.out.println("请重新输入迷宫的长度和宽度");
                    continue;
                }
                break;
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