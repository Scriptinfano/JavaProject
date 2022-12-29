package MazeProblem.selfCode;

/**
 * 迷宫生成器类，负责创造符号要求的迷宫类{@link Maze}
 *
 * @author localuser
 */
public class MazeCreator {
    /**
     * 迷宫生成有多种算法，这些枚举常量标识使用哪种算法生成迷宫
     */
    public enum CreateMode {
        /**
         * 深度深度优先生成迷宫
         */
        DEPTH,

        /**
         * 递归分割生成迷宫
         */
        RECURSE

    }

    /**
     * 要求生成的迷宫的长度最小不能小于这个值
     */
    private static final int minimumLength = 4;
    /**
     * 要求生成的迷宫的宽度最小不能小于这个值
     */
    private static final int minimumWidth = 4;

    /**
     * 获得新迷宫，在内部创建好Maze类，然后返回符合要求的Maze对象。
     * 迷宫生成器要求生成的迷宫至少是4*4的，如果要求生成的迷宫小于这个值则不予生成，直接返回空引用
     *
     * @return {@link Maze}
     */
    public Maze getNewMaze(int length, int width) {
        if (length < minimumLength || width < minimumWidth)
            return null;//要求制造的迷宫不符合要求

        Maze maze=new Maze(length,width);
        //利用本类的枚举类实现迷宫的多算法生成
        maze.setRandomPath(CreateMode.DEPTH);
        return maze;
    }
}
