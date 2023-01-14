package MazeProblem.selfCode;

import org.jetbrains.annotations.NotNull;

import java.util.*;

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
    /**
     * 横坐标
     */
    public final int x;
    /**
     * 纵坐标
     */
    public final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}


/**
 * 迷宫类
 */
public class Maze {
    /**
     * 起点
     */
    private final PathPoint startPoint;
    /**
     * 终点
     */
    private final PathPoint endPoint;
    /**
     * 已经打通的路径集合，所有在该容器中的点都是已经被打通的通路
     */
    private final ArrayList<PathPoint> pathList = new ArrayList<>();
    /**
     * 代表迷宫的二维矩阵，假设该矩阵的大小是length*width，且该矩阵的四周都是不可打破的墙壁，
     * 迷宫的起点一般是(1,1)，终点一般是(length-2,width-2)也就是矩阵除去四周中间留白区域的右下角
     */
    private final PathPoint[][] maze;

    /**
     * 标识该迷宫是否已被解决
     */
    private boolean hasSolved = false;

    /**
     * 得到该迷宫的起点坐标
     *
     * @return {@link PathPoint} 返回的坐标
     */
    public PathPoint getStartPoint() {
        return startPoint;
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
     * 将没有墙的路径点加入路径容器并将该路经点的通路标识设为false，确保该路径点一定在迷宫中
     */
    public void addPointToPathList(PathPoint point) {
        pathList.add(point);
        point.setPathWay(false);
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
        return !pathList.contains(point) && point.isPathOrWallPoint();
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
     * 迷宫类的构造器
     *
     * @param mazeSize 正方形迷宫的边长
     */
    public Maze(int mazeSize) {

        maze = new PathPoint[mazeSize][mazeSize];//初始化迷宫矩阵
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
        //TODO 在实现寻路算法之后考虑让用户指定起点和终点
        startPoint = maze[1][1];
        endPoint = maze[mazeSize - 2][mazeSize - 2];
    }

    /**
     * 得到指定路径点四周的四个路点，其中可能有空引用，说明传入点的某个方向超出了迷宫的边界
     * 注意要保证传入的路径点也是路点。
     *
     * @param point 该接口以该点为中心返回四周的四个路点引用
     * @return {@link PathPoint[]} 返回的存储周围四个路点引用的数组，其中有一些可能是空引用，原因是超出了迷宫的边界
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
     * 显示迷宫
     * 在屏幕上输出迷宫的形状，墙点用*标识，迷宫中起点到终点的唯一一条路径用+号标识，通路不用任何符号标识（用空格标识）
     *
     * @param originOrSolved 是否查看原图还是查看已经求解的迷宫的图，若为true则标识查看原图，否则查看已经求解之后的图像
     */
    public void showMaze(boolean originOrSolved) {
        if (!hasSolved && !originOrSolved)
            throw new IllegalArgumentException("输出迷宫图像时参数设置错误，在未解决迷宫之前不能查看解决迷宫之后的图像");
        for (PathPoint[] pathPoints : maze) {
            for (PathPoint pathPoint : pathPoints) {
                if (pathPoint.hasWall())
                    System.out.print(" * ");
                else if (pathPoint.isPathWay() && !originOrSolved) {
                    System.out.format(" \33[31;1m+\33[0m ");
                } else {
                    System.out.print("   ");
                }
            }
            System.out.println();
        }
    }

    /**
     * 解决迷宫
     * 使用A*寻路算法求解唯一的一条通路
     *
     * @throws MazeHasSolvedException 迷宫解决异常
     */
    public void solveMaze() throws MazeHasSolvedException {
        if (hasSolved)
            throw new MazeHasSolvedException();
        MazeSolver solver = new MazeSolver();
        solver.run();
    }

    /**
     * 检查迷宫是否已被解决
     *
     * @return boolean 为true则标识已被解决，否则未被解决
     */
    public boolean hasSolved() {
        return hasSolved;
    }

    /**
     * 路径点类，迷宫的基本组成部分，代表迷宫中的路径点。实现了<{@link Comparable}><{@link PathPoint}>接口之后,
     * 如果将该类对象保存到{@link Set}容器中，那么该容器就可以实现根据路径点在A*寻路算法中的消耗排序。如果保存在{@link ArrayList}容器中，在调用{@link Collections#sort(List)}
     * 方法后，也能完成对容器中路径点按照F值大小进行排序
     */
    static class PathPoint implements Comparable<PathPoint> {
        /**
         * A*寻路算法中要设定父节点以便从终点开始遍历该字段找到唯一的一条通路
         */
        private PathPoint fatherPoint;
        /**
         * A*寻路算法公式F=G+H中的G值
         */
        private Double distanceToStart;
        /**
         * A*寻路算法公式F=G+H中的H值
         */
        private Double distanceToEnd;
        /**
         * 标识该路径点是否是最后求解完迷宫之后，迷宫起点到终点的唯一一条路径，该字段有三种情况：<p>
         * 1、null: 该路径点上有墙<p>
         * 2、true: 该路经点属于起点到终点的通路<p>
         * 3、false: 该路径点不属于起点到终点的通路
         * 设定该字段的目的：在输出迷宫的时候，若该标识符为true，则就可以输出另外一个符号标识该路径点属于起点到终点通路的路径
         */
        private Boolean pathWay;

        /**
         * 标识该点是路点还是墙点，什么是路点和墙点：<p>
         * 如果按照普利姆算法生成迷宫，我们将除了迷宫四周不可打破的墙壁之外的中间空白区域用
         * 网格划分，这些网格组成墙点，剩下的留白处是路点，注意在一开始所有的路点和墙点都有墙
         * 详情请参考视频：【scratch实现-prim算法生成迷宫重制优化版】
         * <a href="https://www.bilibili.com/video/BV1xp4y1r7Ng/?p=3&share_source=copy_web&vd_source=ecaf67eab86977249adc291d11e7fa19">视频链接</a>
         */
        private Boolean pathOrWallPoint;

        /**
         * 得到当前节点的父节点
         *
         * @return {@link PathPoint} 返回当前的父节点
         */
        public PathPoint getFatherPoint() {
            return fatherPoint;
        }

        /**
         * 设置当前节点的父节点
         *
         * @param fatherPoint 即将成为该节点父节点的节点
         */
        public void setFatherPoint(PathPoint fatherPoint) {
            this.fatherPoint = fatherPoint;
        }

        /**
         * 返回A星算法中的G值，即起点到该路径点的消耗
         *
         * @return {@link Double} A星算法中的G值
         */
        public Double getDistanceToStart() {
            return distanceToStart;
        }

        /**
         * 设定该路径点在A星算法中的G值，即起点到该路径点的消耗
         *
         * @param distanceToStart 起点到该路径点的消耗
         */
        public void setDistanceToStart(Double distanceToStart) {
            this.distanceToStart = distanceToStart;
        }

        /**
         * 得到该路径点到终点的直线距离
         *
         * @return {@link Double} 该路径点到终点的直线距离
         */
        public Double getDistanceToEnd() {
            return distanceToEnd;
        }

        /**
         * 路径点的横坐标
         */
        private final int x;
        /**
         * 路径点的纵坐标
         */
        private final int y;

        //TODO 检查compareTo函数是否正确，确保调用Collections.sort（）之后，容器中是按照F值从小到大排列
        @Override
        public int compareTo(@NotNull Maze.PathPoint o) {
            double value1 = distanceToStart + distanceToEnd;
            double value2 = o.distanceToStart + o.distanceToEnd;
            return Double.compare(value1, value2);
        }

        /**
         * 当该引用为空引用时，说明该路径点上不存在墙
         */
        private Wall wall;

        /**
         * 设定该路径点到终点的直线距离
         *
         * @param distanceToEnd 该路径点到终点的直线距离
         */
        public void setDistanceToEnd(Double distanceToEnd) {
            this.distanceToEnd = distanceToEnd;
        }

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
         * 在该路径点上放置墙，当breakable为true时则表示放置可以打破的墙，为false时表示放置不可打破的墙
         *
         * @param breakable 为true时则表示放置可以打破的墙，为false时表示放置不可打破的墙
         */
        public void setWall(boolean breakable) {
            if (wall == null)
                wall = new Wall(breakable);
        }

        /**
         * 强制打破墙，在使用前请检查墙是否允许打破
         */
        public void breakWall() {
            wall = null;
        }

        /**
         * 判断该路径点是否属于通路
         *
         * @return boolean 判断结果
         */
        public boolean isPathWay() {
            return Objects.requireNonNullElse(pathWay, false);
        }

        /**
         * 设置迷宫通路标识
         *
         * @param pathWay 标识值，若为true，表示该节点属于通路，否则不属于
         */
        public void setPathWay(Boolean pathWay) {
            this.pathWay = pathWay;
        }
    }

    static class MazeHasSolvedException extends Exception {
        public MazeHasSolvedException() {
            super("迷宫已经被求解，不能重复求解迷宫，请调用输出接口查看结果");
        }
    }

    /**
     * 内部类迷宫求解器类，内含指向外部对象的引用，便于直接对迷宫类中的字段进行操作
     *
     * @author Mingxiang
     */
    private class MazeSolver {
        /**
         * A*寻路算法中的开放列表
         */
        private final List<PathPoint> openList = new ArrayList<>();
        /**
         * A*寻路算法中的闭合列表
         */
        private final Set<PathPoint> closeList = new HashSet<>();

        /**
         * 计算指定A*寻路算法中节点的G值和H值
         *
         * @param point 指定要计算的节点
         */
        private void calculateCost(PathPoint point) {
            //先判断该节点是否有父节点，因为g值要根据父节点的g值进行计算。只有起点没有父节点，所以其g值是0。
            if (point.getDistanceToStart() == null && point.getDistanceToEnd() == null) {
                double g = point.getFatherPoint() != null ? point.getFatherPoint().getDistanceToStart() + 1 : 0;
                point.setDistanceToStart(g);
                point.setDistanceToEnd(distanceToEnd(point));
            }
        }

        /**
         * 使用直线距离（勾股定理）直接计算当前路径点到终点的直线距离，也就是计算A*寻路算法公式中的h值
         * 其他可以作为h值的值：曼哈顿距离
         *
         * @param point 要计算的点
         * @return double 当前点到终点的直线距离，A*寻路算法公式中的h值
         */
        private double distanceToEnd(PathPoint point) {
            return Math.sqrt(Math.pow(point.getX() - endPoint.getX(), 2) + Math.pow(point.getY() - endPoint.getY(), 2));
        }

        /**
         * 使用A*寻路算法具体求解的过程
         *
         * @throws MazeHasSolvedException 迷宫无法重复求解异常
         */
        public void run() throws MazeHasSolvedException {
            if (!hasSolved) {
                openList.add(startPoint);
                calculateCost(startPoint);
                startPoint.setPathWay(true);
                while (!openList.isEmpty()) {
                    Collections.sort(openList);
                    processOpenNode(openList.get(0));//选择开放列表中F值最小的节点，由于openList已经根据节点的F值排过序了，此时openList前面的元素是F值较小的节点
                }
                PathPoint pointer = endPoint;
                while (pointer.getFatherPoint() != null) {
                    pointer.setPathWay(true);
                    pointer = pointer.getFatherPoint();
                }
                hasSolved = true;
            } else throw new MazeHasSolvedException();
        }

        /**
         * 返回指定路径点四周所有没有墙的节点
         *
         * @param point 指定的路径点
         * @return {@link ArrayList}<{@link PathPoint}> 返回的没有墙的路径点的集合
         */
        private ArrayList<PathPoint> getGroundPathPoints(PathPoint point) {
            PathPoint[] groundPoints = new PathPoint[4];
            int currX = point.getX();
            int currY = point.getY();
            groundPoints[0] = maze[currX - 1][currY].hasWall() ? null : maze[currX - 1][currY];//当前节点上边的节点
            groundPoints[1] = maze[currX + 1][currY].hasWall() ? null : maze[currX + 1][currY];//当前节点下边的节点
            groundPoints[2] = maze[currX][currY - 1].hasWall() ? null : maze[currX][currY - 1];//当前节点左边的节点
            groundPoints[3] = maze[currX][currY + 1].hasWall() ? null : maze[currX][currY + 1];//当前节点右边的节点
            ArrayList<PathPoint> points = new ArrayList<>();
            for (PathPoint thePoint : groundPoints) {
                if (thePoint != null)
                    points.add(thePoint);
            }
            return points;
        }

        /**
         * 当选中了开放列表中F值最小的节点之后，对该节点以及周边节点做相关处理
         *
         * @param point 已经选中的在openList中的节点，且该节点是openList中F值最小的节点
         */
        private void processOpenNode(PathPoint point) {
            //确保处理的节点都是迷宫中的路，pathList中存储了迷宫中所有为路的节点
            if (pathList.contains(point) && openList.contains(point)) {
                openList.remove(point);
                closeList.add(point);
                ArrayList<PathPoint> groundPathPoints = getGroundPathPoints(point);
                for (PathPoint thePathpoint : groundPathPoints) {
                    if (!closeList.contains(thePathpoint)) {
                        if (!openList.contains(thePathpoint)) {
                            openList.add(thePathpoint);
                        } else {
                            if (point.getDistanceToStart() + 1 > thePathpoint.getDistanceToStart())
                                continue;
                        }
                        thePathpoint.setFatherPoint(point);
                        calculateCost(thePathpoint);
                    }
                }
            }
        }

        /**
         * 重置求解器
         */
        public void resetSolver() {
            openList.clear();
            closeList.clear();
        }
    }
}