package os_experiment.page_based_virtual_memory_manage;

import java.util.Random;
import java.util.random.RandomGenerator;

/**
 * 作业模拟类
 *
 * @author Mingxiang
 */
public class Process {


    /**
     * 进程名
     */
    private final String name;
    /**
     * 作业总的页面数
     */
    private final int totalPage;
    /**
     * 向操作系统申请的页面数
     */
    private final int applyPageCount;
    /**
     * 进程第一次运行时需要先载入主存的页面
     */
    private final int[] initPageCodes;
    /**
     * 该进程需要依次执行的指令
     */
    private final Instruction[] instructions;


    /**
     * 创建一个作业，使用该构造函数之前，确保totalPage>applyPageCount，initPageCode数组的元素数量必须和totalPage相同，且其中的最大页框号必须小于主存总的页框数
     *
     * @param totalPage      作业总的页面数
     * @param applyPageCount 向操作系统申请的页面数
     * @param name           进程的名字
     * @param initPageCodes  当该进程首次载入主存时，首先要放到主存中的逻辑页号
     */
    private Process(String name, int totalPage, int applyPageCount, int[] initPageCodes) {
        this.name = name;
        this.totalPage = totalPage;
        this.applyPageCount = applyPageCount;
        this.initPageCodes = initPageCodes;

        int instructionNum = totalPage * Page.pageSize;
        instructions = new Instruction[instructionNum];
        RandomGenerator generator = new Random(System.currentTimeMillis());

        //创建进程所需要的所有指令
        String[] names = {"加指令", "减指令", "乘指令", "存指令", "取指令", "移位指令"};
        for (int i = 0; i < instructionNum; i++) {
            int randomNameIndex = generator.nextInt(0, names.length);
            instructions[i] = new Instruction(i % totalPage, i % Page.pageSize, names[randomNameIndex]);
        }
        shuffle(instructions);
    }

    /**
     * 打乱指令序列的顺序
     *
     * @param instructions 指示
     */
    private void shuffle(Instruction[] instructions) {
        for (int i = instructions.length - 1; i >= 0; --i) {
            RandomGenerator generator = new Random(System.currentTimeMillis());
            int random = generator.nextInt(0, instructions.length);
            Instruction temp = instructions[random % (i + 1)];
            instructions[random % (i + 1)] = instructions[i];
            instructions[i] = temp;
        }

    }

    /**
     * 创建进程实例，若发生以下三种情况之一，则抛出{@link IllegalArgumentException}异常
     *
     * @param name           进程的名字
     * @param totalPage      进程的总页数
     * @param applyPageCount 系统为该进程分配的页框数
     * @param initPageCodes  初始化页面逻辑页号序列
     * @return {@link Process} 返回的进程实例
     */
    public static Process createInstance(String name, int totalPage, int applyPageCount, int[] initPageCodes) {
        if (totalPage < applyPageCount)
            throw new IllegalArgumentException("系统为该进程分配的页框数大于了作业的总页数");
        if (initPageCodes.length > applyPageCount)
            throw new IllegalArgumentException("初始载入页序列的长度大于了系统为该进程分配的最大页框数");
        for (int i = 0; i < initPageCodes.length; i++) {
            if (i != initPageCodes[i]) throw new IllegalArgumentException("初始载入页面必须连续且必须从0号页开始，默认使得进程运行的代码段都放在开头");
        }
        return new Process(name, totalPage, applyPageCount, initPageCodes);
    }

    /**
     * 得到该作业第一次载入主存时应加载的页号序列
     *
     * @return {@link int[]} 页号序列
     */
    public int[] getInitPageCode() {
        return initPageCodes;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public int getApplyPageCount() {
        return applyPageCount;
    }

    public Instruction[] getInstructions() {
        return instructions;
    }


}
