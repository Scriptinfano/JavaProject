package other.switchbox;

import java.util.*;

/**
 * 开关盒布线问题解决器
 *
 * @author Mingxiang
 * @date 2022/09/18
 */
final public class SwitchboxRoutingSolver {
    private static Scanner scanner = new Scanner(System.in);
    Stack<Integer> routingStack;//解决开关盒布线问题中必须要用到的栈
    ArrayList<NetGroup> netGroups;//存储问题所要求的网组
    ArrayList<Integer> switchBoxLayout;//布线盒的布局

    /**
     * 开关盒布线问题解决器的构造函数
     */
    public SwitchboxRoutingSolver() {
        routingStack = new Stack<>();
    }

    public static boolean checkRepeat(Object[] array) {
        Set<Object> set = new HashSet<Object>();
        Collections.addAll(set, array);
        if (set.size() != array.length) {
            return false;//有重复
        } else {
            return true;//不重复
        }
    }

    public static void main(String[] args) {

        SwitchboxRoutingSolver solver = new SwitchboxRoutingSolver();
        solver.run();
    }

    /**
     * 运行开关盒布线问题解决器（控制整个解决过程的流程）
     */
    public void run() {
        userInput();
        System.out.println("输入完毕");
        System.out.println("布线盒的布局如下");
        System.out.println(switchBoxLayout.toString());
        System.out.println("网组如下");
        for (NetGroup group : netGroups) {
            System.out.println(group.toString());
        }
    }

    /**
     * 获取用户输入的关于本问题的基本条件，例如管脚的布局和网组
     */
    private void userInput() {
        System.out.println("在该布线盒布线问题中，一共有几个管脚");
        int pinSize;
        while (true) {
            try {
                String userInput = scanner.nextLine();
                pinSize = Integer.parseInt(userInput);
                if (pinSize % 2 != 0) {
                    System.out.println("管脚的数量只能是偶数，不能是奇数，在下一行重新输入");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.print("输入的数据不合法，请重新输入：");
            }
        }
        int[] pinLayout = new int[pinSize];
        System.out.println("请以顺时针顺序输入管脚布局（每一个管脚编号中间用空格分开）");
        {
            int i = 0;
            while (i < pinSize) {
                try {
                    pinLayout[i] = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.out.print("输入的数据中有非整形数字，请从头开始输入：");
                    i = 0;
                    scanner = new Scanner(System.in);
                    continue;
                }
                i++;
            }
            scanner = new Scanner(System.in);
        }


        switchBoxLayout = new ArrayList<>(pinLayout.length);
        for (int j : pinLayout) switchBoxLayout.add(j);

        int[] netGroupArray = new int[pinLayout.length];
        int netGroupIndex = 0;
        for (int j = 0; j < pinLayout.length / 2; j++) {
            System.out.print("请输入第" + (j + 1) + "个网组（中间用空格分开）：");
            int k = 0;
            boolean flag = false;
            while (k < 2) {
                try {
                    netGroupArray[netGroupIndex] = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.out.print("输入的数据中有非整形数字，请从头开始输入：");
                    k = 0;
                    if (flag)
                        netGroupIndex--;
                    scanner = new Scanner(System.in);
                    continue;
                }
                k++;
                netGroupIndex++;
                flag = true;
            }
            scanner = new Scanner(System.in);
        }
        NetGroup[] netGroupList = new NetGroup[netGroupArray.length / 2];
        int j = 0;
        for (int i = 0; i < netGroupList.length; i++) {
            netGroupList[i] = new NetGroup(netGroupArray[j], netGroupArray[j + 1]);
            j += 2;
        }
        netGroups = new ArrayList<>(netGroupList.length);
        netGroups.addAll(Arrays.asList(netGroupList));
    }

}
