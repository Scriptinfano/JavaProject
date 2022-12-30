package MazeProblem.selfCode;

class WallNotFoundException extends RuntimeException{
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
     * 标识该点是路点还是墙点，什么是路点和墙点：
     * 如果按照普利姆算法生成迷宫，我们将除了迷宫四周不可打破的墙壁之外的中间空白区域用
     * 网格划分，这些网格组成墙点，剩下的留白处是路点，注意在一开始所有的路点和墙点都有墙
     * 详情请参考视频：【scracth实现-prim算法生成迷宫重制优化版】
     * <a href="https://www.bilibili.com/video/BV1xp4y1r7Ng/?p=3&share_source=copy_web&vd_source=ecaf67eab86977249adc291d11e7fa19">视频链接</a>
     */
    private Boolean pathOrWallPoint;

    /**
     * 标识该路径点是否已加入迷宫的通路
     */
    private Boolean hasVisited;

    public Boolean hasVisited() {
        return hasVisited;
    }

    public void setVisited(Boolean hasVisited) {
        this.hasVisited = hasVisited;
    }

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
    public void setPathOrWallPoint(boolean mark){
        pathOrWallPoint=mark;
    }

    /**
     * 判断该路径点是路点还是墙点，如果为true则是路点，为false则是墙点
     *
     * @return {@link Boolean} 如果为true则是路点，为false则是墙点
     */
    public Boolean isPathOrWallPoint(){
        return pathOrWallPoint;
    }

    /**
     * 判断该路径点上是否有墙
     *
     * @return boolean
     */
    public boolean hasWall(){
        return wall!=null;
    }

    public boolean wallIsBreakable(){
        if(hasWall())
            return wall.isBreakable();
        else throw new WallNotFoundException("此处没有墙，无法检查墙是否可被打破");
    }

    public void breakWall() {
        wall=null;
    }
}
