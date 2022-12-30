package MazeProblem.selfCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

class WallBreaker {
    /**
     * 碎墙器在迷宫中的横坐标
     */
    private int x;
    /**
     * 碎墙器在迷宫中的纵坐标
     */
    private int y;
    /**
     * 迷宫类
     */
    private final Maze maze;

    /**
     * 随机数生成器
     */
    private final Random randomGenerator = new Random(System.currentTimeMillis());

    /**
     * 候选路径坐标列表
     */
    private final ArrayList<Point> candidateList = new ArrayList<>();

    /**
     * 初始化碎墙器的坐标，指示碎墙器从哪里开始运作，并指定碎墙器要接管哪个迷宫，指定迷宫类引用
     *
     * @param maze       要接管的迷宫类引用
     * @param startPoint 起点坐标
     */
    public WallBreaker(Point startPoint, Maze maze) {
        this.x = startPoint.x;
        this.y = startPoint.y;
        this.maze = maze;
    }

    /**
     * 从候选的可走路径点的列表中得到随机的路径点坐标
     *
     * @return {@link Point}
     */
    private Point getRandomPoint() {
        int index = randomGenerator.nextInt(0, candidateList.size());
        return candidateList.get(index);
    }

    private void updateCandidateList() {
        //根据碎墙器当前所处位置以及周围墙的状态更新候选的路径列表
        Point[] points = new Point[4];
        points[0] = new Point(x - 2, y);
        points[1] = new Point(x + 2, y);
        points[2] = new Point(x, y - 2);
        points[3] = new Point(x, y + 2);
        Point[] correctPoints = maze.verifyPoint(points);
        candidateList.addAll(Arrays.asList(correctPoints));

    }

    /**
     * 移动碎墙器，根据传入的坐标将其移动到指定位置，
     *
     * @param position 指定的位置坐标
     */
    private void move(Point position) {


    }

    /**
     * 运行普利姆算法生成迷宫
     */
    public void runPrim() {
        updateCandidateList();//根据当前碎墙器所在的位置更新附近的候选点
        Point randomPoint = getRandomPoint();//从候选列表中得到随机的一个点
        breakThrough(randomPoint);//将该点和最近的已打通的路径之间的墙壁打通，并将该墙壁所在位置设为已加入的路径且
    }

    private void breakWall() {
        //判断碎墙器当前所在位置是否有墙且墙是否可以打碎，若能打碎则打碎

    }

    private void breakThrough(Point point) {

    }


}

/**
 * 迷宫生成器类，负责创造符号要求的迷宫类{@link Maze}，此类采用普利姆算法生成迷宫
 *
 * @author localuser
 */
public class MazeCreator {


    /**
     * 具体的打通迷宫的操作由此碎墙器完成
     */
    private WallBreaker breaker;

    /**
     * 要求生成的迷宫的长度最小不能小于这个值
     */
    private static final int minimumLength = 10;

    /**
     * 要求生成的迷宫的宽度最小不能小于这个值
     */
    private static final int minimumWidth = 10;

    /**
     * 获得新迷宫，在内部创建好Maze类，然后返回符合要求的Maze对象。
     * 迷宫生成器要求生成的迷宫至少是10*10的，如果要求生成的迷宫小于这个值则不予生成，直接返回空引用
     *
     * @return {@link Maze}
     */
    public Maze getNewMaze(int length, int width) {
        if (length < minimumLength || width < minimumWidth)
            return null;//要求制造的迷宫不符合要求
        Maze maze = new Maze(length, width);
        breaker = new WallBreaker(maze.getStartPoint(), maze);
        //利用本类的枚举类实现迷宫的多算法生成
        setRandomPath();//碎墙器会对maze进行处理打通其中的通路
        return maze;
    }

    /**
     * 使用普利姆算法为全是墙的迷宫生成路径，打通迷宫里的墙形成可以走的通路
     */
    private void setRandomPath() {
        breaker.runPrim();

    }
}
