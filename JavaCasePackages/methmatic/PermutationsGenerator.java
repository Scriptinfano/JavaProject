//该程序演示使用递归法生成数列的全排列组合

package methmatic;

import java.util.ArrayList;

/**
 * 全排列生成器类
 */
public class PermutationsGenerator {
    private ArrayList<ArrayList<Integer>> permutationsContainer;
    private Integer[] originalArray;

    private int counter = 0;

    public PermutationsGenerator(int dimension) {
        permutationsContainer = new ArrayList<>();
        originalArray = new Integer[dimension];
        for (int i = 0; i < dimension; i++) {
            originalArray[i] = i + 1;
        }
    }

    public void run() {
        permutationsBackTrack(originalArray, new ArrayList<>());
    }

    private void permutationsBackTrack(Integer[] nums, ArrayList<Integer> current) {
        if (current.size() == nums.length) {
            permutationsContainer.add(new ArrayList<>(current));
            counter++;
            return;
        }
        for (Integer num : nums) {
            if (current.contains(num)) continue;
            current.add(num);
            permutationsBackTrack(nums, current);
            current.remove(current.size() - 1);
        }
    }

    public int[][] getPermutations() {
        int[][] result = new int[permutationsContainer.size()][];
        for (int i = 0; i < permutationsContainer.size(); i++) {
            result[i] = new int[permutationsContainer.get(i).size()];
            for (int j = 0; j < permutationsContainer.get(j).size(); j++) {
                result[i][j] = permutationsContainer.get(i).get(j);
            }
        }
        return result;
    }

    public void showResult() {
        for (ArrayList<Integer> integers : permutationsContainer) {
            for (int j = 0; j < permutationsContainer.get(j).size(); j++) {

                System.out.print(integers.get(j) + " ");
            }
            System.out.println();
        }

    }

    public void reset() {
        permutationsContainer = new ArrayList<>();
        originalArray = new Integer[0];
        counter = 0;

    }

    public int getCounter() {
        return counter;
    }
}


