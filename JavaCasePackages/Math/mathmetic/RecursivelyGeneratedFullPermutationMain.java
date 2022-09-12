//该程序演示使用递归法生成数列的全排列组合

package mathmetic;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 全排列生成器类
 */
class PermutationsGenerator {
    private ArrayList<ArrayList<Integer>> permutationsContainer;
    private Integer[] originalArray;

    private int counter = 0;

    public PermutationsGenerator(int dimension) {
        permutationsContainer = new ArrayList<ArrayList<Integer>>();
        originalArray = new Integer[dimension];
        for (int i = 0; i < dimension; i++) {
            originalArray[i] = i + 1;
        }
    }

    public void run() {
        permutationsBackTrack(originalArray, new ArrayList<Integer>());
    }

    private void permutationsBackTrack(Integer[] nums, ArrayList<Integer> current) {
        if (current.size() == nums.length) {
            permutationsContainer.add(new ArrayList<Integer>(current));
            counter++;
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (current.contains(nums[i])) continue;
            current.add(nums[i]);
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
        for (int i = 0; i < permutationsContainer.size(); i++) {
            for (int j = 0; j < permutationsContainer.get(j).size(); j++) {

                System.out.print(permutationsContainer.get(i).get(j) + " ");
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


public class RecursivelyGeneratedFullPermutationMain {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("输入一元排列的尾数：");
        int dimension = scanner.nextInt();
        PermutationsGenerator generator = new PermutationsGenerator(dimension);
        generator.run();
        int[][] result = generator.getPermutations();
        System.out.println("一共生成" + MathUtil.factorial(dimension) + "种排列");
        generator.showResult();
        System.out.println("递归调用次数=" + generator.getCounter());
    }
}
