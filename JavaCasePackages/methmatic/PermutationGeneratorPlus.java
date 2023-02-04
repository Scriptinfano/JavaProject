package methmatic;

import exceptions.RequiredActionNotExcuteException;
import exceptions.UnRunException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 全排列生成器
 *
 * @author Mingxiang
 */
public class PermutationGeneratorPlus {
    private final List<List<Integer>> arrangementList = new ArrayList<>();//存放n元串的全排列的容器
    private int[][] arrangementArray;

    private Integer dimension = null;

    private Integer selectSize = null;

    private boolean haveBeenRun = false;

    private int counter = 0;//记录全排列生成的个数

    /**
     * Sets dimension.
     *
     * @param dimension the dimension
     */
    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    /**
     * Sets select size.
     *
     * @param selectSize the select size
     */
    public void setSelectSize(int selectSize) {
        this.selectSize = selectSize;
    }

    /**
     * Gets counter.
     *
     * @return the counter
     */
    public int getCounter() {
        return counter;
    }

    /**
     * The enum Mode.
     */
    enum mode//代表运行模式
    {
        /**
         * Normal mode.
         */
        NORMAL,//代表常规模式：输出n元串的全排列
        /**
         * Select mode.
         */
        SELECT//找出从自然数1,2,3...n中任取r个数的所有组合
    }

    /**
     * 以特定模式运行该全排列生成器，运行后请调用重写的toString输出排序结果
     *
     * @param theMode 指定的模式
     */
    public void run(mode theMode) {
        switch (theMode) {
            case NORMAL -> {
                if (dimension == null)
                    throw new RequiredActionNotExcuteException("未调用setDimension()设置dimension属性，无法以NORMAL模式运行");
                run_NORMAL();
            }
            case SELECT -> {
                if (dimension == null || selectSize == null)
                    throw new RequiredActionNotExcuteException("未调用setSelectSize()设置dimension属性或selectSize属性，无法以SELECT模式运行");
                run_SELECT();
            }
        }
        haveBeenRun = true;
    }


    /**
     * 以NORMAL模式运行
     */
    private void run_NORMAL() {
        int[] nums = new int[dimension];
        for (int i = 0; i < dimension; i++) {
            nums[i] = i + 1;
        }
        permuteBackTrace(nums, new ArrayList<>());//首次调用递归函数
        arrangementArray = toArray();
    }

    /**
     * 递归函数求全排列
     *
     * @param nums    初始的一元顺序排列
     * @param current 当前排列
     */
    private void permuteBackTrace(int[] nums, ArrayList<Integer> current) {
        if (nums.length == current.size()) {
            arrangementList.add(new ArrayList<>(current));
            return;
        }
        for (int num : nums) {
            if (current.contains(num)) continue;
            current.add(num);
            permuteBackTrace(nums, current);
            current.remove(current.size() - 1);
        }
    }

    /**
     * 以SELECT模式运行
     */
    private void run_SELECT() {
        //TODO 找出从自然数1,2,3,...,n中任取r个数的所有组合
        int[] nums = new int[dimension];
        for (int i = 0; i < dimension; i++) {
            nums[i] = i + 1;
        }
        permuteBackTrace(selectSize, nums, new ArrayList<>());//首次调用递归函数
        arrangementArray = toArray();


    }

    /**
     * 递归函数求选择性组合排列
     *
     * @param size    深度优先遍历的深度
     * @param nums    初始排列
     * @param current 当前排列
     */
    private void permuteBackTrace(int size, int[] nums, ArrayList<Integer> current) {
        if (current.size() == size) {
            arrangementList.add(new ArrayList<>(current));
            counter++;
            return;
        }
        for (int num : nums) {
            if (current.contains(num) || lessThanCurrent(num, current)) continue;
            current.add(num);
            permuteBackTrace(size, nums, current);
            current.remove(current.size() - 1);
        }

    }

    /**
     * 如果num小于current中的所有元素，那么返回true，否则返回false
     *
     * @param num     要比较的数字
     * @param current 被比较的数字组合
     * @return boolean 返回的判断结果
     */
    private boolean lessThanCurrent(int num, ArrayList<Integer> current) {
        for (Integer integer : current) {
            if (num < integer) return true;
        }
        return false;
    }


    /**
     * 将内部的List<List<Integer>>转换成int[][]
     *
     * @return {@link int[][]} 返回已经转换好的二维数组
     */
    private int[][] toArray() {
        int[][] result = new int[arrangementList.size()][];
        for (int i = 0; i < arrangementList.size(); i++) {
            int size = arrangementList.get(i).size();
            result[i] = new int[size];
            for (int j = 0; j < size; j++) {
                result[i][j] = arrangementList.get(i).get(j);
            }
        }
        return result;
    }

    /**
     * 显示输出内部已经计算好的全排列二维数组
     *
     * @return {@link String} 以字符串的形式返回排序结果
     */
    @Override
    public String toString() {
        if (!haveBeenRun) throw new UnRunException();

        StringBuilder stringBuilder = new StringBuilder();

        for (int[] ints : arrangementArray) {
            for (int anInt : ints) {
                stringBuilder.append(anInt).append(" ");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    /**
     * 直接以int[][]的形式返回结果数组
     *
     * @return {@link int[][]} 代表返回的结果数组
     */
    public int[][] getArrangementArray() {
        if (!haveBeenRun) throw new UnRunException();

        return arrangementArray;
    }

    /**
     * Gets arrangement list.
     *
     * @return the arrangement list
     */
    public List<List<Integer>> getArrangementList() {
        if (!haveBeenRun) throw new UnRunException();

        return arrangementList;
    }

}

class PermutationGeneratorTester {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        test2();

    }

    public static void test() {
        System.out.print("输入一元排列的尾数：");
        int dimension = scanner.nextInt();
        PermutationsGenerator generator = new PermutationsGenerator(dimension);
        generator.run();
        int[][] result = generator.getPermutations();
        System.out.println("一共生成" + MathUtil.factorial(dimension) + "种排列");
        generator.showResult();
        System.out.println("递归调用次数=" + generator.getCounter());

    }

    public static void test2() {
        PermutationGeneratorPlus generator = new PermutationGeneratorPlus();
        generator.setDimension(5);
        generator.setSelectSize(3);
        generator.run(PermutationGeneratorPlus.mode.SELECT);
        System.out.println(generator.toString());
        System.out.println("一共生成了" + generator.getCounter() + "种排列");

    }
}