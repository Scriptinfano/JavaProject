package practice1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.IllegalFormatCodePointException;
import java.util.Objects;

/**
 * 代表图中的边，用起点和终点表示
 *
 * @author Mingxiang
 */
class Edge implements Comparable<Edge> {
    private Integer head;
    private Integer tail;

    private Integer value;

    public Edge(Integer head, Integer tail, Integer value) {
        this.head = head;
        this.tail = tail;
        this.value = value;
    }

    public Integer getHead() {
        return head;
    }

    public void setHead(Integer head) {
        this.head = head;
    }

    public Integer getTail() {
        return tail;
    }

    public void setTail(Integer tail) {
        this.tail = tail;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    /**
     * 输出这条边的具体信息
     */
    public void output() {
        System.out.print("[起点序号：" + head + "，终点序号：" + tail + "，权值：" + value + "] ");
    }

    @Override
    public int compareTo(Edge o) {
        return this.value.compareTo(o.value);
    }
}

/**
 * 基于邻接矩阵存储的无向全连通图
 *
 * @author Mingxiang
 */
public class GraphBaseOnMatrix {

    /**
     * 邻接矩阵，方便根据两个点的编号找到边的信息
     */
    private Integer[][] adjacentMatrix;

    /**
     * 代表最小生成树，其中的每个边都是最小生成树的边
     */
    private ArrayList<Edge> mst;

    /**
     * 存储所有边信息的数组
     */
    private Edge[] edges;

    /**
     * 连通分量标记，数组中的每个元素代表图中的顶点，元素的序号就是顶点在邻接矩阵中的行号或列号，起初每个元素的值各不相同，在构造最小生成树时改变元素的值，直到所有元素都属于一个连通分量
     */
    private Integer[] marks;

    /**
     * 最小生成树的所有边的权值之和
     */
    private int totalValue = 0;

    private int pointSize = 0;

    /**
     * 构造函数
     */
    public GraphBaseOnMatrix() {
    }

    /**
     * 验证传入的邻接矩阵上三角部分的行主次序序列是否符合要求，若符合要求同时初始化内部代表点的个数的变量
     * @param arr 该整型数组依次按照行主次序存储各边权值，例如有五个点的全连通图（依次名为A,B,C,D,E），
     *            那么该数组中存储的权值依次是{AB,AC,AD,AE,BC,BD,BE,CD,CE,DE}
     *            其中AB意思是A点和B点之间的权值，确保此数组一定是正确的，否则无法构造出正确的邻接矩阵
     * @return boolean 返回true时说明数组的长度符合要求，同时计算出的正确的点的个数会赋给内部表示图的顶点个数的变量
     */
    private boolean verifyArr(Integer[] arr) {
        double pointSize = (1 + Math.sqrt(1 + 8 * arr.length)) / 2;
        if (pointSize - Math.floor(pointSize) == 0) {
            this.pointSize = (int) pointSize;
            return true;
        } else {
            return false;
        }
    }

    /**
     * 设置图的邻接矩阵
     *
     * @param values 代表图的邻接矩阵的上三角部分的行主次序的有序序列
     * @throws IllegalFormatCodePointException 非法的参数，表示该有序序列不符合要求
     */
    public void setGraph(Integer[] values)throws IllegalFormatCodePointException {
        if (!verifyArr(values)) {
            throw new IllegalArgumentException("传入的邻接矩阵上三角部分的行主次序的长度不符合要求");
        }
        //实例化存储边信息的数组，
        edges = new Edge[values.length];

        //实例化联通分量标记数组
        marks = new Integer[pointSize];
        for (int i = 0; i < pointSize; i++) {
            marks[i] = i;
        }

        //实例化邻接矩阵
        adjacentMatrix = new Integer[pointSize][pointSize];
        for (int i = 0; i < adjacentMatrix.length; i++) {
            for (int j = 0; j < adjacentMatrix[i].length; j++) {
                if (i == j)
                    adjacentMatrix[i][j] = 0;
                else if (j > i) {
                    int index = (int) (((-1 / (double) 2) * i * i + (pointSize - (3 / (double) 2)) * i + j - 1));
                    Integer theValue = values[index];
                    adjacentMatrix[i][j] = theValue;
                    edges[index] = new Edge(i, j, theValue);
                } else adjacentMatrix[i][j] = adjacentMatrix[j][i];
            }
        }

        //实例化存储最小生成树边的容器
        mst = new ArrayList<>();

    }

    /**
     * 输出该图所代表的邻接矩阵
     */
    public void outputMatrix() {
        if(adjacentMatrix==null){
            System.out.println("图未设定，请先使用setGraph接口设定图的邻接矩阵");
            return;
        }
        System.out.println("输出邻接矩阵");
        for (Integer[] arr : adjacentMatrix) {
            System.out.println(Arrays.toString(Arrays.stream(arr).mapToInt(Integer::valueOf).toArray()));
        }
        System.out.println("邻接矩阵输出完成");
    }

    /**
     * 使用克鲁斯卡尔算法生成最小生成树，将最小生成树的每个边放入内部的容器
     */
    public void generateMst() {
        if (mst.size() == 0) {
            Arrays.sort(edges);//对edges中的边按照边的权值大小进行排序
            for (int i = 0; i < edges.length; i++) {
                Integer head = edges[i].getHead();
                Integer tail = edges[i].getTail();
                if (!Objects.equals(marks[head], marks[tail])) {
                    //如果这条边在两个不同的连通分量上，则一定不会构成环
                    mst.add(edges[i]);//将这条符合条件的边放入最小生成树中
                    totalValue += edges[i].getValue();
                    int standardValue = marks[tail];//一定要提前保存该值，否则如果mark[tail]被修改，那么标准值（代表已有的连通分量）就会改变
                    for (int j = 0; j < adjacentMatrix.length; j++) {
                        //adjacentMatrix.length是点的个数
                        if (Objects.equals(marks[j], standardValue))
                            marks[j] = marks[head];
                    }
                }
            }
        } else {
            System.out.println("最小生成树已生成，请调用其他接口得到结果");
        }
    }

    /**
     * 输出最小生成树中的每一条边
     */
    public void outputMst() {
        if (mst.isEmpty()) {
            System.out.println("无法输出最小生成树的边，请先调用generateMst接口，然后再输出边");
            return;
        }
        System.out.println("输出最小生成树中的每一条边");
        for (Edge edge : mst) {
            edge.output();
            System.out.println();
        }
        System.out.println("输出完成");
    }

    /**
     * 输出最小的总权值
     */
    public void outputValue() {
        if (mst.isEmpty()) {
            System.out.println("无法输出最小权值之和，请先调用generateMst接口，然后再输出最小权值之和");
            return;
        }
        System.out.println("最小权值之和=" + totalValue);
        System.out.println("输出完成");
    }


}

