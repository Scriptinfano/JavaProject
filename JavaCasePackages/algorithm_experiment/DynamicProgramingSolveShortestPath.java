package algorithm_experiment;

import java.util.ArrayList;
import java.util.Arrays;

public class DynamicProgramingSolveShortestPath {
    private static final ArrayList<HeadNode> graphList;//代表整个邻接表
    private static final int nodeNum = graphList.size();//节点总数
    private static final int[] cost = new int[nodeNum];//在阶段决策中，各个顶点到收点的最小费用
    private static final int[] path = new int[nodeNum];//从原点到收点的最短路径上的顶点编号

    static {
        //邻接表的构造
        HeadNode[] headNodes = new HeadNode[7];
        headNodes[0] = new HeadNode(0);
        headNodes[1] = new HeadNode(1);
        headNodes[2] = new HeadNode(2);
        headNodes[3] = new HeadNode(3);
        headNodes[4] = new HeadNode(4);
        headNodes[5] = new HeadNode(5);
        headNodes[6] = new HeadNode(6);
        graphList = new ArrayList<>(headNodes.length);

        graphList.addAll(Arrays.asList(headNodes));

        graphList.get(0).addLineNode(graphList.get(1), 4);
        graphList.get(0).addLineNode(graphList.get(2), 5);
        graphList.get(0).addLineNode(graphList.get(3), 8);

        graphList.get(1).addLineNode(graphList.get(3), 6);
        graphList.get(1).addLineNode(graphList.get(4), 6);

        graphList.get(2).addLineNode(graphList.get(3), 5);
        graphList.get(2).addLineNode(graphList.get(5), 7);

        graphList.get(3).addLineNode(graphList.get(4), 8);
        graphList.get(3).addLineNode(graphList.get(5), 9);
        graphList.get(3).addLineNode(graphList.get(6), 9);

        graphList.get(4).addLineNode(graphList.get(6), 5);

        graphList.get(5).addLineNode(graphList.get(6), 4);
    }

    public static void main(String[] args) {
        for (int i = 0; i < nodeNum; i++) {
            cost[i] = 0;
            path[i] = -1;
        }
        for (int i = nodeNum - 2; i >= 0; i--) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < graphList.get(i).lineList.size(); j++) {
                LineNode lineNode = graphList.get(i).lineList.get(j);
                int costValue = lineNode.value + cost[lineNode.node.index];
                if (costValue < min) {
                    cost[i] = costValue;
                    min = cost[i];
                    path[i] = lineNode.node.index;
                }
            }
        }
        int i = 0;
        int index;
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);
        while (path[i] != nodeNum - 1) {
            index = path[i];
            list.add(index);
            i = index;
        }
        list.add(nodeNum - 1);
        System.out.println("最短路径为：" + list);
    }

    /**
     * 邻接表边节点
     *
     * @author Mingxiang
     */
    private record LineNode(HeadNode node, int value) {
    }

    /**
     * 代表图中的顶点，在图的邻接表表示法中是边链表的头节点
     *
     * @author Mingxiang
     */
    private static class HeadNode {
        /**
         * 该顶点在邻接表中的编号
         */
        public final int index;

        /**
         * 存储所有以该顶点为始点的边节点的链表集合
         */
        private final ArrayList<LineNode> lineList;

        public HeadNode(int theIndex) {
            index = theIndex;
            lineList = new ArrayList<>();
        }

        public void addLineNode(HeadNode adjacency, int value) {
            lineList.add(new LineNode(adjacency, value));
        }
    }
}
