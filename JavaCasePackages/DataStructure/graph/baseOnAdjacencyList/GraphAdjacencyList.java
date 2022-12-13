package graph.baseOnAdjacencyList;

import myScannerAndPrinter.ScannerPlus;

import java.io.PrintStream;
import java.util.*;

/**
 * 边链表中的边节点，代表图中的连接节点的边
 *
 * @author Mingxiang
 */
class LineNode {
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

/**
 * 代表图中的顶点，在图的邻接表表示法中是边链表的头节点
 *
 * @author Mingxiang
 */
class HeadNode {
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
    public int getLineDistanceByOrder(int index) {
        return Integer.parseInt(lineList.get(index).getInfo());
    }

    /**
     * 如果该顶点的邻接点A的在图中的编号是指定的编号index，则返回A与该顶点之间的边的权值
     *
     * @param index 指定的顶点的编号
     * @return int 返回边的权值，如果编号为index的顶点没有和当前节点相连，则返回-1
     */
    public int getLineDistanceByIndex(int index) {
        for (LineNode lineNode : lineList) {
            if (lineNode.getAdjacency().getIndex() == index)
                return Integer.parseInt(lineNode.getInfo());
        }
        return -1;
    }
}


/**
 * 迪杰斯特拉算法求解器，内含该算法所需的三个数组，使用方法是传入存储所有顶点的容器和该算法所需的起始点然后调用接口run()
 *
 * @author Mingxiang
 */
class DijkstraSolver {
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
                if (node.getLineDistanceByOrder(j) < 0)
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
                    int newDistance = distance[currentNode.getIndex()] + currentNode.getLineDistanceByOrder(i);//到达该顶点的最短距离加上目前正在遍历的邻接点到该顶点的距离
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
        Map<Integer, Integer> sorter = new HashMap<Integer, Integer>();
        for (int i = 0; i < hasVisited.length; i++) {
            if (!hasVisited[i] && distance[i] != null) {
                sorter.put(i, distance[i]);
            }
        }
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(sorter.entrySet());

        list.sort(new Comparator<>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return Integer.compare(o1.getValue() - o2.getValue(), 0);
            }
        });

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
        ArrayList<HeadNode> shortestPathCollection = new ArrayList<HeadNode>();
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

/**
 * 弗洛伊德算法求解器<br/>
 * 使用方法：
 *
 * @author Mingxiang
 */
class FloydSolver {

    /**
     * 最短路径上顶点Vj的前一顶点的序号，Vi到Vj之间无弧，则Path(i,j)=-1;
     */
    private ArrayList<ArrayList<Integer>> path;

    /**
     * 记录顶点Vi和Vj之间的最短路径长度
     */
    private ArrayList<ArrayList<Integer>> distance;

    private boolean hasRun = false;
    private boolean hasSet = false;

    /**
     * 弗洛伊德解算器<br>
     * 该构造器为空参构造器，仅构造该对象是无法使用该对象的，需要调用set接口设置内部的邻接表<br/>
     */
    public FloydSolver() {
    }

    public void set(ArrayList<HeadNode> headNodeList) {
        int nodeSize = headNodeList.size();//节点数量
        path = new ArrayList<>(nodeSize);
        distance = new ArrayList<>(nodeSize);
        for (int i = 0; i < nodeSize; i++) {
            path.add(new ArrayList<>(nodeSize));
            distance.add(new ArrayList<>(nodeSize));
            for (int j = 0; j < nodeSize; j++) {
                path.get(i).add(null);
                path.get(i).set(j, -1);
                distance.get(i).add(null);
            }
        }

        //给distance矩阵和path矩阵赋值
        for (int i = 0; i < nodeSize; i++) {
            HeadNode currentHeadNode = headNodeList.get(i);
            for (int j = 0; j < currentHeadNode.getOutDegree(); j++) {
                HeadNode theAdjacencyNode = currentHeadNode.getAdjacencyNode(j);
                distance.get(i).set(theAdjacencyNode.getIndex(), currentHeadNode.getLineDistanceByOrder(j));
                distance.get(i).set(currentHeadNode.getIndex(), 0);//自己到自己的最短距离是0
                path.get(i).set(theAdjacencyNode.getIndex(), i);
            }
        }
        hasSet = true;
    }


    public void run() {
        if (!hasSet) throw new RuntimeException("FloydSolver对象未完成必要设置（未调用set()接口），无法运行run接口");
        int nodeSize = path.size();
        for (int k = 0; k < nodeSize; k++) {
            for (int i = 0; i < nodeSize; i++) {
                for (int j = 0; j < nodeSize; j++) {
                    if (distance.get(i).get(k) + distance.get(k).get(j) < distance.get(i).get(j)) {
                        //由i经过k到j的路径更短
                        distance.get(i).set(j, distance.get(i).get(k) + distance.get(k).get(j));//更新distance[i][j]
                        path.get(i).set(j, path.get(k).get(j));//更改j的前驱为k
                    }
                }
            }
        }
        hasRun = true;
    }

