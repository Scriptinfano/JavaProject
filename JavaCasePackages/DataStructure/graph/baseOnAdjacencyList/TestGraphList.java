package DataStructure.graph.baseOnAdjacencyList;

import myScannerAndPrinter.ScannerPlus;

import java.io.PrintStream;

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
