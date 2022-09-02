//约瑟夫环问题升级版
package other.josephring;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

class Person {
    private Integer data;
    private Person next;

    public Person(Integer theData, Person theNext) {
        this.data = theData;
        this.next = theNext;
    }

    public Person() {
    }

    public Integer getData() {
        return data;
    }

    public void setData(Integer data) {
        this.data = data;
    }

    public Person getNext() {
        return next;
    }

    public void setNext(Person next) {
        this.next = next;
    }
}

class JosephRingList {
    private Person head = null;//循环链表的头指针

    private int listSize = 0;//循环链表中元素的个数

    public JosephRingList(int theListSize) {
        this.listSize = theListSize;
        this.head = new Person(1, null);
        Person currentPerson = head;
        for (int i = 1; i < listSize; i++) {
            currentPerson.setNext(new Person(i + 1, null));
            currentPerson = currentPerson.getNext();
        }
        currentPerson.setNext(head);//将链表的最后一个节点的指针域设为head，形成循环链表

    }

    public Person getHead() {
        return head;
    }

    public int getListSize() {
        return listSize;
    }

    /**
     * 删除指定节点
     *
     * @param previousPersonToBeErased 指向要删除节点的前驱节点
     */
    public void erase(Person previousPersonToBeErased) {
        previousPersonToBeErased.setNext(previousPersonToBeErased.getNext().getNext());
        listSize--;
    }

    public boolean empty() {
        return listSize == 0;
    }
}

class JosephRingManager {
    //单例设计模式的懒汉实现构造过程
    private static JosephRingManager instance = null;
    private static ArrayList<Integer> answer = new ArrayList<Integer>();//永远只有这一个数组来存储每一次求解
    private final Scanner scanner = new Scanner(System.in);
    private Person pointer;//指向正在报数的人
    //私有变量部分
    private JosephRingList ringList;//约瑟夫环形链表
    private int lastNumber;//人数到几停止

    //私有无参构造函数
    private JosephRingManager() {
    }

    public static JosephRingManager getInstance() {
        if (instance == null)
            instance = new JosephRingManager();
        return instance;
    }

    //私有setter接口
    private void setLastNumber(int lastNumber) {
        this.lastNumber = lastNumber;
    }

    private void setRingList(int size) {
        this.ringList = new JosephRingList(size);
        pointer = this.ringList.getHead();

    }

    //该类为单例设计模式，整个程序只有一个JosephRingManager对象，每一次想要求解约瑟夫问题，都需要用唯一的实例来调用这个runProcess接口设置问题参数
    public void runProcess() {
        System.out.println("请在本轮约瑟夫环问题的解决过程中输入约瑟夫环问题中人的数量：");
        int personSize = InputChecker.getInt();//人的数量
        System.out.println("请在本轮约瑟夫环问题的解决过程中输入约瑟夫环问题中人应该数到几才能停止：");
        int lastNumber = InputChecker.getInt();//人应该数到几停止
        instance.setRingList(personSize);
        instance.setLastNumber(lastNumber);
        int winnerIndex = solveProblem();//返回最终结果
        System.out.println("本轮约瑟夫环问题的胜利者是" + winnerIndex);
        answer.add(winnerIndex);
        ControlUtil.pause();//暂停程序
    }

    //求解约瑟夫问题的具体细节，返回胜利者的编号
    private int solveProblem() {
        int count = 1;//起初先从1开始数
        while (ringList.getListSize() != 1) {
            if (count == lastNumber - 1) {
                ringList.erase(pointer);//删除pointer节点之后的节点
                pointer = pointer.getNext();
                count = 1;
                continue;
            }
            pointer = pointer.getNext();
            count++;
        }
        return pointer.getData();
    }

    //显示每一次解决约瑟夫环的结果
    public void showRecord() {
        if (answer.size() == 0) {
            System.out.println("目前没有任何记录");
            ControlUtil.pause();
        } else {
            int i = 0;
            System.out.println("以下是每一次的记录：");
            for (Integer index : answer) {
                System.out.println("第" + (i + 1) + "次约瑟夫环问题结果：" + index);
                i++;
            }
            ControlUtil.pause();
        }
    }
}

public class JosephRingMain {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        JosephRingManager manager = JosephRingManager.getInstance();//单例设计模式必须通过静态函数得到内部创建的实例对象
        while (true) {
            while (true) {
                String choice = null;
                showMenu();
                System.out.print("请输入你的选择：");
                choice = InputChecker.getString();
                if (choice.equals("1")) {
                    manager.runProcess();
                } else if (choice.equals("2")) {
                    manager.showRecord();
                } else if (choice.equals("3")) {
                    exitProgram();
                } else {
                    System.out.println("你的输入不合法，请重新输入");
                }

            }

        }
    }

    public static void showMenu() {
        System.out.println("**************************************");
        System.out.println("*           1、开始解决约瑟夫环问题      *");
        System.out.println("*           2、显示每一次解决的答案      *");
        System.out.println("*           3、退出程序                *");
        System.out.println("**************************************");
    }

    public static void exitProgram() {
        String choice = null;
        System.out.println("确定退出吗？输入(y/n):");
        while (true) {
            choice = InputChecker.getString();
            if (choice.equals("y") || choice.equals("Y"))
                System.exit(0);
            else if (choice.equals("n") || choice.equals("N")) {
                return;
            } else {
                System.out.print("输入内容不正确，请重新输入:");
            }
        }

    }
}

class InputChecker {
    private static Scanner scanner = new Scanner(System.in);

    //只需要将要输入的数字传入函数，即可检测输入的是否正确
    public static int getInt() {
        int data = 0;
        while (true) {
            try {
                data = scanner.nextInt();
                scanner = new Scanner(System.in);//刷新缓冲
                return data;
            } catch (InputMismatchException e) {
                System.out.println("输入的数据不是整型，请重新输入：");
                scanner = new Scanner(System.in);//java中通过重新分配Scanner对象来达到刷新缓冲区的目的
            }
        }
    }

    public static String getString() {
        String data = null;
        while (true) {
            try {
                data = scanner.nextLine();
                if (data.equals("")) throw new NoSuchElementException("你输入的是一个空行，请重新输入");
                scanner = new Scanner(System.in);//刷新缓冲
                return data;
            } catch (NoSuchElementException e) {
                System.out.println(e.getMessage());
                scanner = new Scanner(System.in);
            }
        }
    }
}

class ControlUtil {
    private static Scanner scanner = new Scanner(System.in);

    public static void pause() {
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
}