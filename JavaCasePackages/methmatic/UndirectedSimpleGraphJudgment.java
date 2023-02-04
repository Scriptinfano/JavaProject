package methmatic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 无向简单图的判断
 * 原理：Havel定理
 *
 * @author Mingxiang
 */
public class UndirectedSimpleGraphJudgment {
    public static JudgeResultMark judge(Integer[] sequence) {
        if (MathUtil.sum(sequence) % 2 != 0 || !count(sequence))
            return JudgeResultMark.neither;
        ArrayList<Integer> list = new ArrayList<>(Arrays.stream(sequence).toList());
        while (!allZero(list)) {
            //循环直到所有元素为0为止
            list.sort((o1, o2) -> o2 - o1);
            int k = list.get(0);
            list.remove(0);
            for (int i = 0; i < k; i++)
                list.set(i, list.get(i) - 1);
            if (hasNegative(list)) return JudgeResultMark.undirected;//如果在这个过程中间出现了负数则说明仅仅是无向图，不是简单图
        }
        return JudgeResultMark.simpleUndirected;
    }

    /**
     * 统计奇度顶点个数
     *
     * @param sequence 序列
     * @return boolean 如果奇度顶点个数为偶数则返回true，否则返回false
     */
    private static boolean count(Integer[] sequence) {
        int result = 0;
        for (Integer value : sequence) {
            if (value % 2 != 0)
                result++;
        }
        return result % 2 == 0;
    }

    private static boolean hasNegative(List<Integer> list) {
        for (Integer value : list)
            if (value < 0) return true;
        return false;
    }

    /**
     * 如果元素全是0，则返回true
     *
     * @param list 列表
     * @return boolean
     */
    private static boolean allZero(List<Integer> list) {
        for (Integer value : list)
            if (value != 0)
                return false;
        return true;
    }

    public enum JudgeResultMark {
        neither,//表示不是无向图
        undirected,//表示仅为无向图
        simpleUndirected//表示是无向简单图

    }

}

class TestJudge {
    public static void main(String[] args) {
        Integer[] arr = {1, 1, 1, 2, 3};
        UndirectedSimpleGraphJudgment.JudgeResultMark mark = UndirectedSimpleGraphJudgment.judge(arr);
        if (mark == UndirectedSimpleGraphJudgment.JudgeResultMark.neither)
            System.out.println("两者都不是");
        else if (mark == UndirectedSimpleGraphJudgment.JudgeResultMark.undirected)
            System.out.println("仅仅是无向图");
        else if (mark == UndirectedSimpleGraphJudgment.JudgeResultMark.simpleUndirected)
            System.out.println("是无向简单图");
    }
}
