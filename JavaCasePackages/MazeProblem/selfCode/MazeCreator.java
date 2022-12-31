package MazeProblem.selfCode;

import java.util.ArrayList;
import java.util.Random;

/**
 * 墙类
 *
 * @param breakable 标识该墙是否是可以打破的
 */
record Wall(boolean breakable) {

    /**
     * 判断该墙是否可打破
     *
     * @return boolean
     */
    @Override
    public boolean breakable() {
        return breakable;
    }
}

class Point {
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int x;
    public int y;
}

/**
 * 路径点类，迷宫的组成部分
 *
 * @author localuser
 */
class PathPoint {
    private final int x;
    private final int y;

    /**
     * 标识该点是路点还是墙点，什么是路点和墙点：
     * 如果按照普利姆算法生成迷宫，我们将除了迷宫四周不可打破的墙壁之外的中间空白区域用
     * 网格划分，这些网格组成墙点，剩下的留白处是路点，注意在一开始所有的路点和墙点都有墙
     * 详情请参考视频：【scracth实现-prim算法生成迷宫重制优化版】
     * <a href="https://www.bilibili.com/video/BV1xp4y1r7Ng/?p=3&share_source=copy_web&vd_source=ecaf67eab86977249adc291d11e7fa19">视频链接</a>
     */
    private Boolean pathOrWallPoint;

    /**
     * 当该引用为空引用时，说明该路径点上不存在墙，当该路径点上有墙时，可以判断墙是否是可以打通的
     */
    private Wall wall;


    /**
     * PathPoint构造器，初始化路径点的坐标
     *
     * @param x x 初始化之后的横坐标
     * @param y y 初始化之后的纵坐标
     */
    public PathPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * 得到当前路径点的横坐标
     *
     * @return int 当前路径点的横坐标
     */
    public int getX() {
        return x;
    }

    /**
     * 得到当前路径点的纵坐标
     *
     * @return int 当前路径点的纵坐标
     */
    public int getY() {
        return y;
    }


    /**
     * 在该路径点上放置墙，当breakable为true时则表示放置可以打破的墙，为false时表示放置不可打破的墙
     *
     * @param breakable 为true时则表示放置可以打破的墙，为false时表示放置不可打破的墙
     */
    public void setWall(boolean breakable) {
        if (wall == null)
            wall = new Wall(breakable);
    }

    /**
     * 设定该路径点是路点还是墙点
     *
     * @param mark 如果为true则是路点，为false则是墙点
     */
    public void setPathOrWallPoint(boolean mark) {
        pathOrWallPoint = mark;
    }

    /**
     * 判断该路径点是路点还是墙点，如果为true则是路点，为false则是墙点
     *
     * @return {@link Boolean} 如果为true则是路点，为false则是墙点
     */
    public Boolean isPathOrWallPoint() {
        return pathOrWallPoint;
    }

    /**
     * 判断该路径点上是否有墙
     *
     * @return boolean
     */
    public boolean hasWall() {
        return wall != null;
    }

    /**
     * 取得对该路径点上墙的控制权
     *
     * @return {@link Wall}
     */
    public Wall getWall() {
        return wall;
    }

    /**
     * 强制打破墙，在使用前请检查墙是否允许打破
     */
    public void breakWall() {
        wall = null;
    }
}

class IllegalPathPointException extends RuntimeException {
    public IllegalPathPointException(PathPoint point) {
        super("错误点坐标：");
        errorPoint = point;
    }

    public IllegalPathPointException(String msg, PathPoint point) {
        super(msg + "，错误点坐标：");
    }

    private PathPoint errorPoint;

    @Override
    public String getMessage() {
        return super.getMessage() + "(" + errorPoint.getX() + "," + errorPoint.getY() + ")";
    }
}

/**
 * 迷宫类
 *
 * @author localuser
 */
class Maze {
    /**
     * 代表迷宫的二维矩阵，假设该矩阵的大小是length*width，且该矩阵的四周都是不可打破的墙壁，
     * 迷宫的起点一般是(1,1)，终点一般是(length-2,width-2)也就是矩阵除去四周中间留白区域的右下角
     */
    private PathPoint[][] maze;

    /**
     * 在迷宫中走的球
     */
    private Ball ball;

    /**
     * 起点
     */
    private final Point startPoint;

    /**
     * 终点
     */
    private final Point endPoint;

