package MazeProblem.selfCode;

class WallNotFoundException extends Exception{
    public WallNotFoundException() {}
    public WallNotFoundException(String msg){
        super(msg);
    }
}
/**
 * 路径点类，迷宫的组成部分
 *
 * @author localuser
 */
public class PathPoint {
    private final int x;
    private final int y;

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
     * 判断该路径点上是否有墙
     *
     * @return boolean
     */
    public boolean hasWall(){
        return wall!=null;
    }

    public boolean wallIsBreakable() throws WallNotFoundException {
        if(hasWall())
            return wall.isBreakable();
        else throw new WallNotFoundException("此处没有墙，无法检查墙是否可被打破");
    }
}
