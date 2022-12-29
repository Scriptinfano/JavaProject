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
    private Point startPoint;

    /**
     * 终点
     */
    private Point endPoint;

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
                maze[i][j]=new PathPoint(i,j);
                if((i==startPoint.x&&j==startPoint.y)||(i==endPoint.x && j == endPoint.y)){
                    //在迷宫的起点和终点不放置墙
                    continue;
                }
                maze[i][j].setWall(true);
            }
        }
        ball=new Ball(startPoint.x,startPoint.y);//将球放到迷宫的起点
    }

    /**
     * 使用算法为全是墙的迷宫生成路径，打通迷宫里的墙形成可以走的通路
     */
    public void setRandomPath(MazeCreator.CreateMode mode) {
        if (mode == MazeCreator.CreateMode.DEPTH) {
            //TODO 使用深度优先算法打通迷宫中的通路
        } else if (mode == MazeCreator.CreateMode.RECURSE) {

        }

    }
}