    /**
     * 得到最短路径长度
     *
     * @param startIndex 起始节点编号
     * @param endIndex   终止节点编号
     * @return int 返回最短路径的长度
     */
    public int getShortestPathLength(int startIndex, int endIndex) {
        if (!hasSet)
            throw new RuntimeException("未执行set接口，无法执行getShortestPathLength操作");
        else if (!hasRun)
            throw new RuntimeException("已正确设置set，但未执行run接口，无法执行getShortestPathLength操作");
        else {
            return distance.get(startIndex).get(endIndex);
        }

    }
}


/**
 * 有向图的邻接表表示法，每个顶点放在数组容器中，每个顶点关联一个边链表，边链表中存放关联于该顶点的所有边的信息
 *
 * @author Mingxiang
 */
public class GraphAdjacencyList {

    private static final PrintStream printer = System.out;
    private static final ScannerPlus scanner = new ScannerPlus();
    private ArrayList<HeadNode> graphList;//代表整个邻接表

    private boolean[] hasVisited;//标记顶点是否经过了访问，经过默认初始化之后，所有值均为false

    /**
     * 按照图片所示构造有向图或无向图
     */
    private void create_default() {
        //createOrientedGraph();//构造有向图，该有向图在该源文件所在的文件夹中，测试图的遍历功能
        createUndirectedGraph();//构造无向图，该无向图在该源文件所在的文件夹中，测试迪杰斯特拉算法
    }

    /**
     * 存储结构采用邻接表，构造图，该构造函数有两个模式{@link CREATE_MODE}
     * 分别是：USER_MODE（在命令行窗口与用户互动获得图的相关信息）、DEFAULT_MODE（以给定的图直接构造，给定的图在本源文件所在的文件夹中可以找到）
     *
     * @param theMode {@link CREATE_MODE}供选择的模式
     */
    public GraphAdjacencyList(CREATE_MODE theMode) {
        if (theMode == CREATE_MODE.USER_MODE)
            create_user();//通过用户输入
        else if (theMode == CREATE_MODE.DEFAULT_MODE)
            create_default();
        else throw new RuntimeException("错误初始化");
    }

