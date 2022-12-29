//本程序主要讲解java中的数组及其使用
package arrayutil;

import analyzer.AlgorithmAnalyzer;

import java.util.Arrays;
import java.util.Scanner;


/**
 * 该类仅用于测试ArrayUtil工具类，不要在其他地方调用
 *
 * @author Mingxiang
 */
class ArrayUtilTester {
    private static final Scanner scanner = new Scanner(System.in);

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
        sorter.quickSort(1, backUpArrays[1].length);//排序器调用快速排序法对数组排序
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


}
