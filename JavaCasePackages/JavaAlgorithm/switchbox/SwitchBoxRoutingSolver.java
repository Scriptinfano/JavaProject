//解决开关盒布线问题

package JavaAlgorithm.switchbox;

import myScannerAndPrinter.ScannerPlus;

import java.util.*;

/**
 * 开关盒布线问题解决器
 *
 * @author Mingxiang
 * @date 2022/09/18
 */
final public class SwitchBoxRoutingSolver {
    private static Scanner scanner = new Scanner(System.in);
    private static final ScannerPlus scannerPlus = new ScannerPlus();
    private final Stack<Integer> routingStack;//解决开关盒布线问题中必须要用到的栈
    private ArrayList<NetGroup> netGroups;//存储问题所要求的网组
    private ArrayList<Integer> switchBoxLayout;//布线盒的布局

    /**
     * 开关盒布线问题解决器的构造函数
     */
    public SwitchBoxRoutingSolver() {
        routingStack = new Stack<>();
    }


    public static void main(String[] args) {

        SwitchBoxRoutingSolver solver = new SwitchBoxRoutingSolver();
        solver.run();
    }

    /**
     * 运行开关盒布线问题解决器（控制整个解决过程的流程）
     */
    public void run() {
        userInput();
        for (Integer pinIndex : switchBoxLayout) {
            if (routingStack.isEmpty())
                routingStack.add(pinIndex);
            else {
                if (netGroupMatch(pinIndex, routingStack.peek())) {
                    routingStack.pop();
                } else {
                    routingStack.push(pinIndex);
                }
            }
        }
        System.out.println("运行结果：");
        if (routingStack.isEmpty()) {
            System.out.print("成功匹配");
        } else {
            System.out.print("失败匹配");
        }
    }

    /**
     * 检测当前管脚编号是否和栈顶的编号匹配（两个编号属于一个网组）
     *
     * @param currentIndex 当前正在处理的管脚编号
     * @param stackIndex   栈顶的管脚编号
     * @return boolean 如果匹配则返回true,不匹配则返回false
     */
    private boolean netGroupMatch(Integer currentIndex, Integer stackIndex) {
        NetGroup self1 = new NetGroup(currentIndex, stackIndex);
        NetGroup self2 = new NetGroup(stackIndex, currentIndex);
        for (NetGroup other : netGroups) {
            if (other.equals(self1))
                return true;
            if (other.equals(self2))
                return true;
        }
        return false;
    }

    /**
     * 获取用户输入的关于本问题的基本条件，例如管脚的布局和网组
     */
    private void userInput() {
        System.out.println("在该布线盒布线问题中，一共有几个管脚");
        int pinSize;
        while (true) {
            pinSize = scannerPlus.nextInt();
            if (pinSize % 2 != 0) {
                System.out.print("管脚的数量只能是偶数，不能是奇数，请重新输入");
            } else break;
        }

        System.out.println("请以顺时针顺序输入管脚布局（每一个管脚编号中间用空格分开）");
        int[] pinLayout = scannerPlus.nextIntArray(pinSize, false, false);

        switchBoxLayout = new ArrayList<>(pinLayout.length);
        for (int j : pinLayout) switchBoxLayout.add(j);

        //TODO 输入网组的设计还可以再优化一下，比如说从已经取得的管脚中不断选择两个来组成每一个网组
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
