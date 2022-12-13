package graph.baseOnMatrix;

import java.util.ArrayList;
import java.util.Arrays;
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
        System.out.print("[起点序号：" + head + "，终点序号：" + tail + "，权值：" + value + "]  ");
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
    private final Integer[][] adjacentMatrix;

    /**
     * 代表最小生成树，其中的每个边都是最小生成树的边
     */
    private final ArrayList<Edge> mst;

    /**
     * 存储所有边信息的数组
     */
    private final Edge[] edges;

    /**
     * 连通分量标记，数组中的每个元素代表图中的顶点，元素的序号就是顶点在邻接矩阵中的行号或列号，起初每个元素的值各不相同，在构造最小生成树时改变元素的值，直到所有元素都属于一个连通分量
     */
    private final Integer[] marks;

    /**
     * 构造函数
     *
     * @param values 该整型数组依次按照行主次序存储各边权值，例如有五个点的全连通图（依次名为A,B,C,D,E），那么该数组中存储的权值依次是{AB,AC,AD,AE,BC,BD,BE,CD,CE,DE}
     *               其中AB意思是A点和B点之间的权值，确保此数组一定是正确的，否则无法构造出正确的邻接矩阵
     */
    public GraphBaseOnMatrix(Integer[] values) {
        edges = new Edge[values.length];

        int pointSize = (1 + (int) Math.sqrt(1 + 8 * values.length)) / 2;//根据公式计算出点的个数
        marks = new Integer[pointSize];
        for (int i = 0; i < pointSize; i++) {
            marks[i] = i;
        }

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
        mst = new ArrayList<>();

    }

    public static void main(String[] args) {
        Integer[] edges = new Integer[]{
                60, 63, 56, 72, 48, 84, 32, 50, 47, 97
        };
        GraphBaseOnMatrix graph = new GraphBaseOnMatrix(edges);
        graph.generateMst();
        graph.outputMst();
    }

    /**
     * 输出该图所代表的邻接矩阵
     */
    public void outputMatrix() {
        System.out.println("输出邻接矩阵");
        for (Integer[] arr : adjacentMatrix) {
            System.out.println(Arrays.toString(Arrays.stream(arr).mapToInt(Integer::valueOf).toArray()));
        }
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
                    for (int j = 0; j < edges.length; j++) {
                        if (Objects.equals(marks[j], marks[tail]))
                            marks[j] = marks[head];
                    }
                }
            }
        }
    }

    /**
     * 输出最小生成树中的每一条边
     */
    public void outputMst() {
        for (int i = 0; i < edges.length; i++) {
            mst.get(i).output();
        }
        System.out.println("");
    }
}
