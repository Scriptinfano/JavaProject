//本程序主要讲解java中的数组及其使用
package array;

import analyzer.AlgorithmAnalyzer;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import static array.ArrayUtil.binarySearch;


/**
 * 排序工具类,包含对数组的一些排序方法
 *
 * @author Mingxiang
 * @date 2022/08/27
 */
class ArraySorter<T extends Comparable<T>> {
    /**
     * 内部要排列的数组
     */
    T[] array = null;

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
    public void quickSort(int left, int right) {
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
    public void insertSort() {
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

    /**
     * 最常见的冒泡排序
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
     * 优化之后的冒泡排序，也就是能及时终止的冒泡排序
     */
    public void betterBubbleSort() {
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

/**
 * 自定义的Array工具类，包含一些对数组的简单操作
 *
 * @author Mingxiang
 * @version 1.0
 */
class ArrayUtil {
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
     * 该方法返回一个整型对象数组，其中的元素随机且不重复，范围在begin到end之间
     *
     * @param size  随机数组的大小
     * @param begin 随机数组中元素可能取值范围的起始值
     * @param end   随机数组中元素可能取值范围的终止值
     * @return Integer[] 返回的随机数数组
     */
    public static Integer[] randomArray(int size, int begin, int end) {
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


/**
 * 该类仅用于测试ArrayUtil工具类，不要在其他地方调用
 *
 * @author Mingxiang
 * @date 2022/09/03
 */
public class ArrayMain {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        //ArrayUtil.analysisAlgorithmTime();
        //testArraySorter();
        testMaxOrMinFunc();
    }

    private static void testArraySorter() {
        Integer[] theArray = {6, 5, 4, 3, 2, 1};
        ArraySorter<Integer> sorter = new ArraySorter<>(theArray);
        sorter.rankSort();
        ArrayUtil.showArray(theArray);

    }

    private static void testMaxOrMinFunc() {
        Integer[] array = ArrayUtil.randomArray(20, 1, 10);
        //Integer[][] backUpArray = ArrayUtil.getBackUpArrays(array, 2);


        long begin1 = System.nanoTime();
        Integer[] result = ArrayUtil.MaxOrMinNumberOfArray(array);
        long end1 = System.nanoTime();

        long begin2 = System.nanoTime();
        Integer[] result2 = ArrayUtil.MaxOrMinNumberOfArray2(array);
        long end2 = System.nanoTime();

        System.out.println("MaxOrMinNumberOfArray耗费时间" + AlgorithmAnalyzer.getAlgorithmTime(begin1, end1));
        System.out.println("MaxOrMinNumberOfArray耗费时间" + AlgorithmAnalyzer.getAlgorithmTime(begin2, end2));


    }

    //测试数组元素的默认初始化
    private static void defaultArrayElement() {
        //各元素数组的默认值
        //char数组的默认元素不是'0',而是asc码的0，
        char[] charArray = new char[10];
        System.out.println(charArray);

        //当数组元素是boolean型时，默认值为false
        boolean[] boolArray = new boolean[10];
        System.out.println(boolArray[0]);

        //当二维数组的两个维度都指定时，则说明声明之后就初始化了，输出arr[0]时输出的就是地址
        int[][] arr = new int[3][3];
        System.out.println(arr[0]);//输出地址值
        //当二维数组仅指定了第一维度的值时，由于二维未指定导致二维维数组中的每个元素作为一维数组是未初始化的，即null
        int[][] arr2 = new int[3][];
        System.out.println(arr2[0]);//输出null
        int[][] arr3 = new int[3][];
        System.out.println(arr3[0][2]);//会抛出异常NullPointerException，因为无法根据null找到地址，报空指针异常

    }

    //Arrays工具类的使用
    private static void testArraysUtil() {
        //Arrays中常见的方法
        //1、boolean equals(int[]a,int[]b) 判断两个数组是否相等
        int[] arr = new int[]{12, 23, 34, 54};
        int[] arr2 = new int[]{12, 23, 34, 12};
        boolean isEqual = Arrays.equals(arr, arr2);
        System.out.println(isEqual);
        double[] arr3 = new double[]{12.23, 12.42};
        double[] arr4 = new double[]{31d, 12.21};
        boolean isEqual2 = Arrays.equals(arr3, arr3.clone());
        System.out.println(isEqual2);

        //2、String toString(int[]a) 输出数组信息
        String arrayString = Arrays.toString(arr4);
        System.out.println(arrayString);
        //3、void fill(int[]a,int val) 将指定值填充到数组中
        Arrays.fill(arr, 10);
        String arrayString2 = Arrays.toString(arr);
        System.out.println(arrayString2);
        //4、void sort(int[]a) 对数组进行排序
        Integer[] randomArray = ArrayUtil.randomArray(50, 0, 100);
        Arrays.sort(randomArray);
        String arrayString3 = Arrays.toString(randomArray);
        System.out.println(arrayString3);
        //5、int binarySearch(int[]a,int key) 堆排序后的数组进行二分法检索指定的值
        System.out.println("查找上面输出的数组中的元素，请输入要查找的数字");
        int targetNumber = scanner.nextInt();
        int index = Arrays.binarySearch(randomArray, targetNumber);
        if (index >= 0) System.out.println("找到了，索引是：" + index);
        else System.out.println("未找到");

    }

    //拷贝数组中的元素
    private static void copyArray() {
        //拷贝数组的第一种方法是使用System类的低层级arrayCopy()方法
        String[] names = new String[3];
        names[0] = new String("操");
        names[1] = new String("你");
        names[2] = new String("妈");
        String[] tempArray = new String[2 * names.length];
        System.arraycopy(names, 0, tempArray, 0, names.length);//将names数组的元素拷贝到新数组tempArray中
        names = tempArray;//names只是充当一个引用作用，随时可以引用其他数组对象，只要原来的数组对象不存在引用，那么原来的数组对象所占有的空间将在下一轮垃圾回收中被回收
        names[3] = "大";
        names[4] = "傻";
        names[5] = "逼";
        ArrayUtil.showArray(names);
        System.out.println();

        //拷贝数组的第二种方法是使用 java.util.Arrays.copyOf()和copyOfRange()方法
        int[] bar = new int[]{1, 2, 3, 4, 5};
        int[] barCopy = Arrays.copyOf(bar, bar.length);//copyOf接受最初的数组和一个目标长度作为参数
        int[] barExtraCopy = Arrays.copyOf(bar, bar.length + 2);//若目标数组长度比最初数组长，那么copyOf将会用0或null填充新数组以达到想要的长度
        int[] rangeCopy = Arrays.copyOfRange(bar, 0, bar.length);//copyOfRange()接受一个开始索引（包括该索引）和一个结束索引（不包括该索引）以及一个想要的长度，如果必要的话，它也将进行填充补齐
        //拷贝数组的第三种方法是调用所有对象基类Object的方法clone()直接克隆数组
        int[] barCloneCopy = bar.clone();
        System.out.println("显示克隆之后的数组：");
        ArrayUtil.showArray(barCloneCopy);
        System.out.println("拷贝二维数组");
        //拷贝二维数组
        int[][] numbers = {{1, 2, 3, 4}, {45, 48}};
        int[][] numbers_copy = numbers.clone();
        for (int i = 0; i < numbers_copy.length; i++) {
            for (int j = 0; j < numbers_copy[i].length; j++)
                System.out.print(numbers_copy[i][j] + " ");
            System.out.println();
        }

    }

    /**
     * 对各种排序算法做时间分析，并运行二分查找寻找给定的元素检验排序是否正确
     */
    private static void analysisAlgorithmTime() {
        ArraySorter<Integer> sorter = new ArraySorter<Integer>(null);//实例化排序器对象
        Integer[] arr = ArrayUtil.randomArray(100, 1, 10000);//生成随机数组
        Integer[][] backUpArrays = ArrayUtil.getBackUpArrays(arr, 6);//返回一个未排序数组的集合，这些集合都是上面那个未排序数组的拷贝


        long beginMillis1 = System.nanoTime();//以纳秒为单位返回系统单位时间
        sorter.resetArray(backUpArrays[0]);
        sorter.betterBubbleSort();//排序器调用bubbleSort算法对数组进行排序
        long endMillis1 = System.nanoTime();

        long beginMillis2 = System.nanoTime();//以纳秒为单位返回系统单位时间
        sorter.resetArray(backUpArrays[1]);
        sorter.quickSort(0, arr.length - 1);//排序器调用快速排序法对数组排序
        long endMillis2 = System.nanoTime();//以纳秒为单位返回系统单位时间

        long beginMillis3 = System.nanoTime();//以纳秒为单位返回系统单位时间
        sorter.resetArray(backUpArrays[2]);
        sorter.bubbleSort();//排序器调用快速排序法对数组排序
        long endMillis3 = System.nanoTime();//以纳秒为单位返回系统单位时间

        long beginMillis4 = System.nanoTime();//以纳秒为单位返回系统单位时间
        sorter.resetArray(backUpArrays[3]);
        sorter.insertSort();//排序器调用快速排序法对数组排序
        long endMillis4 = System.nanoTime();//以纳秒为单位返回系统单位时间

        long beginMillis5 = System.nanoTime();//以纳秒为单位返回系统单位时间
        sorter.resetArray(backUpArrays[4]);
        sorter.selectSort();//排序器调用选择排序法对数组排序
        long endMillis5 = System.nanoTime();//以纳秒为单位返回系统单位时间

        long beginMillis6 = System.nanoTime();//以纳秒为单位返回系统单位时间
        sorter.resetArray(backUpArrays[5]);
        sorter.rankSort();//排序器调用名次排序法对数组排序
        long endMillis6 = System.nanoTime();//以纳秒为单位返回系统单位时间


        ArrayUtil.showBothArray(arr, backUpArrays[4]);

        System.out.println("优化冒泡排序总共耗费时间:" + AlgorithmAnalyzer.getAlgorithmTime(beginMillis1, endMillis1) + "毫秒");
        System.out.println("快速排序总共耗费时间:" + AlgorithmAnalyzer.getAlgorithmTime(beginMillis2, endMillis2) + "毫秒");
        System.out.println("普通冒泡排序排序总共耗费时间:" + AlgorithmAnalyzer.getAlgorithmTime(beginMillis3, endMillis3) + "毫秒");
        System.out.println("插入排序排序总共耗费时间:" + AlgorithmAnalyzer.getAlgorithmTime(beginMillis4, endMillis4) + "毫秒");
        System.out.println("选择排序排序总共耗费时间:" + AlgorithmAnalyzer.getAlgorithmTime(beginMillis5, endMillis5) + "毫秒");
        System.out.println("名次排序排序总共耗费时间:" + AlgorithmAnalyzer.getAlgorithmTime(beginMillis6, endMillis6) + "毫秒");


        System.out.println("输入要查找的数字：");
        int userNumber = 0;
        userNumber = scanner.nextInt();
        int index = binarySearch(backUpArrays[0], userNumber);//注意二分查找的条件是数组是有序的，必须提前排好序
        if (index != -1) System.out.println("找到了，下标为：" + index);
        else System.out.println("未找到");
    }


}
