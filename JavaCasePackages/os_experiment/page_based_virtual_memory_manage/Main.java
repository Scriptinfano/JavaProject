package os_experiment.page_based_virtual_memory_manage;

/**
 * 实验内容：请求页式存储管理中硬件的地址转换和缺页中断，采用先进先出调度法处理缺页中断
 * <p>
 * 实验前提条件：
 * 1、假设主存共有m个页框，页表同样必须有m个页表项
 * 2、作业总的页面数要大于m，且作业投入主存运行时，主存会将自己的所有页框分配给该作业
 * 3、假定模拟过程中CPU仅执行一个作业的所有指令
 *
 * @author Mingxiang
 */
public class Main {
    public static void main(String[] args) {


        //创建进程，并设定进程应该先载入主存的页号
        Process process = Process.createInstance("A", 10, 4, new int[]{0, 1, 2, 3});

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