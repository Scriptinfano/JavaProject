package graph.adjacencyList;

import myscan.ScannerPlus;

import java.io.PrintStream;
import java.util.*;

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
        return lineList.get(index - 1).getAdjacency();
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
 * 迪杰斯特拉算法求解器，内含该算法所需的三个数组
 *
 * @author Mingxiang
 */
class DijkstraSolver {
    private final boolean[] hasVisited;
    private final Integer[] distance;
    private final HeadNode[] parent;
    private final HeadNode startNode;
    private final HeadNode endNode;
    private ArrayList<HeadNode> pathCollection;//所有进入该集合的顶点，都表示该顶点已经算在最短路径中了

    /**
     * dijkstra算法解算器的构造函数
     *
     * @param size 该算法所要求解的图中有几个顶点
     */
    public DijkstraSolver(int size, HeadNode theStartNode, HeadNode theEndNode) {
        pathCollection = new ArrayList<>();
        hasVisited = new boolean[size];//java的boolean数组默认初始化时，所有元素均为false
        distance = new Integer[size];
        parent = new HeadNode[size];
        startNode = theStartNode;
        endNode = theEndNode;

        //更新起点的信息
        update(startNode, 0, null);
        //将起点纳入路径
        pathCollection.add(startNode);
        hasVisited[startNode.getIndex()] = true;

    }

    public void run(ArrayList<HeadNode> headNodeList) {
        //FIXME 该算法仍然有bug，待修改
        HeadNode currentNode = startNode;

        int totalDistance = 0;

        while (currentNode != endNode) {

            //依次检索所有不在最短路径集合中的且与当前节点相连的顶点，并更新这些节点在三个数组中的信息
            for (int i = 0; i < currentNode.getOutDegree(); i++) {
                HeadNode theAdjacencyNode = currentNode.getAdjacencyNode(i + 1);//当前正在处理的邻接点
                //检查该邻接点是否已被纳入最短路径集合
                if (!pathCollection.contains(theAdjacencyNode)) {
                    int theDistance = currentNode.getLineDistanceByOrder(i);//当前顶点与当前邻接点的距离
                    if (distance[theAdjacencyNode.getIndex()] == null || theDistance + totalDistance < distance[theAdjacencyNode.getIndex()])
                        update(theAdjacencyNode, theDistance + totalDistance, currentNode);
                    else update(theAdjacencyNode, distance[theAdjacencyNode.getIndex()], currentNode);
                }
            }
            int minIndex = scan();//未选顶点中的distance值最小的顶点的编号
            hasVisited[minIndex] = true;//minIndex所对应的顶点是即将添加到最短路径的顶点，所以将hasVisited的对应位置置true
            totalDistance += currentNode.getLineDistanceByIndex(minIndex);
            currentNode = headNodeList.get(minIndex);
            pathCollection.add(currentNode);

        }

    }

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
            if (!hasVisited[i]) {
                if (distance[i] != null) {
                    sorter.put(i, distance[i]);
                }
            }
        }
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(sorter.entrySet());

        list.sort(new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return Integer.compare(o1.getValue() - o2.getValue(), 0);
            }
        });

        return list.get(0).getKey();

    }

    /**
     * 返回最短路径集合
     *
     * @return {@link ArrayList}<{@link HeadNode}> 最短路径集合，其中HeadNode的顺序就是最短路径
     */
    public ArrayList<HeadNode> getPathCollection() {
        return pathCollection;
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
            node.output();
            hasVisited[index] = true;
            if (node.emptyLineList()) return;
            for (int i = 0; i < node.getOutDegree(); i++) {
                HeadNode theNode = node.getAdjacencyNode(i + 1);
                traverseRecursion(theNode, theNode.getIndex());
            }
        }
    }

    /**
     * 使用迪杰斯特拉算法寻找最短路径
     *
     * @param theStartNode 路径的起始节点
     * @param theEndNode   路径的结束节点
     * @return {@link ArrayList}<{@link HeadNode}> 返回一个由有序顶点组成的ArrayList容器，顶点的顺序就是最短的路径
     */
    public ArrayList<HeadNode> getShortestPathByDijkstra(HeadNode theStartNode, HeadNode theEndNode) {
        DijkstraSolver dijkstraSolver = new DijkstraSolver(graphList.size(), theStartNode, theEndNode);
        dijkstraSolver.run(graphList);
        return dijkstraSolver.getPathCollection();
    }

    /**
     * 图的一次广度优先遍历，仅遍历一个连通分量
     */
    private void breadthTraverseOnce(HeadNode theNode) {
        Queue<HeadNode> theQueue = new LinkedList<>();
        if (!hasVisited[theNode.getIndex()]) {
            theNode.output();
            hasVisited[theNode.getIndex()] = true;
            theQueue.add(theNode);
        } else return;
        //队列不为空则继续循环
        while (!theQueue.isEmpty()) {
            HeadNode currentNode = theQueue.poll();
            //依次检查currentNode的邻接点是否被访问，如果未被访问则输出并将其入队，其将在下一次循环中出队并检查它的所有邻接点
            for (int i = 0; i < currentNode.getOutDegree(); i++) {
                //循环次数是当前顶点的出度，也就是检查当前顶点的所有邻接点
                HeadNode adjacencyNode = currentNode.getAdjacencyNode(i + 1);
                if (!hasVisited[adjacencyNode.getIndex()]) {
                    //若邻接点在之前未被访问过，则输出其权值，并将其入队
                    adjacencyNode.output();
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
}

/**
 * @author Mingxiang
 * @see GraphAdjacencyList GraphAdjacencyList的测试类
 */
class TestGraphList {
    private static final ScannerPlus scanner = new ScannerPlus();
    private static final PrintStream printer = System.out;

    public static void main(String[] args) {
        GraphAdjacencyList graph = new GraphAdjacencyList(GraphAdjacencyList.CREATE_MODE.DEFAULT_MODE);
        System.out.print("图的深度优先遍历：");
        graph.depthTraverse();
        printer.println();
        System.out.print("图的广度优先遍历：");
        graph.breadthTraverse();

        printer.println();

        ArrayList<HeadNode> pathList = graph.getShortestPathByDijkstra(graph.getHeadNode(0), graph.getHeadNode(8));
        for (HeadNode theNode : pathList)
            printer.print(theNode.getIndex() + " ");
        printer.println();

    }
}
