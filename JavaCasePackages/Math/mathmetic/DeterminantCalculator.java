/**
 * 本程序可以计算任意阶数的二阶行列式的结果
 */
package mathmetic;

import java.util.ArrayList;
import java.util.List;

/**
 * 行列式计算器,按照行列式定义公式进行计算，n阶行列式是n!个多项式的代数和，其中每一个多项式
 * 是位于不同行和不同列的n个元素的乘积，把这n个元素以行指标为自然序列排好位置，当列指标构成
 * 的排列是偶排列时，该项带正号；是奇排列时，该项带负号
 *
 * @author Mingxiang
 * @date 2022/10/04
 */
public class DeterminantCalculator {
    private int[][] determinant;//存放n阶行列式的二维数组
    private int dimension;//n阶行列式的阶数

    private List<List<Integer>> res = new ArrayList<List<Integer>>();//存放n元串的全排列的容器

    /**
     * 构造器
     *
     * @param determinant 表示n阶行列式的二维数组
     */
    public DeterminantCalculator(int[][] determinant) {
        this.determinant = determinant;
        this.dimension = determinant.length;
    }

    /**
     * 无参构造器，使用该构造器构造对象时，一定要使用setDeterminant()设置内部的行列式
     */
    public DeterminantCalculator() {
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

    /**
     * 重设内部存储的表示n阶行列式的二维数组
     *
     * @param determinant 传入的n阶行列式
     */
    public void setDeterminant(int[][] determinant) {
        this.determinant = determinant;
        this.dimension = determinant.length;
        if (res.size() != 0)
            res = new ArrayList<List<Integer>>();
    }

    /**
     * 返回n阶行列式的运算结果
     */
    public int run() {
        FullArrangementGenerator generator = new FullArrangementGenerator();
        generator.setDimension(dimension);
        generator.run(FullArrangementGenerator.mode.NORMAL);
        res = generator.getArrangementList();
        int sum = 0;
        for (int count = 0; count < res.size(); count++) {
            int reverseOrderNum = getT(res.get(count));//算出逆序数
            int coefficient = (reverseOrderNum % 2 == 0) ? 1 : -1;//当列标符合偶排列时，多项式的符号是正的，否则是负的
            for (int i = 0; i < this.dimension; i++) {
                coefficient *= determinant[i][res.get(count).get(i)];
            }
            sum += coefficient;
        }
        return sum;
    }

}

class Tester {
    public static void main(String[] args) {
        int[][] thirdDeterminant = {
                {1, 2000, 2001, 2002},
                {0, -1, 0, 2003},
                {0, 0, -1, 2004},
                {0, 0, 0, 2005}
        };
        DeterminantCalculator determinant = new DeterminantCalculator(thirdDeterminant);
        int result = determinant.run();
        System.out.println(result);
    }
}