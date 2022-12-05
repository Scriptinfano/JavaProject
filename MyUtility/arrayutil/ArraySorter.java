package arrayutil;

import exceptions.SorterUninitializedException;

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


    /**
     * 快速排序
     *
     * @param left  快速排序要指定的左边界
     * @param right 快速排序要指定的右边界
     * @throws IllegalArgumentException 指示左边界或右边界指定错误
     */
    public void quickSort(int left, int right) throws SorterUninitializedException {
        if (array == null) throw new SorterUninitializedException();
        if (left < right) {
            int i = left, j = right;
            T standard = array[i];
            while (i < j) {
                while (array[j].compareTo(standard) >= 0 && i < j) {
                    j--;
                }
                if (i < j) {
                    array[i++] = array[j];
                }
                while (array[i].compareTo(standard) < 0 && i < j) {
                    i++;
                }
                if (i < j) {
                    array[j--] = array[i];
                }
            }
            array[i] = standard;//此时i和j相等，将基准数填入
            quickSort(left, i - 1);
            quickSort(i + 1, right);
        }
    }


    /**
     * 插入排序<p>
     * 插入排序的思想:从数组的第二个元素开始遍历数组，比较该元素与前一个元素，如果该元素
     * 比前一个元素大，则继续遍历下一个元素，如果该元素比前一个元素小，则保存这个元素的值，
     * 将这个元素不断地向后挪，直到该元素比后一个元素小为止，挪的过程中采用将前一个元素覆
     * 写后一个元素的方式实现，不用担心后一个元素被抹掉，因为已经提前保存了这个值
     */
    public void insertSort() throws SorterUninitializedException {
        if (array == null) throw new SorterUninitializedException();

        for (int i = 1; i < array.length; i++) {
            T value = array[i];
            insert(i, value);
        }
    }

    /**
     * 表示插入排序每一次遍历并且插入的过程
     *
     * @param rightIndex 代表要插入的元素的下标
     * @param value      代表要插入的元素的值
     */
    private void insert(int rightIndex, T value) {
        int i;
        for (i = rightIndex - 1; i >= 0 && value.compareTo(array[i]) < 0; i--) {
            array[i + 1] = array[i];
        }
        array[i + 1] = value;
    }

    /**
     * 对代码进行简化之后的插入排序
     */
    public void insertionSort() throws SorterUninitializedException {
        if (array == null) throw new SorterUninitializedException();

        for (int i = 1; i < array.length; i++) {
            T t = array[i];
            int j;
            for (j = i - 1; j >= 0 && array[j].compareTo(t) > 0; j--) {
                array[j + 1] = array[j];
            }
            array[j + 1] = t;
        }

    }

    /**
     * 最常见的冒泡排序
     */
    public void bubbleSort() throws SorterUninitializedException {
        if (array == null) throw new SorterUninitializedException();

        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j].compareTo(array[j + 1]) > 0) {
                    swap(j, j + 1);
                }
            }
        }

    }

    /**
     * 优化之后的冒泡排序，也就是能及时终止的冒泡排序
     */
    public void betterBubbleSort() throws SorterUninitializedException {
        if (array == null) throw new SorterUninitializedException();

        for (int i = array.length; i > 1 && betterBubble(i); i--) ;
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
     * 名次排序<p>
     * 数组中元素的名次是什么：一个元素在一个序列中的名次是所有比它小的元素个数加上
     * 在它左边出现的与它相同的元素个数
     *
     * @implNote 该函数通过计算数组中各元素的名次，然后调用交换算法将每个元素转移到正确的位置上
     * 实现从小到大排序
     */
    public void rankSort() throws SorterUninitializedException {
        if (array == null) throw new SorterUninitializedException();

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
     * 优化之后的选择排序，可以在已经排好序的前提下提前终止循环
     */
    public void selectSort() throws SorterUninitializedException {
        if (array == null) throw new SorterUninitializedException();

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


}
