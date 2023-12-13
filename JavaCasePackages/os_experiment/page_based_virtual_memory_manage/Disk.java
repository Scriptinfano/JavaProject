package os_experiment.page_based_virtual_memory_manage;

/**
 * 模拟外存的类，存储一个作业所需要的所有页
 *
 * @author Mingxiang
 */
public class Disk {
    /**
     * 外存存储的唯一一个作业
     */
    private final Process process;

    /**
     * 存储进程所需要的所有页面
     */
    private final Page[] pages;

    /**
     * 将构造好的页面集合（所有页面已经将指令正确放入了指定的位置）
     *
     * @param pages 页面集合
     */
    private Disk(Page[] pages, Process process) {
        this.process = process;
        this.pages = pages;
    }

    /**
     * 创建外存实例，将指定作业的所有指令装入页面
     *
     * @param process 指定外存要存储哪一个作业的所有页面
     * @return {@link Disk}
     */
    public static Disk createInstance(Process process) {
        int pageCount = process.getTotalPage();//得到作业总的页面数
        Page[] pages1 = new Page[pageCount];//创建用户的进程逻辑页面空间
        int instructNum = process.getInstructions().length;//进程中的指令总数
        for (int i = 0; i < instructNum; i++) {
            int offset = process.getInstructions()[i].getInPageOffset();//得到指令在页面中的偏移量
            int pageCode = process.getInstructions()[i].getPageCode();//得到该指令所在的页面的页号
            if (pages1[pageCode] == null) pages1[pageCode] = new Page(Page.pageSize * pageCode);//若对应的页面未创建则创建，并指明其在外存的物理地址
            pages1[pageCode].setInstruction(offset, process.getInstructions()[i]);//将指令放入指定页面
        }
        System.out.println("所有页面初始化完毕，输出各页面在外存中的起始地址");
        for (int i = 0; i < pages1.length; i++) {
            System.out.println("第" + (i + 1) + "个页面在外存中的物理地址为=" + pages1[i].getDiskAddress());
        }
        return new Disk(pages1, process);//将创建好的所有页面放入外存
    }

    /**
     * 取得外存存储的进程，此实验假设外存仅有一个作业
     *
     * @return {@link Process}
     */
    public Process getProcess() {
        return process;
    }

    /**
     * 根据逻辑页号获取指定页面，一般用于主存将外存的页面复制调出
     *
     * @param i 逻辑页号
     * @return {@link Page} 调出的页面
     */
    public Page getPage(int i) {
        return pages[i];
    }

    /**
     * 将指定页面写回外存
     *
     * @param i    逻辑页号
     * @param page 指定的页面
     */
    public void setPage(int i, Page page) {
        pages[i] = page;
    }

}
