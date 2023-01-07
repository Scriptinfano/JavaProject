package MazeProblem.selfCode;

import viewManagerPack.ViewManager;

/**
 * 在迷宫中走的球，球会记录下自己走过的正确的通路
 *
 * @author localuser
 * @date 2023/01/01
 */
class Ball {
    /**
     * 球在迷宫中的横坐标
     */
    private int x;
    /**
     * 球在迷宫中的纵坐标
     */
    private int y;

    /**
     * 球
     *
     * @param x 初始化时球的横坐标
     * @param y 初始化时球的纵坐标
     */
    public Ball(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * 取得球的横坐标
     *
     * @return int
     */
    public int getX() {
        return x;
    }

    /**
     * 设置球的横坐标
     *
     * @param x x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * 取得球的纵坐标
     *
     * @return int
     */
    public int getY() {
        return y;
    }

    /**
     * 设置球的纵坐标
     *
     * @param y y
     */
    public void setY(int y) {
        this.y = y;
    }
}

/**
 * 要求创建的迷宫不符合要求时，抛出此异常
 */
class MazeCreateErrorException extends Exception {
    /**
     * 迷宫创建错误异常类构造器
     *
     * @param message 错误消息
     */
    public MazeCreateErrorException(String message) {
        super(message);
    }
}

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

    private Ball ball;


    /**
     * 负责调用迷宫生成算法，返回迷宫给maze引用
     */
    private final MazeCreator creator = new MazeCreator();

    public void generateNewMaze(int mazeSize) throws MazeCreateErrorException {
        maze = creator.getNewMaze(mazeSize);
        if (maze == null)
            throw new MazeCreateErrorException("要求创建的迷宫的大小不合要求，正方形迷宫的边长必须大于或等于11且必须为奇数");
        ball = new Ball(maze.getStartPoint().x, maze.getStartPoint().y);
    }

    /**
     * 球在迷宫中找路径的过程
     */
    public void solveMaze() throws MazeCreateErrorException {
        if (hasMaze()) {
            ball = new Ball(maze.getStartPoint().x, maze.getStartPoint().y);//将球放在迷宫的入口处

        } else throw new MazeCreateErrorException("迷宫未创建，请先生成迷宫");
    }

    public void showMaze() throws MazeCreateErrorException {
        if (hasMaze()) {
            showMaze();
        } else throw new MazeCreateErrorException("迷宫未创建，请先生成迷宫");
    }

    /**
     * 判断内部的迷宫是否已经生成
     *
     * @return boolean
     */
    public boolean hasMaze() {
        return maze != null;
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
    private MazeSolver solver = new MazeSolver();

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
            solver.solveMaze();
        } catch (MazeCreateErrorException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * 显示迷宫
     */
    private void showMaze() {
        try {
            solver.showMaze();
        } catch (MazeCreateErrorException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * 生成新迷宫
     */
    private void generateNewMaze() {
        if (solver.hasMaze()) {
            printer.print("迷宫已生成，是否重新生成（输入0表示不再设定，输入1表示继续设定：）");
            int choice = scanner.nextSelectionByInt(0, 1);//要求用户只能输入两个数字表示确认还是否定
            if (choice == 1) {
                solver = null;//解除图当前的引用绑定，重新设定
                generateNewMaze();//重新调用本函数设置新图
            }
        } else {
            //执行生成新迷宫的接口
            while (true) {
                System.out.print("请输入正方形迷宫的边长(大小至少为11且必须是奇数)：");
                int mazeSize = scanner.nextInt();
                try {
                    solver.generateNewMaze(mazeSize);
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