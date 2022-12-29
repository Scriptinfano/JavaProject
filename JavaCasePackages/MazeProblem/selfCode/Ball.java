package MazeProblem.selfCode;

/**
 * 走迷宫的球
 *
 * @author localuser
 */
public class Ball {
    /**
     * 球在迷宫中的横坐标
     */
    private int x;
    /**
     * 球在迷宫中的纵坐标
     */
    private int y;

    /**
     * 球
     *
     * @param x 初始化时球的横坐标
     * @param y 初始化时球的纵坐标
     */
    public Ball(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * 取得球的横坐标
     *
     * @return int
     */
    public int getX() {
        return x;
    }

    /**
     * 设置球的横坐标
     *
     * @param x x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * 取得球的纵坐标
     *
     * @return int
     */
    public int getY() {
        return y;
    }

    /**
     * 设置球的纵坐标
     *
     * @param y y
     */
    public void setY(int y) {
        this.y = y;
    }
}
