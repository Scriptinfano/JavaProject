package DataStructure.graph.baseOnAdjacencyList;

import java.util.*;

/**
 * 迪杰斯特拉算法求解器，仅适用于使用邻接表存储结构的图使用
 * 使用方法：实例化对象之后，调用run方法求解之后，可以使用getShortestPathLength求解最短路径的长度
 * 或使用getShortestPath得到某点到某点的最短路径该怎么走
 *
 * @author Mingxiang
 */
public class DijkstraSolver {
    /**
     * 标记顶点是否已被访问
     */
    private final boolean[] hasVisited;
    /**
     * 起始点到各顶点的最短距离
     */
    private final Integer[] distance;

    /**
     * 各顶点的上一个访问的节点
     */
    private final HeadNode[] parent;

    /**
     * Dijkstra算法的起始点
     */
    private final HeadNode startNode;

    /**
     * 存储所有顶点的容器
     */
    private final ArrayList<HeadNode> headNodeList;

    /**
     * 所有进入该集合的顶点，都表示该顶点已经算在最短路径中了
     */
    private final ArrayList<HeadNode> pathCollection;


    /**
     * dijkstra算法解算器构造器
     *
     * @param theHeadNodeList 存储图中所有顶点的容器
     * @param theStartNode    Dijkstra算法求解的是一个顶点到图中所有顶点的最短路径，所以需要一个起始顶点作为遍历的起始点
     */
    public DijkstraSolver(ArrayList<HeadNode> theHeadNodeList, HeadNode theStartNode) {
        headNodeList = theHeadNodeList;
        pathCollection = new ArrayList<>();
        int size = theHeadNodeList.size();
        hasVisited = new boolean[size];//java的boolean数组默认初始化时，所有元素均为false
        distance = new Integer[size];
        parent = new HeadNode[size];
        startNode = theStartNode;

        if (!theHeadNodeList.isEmpty()) {
            //更新起点的信息
            update(headNodeList.get(0), 0, null);
            pathCollection.add(headNodeList.get(0));
            hasVisited[headNodeList.get(0).getIndex()] = true;
        } else throw new RuntimeException("构造DijkstraSolver时传入的ArrayList<HeadNode>容器为空，无法初始化");

    }

    /**
     * Dijkstra算法不允许有负权边，因为Dijkstra算法在计算最短路径时，不会因为负边的出现而更新已经计算过(收录过)的顶点的路径长度
     *
     * @return boolean
     */
    private boolean hasNegativeLine() {
        for (HeadNode node : headNodeList) {
            for (int j = 0; j < node.getOutDegree(); j++) {
                if (node.getDistanceByOrder(j) < 0)
                    return true;
            }
        }
        return false;
    }

    /**
     * 运行求解器
     */
    public void run() throws UnsupportedOperationException {
        if (hasNegativeLine())
            throw new UnsupportedOperationException("Dijkstra算法不允许有负权边，无法执行运行操作");

        HeadNode currentNode = startNode;
        //从startNode开始依次遍历所有顶点

        //while循环的条件是当pathCollection中包含所有顶点时结束循环
        while (!pathCollection.containsAll(headNodeList)) {
            //依次检索所有不在最短路径集合中的且与当前节点相连的顶点，并更新这些节点在三个数组中的信息
            for (int i = 0; i < currentNode.getOutDegree(); i++) {
                HeadNode theAdjacencyNode = currentNode.getAdjacencyNode(i);//当前正在处理的邻接点
                //检查该邻接点是否已被纳入最短路径集合
                if (!pathCollection.contains(theAdjacencyNode)) {
                    int newDistance = distance[currentNode.getIndex()] + currentNode.getDistanceByOrder(i);//原点到该点的最短距离加上目前正在遍历的邻接点到该顶点的距离
                    if (distance[theAdjacencyNode.getIndex()] == null || newDistance < distance[theAdjacencyNode.getIndex()])
                        update(theAdjacencyNode, newDistance, currentNode);//更新邻接点的状态
                    else update(theAdjacencyNode, distance[theAdjacencyNode.getIndex()], parent[theAdjacencyNode.getIndex()]);//维持该邻接点原有的状态不变
                }
            }
            int minIndex = scan();//未选顶点中的distance值最小的顶点的编号
            hasVisited[minIndex] = true;//minIndex所对应的顶点是即将添加到最短路径的顶点，所以将hasVisited的对应位置置true

            currentNode = headNodeList.get(minIndex);//更新最短路径中的最新节点
            pathCollection.add(currentNode);//将最新的节点加入已选择的节点集合中

        }

    }

    /**
     * 更新指定顶点的信息
     *
     * @param theNode     指定的顶点
     * @param theDistance 新的distance值
     * @param theParent   新的parent值
     */
    private void update(HeadNode theNode, int theDistance, HeadNode theParent) {
        distance[theNode.getIndex()] = theDistance;
        parent[theNode.getIndex()] = theParent;
    }

    /**
     * 扫描未选顶点中distance值最小的顶点，返回该顶点的编号
     *
     * @return int
     */
    private int scan() {
        Map<Integer, Integer> sorter = new HashMap<>();
        for (int i = 0; i < hasVisited.length; i++) {
            if (!hasVisited[i] && distance[i] != null) {
                sorter.put(i, distance[i]);
            }
        }
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(sorter.entrySet());

        list.sort((o1, o2) -> Integer.compare(o1.getValue() - o2.getValue(), 0));

        return list.get(0).getKey();

    }

    /**
     * 得到起始顶点到指定顶点的最短路径
     *
     * @param index 指定顶点在图中的编号
     * @return int 最短路径值
     */
    public int getShortestPathLength(int index) {
        if (index < 0 || index >= distance.length) {
            throw new IllegalArgumentException("得到起始顶点到指定顶点的最短路径时指定的顶点编号不存在");
        }
        return distance[index];
    }

    /**
     * 得到起始顶点到指定顶点的路径集合，其中包含起始顶点到指定顶点的所有路径节点
     *
     * @param index 指定的顶点在图中的编号
     * @return {@link ArrayList}<{@link HeadNode}>
     */
    public ArrayList<HeadNode> getShortestPath(int index) {
        if (index < 0 || index >= distance.length) {
            throw new IllegalArgumentException("得到起始顶点到指定顶点的最短路径序列时指定的顶点编号不存在");
        }
        ArrayList<HeadNode> shortestPathCollection = new ArrayList<>();
        shortestPathCollection.add(headNodeList.get(index));
        HeadNode currentParentNode = parent[index];
        shortestPathCollection.add(currentParentNode);
        while (currentParentNode != startNode) {
            currentParentNode = parent[currentParentNode.getIndex()];
            shortestPathCollection.add(currentParentNode);
        }
        Collections.reverse(shortestPathCollection);
        return shortestPathCollection;
    }

}
