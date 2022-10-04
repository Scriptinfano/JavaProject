package mathmetic;

import exceptions.RequiredSettingsNotCalledException;
import exceptions.UnRunException;

import java.util.ArrayList;
import java.util.List;

/**
 * 全排列生成器
 *
 * @author Mingxiang
 */
public class FullArrangementGenerator {
    private List<List<Integer>> arrangementList = new ArrayList<>();//存放n元串的全排列的容器
    private int[][] arrangementArray;

    private Integer dimension = null;

    private Integer selectSize = null;

    private boolean haveRunned = false;

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public void setSelectSize(int selectSize) {
        this.selectSize = selectSize;
    }

    /**
     * 以NORMAL模式运行
     *
     * @param theDimension 指定排列的阶数
     */
    private void run(int theDimension) {
        int[] nums = new int[theDimension];
        for (int i = 0; i < theDimension; i++) {
            nums[i] = i + 1;
        }
        permuteBackTrace(nums, new ArrayList<>());//首次调用递归函数
        arrangementArray = toArray();
    }

    /**
     * 以SELECT模式运行
     *
     * @param theDimension  指定要从几个数中选择进行组合
     * @param theSelectSize 指定要选择其中几个数进行全排列
     */
    private void run(int theDimension, int theSelectSize) {

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
                    throw new RequiredSettingsNotCalledException("未调用setDimension()设置dimension属性，无法以NORMAL模式运行");
                run(dimension);
            }
            case SELECT -> {
                if (dimension == null && selectSize == null)
                    throw new RequiredSettingsNotCalledException("未调用setSelectSize()设置dimension属性和selectSize属性，无法以SELECT模式运行");
                run(dimension, selectSize);
            }
        }
        haveRunned = true;
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
     * 递归函数求选择性组合排列
     *
     * @param size    深度优先遍历的深度
     * @param nums    初始排列
     * @param current 当前排列
     */
    private void permuteBackTrace(int size, int[] nums, ArrayList<Integer> current) {
        if (size == current.size()) {
            arrangementList.add(new ArrayList<>(current));
            return;
        }
        for (int num : nums) {
            if (current.contains(num)) continue;
            current.add(num);
            permuteBackTrace(size, nums, current);
            current.remove(current.size() - 1);
        }

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
        if (!haveRunned) throw new UnRunException();

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < arrangementArray.length; i++) {
            for (int j = 0; j < arrangementArray[i].length; j++) {
                stringBuilder.append(arrangementArray[i][j]).append(" ");
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
        if (!haveRunned) throw new UnRunException();

        return arrangementArray;
    }

    public List<List<Integer>> getArrangementList() {
        if (!haveRunned) throw new UnRunException();

        return arrangementList;
    }

    enum mode//代表运行模式
    {
        NORMAL,//代表常规模式：输出n元串的全排列
        SELECT//找出从自然数1,2,3...n中任取r个数的所有组合
    }
}

class ArrangementTester {
    public static void main(String[] args) {
        FullArrangementGenerator generator = new FullArrangementGenerator();
        generator.run(FullArrangementGenerator.mode.NORMAL);
        System.out.println(generator.toString());

    }
}