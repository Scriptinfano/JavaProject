package graph.baseOnAdjacencyList;

import java.util.ArrayList;

/**
 * 弗洛伊德求解器，仅构造该对象是无法使用该对象的，需要调用set接口设置内部的邻接表
 *
 * @author Mingxiang
 */
public class FloydSolver {

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
