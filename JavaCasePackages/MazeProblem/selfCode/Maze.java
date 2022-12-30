package MazeProblem.selfCode;

class Point {
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int x;
    public int y;
}

/**
 * 碎墙器，负责生成迷宫时在迷宫中凿墙，生成迷宫路径
 *
 * @author localuser
 */


/**
 * 迷宫类
 *
 * @author localuser
 */
public class Maze {
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

    public Point getStartPoint(){
        return startPoint;
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
                //TODO 此处关于是否应该在初始化时在迷宫的起点和终点放墙，有待讨论
                if ((i == startPoint.x && j == startPoint.y) || (i == endPoint.x && j == endPoint.y)) {
                    //在迷宫的起点和终点不放置墙
                    continue;
                }
                maze[i][j].setWall(true);
                if(i%2==0||j%2==0){
                    maze[i][j].setPathOrWallPoint(false);
                }else{
                    maze[i][j].setPathOrWallPoint(true);
                }

            }
        }
        ball = new Ball(startPoint.x, startPoint.y);//将球放到迷宫的起点
    }

    /**
     * 验证指定的坐标组是否符合加入候选队列的条件，返回符合要求的点的数组
     * @param point 传入的一系列待检测的点
     * @return {@link Point[]} 返回合法的可以加入候选队列的点的数组
     */
    public Point[] verifyPoint(Point[] point){

    }


}
