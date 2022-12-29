package MazeProblem.selfCode;

/**
 * 迷宫求解类
 *
 * @author localuser
 */
public class MazeSolver {
    /**
     * 迷宫
     */
    private Maze maze;
    /**
     * 负责调用迷宫生成算法，返回迷宫给maze引用
     */
    private MazeCreator creator=new MazeCreator();

    public void generateNewMaze(int length,int width){
        maze=creator.getNewMaze(length,width);
    }
}
