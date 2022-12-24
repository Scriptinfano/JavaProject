package arrayutil;

import java.util.Arrays;

/**
 * 排序工具类,包含对数组的一些排序方法
 *
 * @author Mingxiang
 */
public class ArraySorter<T extends Comparable<T>> {
    /**
     * 内部要排列的数组
     */
    private T[] array = null;

    /**
     * 构造ArraySorter对象，将要进行排序的数组作为属性传入ArraySorter对象，以便其调用排序算法对其进行排序
     *
     * @param theArray 表示要进行排序的数组
     */
    public ArraySorter(T[] theArray) {
        this.array = theArray;
    }

    /**
     * 重设要排序的数组，一般用法是仅声明一个ArraySorter对象，调用该方法重设排序数组即可对其他数组进行排序
     */
    public void resetArray(T[] array) {
        this.array = array;
    }


    public static void main(String[] args) {
        Integer[] arr = ArrayUtil.randomIntegerArray(10, 1, 100);
        ArrayUtil.showArray(arr);
        ArraySorter<Integer> sorter = new ArraySorter<>(arr);
        sorter.binaryInsertSort();
    }

    /**
     * 快速排序
     * <p>算法特点<p>
     * 1、不稳定排序<p>
     * 2、排序时需要定位表的上下界，所以适合于顺序结构<p>
     * 3、适合初始记录无序，数组元素个数较大的情况
     *
     * @param left  快速排序要指定的左边界，在调用此函数时默认填写0
     * @param right 快速排序要指定的右边界，在调用此函数时默认填写数组最后一个下标
     */
    public void quickSort(int left, int right) {
        /*如何选取轴值
         * 1、使用第一个记录的关键码
         * 2、选取序列中间记录的关键码
         * 3、比较序列中第一个记录，最后一个记录和中间记录的关键码，取关键码居中的作为轴值并调换到第一个记录的位置
         * 4、随机选取轴值*/
        if (left < right) {
            int i = left, j = right;
            T standard = array[i];
            while (i < j) {
                while (array[j].compareTo(standard) >= 0 && i < j) {
                    j--;
                }
                if (i < j) {
                    swap(i, j);
                    i++;
                }
                while (array[i].compareTo(standard) <= 0 && i < j) {
                    i++;
                }
                if (i < j) {
                    swap(i, j);
                    j--;
                }
            }
            //此时i和j相等，i和j的值就是轴值的下标，轴值左侧都是小于轴值的值，轴值右侧都是大于轴值的值
            quickSort(left, i - 1);
            quickSort(i + 1, right);
        }
    }

    /**
     * 表示插入排序每一次遍历并且插入的过程
     *
     * @param index 代表要插入的元素的下标
     * @param value 代表要插入的元素的值
     */
    private void insert(int index, T value) {
        int i;
        for (i = index - 1; i >= 0 && value.compareTo(array[i]) < 0; i--) {
            array[i + 1] = array[i];
        }
        array[i + 1] = value;
    }



    /*
    public void insertionSort() {
        for (int i = 1; i < array.length; i++) {
            T t = array[i];
            int j;
            for (j = i - 1; j >= 0 && array[j].compareTo(t) > 0; j--) {
                array[j + 1] = array[j];
            }
            array[j + 1] = t;
        }
    }
    */

    /**
     * 直接插入排序<p>
     * 插入排序的思想:从数组的第二个元素开始遍历数组，比较该元素与前一个元素，如果该元素
     * 比前一个元素大，则继续遍历下一个元素，如果该元素比前一个元素小，则保存这个元素的值，
     * 将这个元素不断地向后挪，直到该元素比后一个元素小为止，挪的过程中采用将前一个元素覆
     * 写后一个元素的方式实现，不用担心后一个元素被抹掉，因为已经提前保存了这个值
     * <p>
     *     算法特点：<p>
     *     1、稳定排序<p>
     *     2、适合链式存储结构<p>
     *     3、适合初始基本有序且数组元素个数较小的情况<p>
     */
    public void insertSort() {
        for (int i = 1; i < array.length; i++) {
            T value = array[i];
            insert(i, value);
        }
    }

    /**
     * 折半插入排序
     * <p>
     *     算法特点：
     *     <p>
     *        1、排序稳定<p>
     *        2、只能用于顺序结构<p>
     *        3、适合初始记录无序，数组元素数较大的情况<p>
     */
    public void binaryInsertSort() {

        for (int i = 1; i < array.length; i++) {
            T temp = array[i];
            int low = 0, high = i - 1;
            while (low <= high) {
                int mid = (low + high) / 2;
                if (temp.compareTo(array[mid]) < 0)
                    high = mid - 1;
                else low = mid + 1;
            }
            for (int j = i - 1; j >= high + 1; j--)
                array[j + 1] = array[j];
            array[high + 1] = temp;
        }
    }

