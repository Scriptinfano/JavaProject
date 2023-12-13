package os_experiment.page_based_virtual_memory_manage;

/**
 * 实验内容：请求页式存储管理中硬件的地址转换和缺页中断，采用先进先出调度法处理缺页中断
 * <p>
 * 实验前提条件：<p>
 * 1、假定模拟过程中CPU仅执行一个作业的所有指令<p>
 * 2、初始载入页面的顺序必须连续且从0号页开始
 *
 * @author Mingxiang
 */
public class Main {
    /**
     * 输入初始化的页面数，即操作系统一开始为进程分配的页框数，得到从0开始的连续的初始化页号数组
     *
     * @param i 初始化页面数
     * @return {@link int[]} 从0开始的连续的初始化页号数组
     */
    private static int[] getArr(int i) {
        int[] arr = new int[i];
        for (int j = 0; j < i; j++) {
            arr[j] = j;
        }
        return arr;
    }

    public static void main(String[] args) {
        //创建进程，并设定进程应该先载入主存的页号

        int applyPageCount = 14;
        Process process = Process.createInstance("A", 20, applyPageCount, getArr(applyPageCount));

        //创建外存实例，外存根据进程中的指令所指明的页号及页内地址创建相应的页供内存调取
        Disk disk = Disk.createInstance(process);

        //创建主存实例，并指定该主存可存取的外存
        RAM ram = new RAM(disk);

        //创建CPU实例，并设定CPU接管的主存和外存
        CPU cpu = new CPU(ram, disk);
        float lackRate = cpu.run();
        System.out.println("本次调度的缺页率为%" + lackRate * 100);

    }
}