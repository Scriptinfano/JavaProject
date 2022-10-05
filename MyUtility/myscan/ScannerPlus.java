package myscan;

import java.util.*;

/**
 * Scanner升级版，增加类型输入不正确时可自动报错的功能
 *
 * @author Mingxiang
 * @date 2022/09/18
 */
public class ScannerPlus {
    private Scanner scanner = new Scanner(System.in);

    private static boolean checkRepeat(Object[] array) {
        Set<Object> set = new HashSet<Object>();
        Collections.addAll(set, array);
        if (set.size() != array.length) {
            return false;//有重复
        } else {
            return true;//不重复
        }
    }

    //检查数组是否有负数
    private static boolean hasNegative(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] < 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取用户输入的整数，当用户的输入不合法时要求重新输入
     *
     * @return int
     */
    public int nextInt() {
        int intData;
        while (true) {
            try {
                String userInput = scanner.nextLine();
                intData = Integer.parseInt(userInput);
                break;
            } catch (NumberFormatException e) {
                System.out.print("输入的数据不是整型int，请重新输入：");
                throw e;
            } catch (NoSuchElementException e) {
                System.out.print("被请求的元素不存在，请重新输入：");
            } catch (IllegalStateException e) {
                System.out.print("输入的流被关闭，请重新输入：");
                scanner = new Scanner(System.in);
            }
        }
        return intData;
    }

    /**
     * 获取用户输入的双浮点型数字，当用户的输入不合法时要求重新输入
     *
     * @return double
     */
    public double nextDouble() {
        double doubleData;
        while (true) {
            try {
                String userInput = scanner.nextLine();
                doubleData = Double.parseDouble(userInput);
                break;
            } catch (NumberFormatException e) {
                System.out.print("输入的数据不是双浮点类型Double，请重新输入：");
            } catch (NoSuchElementException e) {
                System.out.print("被请求的元素不存在，请重新输入：");
            } catch (IllegalStateException e) {
                System.out.print("输入的流被关闭，请重新输入：");
                scanner = new Scanner(System.in);
            }
        }
        return doubleData;
    }

    /**
     * 获取用户输入的单浮点数据，当用户的输入不合法时要求重新输入
     *
     * @return float
     */
    public float nextFloat() {
        float floatData;
        while (true) {
            try {
                String userInput = scanner.nextLine();
                floatData = Float.parseFloat(userInput);
                break;
            } catch (NumberFormatException e) {
                System.out.print("输入的数据不是单浮点类型float，请重新输入：");
            } catch (NoSuchElementException e) {
                System.out.print("被请求的元素不存在，请重新输入：");
            } catch (IllegalStateException e) {
                System.out.print("输入的流被关闭，请重新输入：");
                scanner = new Scanner(System.in);
            }
        }
        return floatData;
    }

    /**
     * 获取用户输入的长整型数字，当用户的输入不合法时要求重新输入
     *
     * @return long
     */
    public long nextLong() {
        long longData;
        while (true) {
            try {
                String userInput = scanner.nextLine();
                longData = Long.parseLong(userInput);
                break;
            } catch (NumberFormatException e) {
                System.out.print("输入的数据不是长整型long，请重新输入：");
            } catch (NoSuchElementException e) {
                System.out.print("被请求的元素不存在，请重新输入：");
            } catch (IllegalStateException e) {
                System.out.print("输入的流被关闭，请重新输入：");
                scanner = new Scanner(System.in);
            }
        }
        return longData;
    }

    /**
     * 获取用户输入的字符型数字
     *
     * @return char
     */
    public char nextChar() {
        char charData;
        while (true) {
            try {
                String userInput = scanner.nextLine();
                charData = userInput.charAt(0);
                break;
            } catch (NumberFormatException e) {
                System.out.print("输入的数据不是字符型char，请重新输入：");
            } catch (NoSuchElementException e) {
                System.out.print("被请求的元素不存在，请重新输入：");
            } catch (IllegalStateException e) {
                System.out.print("输入的流被关闭，请重新输入：");
                scanner = new Scanner(System.in);
            }
        }
        return charData;
    }

    public static void pause() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("敲enter键或键入任意内容以继续：");
        while (true) {
            try {
                String anyKey = scanner.nextLine();
                if (anyKey.equals("")) throw new NoSuchElementException();//什么也不输入也可以继续
                scanner = new Scanner(System.in);
            } catch (NoSuchElementException e) {
                break;
            }
        }
    }

    /**
     * 要求用户输入一串数字（每个数字用空格分开），并将这些数字保存到内部创建的数组中，并返回这个数组
     *
     * @param size        用户需要输入多少个数字
     * @param hasNegative 是否允许用户的输入有重复，为true时允许有重复，反之则不允许有重复
     * @param hasRepeat   是否允许用户的输入存在负数，为true时允许有负数，反之则不允许
     * @return {@link int[]}
     */
    public int[] nextIntArray(int size, boolean hasNegative, boolean hasRepeat) {
        int[] array = new int[size];
        while (true) {
            int i = 0;
            while (i < size) {
                try {
                    array[i] = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.out.print("管脚的编号必须是整数，请从头开始输入：");
                    i = 0;
                    scanner = new Scanner(System.in);
                    continue;
                }
                i++;
            }
            scanner = new Scanner(System.in);
            if (!hasRepeat) {
                if (!checkRepeat(Arrays.stream(array).boxed().toArray(Integer[]::new))) {
                    System.out.println("输入的数字中不能有重复，请重新输入");
                    continue;
                }
            }
            if (!hasNegative) {
                if (hasNegative(array)) {
                    System.out.println("输入的数字中不能有负数，请重新输入");
                    continue;
                }
            }
            break;
        }
        return array;
    }

    /**
     * 获取用户输入的字符串，当用户的输入不合法时要求重新输入
     *
     * @return {@link String}
     */
    public String nextLine() {
        String lineData;
        while (true) {
            try {
                lineData = scanner.nextLine();
                if (lineData.equals("")) throw new NoSuchElementException("你输入的是一个空行，请重新输入");
                break;
            } catch (NoSuchElementException e) {
                System.err.println(e.getMessage());
            } catch (IllegalStateException e) {
                System.out.print("输入的流被关闭，请重新输入：");
                scanner = new Scanner(System.in);
            }
        }
        return lineData;
    }

    /**
     * 用户只能在指定的范围之内输入选择（选择以整数的形式出现时可以使用），并以字符串的形式返回用户的选择
     *
     * @param selectMin 最小选择范围
     * @param selectMax 最大选择范围
     * @return {@link String} 用户输入的内容，这个内容一定是数字，且以字符串的形式
     */
    public String nextSelectionByString(int selectMin, int selectMax) {
        while (true) {
            String userIn = nextLine();
            int choice;
            try {
                choice = Integer.parseInt(userIn);
            } catch (NumberFormatException e) {
                System.out.println("输入错误，请输入正确的选项");
                continue;
            }
            if (choice < selectMin || choice > selectMax)
                System.out.println("你输入的内容不在范围之内，请重新输入");
            else return String.valueOf(choice);
        }
    }

    /**
     * 用户只能在指定的范围之内输入选择（选择以整数的形式出现时可以使用），并以整数的形式返回用户的选择
     *
     * @param selectMin 最小选择范围
     * @param selectMax 最大选择范围
     * @return int 用户输入的整型
     */
    public int nextSelectionByInt(int selectMin, int selectMax) {
        String select = nextSelectionByString(selectMin, selectMax);
        return Integer.parseInt(select);
    }
}
