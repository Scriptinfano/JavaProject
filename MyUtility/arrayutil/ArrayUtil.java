package arrayutil;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReferenceArray;

/**
 * 自定义的Array工具类，包含一些对数组的简单操作
 *
 * @author Mingxiang
 * @version 1.0
 */
public class ArrayUtil {
    public static Scanner scanner = new Scanner(System.in);


    /**
     * 输出排序前后的数组
     *
     * @param arrayOne 代表没有经过排序的数组
     * @param arrayTwo 代表已经经过排序的数组
     */
    public static void showBothArray(Integer[] arrayOne, Integer[] arrayTwo) {
        System.out.println("在排序之前的数组");
        showArray(arrayOne);//输出排序前数组
        System.out.println("在排序之后的数组");
        showArray(arrayTwo);
    }


    /**
     * 返回指定数组的备份数组的集合，集合中的每一个元素都是第一个参数所代表的数组的拷贝，size指定了要返回多少个拷贝
     *
     * @param array 待拷贝的数组
     * @param size  指定要拷贝多少个数组
     * @return {@link Integer[][]} 返回拷贝数组的集合
     */
    public static Integer[][] getBackUpArrays(Integer[] array, int size) {
        Integer[][] backupArray = new Integer[size][];//保存没有排序的数组
        for (int i = 0; i < backupArray.length; i++) {
            backupArray[i] = array.clone();
        }
        return backupArray;
    }


    /**
     * 输出给定数组，该数组的参数类型更加广泛，采用Object[]作为参数，可输出更多对象数组
     *
     * @param arr 传入的要输出的数组
     */
    public static void showArray(Object[] arr) {
        for (Object i : arr)
            System.out.print(i + " ");
        System.out.println();
    }

    /**
     * 输出指定的int[]数组
     *
     * @param arr 给定的int[]数组
     */
    public static void showArray(int[] arr) {
        for (int i : arr)
            System.out.print(i + " ");
        System.out.println();
    }


    /**
     * 反转数组元素
     *
     * @param array 传入的要反转的int[]型数组
     * @return int[] 返回已经反转之后的数组
     */
    public static int[] reverseArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
        for (int i = 0; i < array.length / 2; i++) {
            int temp = array[i];
            array[i] = array[array.length - i - 1];
            array[array.length - i - 1] = temp;
        }
        return array;
    }

    /**
     * 返回一个随机Integer数组，元素随机且不重复，数组元素个数是size，范围是[begin,end]
     *
     * @param size  随机数组的大小
     * @param begin 随机数组中元素可能取值范围的起始值
     * @param end   随机数组中元素可能取值范围的终止值
     * @return {@link Integer[]} 返回的随机数数组
     */
    public static Integer[] randomIntegerArray(int size, int begin, int end) {
        Integer[] randomArray = new Integer[size];
        Random randomGenerator = new Random(System.currentTimeMillis());
        for (int i = 0; i < randomArray.length; i++) {
            loopLabel:
            while (true) {
                Integer tempInteger = randomGenerator.nextInt(begin, end + 1);
                for (int j = 0; j < i; j++) {
                    if (tempInteger.equals(randomArray[j])) continue loopLabel;
                }
                randomArray[i] = tempInteger;
                break;
            }
        }
        return randomArray;
    }

    /**
     * 返回一个随机int数组，元素随机且不重复，数组元素个数是size，范围是[begin,end]
     *
     * @param size  随机数组的大小
     * @param begin 随机数组中元素可能取值范围的起始值
     * @param end   随机数组中元素可能取值范围的终止值
     * @return {@link int[]} 返回的随机数数组
     */
    public static int[]randomIntArray(int size,int begin,int end){
        return Arrays.stream(randomIntegerArray(size,begin,end)).mapToInt(Integer::valueOf).toArray();
    }

    /**
     * 利用二分查找法查找给定数组中指定元素的位置
     *
     * @param theArray     给定的要查找的数组
     * @param targetNumber 指定的要查找的数字
     * @return int 返回要查找的数字的下标
     */
    public static int binarySearch(Integer[] theArray, int targetNumber) {
        int left = 0;
        int right = theArray.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;

            if (targetNumber > theArray[mid])//比较运算符两边只要有一个是基本类型，右边的包装器会自动拆箱
                left = mid + 1;
            else if (targetNumber < theArray[mid])
                right = mid - 1;
            else return mid;
        }
        return -1;
    }

    /**
     * 找到给定数组中最大的元素和最小的元素
     *
     * @param array 给定的要查找最大元素和最小元素的数组
     * @return Integer[] 返回的整型数组，其中第一个元素是给定数组的最小值，第二个元素就是给定数组的最大值
     */
    public static Integer[] MaxOrMinNumberOfArray(Integer[] array) {
        if (array.length < 2) throw new IllegalArgumentException("数组中的元素个数少于两个，无法得出最大与最小值");
        int max = array[0], min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) max = array[i];
            if (array[i] < min) min = array[i];
        }
        return new Integer[]{array[min], array[max]};
    }

    public static Integer[] MaxOrMinNumberOfArray2(Integer[] array) {
        if (array.length < 2) throw new IllegalArgumentException("数组中的元素个数少于两个，无法得出最大与最小值");
        int max = 0, min = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[i] > array[max]) max = i;
            else if (array[i] < array[min]) min = i;
        }
        return new Integer[]{array[min], array[max]};
    }


}
