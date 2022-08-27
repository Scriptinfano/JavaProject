//本程序主要讲解java中的数组及其使用
package java.array;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;


/**
 * 排序工具类,包含对数组的一些排序方法
 *
 * @author Mingxiang
 * @date 2022/08/27
 */
class ArraySorter<T extends Comparable<T>> {

    //快速排序，调用时要指定左右边界
    public void quickSort(T[] array, int left, int right) {
        if (left < 0 || right > array.length || right - left <= 1) return;
        int i = left, j = right;
        T standard = array[i];
        while (i != j) {
            while (array[j].compareTo(standard) >= 0 && i < j) {
                j -= 1;
            }
            while (array[i].compareTo(standard) <= 0 && i < j) {
                i += 1;
            }
            if (i < j)
                swap(array, i, j);
        }
        swap(array, left, i);
        quickSort(array, left, i - 1);
        quickSort(array, i + 1, right);
    }


    //插入排序
    public void insertSort(T[] array) {
        for (int i = 1; i < array.length; i++) {
            T value = array[i];
            insert(array, i, value);
        }
    }

    private void insert(T[] array, int rightIndex, T value) {
        int i;
        for (i = rightIndex - 1; i >= 0 && value.compareTo(array[i]) < 0; i--) {
            array[i + 1] = array[i];
        }
        array[i + 1] = value;
    }

