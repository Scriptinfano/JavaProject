package DataStructure.graph.baseOnAdjacencyList;

import java.util.*;

public class GraphAdjacencyListPlus {
    //TODO 完成该类的设计
    private HashMap<Character, List<Character>> graph;

    /**
     * 当图上各边权值都相同时，广度优先遍历可以解决单源点最短路径问题
     *
     * @param start 代表单源点
     * @return {@link HashMap}<{@link Character},{@link Integer}> 返回存储键值对的哈希表，键代表每一个字符，值是start节点到该字符的最短距离
     */
    public HashMap<Character, Integer> shortestPathOfSinglePoint(char start) {
        Queue<Character> queue = new LinkedList<>();
        Set<Character> set = new HashSet<>();
        HashMap<Character, Integer> distance = new HashMap<>();

        // 首先访问起点start
        queue.add(start);
        // 首先是第0层到起点的距离为0
        int levelDistance = 0;

        while (!queue.isEmpty()) {
            // levelSize是指当前当前层有多少个顶点，当前层所有顶点到起点的距离都是一样的。
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                char vertex = queue.poll();
                // 访问vertex顶点
                distance.put(vertex, levelDistance);
                set.add(vertex);
                // 将vertex顶点的下一层没有访问过的顶点加入队列
                for (char nextVertex : graph.get(vertex)) {
                    if (!set.contains(nextVertex)) {
                        queue.add(nextVertex);
                    }
                }
            }
            // 访问完上一层之后，下一层到起点的距离应该加1
            levelDistance += 1;
        }
        return distance;

    }
}
