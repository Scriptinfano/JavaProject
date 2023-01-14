package Math.mathmetic;

/**
 * 回形数字矩阵类
 * <p>输出一个指定规格的回形矩阵</p>
 *
 * @author Mingxiang
 * @date 2022/08/27
 */
public class CircularDigitalMatrix {
    private int x;//数组元素横坐标

    private int y;//数组元素纵坐标
    private int beginNumber;//从beginNumber这个数字开始输出回形数

    private final int[][] array;//内置的二维数组

    /**
     * 这个构造函数可以指定从哪开始输出，并从多少开始输出
     *
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
        for (int[] ints : array) {
            for (int anInt : ints) {
                System.out.print(anInt + "\t");
            }
            System.out.println();
        }

    }

    @Override
    public String toString() {
        this.run();//调用私有接口run方法完成赋值操作
        StringBuilder context = new StringBuilder();
        //输出整个回形数组
        for (int[] ints : array) {
            for (int anInt : ints) {
                context.append(anInt);
                context.append("\t");
            }
            context.append("\n");
        }
        return context.toString();
    }

    /**
     * <code>Obstruction异常类</code>
     * 回形数组无法再继续赋值输出时会抛出此异常，表示整个数组输出完毕，提示中断私有run()方法
     */
    static class Obstruction extends Exception {
    }
}
