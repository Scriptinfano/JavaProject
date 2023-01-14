package DataStructure.graph.baseOnAdjacencyList;

/**
 * 边链表中的边节点，代表图中的连接节点的边
 *
 * @author Mingxiang
 */
public class LineNode {
    /**
     * 邻接点域，该边指向的节点
     */
    private final HeadNode Adjacency;

    /**
     * 数据域，边链表中的数据域通常为图中边的权值
     */
    private final String info;

    /**
     * 构造线节点
     *
     * @param adjacency 该边指向的顶点
     * @param info      边的权值
     */
    public LineNode(HeadNode adjacency, String info) {
        Adjacency = adjacency;
        this.info = info;
    }

    /**
     * 得到边的权值
     *
     * @return {@link String}
     */
    public String getInfo() {
        return info;
    }

    /**
     * 取得边所连接的顶点
     *
     * @return {@link HeadNode}
     */
    public HeadNode getAdjacency() {
        return Adjacency;
    }
}