    //普通的冒泡循环
    public void bubbleSort(T[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j].compareTo(array[j + 1]) > 0) {
                    swap(array, j, j + 1);
                }
            }
        }

    }

    //能及时终止的冒泡循环
    public void betterBubbleSort(T[] array) {
        for (int i = array.length; i > 1 && betterBubble(array, i); i--) ;
    }

    private boolean betterBubble(T[] array, int n) {
        boolean swapped = false;
        for (int i = 0; i < n - 1; i++) {
            if (array[i].compareTo(array[i + 1]) > 0) {
                swap(array, i, i + 1);
                swapped = true;
            }
        }
        return swapped;
    }

    //在java中交换数组中的两个元素时，数组对象以及交换序号必须传入函数，
    private void swap(T[] array, int i, int j) {
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
     * 输入两个以纳秒为单位的long型值，返回之间的间隔时间，以毫秒为单位
     *
     * @param begin 开始
     * @param end   结束
     * @return {@link double} 返回时间间隔，时间单位是毫秒
     */
    private static double getAlgorithmTime(long begin, long end) {
        double time = (end - begin) / 1000000d;
        return time;
    }


    /**
     * 输出两个指定的数组
     *
     * @param arrayOne 一个数组
     * @param arrayTwo 两个数组
     */
    private static void showBothArray(Integer[] arrayOne, Integer[] arrayTwo) {
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
    private static Integer[][] getBackUpArrays(Integer[] array, int size) {
        Integer[][] backupArray = new Integer[size][];//保存没有排序的数组
        for (int i = 0; i < backupArray.length; i++) {
            backupArray[i] = array.clone();
        }
        return backupArray;
    }


    /**
     * 对各种排序算法做时间分析，并运行二分查找寻找给定的元素
     */
    public static void findNumber() {
        ArraySorter<Integer> sorter = new ArraySorter<Integer>();//实例化排序器对象
        Integer[] arr = randomArray(100, 1, 10000);//生成随机数组
        Integer[][] backUpArrays = getBackUpArrays(arr, 4);//返回一个未排序数组的集合，这些集合都是上面那个未排序数组的拷贝


        long beginMillis1 = System.nanoTime();//以纳秒为单位返回系统单位时间
        sorter.betterBubbleSort(backUpArrays[0]);//排序器调用bubbleSort算法对数组进行排序
        long endMillis1 = System.nanoTime();

        long beginMillis2 = System.nanoTime();//以纳秒为单位返回系统单位时间
        sorter.quickSort(backUpArrays[1], 0, arr.length - 1);//排序器调用快速排序法对数组排序
        long endMillis2 = System.nanoTime();//以纳秒为单位返回系统单位时间

        long beginMillis3 = System.nanoTime();//以纳秒为单位返回系统单位时间
        sorter.bubbleSort(backUpArrays[2]);//排序器调用快速排序法对数组排序
        long endMillis3 = System.nanoTime();//以纳秒为单位返回系统单位时间

        long beginMillis4 = System.nanoTime();//以纳秒为单位返回系统单位时间
        sorter.insertSort(backUpArrays[3]);//排序器调用快速排序法对数组排序
        long endMillis4 = System.nanoTime();//以纳秒为单位返回系统单位时间

        showBothArray(arr, backUpArrays[0]);

        System.out.println("优化冒泡排序总共耗费时间:" + getAlgorithmTime(beginMillis1, endMillis1) + "毫秒");
        System.out.println("快速排序总共耗费时间:" + getAlgorithmTime(beginMillis2, endMillis2) + "毫秒");
        System.out.println("普通冒泡排序排序总共耗费时间:" + getAlgorithmTime(beginMillis3, endMillis3) + "毫秒");
        System.out.println("插入排序排序总共耗费时间:" + getAlgorithmTime(beginMillis4, endMillis4) + "毫秒");


        System.out.println("输入要查找的数字：");
        int userNumber = 0;
        userNumber = scanner.nextInt();
        int index = binarySearch(backUpArrays[0], userNumber);//注意二分查找的条件是数组是有序的，必须提前排好序
        if (index != -1) System.out.println("找到了，下标为：" + index);
        else System.out.println("未找到");
    }


    /**
     * 输出给定数组
     *
     * @param arr 传入的要输出的数组
     */
    public static void showArray(Integer[] arr) {
        for (Integer i : arr)
            System.out.print(i + " ");
        System.out.println();
    }


    /**
     * 演示正确的数组初始化的方式
     */
    public static void arrayInitialize() {
        //数组正确的初始化方式
        int[] arr = new int[]{1, 2, 3};//一维数组静态初始化
        int[] arr2 = {12, 23, 23};//一维数组静态初始化
        int[] arr3 = new int[12];//一维数组动态初始化，此时要赋值只能一个一个赋值
        //二维数组正确的初始化方式
        int[][] arr4 = new int[3][2];//行数和列数都指定，但在java中行必须指定
        int[][] arr5 = {{1, 2, 3}, {12, 32}};
        int[][] arr6 = new int[23][];//可以仅指定行数不指定列数
    }

    //对象数组
    public static void objectArray() {
        //类对象数组的元素是对象的引用，每个元素的默认值是null，直到我们为其分配空间为止
        String[] names = new String[3];
        names[0] = new String("操");
        names[1] = new String("你");
        names[2] = new String("妈");
        for (String str : names) {
            System.out.print(str);
        }
        System.out.println();

        String[] actions = {"run", "swim", "fuck"};//java数组支持C风格的数组初始化

        int a = actions.length;
    }

    //测试数组中可能抛出的异常
    public static void catchArrayException() {
        //若试图访问一个超出数组范围的元素，则会生成一个ArrayIndexOutOfBounds的异常，属于RuntimeException
        String[] states = new String[3];
        try {
            states[0] = "California";
            states[1] = "Oregon";
            states[2] = "NewYork";
            states[3] = "shit";
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("捕获到异常:" + e.getMessage());
        }
        //空指针异常
        try {
            Integer[] array = new Integer[3];
            array = null;//将引用置空
            System.out.println(array[2]);//试图通过空引用访问堆内存会抛出空指针异常
        } catch (NullPointerException e) {
            System.out.println("捕获到异常:" + e.getMessage());
        }


    }

    //拷贝数组中的元素
    public static void copyArray() {
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
        for (String str : names) {
            System.out.print(str);
        }
        System.out.println();

        //拷贝数组的第二种方法是使用 java.util.Arrays.copyOf()和copyOfRange()方法
        int[] bar = new int[]{1, 2, 3, 4, 5};
        int[] barCopy = Arrays.copyOf(bar, bar.length);//copyOf接受最初的数组和一个目标长度作为参数
        int[] barExtraCopy = Arrays.copyOf(bar, bar.length + 2);//若目标数组长度比最初数组长，那么copyOf将会用0或null填充新数组以达到想要的长度
        int[] rangeCopy = Arrays.copyOfRange(bar, 0, bar.length);//copyOfRange()接受一个开始索引（包括该索引）和一个结束索引（不包括该索引）以及一个想要的长度，如果必要的话，它也将进行填充补齐
        int[] barCloneCopy = bar.clone();
        for (int i : barCloneCopy) System.out.print(i + " ");
        System.out.println();
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

    //数组的反转
    public static void reverseArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
        for (int i = 0; i < array.length / 2; i++) {
            int temp = array[i];
            array[i] = array[array.length - i - 1];
            array[array.length - i - 1] = temp;
        }
        for (int i : array)
            System.out.print(i + " ");
        System.out.println();

    }

    static class Inner {
        public void set(int[] intArray) {
            System.out.println("成功将匿名数组传入函数");
        }
    }

    //测试匿名数组
    public static void anonymousArray() {
        //本函数主要讲解匿名数组
        int example1 = 12;
        int example2 = 13;
        Inner inner = new Inner();
        inner.set(new int[]{example1, example2});
    }

    //数组元素的默认初始化
    public static void defaultArrayElement() {
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

    //输出十行杨辉三角
    public static void pascalTriangle() {
        int[][] pascalTriangle = new int[10][];
        for (int i = 0; i < pascalTriangle.length; i++) {
            pascalTriangle[i] = new int[i + 1];
            int lastIndex = pascalTriangle[i].length - 1;
            pascalTriangle[i][0] = 1;
            pascalTriangle[i][lastIndex] = 1;
        }
        for (int i = 2; i < pascalTriangle.length; i++) {
            for (int j = 1; j < i; j++) {
                pascalTriangle[i][j] = pascalTriangle[i - 1][j - 1] + pascalTriangle[i - 1][j];
            }
        }
        for (int i = 0; i < pascalTriangle.length; i++) {
            for (int j = 0; j < pascalTriangle[i].length; j++) {
                System.out.print(pascalTriangle[i][j] + "\t");
            }
            System.out.println();
        }
    }

    //该方法返回一个整型对象数组，其中的元素随机且不重复，范围在begin到end之间
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

    //二分查找法
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

    //利用二维数组实现输出回形数
    public static void numberOfRounds() {
        CircularDigitalMatrix circularDigitalMatrix = new CircularDigitalMatrix(5, 5, 56);
        circularDigitalMatrix.showMatrix();
    }

    //找出数组元素中最大的数和最小的数
    public static void MaxOrMinNumberOfArray() {
        Integer[] array = randomArray(10, 10, 99);//返回一个不包含重复元素的随机数组
        System.out.println("数组元素概览：");
        for (int i : array) System.out.print(i + " ");
        System.out.println();

        int max = array[0], min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) max = array[i];
            if (array[i] < min) min = array[i];
        }
        System.out.println("数组最大元素为：" + max);
        System.out.println("数组最小元素为：" + min);

    }

    //Arrays工具类的使用
    public static void theUsageOfArrays() {
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
        Integer[] randomArray = randomArray(50, 0, 100);
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


}


/**
 * 回形数字矩阵类
 * <p>输出一个指定规格的回形矩阵</p>
 *
 * @author Mingxiang
 * @date 2022/08/27
 */
class CircularDigitalMatrix {
    private int x;//数组元素横坐标

    private int y;//数组元素纵坐标
    private int beginNumber;//从beginNumber这个数字开始输出回形数

    private int[][] array;//内置的二维数组

    /**
     * <code>Obstruction异常类</code>
     * 回形数组无法再继续赋值输出时会抛出此异常，表示整个数组输出完毕，提示中断私有run()方法
     */
    class Obstruction extends Exception {
    }

    /**
     * 这个构造函数可以指定从哪开始输出，并从多少开始输出
     * @param oneDimension 指定回形数数组的一维量
     * @param twoDimension 指定回形数组的二维量
     * @param beginNumber  表示从几开始输出回形数序列
     */
    public CircularDigitalMatrix(int oneDimension, int twoDimension, int beginNumber) {
        array = new int[oneDimension][];
        for (int i = 0; i < oneDimension; i++) {
            array[i] = new int[twoDimension];
        }
        this.x = 0;//开始输出的起始横坐标
        this.y = 0;//开始输出的起始纵坐标
        this.beginNumber = beginNumber;//从几开始输出
    }

    /**
     * 这个函数开始控制整个回形数序列生成的流程，调用其他四个私有接口完成回形数组的赋值
     */
    private void run() {
        while (true) {
            try {
                this.goRight();
                this.goDown();
                this.goLeft();
                this.goUp();
            } catch (Obstruction exception) {
                break;
            }
        }
    }

    /**
     * 这个方法总是从左向右地为回形数组赋值
     */
    private void goRight() throws Obstruction {
        if (array[x][y + 1] != 0) throw new Obstruction();//一旦发出阻碍异常，则代表整个回形数已经输出完毕了
        if (array[x][y] != 0) y++;//先判断自己所在的位置有没有元素
        while (true) {
            try {
                array[x][y] = beginNumber;
                if (array[x][y + 1] != 0) {
                    beginNumber++;
                    break;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                beginNumber++;
                break;
            }
            beginNumber++;
            y++;
        }
    }

    /**
     * 这个方法总是从右向左地为回形数组赋值
     */
    private void goLeft() throws Obstruction {
        if (array[x][y - 1] != 0) throw new Obstruction();//一旦发出阻碍异常，则代表整个回形数已经输出完毕了
        if (array[x][y] != 0) y--;//先判断自己所在的位置有没有元素
        while (true) {
            try {
                array[x][y] = beginNumber;
                if (array[x][y - 1] != 0) {
                    beginNumber++;
                    break;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                beginNumber++;

                break;
            }
            beginNumber++;
            y--;
        }
    }

    /**
     * 这个方法总是从下向上地为回形数组赋值
     */
    private void goUp() throws Obstruction {
        if (array[x - 1][y] != 0) throw new Obstruction();//一旦发出阻碍异常，则代表整个回形数已经输出完毕了
        if (array[x][y] != 0) x--;//先判断自己所在的位置有没有元素
        while (true) {
            try {
                array[x][y] = beginNumber;
                if (array[x - 1][y] != 0) {
                    beginNumber++;
                    break;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                beginNumber++;

                break;
            }
            beginNumber++;
            x--;
        }
    }

    /**
     * 这个方法总是从上向下地为回形数组赋值
     */
    private void goDown() throws Obstruction {
        if (array[x + 1][y] != 0) throw new Obstruction();//一旦发出阻碍异常，则代表整个回形数已经输出完毕了
        if (array[x][y] != 0) x++;//先判断自己所在的位置有没有元素
        while (true) {
            try {
                array[x][y] = beginNumber;
                if (array[x + 1][y] != 0) {
                    beginNumber++;
                    break;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                beginNumber++;
                break;
            }
            beginNumber++;
            x++;
        }
    }

    /**
     * 赋值整个回形数组并将数组输出出来
     */
    public void showMatrix() {
        this.run();//调用私有接口run方法完成赋值操作

        //输出整个回形数组
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + "\t");
            }
            System.out.println();
        }

    }
}

public class ArrayMain {
    public static void main(String[] args) {

    }
}
