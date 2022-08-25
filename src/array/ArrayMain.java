//本程序主要讲解java中的数组及其使用
package array;

import java.util.Arrays;
import java.util.Random;


/**
 * @author Mingxiang
 * @version 1.0
 */
public class ArrayMain {
    public static void main(String[] args) {
        numberOfRounds();
    }

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
    }

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

        //拷贝数组的第二种方法是使用 java.util.Arrays.copyOf()和copyOfRange()方法
        int[] bar = new int[]{1, 2, 3, 4, 5};
        int[] barCopy = Arrays.copyOf(bar, bar.length);//copyOf接受最初的数组和一个目标长度作为参数
        int[] barExtraCopy = Arrays.copyOf(bar, bar.length + 2);//若目标数组长度比最初数组长，那么copyOf将会用0或null填充新数组以达到想要的长度
        int[] rangeCopy = Arrays.copyOfRange(bar, 0, bar.length);//copyOfRange()接受一个开始索引（包括该索引）和一个结束索引（不包括该索引）以及一个想要的长度，如果必要的话，它也将进行填充补齐
    }

    static class Inner {
        public void set(int[] intArray) {
            System.out.println("成功将匿名数组传入函数");
        }
    }

    public static void anonymousArray() {
        //本函数主要讲解匿名数组
        int example1 = 12;
        int example2 = 13;
        Inner inner = new Inner();
        inner.set(new int[]{example1, example2});
    }

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

    //在数组中输出随机数
    public static void randomArray() {
        int[] randomArray = new int[6];
        Random randomGenerator = new Random(System.currentTimeMillis());
        for (int i = 0; i < randomArray.length; i++) {
            loopLabel:
            while (true) {
                int temp = randomGenerator.nextInt(1, 30);
                for (int j = 0; j < i; j++) {
                    if (temp == randomArray[j]) continue loopLabel;
                }
                randomArray[i] = temp;
                break;
            }
        }
        for (int i : randomArray) {
            System.out.print(i + " ");
        }
    }

    //利用二维数组实现输出回形数
    public static void numberOfRounds() {
        Pointer pointer = new Pointer(0, 0, 1);
        while (true) {
            try {
                pointer = pointer.goRight();
                pointer = pointer.goDown();
                pointer = pointer.goLeft();
                pointer = pointer.goUp();
            } catch (Obstruction exception) {
                break;
            }
        }
        pointer.showArray();
    }
}

class Pointer {
    private int x;
    private int y;
    private int beginNumber;

    int[][] array = new int[5][5];


    public Pointer(int x, int y, int beginNumber) {
        this.x = x;
        this.y = y;
        this.beginNumber = beginNumber;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Pointer goRight() throws Obstruction {
        if (array[x][y + 1] != 0) throw new Obstruction();//一旦发出阻碍异常，则代表整个回形数已经输出完毕了
        if (array[x][y] != 0) y++;//先判断自己所在的位置有没有元素
        while (true) {
            try {
                if (array[x][y + 1] != 0) break;
                array[x][y] = beginNumber;
            } catch (ArrayIndexOutOfBoundsException e) {
                break;
            }
            beginNumber++;
            y++;
        }
        return this;
    }

    public Pointer goLeft() throws Obstruction {
        if (array[x][y - 1] != 0) throw new Obstruction();//一旦发出阻碍异常，则代表整个回形数已经输出完毕了
        if (array[x][y] != 0) y--;//先判断自己所在的位置有没有元素
        while (true) {
            try {
                if (array[x][y - 1] != 0) break;
                array[x][y] = beginNumber;
            } catch (ArrayIndexOutOfBoundsException e) {
                break;
            }
            beginNumber++;
            y--;
        }
        return this;
    }


    public Pointer goUp() throws Obstruction {
        if (array[x - 1][y] != 0) throw new Obstruction();//一旦发出阻碍异常，则代表整个回形数已经输出完毕了
        if (array[x][y] != 0) x--;//先判断自己所在的位置有没有元素
        while (true) {
            try {
                if (array[x - 1][y] != 0) break;
                array[x][y] = beginNumber;
            } catch (ArrayIndexOutOfBoundsException e) {
                break;
            }
            beginNumber++;
            x--;
        }
        return this;
    }

    public Pointer goDown() throws Obstruction {
        if (array[x + 1][y] != 0) throw new Obstruction();//一旦发出阻碍异常，则代表整个回形数已经输出完毕了
        if (array[x][y] != 0) x++;//先判断自己所在的位置有没有元素
        while (true) {
            try {
                if (array[x + 1][y] != 0) break;
                array[x][y] = beginNumber;
            } catch (ArrayIndexOutOfBoundsException e) {
                break;
            }
            beginNumber++;
            x++;
        }
        return this;
    }

    public void showArray() {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + "\t");
            }
            System.out.println();
        }

    }
}

class Obstruction extends Exception {

}