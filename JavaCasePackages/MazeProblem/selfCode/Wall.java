package MazeProblem.selfCode;

/**
 * 墙类
 *
 * @author localuser
 */
public class Wall {
    /**
     * 标识该墙是否是可以打破的
     */
    private final boolean breakable;

    public Wall(boolean breakable) {
        this.breakable = breakable;
    }

    /**
     * 判断该墙是否可打破
     *
     * @return boolean
     */
    public boolean isBreakable() {
        return breakable;
    }
}
