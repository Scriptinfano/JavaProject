package methmatic;

import arrayutil.ArrayUtil;

import java.util.ArrayList;
import java.util.Arrays;

class Something {
}

/**
 * 验证正态分布
 *
 * @author Mingxiang
 */
public class NormalDistribution {
    public static void main(String[] args) {
        ArrayList<ArrayList<Something>> pipes = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            pipes.add(new ArrayList<>());
        }
        int[] arr = {-1, 0, 1};
        for (int i = 0; i < 100000; i++) {
            Integer[] randoms = ArrayUtil.randomIntegerArrayWithDuplicate(arr, 15);
            int count = Arrays.stream(randoms).reduce(0, Integer::sum);
            int preset = count + 15;
            if (preset != 0)
                pipes.get(preset - 1).add(new Something());
            else pipes.get(preset).add(new Something());
        }
        pipes.forEach(a -> System.out.println(a.size()));
    }
}
