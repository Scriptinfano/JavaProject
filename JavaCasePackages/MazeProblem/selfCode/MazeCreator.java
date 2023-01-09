package MazeProblem.selfCode;

import java.util.ArrayList;
import java.util.Random;


/**
 * 迷宫尚未创建异常，迷宫在未创建之前不能执行任何操作
 */
class MazeNotFoundException extends Exception {
    public MazeNotFoundException() {
        super("迷宫未创建，请先生成迷宫之后再进行其他操作");
    }
}

/**
 * 要求创建的迷宫的大小不符合要求时，抛出此异常
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
 * 非法路径点异常，指示哪个路径点发生了错误
 */
class IllegalPathPointException extends RuntimeException {
    /**
     * 出错的错误点
     */
    private Maze.PathPoint errorPoint;

    /**
     * 仅传递是哪个路径点出错了
     *
     * @param point 出错的路径点
     */
    public IllegalPathPointException(Maze.PathPoint point) {
        super("错误点坐标：");
        errorPoint = point;
    }

    /**
     * 传递额外的错误信息
     *
     * @param msg   额外的错误信息
     * @param point 出错的路径点
     */
    public IllegalPathPointException(String msg, Maze.PathPoint point) {
        super(msg + "，错误点坐标：");
    }

    /**
     * 仅传递错误信息
     *
     * @param s 错误信息
     */
    public IllegalPathPointException(String s) {
        super(s);
    }

    @Override
    public String getMessage() {
        return super.getMessage() + "(" + errorPoint.getX() + "," + errorPoint.getY() + ")";
    }
}

/**
 * 碎墙器类，负责生成迷宫时在迷宫中凿墙，生成迷宫路径
 */
class WallBreaker {
    /**
     * 候选路径坐标列表
     */
    private final ArrayList<Maze.PathPoint> candidateList = new ArrayList<>();

    /**
     * 迷宫类
     */
    private final Maze maze;

    /**
     * 随机数生成器
     */
    private final Random randomGenerator = new Random(System.currentTimeMillis());

    /**
     * 与碎墙器暂时关联的路径点引用，当碎墙器执行碎墙操作时，碎的就是这个引用代表的墙
     */
    private Maze.PathPoint breakingPoint;

    /**
     * 初始化碎墙器的坐标，指示碎墙器从哪里开始运作，并指定碎墙器要接管哪个迷宫，指定迷宫类引用
     *
     * @param maze       要接管的迷宫类引用
     * @param startPoint 迷宫的起点坐标
     */
    public WallBreaker(Maze.PathPoint startPoint, Maze maze) {
        breakingPoint = startPoint;
        this.maze = maze;
    }

    /**
     * 从候选的可走路径点的列表中得到随机的路径点坐标
     *
     * @return {@link Maze.PathPoint} 在候选列表中返回的随机的候选路径点引用
     */
    private Maze.PathPoint getRandomPoint() {
        randomGenerator.setSeed(System.currentTimeMillis());
        int index = randomGenerator.nextInt(0, candidateList.size());
        return candidateList.get(index);
    }

    private void updateCandidateList() {
        //根据碎墙器当前所处位置以及周围墙的状态更新候选的路径列表
        Maze.PathPoint[] points = maze.getPathPointGround(breakingPoint);
        for (Maze.PathPoint point : points) {
            //point有可能是空引用
            if (point != null && !candidateList.contains(point) && maze.verifyCandidatePoint(point)) {
                candidateList.add(point);
            }
        }
    }

    /**
     * 移动碎墙器，根据传入的坐标将其移动到指定位置，指定的位置坐标只允许在迷宫中除了四周墙壁的内部
     *
     * @param position 指定的位置坐标路径点
     * @throws IllegalArgumentException 抛出不受查异常，指示碎墙器在移动时取得了未知的坐标
     */
    private void move(Maze.PathPoint position) {
        if (position != null) {
            breakingPoint = position;
        } else throw new IllegalArgumentException("碎墙器在移动时取得了错误的坐标");
    }

    /**
     * 运行普利姆算法生成迷宫
     */
    public void run() {
        breakWall();//开始时碎墙器在起点位置，先打通起点的墙
        maze.addPointToPathList(maze.getPathPoint(getXPosition(), getYPosition()));//将起点加入路径集合
        updateCandidateList();//第一次更新候选列表，此时碎墙器就在迷宫的起点，不需要移动
        while (!candidateList.isEmpty()) {
            Maze.PathPoint randomPoint = getRandomPoint();//从候选列表中得到随机的一个路径点
            move(randomPoint);//将碎墙器移动到选中的候选点上
            updateCandidateList();//根据新选中的候选点更新候选列表
            breakThrough(randomPoint);//将该点和最近的已打通的路径之间的墙壁打通，并将打通的墙所在的路径点以及已经选中的候选点加入路径集合，从候选列表中删除刚选中的候选点
        }
    }

