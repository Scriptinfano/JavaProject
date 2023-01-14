package MazeProblem.reference.referenceA;

import java.util.*;

public class AStar {
    public static final int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    static final List<Pos> openList = new ArrayList<>();
    static final Set<Pos> closeSet = new HashSet<>();
    //  地图数据 0:路 1：障碍
    static final int[][] map = {
            {0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0},
    };

    /**
     * 判断一个节点是不是能走（必须是地图内的点，并且不是障碍）
     *
     * @param x 节点横坐标
     * @param y 节点纵坐标
     * @return boolean 返回true则表示可以走，反之不能
     */
    public static boolean canWalk(int x, int y) {
        int col = map[0].length;
        int row = map.length;
        //在地图范围外
        if (x < 0 || x >= row || y < 0 || y >= col) {
            return false;
        }
        //是障碍
        return map[x][y] != 1;
    }

    /**
     * 找到以该点为中心的附近能走的且不在closeList中的点
     *
     * @param remove 找到以该点为中心的附近能走的且不在closeList中的点
     * @return List<Pos> 返回一个符合要求的点的列表
     */
    private static List<Pos> findLink(Pos remove) {
        int x = remove.getX();
        int y = remove.getY();
        ArrayList<Pos> linkPosList = new ArrayList<>();
        for (int[] dirUnit : dir) {
            int nx = dirUnit[0] + x;
            int ny = dirUnit[1] + y;
            Pos pos = new Pos(nx, ny);
            pos.setParent(remove);
            if (canWalk(nx, ny) && !closeSet.contains(pos)) {
                linkPosList.add(pos);
            }
        }
        return linkPosList;

    }

    //对角线估价法
    private static double diagonal(Pos pos, Pos endPos) {
        int dx = Math.abs(endPos.getX() - pos.getX());
        int dy = Math.abs(endPos.getY() - pos.getY());
        int diag = Math.min(dx, dy);
        int straight = dx + dy;
        return diag + (straight - 2 * diag);
    }

    /**
     * 计算消耗
     */
    private static void calcCost(Pos endPos, Pos pos) {
        pos.setH(diagonal(pos, endPos));
        double g = pos.getParent() != null ? pos.getParent().getG() : 0D;
        pos.setG(g + 1);
    }

    /**
     * 主函数
     */
    public static void main(String[] args) {

        Pos startPos = new Pos(0, 0);
        Pos endPos = new Pos(0, 5);

        openList.add(startPos);
        while (openList.size() > 0) {
            // 排序，去除代价最小的点
            Collections.sort(openList);
            Pos remove = openList.remove(0);
            closeSet.add(remove);
            List<Pos> linkList = findLink(remove);

            for (Pos pos : linkList) {
                calcCost(endPos, pos);
                openList.add(pos);
            }

            if (openList.contains(endPos)) {
                int endPosIdx = openList.indexOf(endPos);
                endPos = openList.get(endPosIdx);
                break;
            }
        }

        Pos parent = endPos.getParent();
        while (parent != null) {
            System.out.println("->  " + parent.getX() + " - " + parent.getY());
            parent = parent.getParent();
        }
    }
}
