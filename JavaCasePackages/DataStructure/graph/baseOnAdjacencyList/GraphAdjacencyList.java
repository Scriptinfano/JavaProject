package DataStructure.graph.baseOnAdjacencyList;

import myScannerAndPrinter.MyScanner;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 基于邻接表表示法的图，每个顶点放在数组容器中，每个顶点关联一个边链表，边链表中存放关联于该顶点的所有边的信息
 * 邻接表中弧节点的数量就是边的总数
 */
public class GraphAdjacencyList {

    private static final PrintStream printer = System.out;
    private static final MyScanner scanner = new MyScanner();
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

    private FloydSolver solver;

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

    /**
     * 与用户交互录入各个顶点与边的信息
     */
    private void create_user() {
        System.out.println("开始录入每个顶点的信息...");
        System.out.println("输入图中有多少个顶点：");
        //代表图中总共有多少个顶点
        int nodeSize = scanner.nextInt();
        graphList = new ArrayList<>(nodeSize);
        hasVisited = new boolean[nodeSize];
        System.out.println("开始录入图中每个顶点的信息...（按照顶点在图中的顺序输入）");
        for (int i = 0; i < nodeSize; i++) {
            System.out.println("输入第" + (i + 1) + "个顶点的权值（任意输入）：");
            graphList.add(new HeadNode(i + 1, scanner.nextLineNoEmpty()));
        }

        System.out.println("开始录入每个顶点所关联的所有边的信息...");
        for (int i = 0; i < nodeSize; i++) {
            System.out.println("请输入第" + (i + 1) + "个顶点的出度：");
            int theOutDegree = scanner.nextInt();
            ArrayList<Integer> hasRepeat = new ArrayList<>();
            for (int j = 0; j < theOutDegree; j++) {
                System.out.println("请输入该顶点所关联的第" + (j + 1) + "条有向边指向了图中的第几个顶点：");
                int theIndex = i + 1;
                //这个循环的意思是该顶点的邻接点不能是自己，必须输入其他邻接点
                while (theIndex == i + 1 || hasRepeat.contains(theIndex)) {
                    theIndex = scanner.nextSelectionByInt(1, nodeSize);
                    if (theIndex == i + 1)
                        System.out.println("该顶点所关联的边不能指向该顶点自身且顶点所关联的有向线段必须各自指向不同的顶点，请重新输入所指向顶点是图中的第几个顶点：");
                }
                System.out.println("请输入该边的权值：");
                graphList.get(i).addLineNode(graphList.get(theIndex - 1), scanner.nextLineNoEmpty());
                hasRepeat.add(theIndex);
            }
        }

        System.out.println("录入完毕");
        MyScanner.pause();

    }

    /**
     * 使用弗洛伊德算法，求解指定起始点到终止点的最短路径长度是多少，只有第一次调用接口会使用
     * 算法求解，在之后的调用后就直接由弗洛伊德算法求解出的表来给出答案
     *
     * @param startIndex 起始点的编号
     * @param endIndex   终止点的编号
     * @return int 起始点和终止点之间的最短路径长度
     */
    public int getShortestPathByFloyd(int startIndex, int endIndex) {
        if (solver == null) {
            solver = new FloydSolver();
            solver.set(graphList);
            solver.run();
        }
        return solver.getShortestPathLength(startIndex, endIndex);
    }


}