    /**
     * 与用户交互录入各个顶点与边的信息
     */
    private void create_user() {
        System.out.println("开始录入每个顶点的信息...");
        System.out.println("输入图中有多少个顶点：");
        //代表图中总共有多少个顶点
        int nodeSize = scanner.nextInt();
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
     * 按照图片默认有向图.jpg构造有向图
     */
    private void createOrientedGraph() {
        HeadNode[] headNodes = new HeadNode[6];
        headNodes[0] = new HeadNode(0, "A");
        headNodes[1] = new HeadNode(1, "B");
        headNodes[2] = new HeadNode(2, "C");
        headNodes[3] = new HeadNode(3, "D");
        headNodes[4] = new HeadNode(4, "E");
        headNodes[5] = new HeadNode(5, "F");
        graphList = new ArrayList<>(headNodes.length);
        hasVisited = new boolean[headNodes.length];
        graphList.addAll(Arrays.asList(headNodes));

        graphList.get(0).addLineNode(graphList.get(1), "5");

        graphList.get(1).addLineNode(graphList.get(2), "50");

        graphList.get(2).addLineNode(graphList.get(4), "10");

        graphList.get(3).addLineNode(graphList.get(2), "20");
        graphList.get(3).addLineNode(graphList.get(4), "60");

        graphList.get(5).addLineNode(graphList.get(1), "10");
        graphList.get(5).addLineNode(graphList.get(3), "30");
        graphList.get(5).addLineNode(graphList.get(4), "100");

    }

    /**
     * 按照图片默认无向图.jpg构造无向图
     */
    private void createUndirectedGraph() {
        HeadNode[] headNodes = new HeadNode[9];
        headNodes[0] = new HeadNode(0, "A");
        headNodes[1] = new HeadNode(1, "B");
        headNodes[2] = new HeadNode(2, "C");
        headNodes[3] = new HeadNode(3, "D");
        headNodes[4] = new HeadNode(4, "E");
        headNodes[5] = new HeadNode(5, "F");
        headNodes[6] = new HeadNode(6, "G");
        headNodes[7] = new HeadNode(7, "H");
        headNodes[8] = new HeadNode(8, "I");
        graphList = new ArrayList<>(headNodes.length);
        hasVisited = new boolean[headNodes.length];
        graphList.addAll(Arrays.asList(headNodes));

        graphList.get(0).addLineNode(graphList.get(1), "4");
        graphList.get(0).addLineNode(graphList.get(2), "8");

        graphList.get(1).addLineNode(graphList.get(0), "4");
        graphList.get(1).addLineNode(graphList.get(2), "3");
        graphList.get(1).addLineNode(graphList.get(3), "8");

        graphList.get(2).addLineNode(graphList.get(0), "8");
        graphList.get(2).addLineNode(graphList.get(1), "3");
        graphList.get(2).addLineNode(graphList.get(4), "1");
        graphList.get(2).addLineNode(graphList.get(5), "6");

        graphList.get(3).addLineNode(graphList.get(1), "8");
        graphList.get(3).addLineNode(graphList.get(4), "2");
        graphList.get(3).addLineNode(graphList.get(7), "4");
        graphList.get(3).addLineNode(graphList.get(6), "7");

        graphList.get(4).addLineNode(graphList.get(3), "2");
        graphList.get(4).addLineNode(graphList.get(2), "1");
        graphList.get(4).addLineNode(graphList.get(5), "6");

        graphList.get(5).addLineNode(graphList.get(2), "6");
        graphList.get(5).addLineNode(graphList.get(4), "6");
        graphList.get(5).addLineNode(graphList.get(7), "2");

        graphList.get(6).addLineNode(graphList.get(3), "7");
        graphList.get(6).addLineNode(graphList.get(7), "14");
        graphList.get(6).addLineNode(graphList.get(8), "9");

        graphList.get(7).addLineNode(graphList.get(5), "2");
        graphList.get(7).addLineNode(graphList.get(3), "4");
        graphList.get(7).addLineNode(graphList.get(6), "14");
        graphList.get(7).addLineNode(graphList.get(8), "10");

        graphList.get(8).addLineNode(graphList.get(7), "10");
        graphList.get(8).addLineNode(graphList.get(6), "9");
    }

    /**
     * 得到图中节点的个数
     *
     * @return int
     */
    public int getNodeSize() {
        return graphList.size();
    }

    /**
     * 图的广度优先遍历
     */
    public void breadthTraverse() {
        for (HeadNode node : graphList) breadthTraverseOnce(node);//对图中所有的顶点调用广度优先遍历函数，因为一次调用只能遍历有向图中的一个连通分量
        Arrays.fill(hasVisited, 0, hasVisited.length, false);//遍历结束之后将hasVisited置为初始状态以免对下次遍历产生影响
    }

    /**
     * 图的深度优先遍历
     */
    public void depthTraverse() {
        for (int i = 0; i < graphList.size(); i++)
            traverseRecursion(graphList.get(i), i);//对图中所有的顶点调用深度优先遍历递归函数，因为一次调用只能遍历有向图中的一个连通分量
        Arrays.fill(hasVisited, 0, hasVisited.length, false);//遍历结束之后将hasVisited置为初始状态以免对下次遍历产生影响
        System.out.println();
    }

    /**
     * 图的深度优先遍历的递归函数
     * 该深度优先遍历在遍历有向图时，仅能遍历其中一个连通分量
     *
     * @param node  当前正在遍历中的节点
     * @param index 正在遍历的节点是邻接表中的第几个节点
     */
    private void traverseRecursion(HeadNode node, int index) {
        if (!hasVisited[index])//如果当前节点未被访问的情况
        {
            node.outPutInfo();
            hasVisited[index] = true;
            if (node.zeroOutDegree()) return;
            for (int i = 0; i < node.getOutDegree(); i++) {
                HeadNode theNode = node.getAdjacencyNode(i);
                traverseRecursion(theNode, theNode.getIndex());
            }
        }
    }

    /**
     * 使用迪杰斯特拉算法输出指定的起始顶点到指定的终点的最短路径（由顶点的编号组成）
     *
     * @param startIndex  路径的起始节点的编号
     * @param targetIndex 指定的顶点的编号
     */
    public void outputShortestPathByDijkstra(int startIndex, int targetIndex) {
        if (startIndex < 0 || targetIndex < 0 || startIndex >= graphList.size() || targetIndex >= graphList.size())
            throw new IllegalArgumentException("调用outputShortestPathByDijkstra()函数时传入的起始顶点编号和终止顶点编号不符合要求");
        DijkstraSolver solver = new DijkstraSolver(graphList, graphList.get(startIndex));
        solver.run();//运行解算器
        ArrayList<HeadNode> result = solver.getShortestPath(targetIndex);
        printer.print(startIndex + "号顶点到" + targetIndex + "号顶点的最短路径为：");
        for (HeadNode theNode : result) {
            printer.print(theNode.getIndex() + " ");
        }
        printer.println();
    }

    public int getShortestPathByDijkstra(int startIndex, int targetIndex) {
        if (startIndex < 0 || targetIndex < 0 || startIndex >= graphList.size() || targetIndex >= graphList.size())
            throw new IllegalArgumentException("调用getShortestPathByDijkstra()函数时传入的起始顶点编号和终止顶点编号不符合要求");
        DijkstraSolver solver = new DijkstraSolver(graphList, graphList.get(startIndex));
        solver.run();//运行解算器
        return solver.getShortestPathLength(targetIndex);
    }

    /**
     * 图的一次广度优先遍历，仅遍历一个连通分量
     */
    private void breadthTraverseOnce(HeadNode theNode) {
        Queue<HeadNode> theQueue = new LinkedList<>();
        if (!hasVisited[theNode.getIndex()]) {
            theNode.outPutInfo();
            hasVisited[theNode.getIndex()] = true;
            theQueue.add(theNode);
        } else return;
        //队列不为空则继续循环
        while (!theQueue.isEmpty()) {
            HeadNode currentNode = theQueue.poll();
            //依次检查currentNode的邻接点是否被访问，如果未被访问则输出并将其入队，其将在下一次循环中出队并检查它的所有邻接点
            for (int i = 0; i < currentNode.getOutDegree(); i++) {
                //循环次数是当前顶点的出度，也就是检查当前顶点的所有邻接点
                HeadNode adjacencyNode = currentNode.getAdjacencyNode(i);
                if (!hasVisited[adjacencyNode.getIndex()]) {
                    //若邻接点在之前未被访问过，则输出其权值，并将其入队
                    adjacencyNode.outPutInfo();
                    hasVisited[adjacencyNode.getIndex()] = true;
                    theQueue.add(adjacencyNode);
                }
            }
        }
    }


    /**
     * USER_MODE（在命令行窗口与用户互动获得图的相关信息）、DEFAULT_MODE（以给定的图直接构造，给定的图在本源文件所在的文件夹中可以找到）
     *
     * @author Mingxiang
     */
    public enum CREATE_MODE {
        USER_MODE,
        DEFAULT_MODE
    }

    /**
     * 获得指定的顶点
     *
     * @param index 顶点的编号
     * @return {@link HeadNode} 返回的指定顶点
     */
    public HeadNode getHeadNode(int index) {
        return graphList.get(index);
    }


    /**
     * 得到图中顶点的个数
     *
     * @return int 顶点的个数
     */
    public int getNumberOfHeadNode() {
        return graphList.size();
    }

    public int getShortestPathByFloyd(int startIndex, int endIndex) {
        FloydSolver solver = new FloydSolver();
        solver.set(graphList);
        solver.run();
        return solver.getShortestPathLength(startIndex, endIndex);
    }


}

/**
 * @author Mingxiang
 * @see GraphAdjacencyList GraphAdjacencyList的测试类
 */
class TestGraphList {
    private static final ScannerPlus scanner = new ScannerPlus();
    private static final PrintStream printer = System.out;

