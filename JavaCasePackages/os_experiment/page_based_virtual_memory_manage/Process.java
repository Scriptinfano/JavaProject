package os_experiment.page_based_virtual_memory_manage;

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
    private Instruction[] instructions;


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
        initInstructions();
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

    /**
     * 在此处设定该进程应该执行的所有指令
     */
    private void initInstructions() {
        instructions = new Instruction[12];
        //创建进程所需要的所有指令
        instructions[0] = new Instruction(1, 0, 70, "加指令");
        instructions[1] = new Instruction(2, 1, 50, "减指令");
        instructions[2] = new Instruction(3, 2, 15, "乘指令");
        instructions[3] = new Instruction(4, 3, 21, "存指令");
        instructions[4] = new Instruction(5, 0, 56, "取指令");
        instructions[5] = new Instruction(2, 6, 40, "减指令");
        instructions[6] = new Instruction(6, 4, 53, "移位指令");
        instructions[7] = new Instruction(1, 5, 23, "加指令");
        instructions[8] = new Instruction(4, 1, 37, "存指令");
        instructions[9] = new Instruction(5, 2, 78, "取指令");
        instructions[10] = new Instruction(1, 4, 1, "加指令");
        instructions[11] = new Instruction(4, 6, 84, "存指令");


    }

    public Instruction[] getInstructions() {
        return instructions;
    }


}