    /**
     * 已经打通的路径集合，所有在该容器中的点都是已经被打通的通路
     */
    private final ArrayList<PathPoint> pathList = new ArrayList<>();

    /**
     * 得到该迷宫的起点坐标
     *
     * @return {@link Point}
     */
    public Point getStartPoint() {
        return startPoint;
    }

    /**
     * 将没有墙的路径点加入路径容器
     */
    public void addPointToPath(PathPoint point) {
        if (!point.hasWall() && insideBoundary(point))
            pathList.add(point);
        else throw new IllegalPathPointException(point);
    }

    /**
     * 返回迷宫的除了边界部分长和宽，如果迷宫的高度和宽度原本都是10，则会返回一个坐标为(9,9)的点
     *
     * @return {@link Point} 返回的两个边界值组成的点坐标
     */
    private Point getBoundary() {
        return new Point(maze.length - 1, maze[0].length - 1);
    }

    /**
     * 输入正确的坐标得到非空的路径点引用，如果坐标不正确将会得到一个空引用
     *
     * @param x x 指定的横坐标
     * @param y y 指定的纵坐标
     * @return {@link PathPoint}
     */
    public PathPoint getPathPoint(int x, int y) {
        try {
            return maze[x][y];
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    /**
     * 迷宫类的构造器
     *
     * @param length 迷宫的长度
     * @param width  迷宫的宽度
     */
    public Maze(int length, int width) {
        maze = new PathPoint[length][width];//初始化迷宫矩阵
        startPoint = new Point(1, 1);//设定迷宫的起点
        endPoint = new Point(length - 2, width - 2);//设定迷宫的终点
        //在迷宫的四周放上不可打破的墙
        for (int i = 0; i < width; i++) {
            maze[0][i] = new PathPoint(0, i);
            maze[0][i].setWall(false);
            maze[length - 1][i] = new PathPoint(length - 1, i);
            maze[length - 1][i].setWall(false);
        }
        for (int i = 1; i < length - 1; i++) {
            maze[i][0] = new PathPoint(i, 0);
            maze[i][0].setWall(false);
            maze[i][width - 1] = new PathPoint(i, width - 1);
            maze[i][width - 1].setWall(false);
        }
        //在迷宫的中间放上可以打破的墙
        for (int i = 1; i < length - 1; i++) {
            for (int j = 1; j < width - 1; j++) {
                maze[i][j] = new PathPoint(i, j);
                maze[i][j].setWall(true);
                maze[i][j].setPathOrWallPoint(i % 2 != 0 && j % 2 != 0);
            }
        }
        ball = new Ball(startPoint.x, startPoint.y);//将球放到迷宫的起点
    }

    /**
     * 验证指定的坐标点能否加入候选列表，由于迷宫边界信息以及路径集合还有路径点是否是墙点的
     * 信息由迷宫类本身管理，所以在碎墙器检查了是否在候选列表中的点的条件之后，该接口只需要再检查三个条件即可<p>
     * 什么样的点可以加入候选点容器:<p>
     * 1、不在迷宫边界上以及之外的点<p>
     * 2、不在已经加入路径集合中的点<p>
     * 3、不在候选列表中的点(碎墙器已经检查了此条件)<p>
     * 4、不在原本是墙点上的点
     *
     * @param point 传入的待检测的点
     * @return boolean 返回true时若点不在候选列表中时就代表可以加入候选列表，返回false时则代表一定不能加入候选列表
     */
    public boolean verifyPoint(PathPoint point) {
        return insideBoundary(point) && !pathList.contains(point) && onRoadPoint(point);
    }

    /**
     * 验证传入的坐标点是否超出迷宫边界
     *
     * @param point 待检测的坐标
     * @return boolean 如果返回true则说明坐标在边界之内
     */
    public boolean insideBoundary(PathPoint point) {
        return point.getX() < getBoundary().x && point.getX() > 0 && point.getY() < getBoundary().y && point.getY() > 0;
    }

    /**
     * 验证传入的坐标是否在迷宫中的路点上
     *
     * @param point 待检测的坐标
     * @return boolean 如果为true则说明迷宫中该坐标上的路径点是路点
     */
    public boolean onRoadPoint(PathPoint point) {
        Boolean result = point.isPathOrWallPoint();
        if (result != null)
            return result;
        else return false;
    }
}

/**
 * 碎墙器类，负责生成迷宫时在迷宫中凿墙，生成迷宫路径
 */
class WallBreaker {
    private PathPoint breakingPoint;

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
    private final ArrayList<PathPoint> candidateList = new ArrayList<>();

    /**
     * 初始化碎墙器的坐标，指示碎墙器从哪里开始运作，并指定碎墙器要接管哪个迷宫，指定迷宫类引用
     *
     * @param maze       要接管的迷宫类引用
     * @param startPoint 迷宫的起点坐标
     */
    public WallBreaker(Point startPoint, Maze maze) {
        breakingPoint = maze.getPathPoint(startPoint.x, startPoint.y);
        this.maze = maze;
    }

    /**
     * 从候选的可走路径点的列表中得到随机的路径点坐标
     *
     * @return {@link Point}
     */
    private PathPoint getRandomPoint() {
        int index = randomGenerator.nextInt(0, candidateList.size());
        return candidateList.get(index);
    }

    private void updateCandidateList() {
        //根据碎墙器当前所处位置以及周围墙的状态更新候选的路径列表
        PathPoint[] points = new PathPoint[4];
        points[0] = maze.getPathPoint(getXPosition() + 2, getYPosition());//上侧坐标点
        points[1] = maze.getPathPoint(getXPosition() - 2, getYPosition());//下侧坐标点
        points[2] = maze.getPathPoint(getXPosition(), getYPosition() - 2);//左侧坐标点
        points[3] = maze.getPathPoint(getXPosition(), getYPosition() + 2);//右侧坐标点
        for (PathPoint point : points) {
            if (!candidateList.contains(point) && maze.verifyPoint(point)) {
                candidateList.add(point);
            }

        }
    }

    /**
     * 移动碎墙器，根据传入的坐标将其移动到指定位置，指定的位置坐标只允许在迷宫除了四周墙壁的内部
     *
     * @param position 指定的位置坐标
     */
    private void move(PathPoint position) {
        if (maze.insideBoundary(position)) {
            breakingPoint = position;
        } else throw new IllegalPathPointException("碎墙器移动到了边界之外", position);
    }

    /**
     * 运行普利姆算法生成迷宫
     */
    public void runPrim() {
        //TODO 考虑如何利用普利姆算法，利用现有接口逐步打通迷宫中的墙


        breakWall();//开始时碎墙器在起点位置，先打通起点的墙
        maze.addPointToPath(maze.getPathPoint(getXPosition(),getYPosition()));//将起点加入路径集合
        updateCandidateList();//第一次更新候选列表，此时碎墙器就在迷宫的起点，不需要移动

        while(!candidateList.isEmpty()){
            PathPoint randomPoint = getRandomPoint();//从候选列表中得到随机的一个路径点
            move(randomPoint);//将碎墙器移动到选中的候选点上
            updateCandidateList();//根据新选中的候选点更新候选列表
            breakThrough(randomPoint);//将该点和最近的已打通的路径之间的墙壁打通，并将打通的墙所在的路径点以及已经选中的候选点加入路径集合，从候选列表中删除刚选中的候选点
        }

    }

    /**
     * 打破该路径点上的允许被打破的墙
     */
    private void breakWall() {
        PathPoint point = maze.getPathPoint(getXPosition(), getYPosition());//根据碎墙器目前所在位置，取得当前所在位置的路径点引用
        if (point.hasWall() && point.getWall().breakable()) {
            point.breakWall();
        } else if (!point.hasWall())
            throw new IllegalPathPointException("碎墙器正在不存在墙的路径点上打碎墙", point);
        else if (!point.getWall().breakable())
            throw new IllegalPathPointException("碎墙器正在打碎不可打碎的墙", point);
    }

    /**
     * 将传入的该点与最邻近的通路之间的路径打通，并将打通的墙以及当前点加入路径集合
     *
     * @param point 点
     */
    private void breakThrough(PathPoint point) {
        //TODO 考虑如何做到将该点与最近的通路之间的墙打通
        candidateList.remove(point);//已经选中了该候选点，该候选点即将加入路径，所以从候选点集合中删除它，确保其不再出现
        breakWall();//打碎候选点所在的墙
        maze.addPointToPath(point);//将候选点所在的路径点加入迷宫的通路中

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
        runBreaker();//操作碎墙器，对maze进行处理打通其中的通路
        return maze;
    }

    /**
     * 运行碎墙器，碎墙器会调用算法对迷宫进行处理，打通其中的通路生成迷宫地图
     */
    private void runBreaker() {
        breaker.runPrim();

    }
}