    /**
     * 碎墙器执行碎墙操作，打碎碎墙器目前所在位置上的墙
     */
    private void breakWall() {
        if (breakingPoint != null && breakingPoint.hasWall() && breakingPoint.getWall().breakable()) {
            breakingPoint.breakWall();
        } else if (breakingPoint == null)
            throw new IllegalPathPointException("碎墙器接管了一个空引用，无法执行碎墙工作");
        else if (!breakingPoint.hasWall())
            throw new IllegalPathPointException("碎墙器正在不存在墙的路径点上打碎墙", breakingPoint);
        else if (!breakingPoint.getWall().breakable())
            throw new IllegalPathPointException("碎墙器正在打碎不可打碎的墙", breakingPoint);
    }

    /**
     * 将传入的该路径点与最邻近的通路之间的路径打通，并将打通的墙以及当前点加入路径集合
     * 请确保碎墙器已经处于传入的路径点引用所代表的路径点上
     *
     * @param point 该路径点即将和离其最近的通路之间联通
     */
    private void breakThrough(Maze.PathPoint point) {

        candidateList.remove(point);//已经选中了该候选点，该候选点即将加入路径集合，所以从候选点集合中删除它，确保其不再出现
        breakWall();//打碎候选点所在的墙
        maze.addPointToPathList(point);//将候选点所在的路径点加入迷宫的通路中
        ArrayList<Maze.PathPoint> pointList = new ArrayList<>();//候选点四周可能有多个在路径集合中的点，可以打通选中的候选点可以任意的其中之一的点之间的墙壁
        for (Maze.PathPoint thePoint : maze.getPathPointGround(point)) {
            if (thePoint != null && maze.inPathList(thePoint))
                pointList.add(thePoint);
        }
        randomGenerator.setSeed(System.currentTimeMillis());
        int randomIndex = randomGenerator.nextInt(0, pointList.size());
        Maze.PathPoint adjacentPoint = pointList.get(randomIndex);//从候选点四周多个在路径集合中的点随机选一个
        Maze.PathPoint wallPoint;//候选坐标点和最近路径点之间的墙
        if (point.getX() == adjacentPoint.getX()) {
            if (point.getY() > adjacentPoint.getY())
                wallPoint = maze.getPathPoint(point.getX(), point.getY() - 1);
            else wallPoint = maze.getPathPoint(point.getX(), point.getY() + 1);
        } else if (point.getY() == adjacentPoint.getY()) {
            if (point.getX() > adjacentPoint.getX())
                wallPoint = maze.getPathPoint(point.getX() - 1, point.getY());
            else wallPoint = maze.getPathPoint(point.getX() + 1, point.getY());
        } else throw new RuntimeException("在打通候选点和路径点之间的墙时发生了错误");
        moveAndBreak(wallPoint);//移动到墙上并碎墙
        maze.addPointToPathList(wallPoint);//将墙所在的路径点加入路径点集合
    }

    /**
     * 移动到指定的坐标并执行碎墙工作
     *
     * @param point 指定的坐标点
     */
    private void moveAndBreak(Maze.PathPoint point) {
        move(point);
        breakWall();
    }

    /**
     * 取得当前碎墙器横坐标
     *
     * @return int 当前碎墙器横坐标
     */
    private int getXPosition() {
        return breakingPoint.getX();
    }

    /**
     * 取得当前碎墙器纵坐标
     *
     * @return int 当前碎墙器纵坐标
     */
    private int getYPosition() {
        return breakingPoint.getY();
    }


}


/**
 * 迷宫生成器类，负责创造符合要求的迷宫类对象{@link Maze}，此类采用普利姆算法生成迷宫
 */
public class MazeCreator {

    /**
     * 要求生成的正方形迷宫的边长不能小于这个值
     */
    private static final int MinimumSize = 11;

    /**
     * 生成新迷宫，然后返回符合要求的Maze对象。
     * 迷宫生成器要求生成的正方形迷宫的边长大小至少为11且必须是奇数，如果传入的参数不符合这个要求，则会抛出异常。
     *
     * @param mazeSize 正方形迷宫的边长
     * @return {@link Maze} 返回的迷宫类引用，此时迷宫中的通路已经生成
     * @throws MazeCreateErrorException 迷宫创建错误异常
     */
    public Maze getNewMaze(int mazeSize) throws MazeCreateErrorException {
        if (mazeSize < MinimumSize || mazeSize % 2 == 0)
            throw new MazeCreateErrorException("要求创建的迷宫的大小不合要求，正方形迷宫的边长必须大于或等于11且必须为奇数");
        Maze maze = new Maze(mazeSize);
        WallBreaker breaker = new WallBreaker(maze.getStartPoint(), maze);
        //利用本类的枚举类实现迷宫的多算法生成
        breaker.run();
        return maze;
    }
}