    public static void main(String[] args) {
        testFloyd();
    }


    public static void testDijkstra() {
        GraphAdjacencyList graph = new GraphAdjacencyList(GraphAdjacencyList.CREATE_MODE.DEFAULT_MODE);
        System.out.print("图的深度优先遍历：");
        graph.depthTraverse();
        printer.println();
        System.out.print("图的广度优先遍历：");
        graph.breadthTraverse();
        printer.println();

        System.out.println("通过Dijkstra算法求解两个点之间的最短路径测试：");
        System.out.print("输入起始点的编号：");
        int startIndex = scanner.nextSelectionByInt(0, graph.getNumberOfHeadNode());
        System.out.print("输入终点的编号：");
        int endIndex = scanner.nextSelectionByInt(0, graph.getNumberOfHeadNode());
        graph.outputShortestPathByDijkstra(startIndex, endIndex);

    }

    public static void testFloyd() {
        //TODO 待测试

        printer.print("通过弗洛伊德算法求解每一对顶点的最短路径：");
        GraphAdjacencyList graph = new GraphAdjacencyList(GraphAdjacencyList.CREATE_MODE.DEFAULT_MODE);
        System.out.print("输入所求最短路径的起始点编号：");
        int start = scanner.nextSelectionByInt(0, graph.getNodeSize() - 1);
        System.out.print("输入所求最短路径的终止点编号：");
        int end;
        do {
            end = scanner.nextSelectionByInt(0, graph.getNodeSize() - 1);
        } while (end == start);
        graph.getShortestPathByFloyd(start, end);
    }
}

