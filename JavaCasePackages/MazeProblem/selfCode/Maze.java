package MazeProblem.selfCode;

import java.util.ArrayList;

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
    public int x;
    /**
     * 纵坐标
     */
    public int y;
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
    private PathPoint[][] maze;

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
        startPoint = maze[1][1];
        endPoint = maze[mazeSize - 2][mazeSize - 2];
    }

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
                else if (maze[i][j].isPathWay()) {
                    System.out.print(" + ");
                } else {
                    System.out.print("   ");
                }
            }
            System.out.println();
        }
    }

    /**
     * 使用A*寻路算法求解唯一的一条通路
     */
    public void solveMaze() {
        //TODO
        ArrayList<PathPoint> openList = new ArrayList<>();
        ArrayList<PathPoint> closeLise = new ArrayList<>();
        PathPoint currentPoint = getStartPoint();

    }

    /**
     * 路径点类，迷宫的基本组成部分，代表迷宫中的路径点
     */
    static class PathPoint {
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
         * 标识该路径点是否是最后求解完迷宫之后，迷宫起点到终点的唯一一条路径，该字段有三种情况：<p>
         * 1、null: 该路径点上有墙<p>
         * 2、true: 该路经点属于起点到终点的通路<p>
         * 3、false: 该路径点不属于起点到终点的通路
         */
        private Boolean pathWay;

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

        public boolean isPathWay() {
            if (pathWay != null)
                return pathWay;
            else return false;
        }

        public void setPathWay(Boolean pathWay) {
            this.pathWay = pathWay;
        }


    }
}