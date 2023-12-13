package os_experiment.page_based_virtual_memory_manage;

/**
 * CPU模拟类
 *
 * @author Mingxiang
 */
public class CPU {
    /**
     * CPU访问主存的引用
     */
    private final RAM ram;

    /**
     * CPU访问外存的引用
     */
    private final Disk disk;

    /**
     * 指定CPU管理的主存和外存构建CPU实例
     *
     * @param ram  主存
     * @param disk 外存
     */
    public CPU(RAM ram, Disk disk) {
        this.ram = ram;
        this.disk = disk;
    }
    /**
     * 运行进程所包含的指令，对进程中的指令所在的页进行调度，返回本次调度的缺页率
     */
    public float run() {
        ram.initProcess(disk.getProcess());//主存初始化内部进程，将进程中指定的初始化页面载入主存（即进程第一次运行所需要的页面放入主存）
        Instruction[] instructions = disk.getProcess().getInstructions();//从主存中将指令序列取出来
        float lackPageSum = 0;
        //分别获得指令序列中的指令
        for (int i = 0; i < instructions.length; i++) {
            int pageCode = instructions[i].getPageCode();//取得当前指令所在页的逻辑页号
            boolean inPage = ram.hasPage(pageCode);//查找指令所在的页面是否在主存中
            boolean isWritable = instructions[i].isWriteabilityInstruction();//指令是否为写入性指令
            if (inPage) {
                //页面在主存中
                int pageFrameCode = ram.getPageFrameCode(pageCode);//主存根据逻辑页号取得页框号
                int address = pageFrameCode * Page.pageSize + instructions[i].getInPageOffset();//计算指令在主存中的绝对地址

                if (isWritable) {
                    //指令是写入性的指令，置指令所在的页面的修改标志位为1，当下一次该页面被换出时将被写回外存
                    ram.setPageModified(pageCode, true);
                    System.out.println("第" + (i + 1) + "条名为" + instructions[i].getName() + "的指令为写入性指令，其所在的逻辑页号为" + pageCode + "的页面在主存的第" + pageFrameCode + "号页框中，该指令的逻辑地址为" + address);
                } else
                    System.out.println("第" + (i + 1) + "条名为" + instructions[i].getName() + "的指令不是写入性指令，其所在的逻辑页号为" + pageCode + "的页面在主存的第" + pageFrameCode + "号页框中，该指令的逻辑地址为" + address);
            } else {
                lackPageSum++;//增加缺页次数，便于后期计算缺页率
                //页面不在主存中
                System.out.println("第" + (i + 1) + "条名为" + instructions[i].getName() + "的指令所在的逻辑页号为" + pageCode + "的页面不在主存中，产生缺页中断，开始页面调度");
                //执行页面调度算法
                ram.fifoDispatch(pageCode, isWritable);

            }

        }
        return lackPageSum / instructions.length;
    }
}