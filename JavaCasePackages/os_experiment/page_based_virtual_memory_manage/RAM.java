package os_experiment.page_based_virtual_memory_manage;

import java.util.Random;
import java.util.random.RandomGenerator;

/**
 * 主存类
 * 假设该主存仅考虑为一个进程服务的情况
 * 页表中页表项的个数等于进程所有的页面数
 *
 * @author Mingxiang
 */
public class RAM {

    /**
     * 主存中的页框总数
     */
    public final int pageFrameCount = 20;
    /**
     * 页表，页表的大小就是操作系统为作业分配的最大页框数
     */
    private final PageEntry[] pageTable;
    /**
     * 页框集合，代表内存中所有的页框
     */
    private final Page[] pages;
    /**
     * 表示外存
     */
    private final Disk disk;
    /**
     * 该变量所代表的值总是表示最晚进入页表的页表项在页表中的编号
     */
    private int latest;

    /**
     * 构造一个专门为存储某个作业的RAM
     *
     * @param disk 设定该主存可以从哪一个外存读取页面
     */
    public RAM(Disk disk) {
        this.disk = disk;
        pageTable = new PageEntry[disk.getProcess().getTotalPage()];//页表中存放的页表项的个数是操作系统为该作业分配的页框数
        pages = new Page[pageFrameCount];//页框总数取决于内存的大小
    }

    /**
     * 初始化进程，将进程中声明的初始化页面载入主存，并初始化页表中的内容
     *
     * @param process 指定的作业进程
     */
    public void initProcess(Process process) {
        int[] pageCodes = process.getInitPageCode();

        RandomGenerator generator = new Random(System.currentTimeMillis());
        //离散地存放从外存中调入的页面
        for (int i = 0; i < pageCodes.length; i++) {
            int random = generator.nextInt(0, pageFrameCount);//生成一个随机的页框号
            pages[random] = disk.getPage(pageCodes[i]);//将主存调入的指定页面放入随机的一个页框中
            PageEntry pageEntry = new PageEntry();
            pageEntry.setPageCode(pageCodes[i]);
            pageEntry.setPageFrameCode(random);
            pageEntry.setInRAM(true);
            pageEntry.setHasModified(false);
            pageTable[pageCodes[i]] = pageEntry;
        }
        for (int i = pageCodes.length; i < pageTable.length; i++) {
            PageEntry entry = new PageEntry();
            entry.setPageCode(-1);
            entry.setInRAM(false);
            entry.setHasModified(false);
            entry.setPageFrameCode(-1);
            pageTable[i] = entry;
        }
        latest = pageCodes[0];

    }


    /**
     * 检查页表，检索逻辑页号pageCode对应的页面是否在内存中
     *
     * @param pageCode 逻辑页号
     * @return boolean 若在主存中，则返回true
     */
    public boolean hasPage(int pageCode) {
        //只要在页表中的页一定在主存中，因为此处页表的大小恰好等于系统为作业分配的最大页面数
        for (PageEntry entry : pageTable) {
            if (entry.getPageCode() == pageCode && entry.isInRam())
                return true;
        }
        return false;
    }

    /**
     * 给定逻辑页号，查询页表，获取已经在主存中的页的页框号
     *
     * @param pageCode 页面代码
     * @return int
     */
    public int getPageFrameCode(int pageCode) {
        if (pageCode < 0 || pageCode > pageTable.length) return -1;
        for (PageEntry entry : pageTable) {
            if (entry.getPageCode() == pageCode)
                return entry.getPageFrameCode();
        }
        return -1;
    }

    public void setPageModified(int pageCode, boolean modified) {
        pageTable[pageCode].setHasModified(modified);
    }

    /**
     * FIFO算法页面调度，将页表最前面的页表项所代表的页面调出内存，修改页表项相关内容，然后将pageCode指定的页面调入内存
     *
     * @param pageCodeOfInPage 要换入主存的页面的逻辑页号
     * @param isWritable       缺页指令是否为可写指令，也就是即将换入的页面换入主存后，执行调度算法之后，CPU执行指令之后是否应该将该页面的修改标志位置1
     */
    public void fifoDispatch(int pageCodeOfInPage, boolean isWritable) {
        int pageFrameCodeOfOutPage = pageTable[latest].getPageFrameCode();
        if (pageTable[latest].isHasModified()) {
            //要换出的页面已经被修改过了，需要写回外存
            disk.setPage(pageTable[latest].getPageCode(), pages[pageFrameCodeOfOutPage]);
        }
        pages[pageFrameCodeOfOutPage] = disk.getPage(pageCodeOfInPage);//将外存的页面调入内存
        pageTable[latest].setPageCode(pageCodeOfInPage);//更新页表项的逻辑页号
        //页表的页框号不变，因为调入的页面就在原来的页面所在的位置
        pageTable[latest].setHasModified(isWritable);//根据当前执行的缺页指令是否为写入性指令，设定页面是否遭到了修改的标志位
        do {
            latest = (latest + 1) % pageTable.length;//更新指向最晚进入主存的页面的指针
        } while (!pageTable[latest].isInRam());//latest不断向后移动直到找到下一条有效的页表项
    }
}
