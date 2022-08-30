/**
 * 本程序可以计算任意阶数的二阶行列式的结果
 */
package mathmetic;

import java.util.ArrayList;
import java.util.List;

//N阶行列式的计算类
class DeterminantOfOrderN {
    /**
     * 存放n阶行列式的二维数组
     */
    private int[][] determinant;
    /**
     * n阶行列式的阶数
     */
    private int dimension;

    /**
     * 存放全排列的容器
     */
    private List<List<Integer>> res = new ArrayList<List<Integer>>();

    /**
     * 构造器
     *
     * @param determinant 表示n阶行列式的二维数组
     */
    public DeterminantOfOrderN(int[][] determinant) {
        this.determinant = determinant;
        this.dimension = determinant.length;
    }

    public DeterminantOfOrderN() {
    }

    /**
     * 算出一种排列的逆序数，目的是根据逆序数求得每种排列前的系数是正的还是负的
     *
     * @param arrJ 列下标的排列数组
     * @return int 返回排列数组的逆序数
     */
    private static int getT(List<Integer> arrJ) {
        int sum = 0;
        for (int i = 0; i < arrJ.size() - 1; i++) {
            for (int j = i + 1; j < arrJ.size(); j++) {
                if (arrJ.get(j).compareTo(arrJ.get(i)) < 0)
                    sum++;
            }
        }
        return sum;
    }

    /**
     * @return int 返回内部存储的代表n阶行列式的二维数组的引用
     */
    public int[][] getDeterminant() {
        return determinant;
    }

    public void setDeterminant(int[][] determinant) {
        this.determinant = determinant;
        this.dimension = determinant.length;
        if (res.size() != 0)
            res = new ArrayList<List<Integer>>();
    }

    /**
     * 返回n阶行列式的运算结果
     */
    public int result() {
        //n阶行列式要计算n的阶乘个多项式的和
        loadFullPermutationSet();
        int sum = 0;
        for (int count = 0; count < res.size(); count++) {
            int reverseOrderNum = getT(res.get(count));
            int coefficient = (reverseOrderNum % 2 == 0) ? 1 : -1;
            for (int i = 0; i < this.dimension; i++) {
                coefficient *= determinant[i][res.get(count).get(i)];
            }
            sum += coefficient;
        }
        return sum;
    }

    /**
     * 这是一个测试函数，显示已经装载好了的全排列集合
     */
    private void showFullPermutationSet() {
        for (List<Integer> array : res) {
            for (int j = 0; j < array.size(); j++) {
                System.out.print(array.get(j) + " ");
            }
            System.out.println();
        }
    }

    /**
     * 该类内部有一个存储n阶一元排列的全排列的容器，这个函数将利用递归算法得出所有排列结果，并将排列结果全部装载在内部
     * 的容器中
     */
    private void loadFullPermutationSet() {
        int[] nums = new int[dimension];//一元顺序排列，如:1,2,3...
        for (int i = 0; i < dimension; i++) {
            nums[i] = i;
        }
        //以下调用递归算法，在调用的过程中会装载全排列容器
        permuteBackTrack(nums, new ArrayList<Integer>());//使用回溯算法求出一元顺序排列的全排列

    }

    private void permuteBackTrack(int[] nums, ArrayList<Integer> current) {
        if (nums.length == current.size()) {
            res.add(new ArrayList<Integer>(current));//将运算结果拷贝一份然后存入内部的容器
            return;//递归回溯到上一级调用
        }
        for (int i = 0; i < nums.length; i++) {
            if (current.contains(nums[i])) continue;
            current.add(nums[i]);
            permuteBackTrack(nums, current);
            current.remove(current.size() - 1);
        }

    }
}

public class Determinant {
    public static void main(String[] args) {
        int[][] thirdDeterminant = {
                {1, 2000, 2001, 2002},
                {0, -1, 0, 2003},
                {0, 0, -1, 2004},
                {0, 0, 0, 2005}
        };
        DeterminantOfOrderN determinant = new DeterminantOfOrderN(thirdDeterminant);
        int result = determinant.result();
        System.out.println(result);
    }
}