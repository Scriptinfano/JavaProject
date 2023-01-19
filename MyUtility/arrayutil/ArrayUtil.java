package arrayutil;

import analyzer.AlgorithmAnalyzer;
import myScannerAndPrinter.NoMoreScanException;
import myScannerAndPrinter.ScannerPlus;

import java.util.Arrays;
import java.util.Random;

/**
 * 自定义的Array工具类，包含一些对数组的简单操作
 *
 * @author Mingxiang
 * @version 1.0
 */
public class ArrayUtil {
    public static ScannerPlus scanner = new ScannerPlus();

    private ArrayUtil(){}

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
     * 注意数组中的对象必须重写toString()方法
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
    public static int[] randomIntArray(int size, int begin, int end) {
        return Arrays.stream(randomIntegerArray(size, begin, end)).mapToInt(Integer::valueOf).toArray();
    }

    /**
     * 利用二分查找法查找给定数组中指定元素的位置，注意此函数要求是否对数组排序，如果找到了元素返回的是未排序时该元素在数组中的位置
     *
     * @param theArray     给定的要查找的数组
     * @param targetNumber 指定的要查找的数字
     * @param autoSort     是否先对数组进行排序，此排序排的是该数组的克隆，不会改变原数组的内容，因为二分查找要求数组有序
     * @return boolean 找到了则返回true，否则返回false
     */
    public static boolean binarySearch(Integer[] theArray, int targetNumber, boolean autoSort) {
        if (autoSort) {
            Integer[] sortedArray = theArray.clone();
            Arrays.sort(sortedArray);
            return binarySearchDetail(sortedArray, targetNumber);
        } else {
            return binarySearchDetail(theArray, targetNumber);
        }
    }

    /**
     * 顺序查找
     *
     * @param theArray     待查找的数组
     * @param targetNumber 要查找的数字
     * @return 返回
     */
    public static boolean sequenceSearch(Integer[] theArray, int targetNumber) {
        for (Integer integer : theArray) {
            if (integer == targetNumber)
                return true;
        }
        return false;
    }


    private static boolean binarySearchDetail(Integer[] theArray, int targetNumber) {
        int left = -1;
        int right = theArray.length;
        while (left + 1 != right) {
            int mid = (int) Math.floor((left + right) / (double) 2);

            if (targetNumber > theArray[mid])//比较运算符两边只要有一个是基本类型，右边的包装器会自动拆箱
                left = mid;
            else if (targetNumber < theArray[mid])
                right = mid;
            else return true;
        }
        return false;
    }

    /**
     * 二分查找的递归算法
     *
     * @param theArray 待查找的数组
     * @param num      待查找的数字
     * @param left     左边界，建议此处写-1
     * @param right    右边界，建议此处写待排序数组的长度
     * @return boolean 找到了则返回true否则返回false
     */
    public static boolean binarySearchRecursion(Integer[]theArray,int num,int left,int right){
        if(left+1==right)
            return false;
        else {
            int mid=(int) Math.floor((left + right) / (double) 2);
            if(theArray[mid]>num)
                return binarySearchRecursion(theArray,num,left,mid);
            else if(theArray[mid]<num)
                return binarySearchRecursion(theArray,num,mid,right);
            else return true;
        }
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

    /**
     * 将两个升序表，合并为一个降序表
     */
    public static int[] mergeArray(int[] a, int[] b) {
        int[] merge = new int[a.length + b.length];
        int i = a.length - 1;
        int j = b.length - 1;
        int k = 0;
        while (k != merge.length) {
            if (i >= 0 && j >= 0) {
                if (a[i] >= b[j]) {
                    merge[k] = a[i];
                    i--;
                } else {
                    merge[k] = b[j];
                    j--;
                }
            } else if (i < 0) {
                merge[k] = b[j];
                j--;
            } else {
                merge[k] = a[i];
                i--;
            }
            k++;
        }
        return merge;
    }

    public static String randomString(int length){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();

        StringBuilder resultStr= new StringBuilder();
        for(int i=0;i<length;i++){
            int number=random.nextInt(str.length());
            resultStr.append(str.charAt(number));
        }
        return resultStr.toString();

    }

}



/**
 * 该类仅用于测试ArrayUtil工具类，不要在其他地方调用
 *
 * @author Mingxiang
 */
class ArrayUtilTester {

    private ArrayUtilTester(){}
    private static final ScannerPlus scanner = new ScannerPlus();

    public static void main(String[] args) {
        //ArrayUtil.analysisAlgorithmTime();
        //testArraySorter();
        //testMaxOrMinFunc();
        analysisAlgorithmTime();
    }

    private static void testMaxOrMinFunc() {
        Integer[] array = ArrayUtil.randomIntegerArray(20, 1, 10);
        //Integer[][] backUpArray = ArrayUtil.getBackUpArrays(array, 2);


        long begin1 = AlgorithmAnalyzer.getCurrentTime();
        Integer[] result = ArrayUtil.MaxOrMinNumberOfArray(array);
        long end1 = AlgorithmAnalyzer.getCurrentTime();

        long begin2 = AlgorithmAnalyzer.getCurrentTime();
        Integer[] result2 = ArrayUtil.MaxOrMinNumberOfArray2(array);
        long end2 = AlgorithmAnalyzer.getCurrentTime();

        System.out.println("MaxOrMinNumberOfArray耗费时间" + AlgorithmAnalyzer.getAlgorithmTime(begin1, end1));
        System.out.println("MaxOrMinNumberOfArray耗费时间" + AlgorithmAnalyzer.getAlgorithmTime(begin2, end2));


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
        Integer[] randomArray = ArrayUtil.randomIntegerArray(50, 0, 100);
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
        names[0] = "操";
        names[1] = "你";
        names[2] = "妈";
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
        for (int[] ints : numbers_copy) {
            for (int anInt : ints) System.out.print(anInt + " ");
            System.out.println();
        }

    }

