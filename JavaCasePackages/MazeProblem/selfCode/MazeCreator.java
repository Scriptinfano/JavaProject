package MazeProblem.selfCode;

import java.util.ArrayList;
import java.util.Random;

/**
 * 墙类，代表迷宫中的墙，迷宫中的墙分为两种，迷宫的边界上都是不可打破的墙，边界之内是可以打破的墙
 */
record Wall(boolean breakable) {

    /**
     * 判断该墙是否可打破
     *
     * @return boolean 返回true时则可以打破
     */
    @Override
    public boolean breakable() {
        return breakable;
    }
}

/**
 * 坐标点类，封装横坐标和纵坐标，方便传递迷宫中的位置信息
 */
class Point {
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * 横坐标
     */
    public int x;
    /**
     * 纵坐标
     */
    public int y;
}

/**
 * 路径点类，迷宫的基本组成部分，代表迷宫中的路径点
 */
class PathPoint {
    /**
     * 路径点的横坐标
     */
    private final int x;
    /**
     * 路径点的纵坐标
     */
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
     * 当该引用为空引用时，说明该路径点上不存在墙
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
     * @return boolean 为true则代表有墙
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

/**
 * 非法路径点异常，指示哪个路径点发生了错误
 */
class IllegalPathPointException extends RuntimeException {
    /**
     * 仅传递是哪个路径点出错了
     *
     * @param point 出错的路径点
     */
    public IllegalPathPointException(PathPoint point) {
        super("错误点坐标：");
        errorPoint = point;
    }

    /**
     * 传递额外的错误信息
     *
     * @param msg   额外的错误信息
     * @param point 出错的路径点
     */
    public IllegalPathPointException(String msg, PathPoint point) {
        super(msg + "，错误点坐标：");
    }

    /**
     * 出错的错误点
     */
    private PathPoint errorPoint;

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
     * @return {@link Point} 返回的坐标
     */
    public Point getStartPoint() {
        return startPoint;
    }

    /**
     * 将没有墙的路径点加入路径容器，确保该路径点一定在迷宫中
     */
    public void addPointToPath(PathPoint point) {
        pathList.add(point);
    }

    /**
     * 返回迷宫的除了边界部分的长和宽，如果迷宫的高度和宽度原本都是10，则会返回一个坐标为(9,9)的点
     *
     * @return {@link Point} 返回的两个边界值组成的点坐标
     */
    private Point getBoundary() {
        return new Point(maze.length - 1, maze[0].length - 1);
    }

    /**
     * 输入正确的坐标得到非空的路径点引用，只能取得迷宫四周墙壁之内的路径点引用，否则将返回空引用
     * 注意在PathPoint类填满迷宫之后，不得使用其他方式获取内部路径点，仅使用该接口获取路径点
     *
     * @param x x 指定的横坐标
     * @param y y 指定的纵坐标
     * @return {@link PathPoint} 返回的路径点引用，若为null，则说明超出了可取的范围
     */
    public PathPoint getPathPoint(int x, int y) {
        if (x < getBoundary().x && x > 0 && y < getBoundary().y && y > 0)
            return maze[x][y];
        else return null;
    }

    /**
     * 迷宫类的构造器
     *
     * @param mazeSize 正方形迷宫的边长
     */
    public Maze(int mazeSize) {
        //FIXME 设置迷宫有问题
        maze = new PathPoint[mazeSize][mazeSize];//初始化迷宫矩阵
        startPoint = new Point(1, 1);//设定迷宫的起点
        endPoint = new Point(mazeSize - 2, mazeSize - 2);//设定迷宫的终点
        //在迷宫的四周放上不可打破的墙
        for (int i = 0; i < mazeSize; i++) {
            maze[0][i] = new PathPoint(0, i);
            maze[0][i].setWall(false);
            maze[mazeSize - 1][i] = new PathPoint(mazeSize - 1, i);
            maze[mazeSize - 1][i].setWall(false);
        }
        for (int i = 1; i < mazeSize - 1; i++) {
            maze[i][0] = new PathPoint(i, 0);
            maze[i][0].setWall(false);
            maze[i][mazeSize - 1] = new PathPoint(i, mazeSize - 1);
            maze[i][mazeSize - 1].setWall(false);
        }
        //在迷宫的中间放上可以打破的墙
        for (int i = 1; i < mazeSize - 1; i++) {
            for (int j = 1; j < mazeSize - 1; j++) {
                maze[i][j] = new PathPoint(i, j);
                maze[i][j].setWall(true);
                maze[i][j].setPathOrWallPoint(i % 2 != 0 && j % 2 != 0);//在迷宫的路径点上标记路点和墙点
            }
        }
    }

    /**
     * 验证指定的坐标点能否加入候选列表，由于传入函数的{@link PathPoint}路径点均由{@link MazeProblem.selfCode.Maze#getPathPoint(int, int)}接口获得
     * 所以传入函数的PathPoint对象只要不是null，则都在迷宫边界之内，所以无需检查是否在边界之内且
     * 候选点是否已经在候选列表中出现已经由{@link WallBreaker}类对象检查，所以该接口只需要再检查两个条件即可，以下是所有条件，此接口仅检查部分条件<p>
     * 什么样的点可以加入候选点容器:<p>
     * 1、不在迷宫边界上以及之外的点（所有待验证的路径点经过getPathPoint的过滤，一定不会出现坐标在迷宫之外的路径点）<p>
     * 2、不在已经加入路径集合中的点<p>
     * 3、不在候选列表中的点(碎墙器已经检查了此条件)<p>
     * 4、不在原本是墙点上的点
     *
     * @param point 传入的待检测的点
     * @return boolean 返回true时若点不在候选列表中时就代表可以加入候选列表，返回false时则代表一定不能加入候选列表
     */
    public boolean verifyCandidatePoint(PathPoint point) {
        return !inPathList(point) && onRoadPoint(point);
    }

