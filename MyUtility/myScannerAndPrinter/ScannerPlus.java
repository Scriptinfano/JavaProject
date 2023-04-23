package myScannerAndPrinter;

import java.util.*;

/**
 * Scanner升级版，增加类型输入不正确时可自动报错的功能
 *
 * @author Mingxiang
 */
public class ScannerPlus {
    private Scanner scanner = new Scanner(System.in);

    /**
     * 检查数组中是否有重复的元素
     *
     * @param array 待检查的数组，如果该数组存放的是自定义类对象，请确保该类重写了hashCode()与equals()方法，否则无法使用此函数确保该数组中是否有重复的元素
     * @return boolean 若为true则说明没有重复的元素，反之则有重复的元素
     */
    private static boolean checkRepeat(Object[] array) {
        Set<Object> set = new HashSet<>();
        Collections.addAll(set, array);
        return set.size() == array.length;
    }

    /**
     * 检查数组中是否有负数
     *
     * @param array 待检查的数组
     * @return boolean 若为true则说明有负数，反之则没有负数
     *///检查数组是否有负数
    private static boolean hasNegative(int[] array) {
        for (int j : array) {
            if (j < 0) {
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
            } catch (NoSuchElementException e) {
                System.out.print("你没有输入任何数据，请重新输入");
            } catch (IllegalStateException e) {
                throw new IllegalArgumentException("输入的流被关闭，无法再次输入");
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
                System.out.print("你没有输入任何数据，请重新输入");
            } catch (IllegalStateException e) {
                throw new IllegalArgumentException("输入的流被关闭，无法再次输入");
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
                System.out.print("你没有输入任何数据，请重新输入");
            } catch (IllegalStateException e) {
                throw new IllegalArgumentException("输入的流被关闭，无法再次输入");
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
                System.out.print("你没有输入任何数据，请重新输入");
            } catch (IllegalStateException e) {
                throw new IllegalArgumentException("输入的流被关闭，无法再次输入");
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
                if (userInput.length() > 1) {
                    System.out.println("请输入字符，不要输入字符串");
                    continue;
                }
                charData = userInput.charAt(0);
                break;
            } catch (NoSuchElementException e) {
                System.out.print("你没有输入任何数据，请重新输入");
            } catch (IllegalStateException e) {
                throw new IllegalArgumentException("输入的流被关闭，无法再次输入");
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
     * 下一个int数组
     * 要求用户输入一串数字（每输入一个数字按一次换行），并将这些数字保存到内部创建的数组中，并返回这个数组
     *
     * @param size        用户需要输入多少个数字
     * @param hasNegative 是否允许用户的输入存在负数，为true时允许有负数，反之则不允许
     * @param hasRepeat   是否允许用户的输入有重复，为true时允许有重复，反之则不允许有重复
     * @return {@link int[]}
     * @throws IllegalArgumentException 非法参数异常，说明数组的大小传入有误
     */
    public int[] nextIntArray(int size, boolean hasNegative, boolean hasRepeat) throws IllegalArgumentException {
        if (size <= 0)
            throw new IllegalArgumentException("数组的大小不能小于等于0，请重新输入");
        int[] array = new int[size];
        while (true) {
            int i = 0;
            while (i < size) {
                array[i] = nextInt();
                i++;
            }
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
                if (lineData.equals(""))
                    throw new NoSuchElementException("你输入的是一个空行，请重新输入");
                break;
            } catch (NoSuchElementException e) {
                System.err.println(e.getMessage());
            } catch (IllegalStateException e) {
                throw new IllegalArgumentException("输入的流被关闭，无法再次输入");
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
                System.out.println("你输入的不是整型，请按要求输入");
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

    /**
     * 用户输入的数字被要求大于或小于一个数时用到，如果upOrDown为true，则limit表示上界，反之则表示下界，当户
     * 的输入越界后输出指定的语句后要重新输入直到输入符合要求的数据
     *
     * @param upOrDown upOrDown为true，则limit表示上界，反之则表示下界
     * @param limit    具体的上界或下界
     * @param message  要求用户重新输入时显示的消息
     * @return double 返回用户正确的输入
     */
    public double nextDoubleWithLimit(boolean upOrDown, int limit, String message) {
        double theData;
        while (true) {
            theData = nextDouble();
            if (upOrDown) {
                if (theData >= limit) {
                    System.out.println(message);
                } else break;
            } else {
                if (theData <= limit) {
                    System.out.println(message);
                } else break;
            }
        }
        return theData;
    }

    public int nextIntWithLimit(boolean upOrDown, int limit, String message) {
        int theData;
        while (true) {
            theData = nextInt();
            if (upOrDown) {
                if (theData >= limit) {
                    System.out.println(message);
                } else break;
            } else {
                if (theData <= limit) {
                    System.out.println(message);
                } else break;
            }
        }
        return theData;
    }


    /**
     * 该函数询问用户是否需要更多输入，当用户回答是的时候不做任何操作，当回答不的时候抛出异常指示外界程序执行某些特殊操作停止录入数据
     *
     * @throws NoMoreScanException 指示没有更多输入的异常
     */
    public void noMoreScan() throws NoMoreScanException {
        IOTransformer.printer.print("你是否还需要继续输入，继续输入请按y，不想输入了请按n：");
        while (true) {
            String message = nextLine();
            if (message.equals("y")) {
                break;
            } else if (message.equals("n")) {
                throw new NoMoreScanException();
            } else {
                IOTransformer.printer.print("你的输入不合要求，请重新输入：");
            }
        }
    }

    /**
     * 清除缓冲区中所有数据
     */
    public void flush() {
        scanner = new Scanner(System.in);
    }

    public void close() {
        scanner.close();
    }

}
