package practice1;

import myScannerAndPrinter.ScannerPlus;

/**
 * 最小生成树生成集成交互系统
 *
 * @author Mingxiang
 */
public class MinimumSpanningTreeGenerateSystem {
    private final ScannerPlus scanner = new ScannerPlus();
    private GraphBaseOnMatrix graph;

    /**
     * 显示主菜单
     */
    public void showMenu() {
        System.out.println("**********欢迎使用最小生成树集成交互系统************");
        System.out.println("*         1、设定新图                          *");
        System.out.println("*         2、查看当前图的邻接矩阵                *");
        System.out.println("*         3、生成最小生成树并输出其边和最小权值之和  *");
        System.out.println("*         4、退出程序                          *");
        System.out.println("**********************************************");
    }

    /**
     * 运行系统，首先调用此接口启动交互
     */
    public void run() {
        while (true) {
            String choice;
            showMenu();
            //TODO 在此处应该显示停车场中的车辆数量
            System.out.print("请输入你的选择：");
            choice = scanner.nextSelectionByString(1, 4);
            switch (choice) {
                case "1" -> ();
                case "2" -> ();
                case "3" -> ();
                case "4" -> ();
                default -> System.out.println("你的输入不合法，请重新输入");
            }
        }

    }

    public void setNewGraph() {

    }

    public void outPutCurrentMatrix() {

    }


}


class TestSystem {
    public static void main(String[] args) {
        Integer[] edges = new Integer[]{
                60, 63, 56, 72, 48, 84, 32, 50, 47, 97
        };
        GraphBaseOnMatrix graph = new GraphBaseOnMatrix(edges);//实例化图对象
        graph.outputMatrix();//输出生成的邻接矩阵
        graph.generateMst();//生成最小生成树
        graph.outputMst();//输出最小生成树中的每一条边
        graph.outputValue();//输出最小权值之和

    }
}
