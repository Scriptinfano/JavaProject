package graph.adjacencyList;

import myscan.ScannerPlus;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 边链表中的边节点，代表图中的连接节点的边
 *
 * @author Mingxiang
 */
class LineNode {
    private HeadNode Adjacency;//邻接点域，指示与顶点Vi邻接的顶点在图中的位置
    private String info;//数据域，边链表中的数据域通常为图中边的权值

    public LineNode(HeadNode adjacency, String info) {
        Adjacency = adjacency;
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public HeadNode getAdjacency() {
        return Adjacency;
    }
}

/**
 * 表头节点，在邻接表中边链表的头节点，也就是图中的每一个顶点
 *
 * @author Mingxiang
 */
class HeadNode {
    private int index;//该头节点在邻接表中的编号，是邻接表中的第几个头节点
    private String info;//数据域，表头节点的数据域通常是图中每个顶点的名字
    private LinkedList<LineNode> lineList;//每个头节点连接一个边链表

    /**
     * 构造头节点
     *
     * @param theIndex 该头节点在邻接表中的编号
     * @param theInfo  头节点的权值
     */
    public HeadNode(int theIndex, String theInfo) {
        index = theIndex;
        info = theInfo;
        lineList = new LinkedList<>();
    }

    /**
     * 向该头节点对应的边链表中添加边节点
     *
     * @param adjacency 边节点所代表的有向边所指向的节点
     * @param theInfo   边所带的权值
     */
    public void addLineNode(HeadNode adjacency, String theInfo) {
        lineList.add(new LineNode(adjacency, theInfo));

    }

    /**
     * 输出头节点权值
     */
    public void output() {
        System.out.print(info + " ");
    }

    /**
     * 返回该头节点的出度
     *
     * @return int 头节点的出度
     */
    public int getOutDegree() {
        return lineList.size();
    }

    /**
     * 得到邻接节点
     *
     * @param index 想要得到的邻接点对该顶点而言是第几个邻接点
     * @return {@link HeadNode} 返回邻接点
     */
    public HeadNode getAdjacencyNode(int index) {
        return lineList.get(index).getAdjacency();
    }

    /**
     * 返回头节点在邻接表中的编号
     *
     * @return int 在邻接表中的编号
     */
    public int getIndex() {
        return index;
    }

    /**
     * 判断该顶点的出度是否为0
     *
     * @return boolean 为0则返回true，否则返回false
     */
    public boolean emptyLineList() {
        return lineList.isEmpty();
    }
}

/**
 * 有向图的邻接表表示法，每个顶点放在数组容器中，每个顶点关联一个边链表，边链表中存放关联于该顶点的所有边的信息
 *
 * @author Mingxiang
 */
public class GraphAdjacencyList {
    private static final ScannerPlus scanner = new ScannerPlus();
    private ArrayList<HeadNode> graphList;//代表整个邻接表
    private int nodeSize;//代表图中总共有多少个顶点

    private boolean[] hasVisited;//标记顶点是否经过了访问，经过默认初始化之后，所有值均为false

    public GraphAdjacencyList() {
        System.out.println("开始录入每个顶点的信息...");
        System.out.println("输入图中有多少个顶点：");
        nodeSize = scanner.nextInt();
        graphList = new ArrayList<HeadNode>(nodeSize);
        hasVisited = new boolean[nodeSize];
        System.out.println("开始录入图中每个顶点的信息...（按照顶点在图中的顺序输入）");
        for (int i = 0; i < nodeSize; i++) {
            System.out.println("输入第" + (i + 1) + "个顶点的权值（任意输入）：");
            graphList.add(new HeadNode(i + 1, scanner.nextLine()));
        }

        System.out.println("开始录入每个顶点所关联的所有边的信息...");
        for (int i = 0; i < nodeSize; i++) {
            System.out.println("请输入第" + (i + 1) + "个顶点的出度：");
            int theOutDegree = scanner.nextInt();
            ArrayList<Integer> hasRepeat = new ArrayList<Integer>();
            for (int j = 0; j < theOutDegree; j++) {
                System.out.println("请输入该顶点所关联的第" + (j + 1) + "条有向边指向了图中的第几个顶点：");
                int theIndex = i + 1;
                //这个for循环的意思是该顶点的邻接点不能是自己，必须输入其他邻接点
                while (theIndex == i + 1 || hasRepeat.contains(theIndex)) {
                    theIndex = scanner.nextSelectionByInt(1, nodeSize);
                    if (theIndex == i + 1)
                        System.out.println("该顶点所关联的边不能指向该顶点自身且顶点所关联的有向线段必须各自指向不同的顶点，请重新输入所指向顶点是图中的第几个顶点：");
                }
                System.out.println("请输入该边的权值：");
                graphList.get(i).addLineNode(graphList.get(theIndex - 1), scanner.nextLine());
                hasRepeat.add(theIndex);
            }
        }

        System.out.println("录入完毕");
        ScannerPlus.pause();
    }

    /**
     * 图的遍历
     */
    public void traverse() {
        traverseRecursion(graphList.get(0), 1);//从第一个顶点开始遍历
        System.out.println();
    }

    /**
     * 图的遍历的递归函数
     *
     * @param node  当前正在遍历中的节点
     * @param index 正在遍历的节点是邻接表中的第几个节点
     */
    private void traverseRecursion(HeadNode node, int index) {
        if (!hasVisited[index - 1])//如果当前节点未被访问的情况
        {
            node.output();
            hasVisited[index - 1] = true;
            if (node.emptyLineList()) return;
            for (int i = 0; i < node.getOutDegree(); i++) {
                HeadNode theNode = node.getAdjacencyNode(i);
                traverseRecursion(theNode, theNode.getIndex());
            }
        }
    }

}

/**
 * @author Mingxiang
 * @see GraphAdjacencyList GraphAdjacencyList的测试类
 */
class TestGraphList {
    private static final ScannerPlus scanner = new ScannerPlus();

    public static void main(String[] args) {
        GraphAdjacencyList graph = new GraphAdjacencyList();
        graph.traverse();
    }
}