    /**
     * 验证待检测的点是否已经在路径集合中
     *
     * @param point 待检测的路径点
     * @return boolean 返回true时则说明已经在路径集合中了，反之则不在路径集合中
     */
    public boolean inPathList(PathPoint point) {
        return pathList.contains(point);
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

    /**
     * 得到指定路径点四周的四个路点，其中可能有空引用，说明传入点的某个方向超出了迷宫的边界
     * 注意取四周的路径点是取四周的路点，所以要保证传入的路径点也是路点
     *
     * @param point 该接口以该点为中心返回四周的四个路径点引用
     * @return {@link PathPoint[]} 返回的存储周围四个路径点引用的数组，其中有一些可能是空引用，原因是超出了迷宫的边界
     */
    public PathPoint[] getPathPointGround(PathPoint point) {
        PathPoint[] points = new PathPoint[4];
        points[0] = getPathPoint(point.getX() + 2, point.getY());//下侧坐标点
        points[1] = getPathPoint(point.getX() - 2, point.getY());//上侧坐标点
        points[2] = getPathPoint(point.getX(), point.getY() - 2);//左侧坐标点
        points[3] = getPathPoint(point.getX(), point.getY() + 2);//右侧坐标点
        return points;
    }

    /**
     * 在屏幕上输出迷宫的形状
     */
    public void showMaze() {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if (maze[i][j].hasWall())
                    System.out.print(" * ");
                else System.out.print("   ");
            }
            System.out.println();
        }
    }
}

/**
 * 碎墙器类，负责生成迷宫时在迷宫中凿墙，生成迷宫路径
 */
class WallBreaker {
    /**
     * 与碎墙器暂时关联的路径点引用，当碎墙器执行碎墙操作时，碎的就是这个引用代表的墙
     */
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
     * @return {@link PathPoint} 在候选列表中返回的随机的候选路径点引用
     */
    private PathPoint getRandomPoint() {
        randomGenerator.setSeed(System.currentTimeMillis());
        int index = randomGenerator.nextInt(0, candidateList.size());
        return candidateList.get(index);
    }

    private void updateCandidateList() {
        //根据碎墙器当前所处位置以及周围墙的状态更新候选的路径列表
        PathPoint[] points = maze.getPathPointGround(breakingPoint);
        for (PathPoint point : points) {
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
    private void move(PathPoint position) {
        if (position != null) {
            breakingPoint = position;
        } else throw new IllegalArgumentException("碎墙器在移动时取得了错误的坐标");
    }

    /**
     * 运行普利姆算法生成迷宫
     */
    public void runPrim() {
        breakWall();//开始时碎墙器在起点位置，先打通起点的墙
        maze.addPointToPath(maze.getPathPoint(getXPosition(), getYPosition()));//将起点加入路径集合
        updateCandidateList();//第一次更新候选列表，此时碎墙器就在迷宫的起点，不需要移动

        while (!candidateList.isEmpty()) {
            PathPoint randomPoint = getRandomPoint();//从候选列表中得到随机的一个路径点
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
    private void breakThrough(PathPoint point) {

        candidateList.remove(point);//已经选中了该候选点，该候选点即将加入路径集合，所以从候选点集合中删除它，确保其不再出现
        breakWall();//打碎候选点所在的墙
        maze.addPointToPath(point);//将候选点所在的路径点加入迷宫的通路中
        ArrayList<PathPoint> pointList = new ArrayList<>();//候选点四周可能有多个在路径集合中的点，可以打通选中的候选点可以任意的其中之一的点之间的墙壁
        for (PathPoint thePoint : maze.getPathPointGround(point)) {
            if (thePoint != null && maze.inPathList(thePoint))
                pointList.add(thePoint);
        }
        randomGenerator.setSeed(System.currentTimeMillis());
        int randomIndex = randomGenerator.nextInt(0, pointList.size());
        PathPoint adjacentPoint = pointList.get(randomIndex);//从候选点四周多个在路径集合中的点随机选一个
        PathPoint wallPoint;//候选坐标点和最近路径点之间的墙
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
        maze.addPointToPath(wallPoint);//将墙所在的路径点加入路径点集合
    }

    /**
     * 移动到指定的坐标并执行碎墙工作
     *
     * @param point 指定的坐标点
     */
    private void moveAndBreak(PathPoint point) {
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
 * 迷宫生成器类，负责创造符号要求的迷宫类{@link Maze}，此类采用普利姆算法生成迷宫
 *
 * @author localuser
 */
public class MazeCreator {

    /**
     * 要求生成的正方形迷宫的边长不能小于这个值
     */
    private static final int MinimumSize = 11;

    /**
     * 获得新迷宫，在内部创建好Maze类，然后返回符合要求的Maze对象。
     * 迷宫生成器要求生成的迷宫至少是10*10的，如果要求生成的迷宫小于这个值则不予生成，直接返回空引用
     *
     * @return {@link Maze}
     */
    public Maze getNewMaze(int mazeSize) {
        if (mazeSize < MinimumSize || mazeSize % 2 == 0)
            return null;//要求制造的迷宫不符合要求
        Maze maze = new Maze(mazeSize);
        WallBreaker breaker = new WallBreaker(maze.getStartPoint(), maze);
        //利用本类的枚举类实现迷宫的多算法生成
        breaker.runPrim();
        return maze;
    }
}
