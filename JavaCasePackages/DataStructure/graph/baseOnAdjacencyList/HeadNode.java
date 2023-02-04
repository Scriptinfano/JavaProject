package DataStructure.graph.baseOnAdjacencyList;

import java.util.LinkedList;

/**
 * 代表图中的顶点，在图的邻接表表示法中是边链表的头节点
 *
 * @author Mingxiang
 */
public class HeadNode {
    /**
     * 该顶点在邻接表中的编号
     */
    private final int index;

    /**
     * 数据域，表头节点的数据域通常是图中每个顶点的名字或者是该节点的权值
     */
    private final String info;

    /**
     * 存储所有以该顶点为始点的边节点的链表集合
     */
    private final LinkedList<LineNode> lineList;

    /**
     * 构造顶点
     *
     * @param theIndex 该顶点在邻接表中的编号
     * @param theInfo  顶点的权值
     */
    public HeadNode(int theIndex, String theInfo) {
        index = theIndex;
        info = theInfo;
        lineList = new LinkedList<>();
    }

    /**
     * 向该顶点添加与该顶点所关联的边（以该顶点为始点的边）
     *
     * @param adjacency 要添加的边的终点
     * @param theInfo   边所带的权值
     */
    public void addLineNode(HeadNode adjacency, String theInfo) {
        lineList.add(new LineNode(adjacency, theInfo));
    }

    /**
     * 输出顶点的权值
     */
    public void outPutInfo() {
        System.out.print("<nodeName:" + info + ",nodeIndex:" + index + "> ");
    }

    /**
     * 返回该顶点的出度
     *
     * @return int 头节点的出度
     */
    public int getOutDegree() {
        return lineList.size();
    }

    /**
     * 得到指定的邻接节点
     *
     * @param index 想要得到的邻接点对该顶点而言是第几个邻接点（注意编号从0开始）
     * @return {@link HeadNode} 返回指定的当前顶点的邻接点
     */
    public HeadNode getAdjacencyNode(int index) {
        return lineList.get(index).getAdjacency();
    }

    /**
     * 返回该顶点在邻接表中的编号
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
    public boolean zeroOutDegree() {
        return lineList.isEmpty();
    }

    /**
     * 如果邻接点A是该顶点的第三个邻接点，则返回该临界点与该顶点之间连线的权值
     *
     * @param index 该边所连接的邻接点是该顶点的第几个邻接点（注意这里的index从0开始）
     * @return int 返回边的长度
     */
    public int getDistanceByOrder(int index) {
        return Integer.parseInt(lineList.get(index).getInfo());
    }

    /**
     * 如果该顶点的邻接点A的在图中的编号是指定的编号index，则返回A与该顶点之间的边的权值
     *
     * @param index 指定的顶点的编号
     * @return int 返回边的权值，如果编号为index的顶点没有和当前节点相连，则返回-1
     */
    public int getDistanceByIndex(int index) {
        for (LineNode lineNode : lineList) {
            if (lineNode.getAdjacency().getIndex() == index)
                return Integer.parseInt(lineNode.getInfo());
        }
        return -1;
    }
}
