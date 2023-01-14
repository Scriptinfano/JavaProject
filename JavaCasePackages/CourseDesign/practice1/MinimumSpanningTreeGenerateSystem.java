package CourseDesign.practice1;

import viewManagerPack.ViewManager;

import java.util.Arrays;

/**
 * 最小生成树生成集成交互系统
 *
 * @author Mingxiang
 */
public class MinimumSpanningTreeGenerateSystem extends ViewManager {

    /**
     * 基于邻接矩阵存储的无向全联通图，该交互系统维护管理该变量
     */
    private GraphBaseOnMatrix graph;

    /**
     * 显示主菜单
     */
    @Override
    protected void showMenu() {
        printer.println("**********欢迎使用最小生成树集成交互系统************");
        printer.println("*         1、设定新图                          *");
        printer.println("*         2、查看当前图的邻接矩阵                *");
        printer.println("*         3、生成最小生成树并输出其边和最小权值之和  *");
        printer.println("*         4、退出程序                          *");
        printer.println("**********************************************");
    }

    /**
     * 运行系统，首先调用此接口启动交互
     */
    @Override
    public void run() {
        while (true) {
            String choice;
            showMenu();
            System.out.print("请输入你的选择：");
            choice = scanner.nextSelectionByString(1, 4);
            switch (choice) {
                case "1" -> setNewGraph();
                case "2" -> outputCurrentMatrix();
                case "3" -> outputTreeAndValue();
                case "4" -> exitProgram();
                default -> System.out.println("你的输入不合法，请重新输入");
            }
        }

    }

    /**
     * 设定新图
     */
    private void setNewGraph() {
        if (graph == null) {
            int[] arr;
            while (true) {
                printer.print("请输入该图的邻接矩阵上三角部分的元素个数：");
                int size = scanner.nextInt();
                //double arrSize = scanner.nextWithLimit(false, 0, "数组的大小不能小于0");//使用该接口直接避免用户输入小于0的数，不使用异常捕捉机制也可以
                printer.println("请输入该图的邻接矩阵上三角部分的行主次序（输入时每输入一个数字键入一个回车）");
                try {
                    arr = scanner.nextIntArray(size, false, true);
                } catch (IllegalArgumentException e) {
                    //元素个数的输入有误，要求重新输入
                    System.out.println(e.getMessage());
                    continue;
                }
                break;
            }
            graph = new GraphBaseOnMatrix();
            graph.setGraph(Arrays.stream(arr).boxed().toArray(Integer[]::new));
        } else {
            printer.print("图的邻接矩阵已设定，是否重新设定（输入0表示不再设定，输入1表示继续设定：）");
            int choice = scanner.nextSelectionByInt(0, 1);//要求用户只能输入两个数字表示确认还是否定
            if (choice == 1) {
                graph = null;//解除图当前的引用绑定，重新设定
                setNewGraph();//重新调用本函数设置新图
            }
        }
    }

    /**
     * 输出当前图的邻接矩阵
     */
    private void outputCurrentMatrix() {
        if (graph == null)
            printer.println("图未设定，请先设定再执行其他操作");
        else {
            graph.outputMatrix();
        }
    }

    /**
     * 输出最小生成树和最小的代价之和
     */
    private void outputTreeAndValue() {
        if (graph == null)
            printer.println("图未设定，请先设定再执行其他操作");
        else {
            graph.generateMst();
            graph.outputMst();
            graph.outputValue();
        }
    }

    /**
     * 强制手动设定新图
     *
     * @param arr 新图的邻接矩阵的上三角部分的行主次序序列数组
     */
    public void setNewGraphDirect(int[] arr) {
        graph = new GraphBaseOnMatrix();
        graph.setGraph(Arrays.stream(arr).boxed().toArray(Integer[]::new));
    }

    /**
     * 强制手动设定新图
     *
     * @param arr 新图的邻接矩阵的上三角部分的行主次序序列数组
     */
    public void setNewGraphDirect(Integer[] arr) {
        graph = new GraphBaseOnMatrix();
        graph.setGraph(arr);
    }


}


class TestSystem {
    public static void main(String[] args) {
        interactTest();
    }

    /**
     * 默认测试
     */
    public static void defaultTest() {
        Integer[] edges = new Integer[]{
                60, 63, 56, 72, 48, 84, 32, 50, 47, 97
        };
        GraphBaseOnMatrix graph = new GraphBaseOnMatrix();
        graph.setGraph(edges);
        graph.generateMst();
        graph.outputMst();
        graph.outputValue();
    }

    /**
     * 交互测试
     */
    public static void interactTest() {
        Integer[] edges = new Integer[]{
                60, 63, 56, 72, 48, 84, 32, 50, 47, 97
        };//参考此数组输入数据，此数据表示的图在本文件夹中
        MinimumSpanningTreeGenerateSystem system = new MinimumSpanningTreeGenerateSystem();
        system.run();
    }
}