    /**
     * 希尔排序
     * <p>
     * 算法特点
     * <p>
     * 1、排序不稳定<p>
     * 2、只能用于顺序结构<p>
     * 3、增量序列可以有各种取法，但应该使增量序列中的值没有除1之外的公因子，并且最后一个增量值必须等于1<p>
     * 4、适合初始记录无序，数组元素个数较大时的情况<p>
     */
    public void shellSort() {
        int size = array.length;
        for (int d = size / 2; d >= 1; d = d / 2) {
            for (int i = d + 1; i <= size; i++) {
                T temp = array[i];
                int j = i - d;
                while (j > 0 && temp.compareTo(array[j]) < 0) {
                    array[j + d] = array[j];
                    j = j - d;
                }
                array[j + d] = temp;

            }
        }
    }

    /**
     * 冒泡排序
     * <p>
     * 算法特点<p>
     * 1、排序稳定<p>
     * 2、可用于链式存储结构<p>
     * 3、适用于初始记录有序，数组元素个数较小的情况
     */
    public void bubbleSort() {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j].compareTo(array[j + 1]) > 0) {
                    swap(j, j + 1);
                }
            }
        }
    }

    /**
     * 优化冒泡排序中每一次遍历不断比较的过程，如果在一次遍历的过程中没有发生交换，
     * 则说明已经排好序了，不需要再进行遍历了，该函数会返回一个false来指示bubbleSort()
     * 中的for循环终止执行
     */
    private boolean betterBubble(int n) {
        boolean swapped = false;
        for (int i = 0; i < n - 1; i++) {
            if (array[i].compareTo(array[i + 1]) > 0) {
                swap(i, i + 1);
                swapped = true;
            }
        }
        return swapped;
    }

    /**
     * 优化之后的冒泡排序，也就是能及时终止的冒泡排序
     */
    public void betterBubbleSort() {
        for (int i = array.length; i > 1 && betterBubble(i); i--) ;
    }

    /**
     * 名次排序<p>
     * 数组中元素的名次是什么：一个元素在一个序列中的名次是所有比它小的元素个数加上
     * 在它左边出现的与它相同的元素个数
     *
     * @implNote 该函数通过计算数组中各元素的名次，然后调用交换算法将每个元素转移到正确的位置上
     * 实现从小到大排序
     */
    public void rankSort() {
        Integer[] rankArray = new Integer[array.length];
        Arrays.fill(rankArray, 0);//fill方法可将指定值填充到指定数组
        for (int i = 1; i < array.length; i++) {
            for (int j = 0; j < i; j++) {
                if (array[j].compareTo(array[i]) <= 0)//注意这里是小于等于号，因为名次的定义是所有比它小的元素个数加在其左边出现的与它相同的元素个数
                    rankArray[i]++;
                else rankArray[j]++;
            }
        }
        ArraySorter sorter = new ArraySorter(rankArray);

        for (int i = 0; i < array.length; i++) {
            while (!rankArray[i].equals(i)) {
                swap(i, rankArray[i]);
                sorter.swap(i, rankArray[i]);
            }
        }
    }

    /**
     * 简单选择排序
     */
    public void simpleSelectionSort() {

    }

    /**
     * 在java中交换数组中的两个元素<p>
     * 注意在java中交换数组的两个元素必须给函数传入数组引用以及两个要交换的元素的下标
     *
     * @param i 要交换的第一个元素的下标
     * @param j 要交换的第二个元素的下标
     */
    private void swap(int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * 优化之后的选择排序，可以在已经排好序的前提下提前终止循环
     */
    public void selectSort() {
        boolean sorted = false;
        for (int i = array.length; !sorted && (i > 1); i--) {
            int indexOfMax = 0;
            sorted = true;
            //找出未排列区间谁的值最大，就将该值和该区间最后一个元素（i指向的元素之前的一个元素）交换，然后不断缩小区间，不断交换直到排好序
            for (int j = 1; j < i; j++) {
                if (array[indexOfMax].compareTo(array[j]) <= 0) indexOfMax = j;
                else sorted = false;
            }
            swap(indexOfMax, i - 1);
        }
    }


}