    /**
     * 对各种排序算法做时间分析，并运行二分查找寻找给定的元素检验排序是否正确
     */
    private static void analysisAlgorithmTime() {
        int arrSize=10000;//要排序的数组中总共有多少个元素
        int begin=1;//随机数生成的范围的起始
        int end=100000;//随机数生成范围的终止

        Integer[] arr = ArrayUtil.randomIntegerArray(arrSize, begin, end);//生成随机数组
        Integer[][] backUpArrays = ArrayUtil.getBackUpArrays(arr, 8);//返回一个未排序数组的集合，这些集合都是上面那个未排序数组的拷贝
        ArraySorter<Integer> sorter = new ArraySorter<>();//实例化排序器对象

        long beginMillis1 = AlgorithmAnalyzer.getCurrentTime();//以纳秒为单位返回系统单位时间
        sorter.setSortArray(backUpArrays[0]);
        sorter.betterBubbleSort();//排序器调用bubbleSort算法对数组进行排序
        long endMillis1 = AlgorithmAnalyzer.getCurrentTime();
        System.out.println("优化冒泡排序总共耗费时间:" + AlgorithmAnalyzer.getAlgorithmTime(beginMillis1, endMillis1) + "毫秒");

        long beginMillis2 = AlgorithmAnalyzer.getCurrentTime();//以纳秒为单位返回系统单位时间
        sorter.setSortArray(backUpArrays[1]);
        sorter.quickSort();//排序器调用快速排序法对数组排序
        long endMillis2 = AlgorithmAnalyzer.getCurrentTime();//以纳秒为单位返回系统单位时间
        System.out.println("快速排序总共耗费时间:" + AlgorithmAnalyzer.getAlgorithmTime(beginMillis2, endMillis2) + "毫秒");

        long beginMillis3 = AlgorithmAnalyzer.getCurrentTime();//以纳秒为单位返回系统单位时间
        sorter.setSortArray(backUpArrays[2]);
        sorter.bubbleSort();//排序器调用快速排序法对数组排序
        long endMillis3 = AlgorithmAnalyzer.getCurrentTime();//以纳秒为单位返回系统单位时间
        System.out.println("普通冒泡排序排序总共耗费时间:" + AlgorithmAnalyzer.getAlgorithmTime(beginMillis3, endMillis3) + "毫秒");

        long beginMillis4 = AlgorithmAnalyzer.getCurrentTime();//以纳秒为单位返回系统单位时间
        sorter.setSortArray(backUpArrays[3]);
        sorter.insertSort();//排序器调用快速排序法对数组排序
        long endMillis4 = AlgorithmAnalyzer.getCurrentTime();//以纳秒为单位返回系统单位时间
        System.out.println("插入排序排序总共耗费时间:" + AlgorithmAnalyzer.getAlgorithmTime(beginMillis4, endMillis4) + "毫秒");

        long beginMillis5 = AlgorithmAnalyzer.getCurrentTime();//以纳秒为单位返回系统单位时间
        sorter.setSortArray(backUpArrays[4]);
        sorter.betterSelectSort();//排序器调用选择排序法对数组排序
        long endMillis5 = AlgorithmAnalyzer.getCurrentTime();//以纳秒为单位返回系统单位时间
        System.out.println("选择排序排序总共耗费时间:" + AlgorithmAnalyzer.getAlgorithmTime(beginMillis5, endMillis5) + "毫秒");

        long beginMillis6=AlgorithmAnalyzer.getCurrentTime();
        sorter.setSortArray(backUpArrays[5]);
        sorter.heapSort();
        long endMillis6=AlgorithmAnalyzer.getCurrentTime();
        System.out.println("堆排序排序总共耗费时间:" + AlgorithmAnalyzer.getAlgorithmTime(beginMillis6, endMillis6) + "毫秒");

        long beginMillis7=AlgorithmAnalyzer.getCurrentTime();
        sorter.setSortArray(backUpArrays[6]);
        sorter.shellSort();
        long endMillis7=AlgorithmAnalyzer.getCurrentTime();
        System.out.println("希尔排序排序总共耗费时间:" + AlgorithmAnalyzer.getAlgorithmTime(beginMillis7, endMillis7) + "毫秒");

        long beginMillis8=AlgorithmAnalyzer.getCurrentTime();
        sorter.setSortArray(backUpArrays[7]);
        sorter.mergeSort();
        long endMillis8=AlgorithmAnalyzer.getCurrentTime();
        System.out.println("归并排序排序总共耗费时间:" + AlgorithmAnalyzer.getAlgorithmTime(beginMillis8, endMillis8) + "毫秒");

        //ArrayUtil.showBothArray(arr, backUpArrays[4]);

        /*System.out.println("输入要查找的数字：");
        int userNumber = 0;
        userNumber = scanner.nextInt();
        int index = binarySearch(backUpArrays[0], userNumber,false);//注意二分查找的条件是数组是有序的，必须提前排好序
        if (index != -1) System.out.println("找到了，下标为：" + index);
        else System.out.println("未找到");*/
    }

    public static void testBinarySearch(){
        Integer[]arr=ArrayUtil.randomIntegerArray(100,1,1000);
        System.out.println(Arrays.toString(arr));
        Arrays.sort(arr);
        while (true){
            System.out.println("输入查询数字:");
            int num=scanner.nextInt();
            if (ArrayUtil.binarySearchRecursion(arr,num,-1,arr.length)) {
                System.out.println("找到了");
            }
            else System.out.println("未找到 ");
            try {
                scanner.noMoreScan();
            } catch (NoMoreScanException e) {
                break;
            }
        }
    }


}
