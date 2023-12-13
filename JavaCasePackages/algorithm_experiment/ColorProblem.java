package algorithm_experiment;

import java.util.ArrayList;
import java.util.HashMap;

public class ColorProblem {
    private static final int colorNum = 3;//最大颜色数
    private static final HashMap<Integer, Node> nodes = new HashMap<>();

    public static void main(String[] args) {
        createGraph();
        coloring();
        for (int i = 1; i <= nodes.size(); i++) {
            System.out.println(nodes.get(i));
        }
    }

    private static void createGraph() {
        nodes.put(1, new Node(1, new int[]{2, 3}));
        nodes.put(2, new Node(2, new int[]{1, 3, 4}));
        nodes.put(3, new Node(3, new int[]{1, 2, 5}));
        nodes.put(4, new Node(4, new int[]{2, 5}));
        nodes.put(5, new Node(5, new int[]{2, 3, 4}));
    }

    private static void coloring() {
        int k = 1;//从第一个节点开始处理
        while (k >= 1) {
            //k是顶点的编号，范围[1,nodes.size()]
            nodes.get(k).color++;//所有颜色向量都初始化为0，此处+1开始染第一个颜色
            while (nodes.get(k).color <= colorNum && !ok(k))
                nodes.get(k).color++;//只要遍历的颜色种类数没有超出规定且当前染色不合法，则更换下一种颜色
            if (nodes.get(k).color <= colorNum) {
                //当前染色合法，然后判断是否处理到了最后一个节点
                if (k == nodes.size())
                    break;//处理到了最后一个节点则退出
                else k++;//处理下一个节点
            } else {
                //遍历了所有颜色之后，染色依然不合法，则清空当前染色，回溯到上一个节点重新染色
                nodes.get(k).color = 0;//清空当前染色
                k--;//回溯到上一个节点
            }
        }
    }

    private static boolean ok(int index) {
        for (int i = 0; i < nodes.get(index).lines.size(); i++) {
            if (nodes.get(nodes.get(index).lines.get(i)).color == nodes.get(index).color)
                return false;
        }
        return true;
    }

    private static class Node {
        public int index;
        public ArrayList<Integer> lines = new ArrayList<>();

        public int color;

        public Node(int code, int[] indexs) {
            this.index = code;
            for (int index : indexs) {
                lines.add(index);
            }
        }

        @Override
        public String toString() {
            return "编号为" + index + "的染色为" + color;
        }
    }
}
