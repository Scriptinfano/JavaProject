package os_experiment.page_based_virtual_memory_manage;

/**
 * 页表项，页表项构成页表，指明作业的某个页是否在主存中并指出在主存的哪一个页框中
 * 注意：页表项中的逻辑页号隐式存储，页表项在页表项数组中的编号就是逻辑页号
 * @author Mingxiang
 */
public class PageEntry {


    /**
     * 该页表项所代表的页面存放在主存中的哪一个页框种
     */
    private int pageFrameCode;

    /**
     * 是否修改标志，标识在上一次执行该页面时是否遭到了修改
     */
    private boolean hasModified;

    /**
     * 表示该页表项所代表的页面是否在RAM中
     */
    private boolean inRam;

    /**
     * 取得映射后的页框号
     *
     * @return int 返回的页框号
     */
    public int getPageFrameCode() {
        return pageFrameCode;
    }

    /**
     * 设置映射后的页框号
     *
     * @param pageFrameCode 指定的页框号
     */
    public void setPageFrameCode(int pageFrameCode) {
        this.pageFrameCode = pageFrameCode;
    }

    /**
     * 检查该页是否被修改
     *
     * @return boolean 为true则标识被修改，仅在换出页面时将其写回外存
     */
    public boolean isHasModified() {
        return hasModified;
    }

    /**
     * 设置是否遭到了修改的标志位
     *
     * @param hasModified 为true则标识被修改
     */
    public void setHasModified(boolean hasModified) {
        this.hasModified = hasModified;
    }

    /**
     * 设置是否在主存中的标记位
     *
     * @param b true即代表在主存中
     */
    public void setInRAM(boolean b) {
        inRam = b;
    }

    /**
     * 判断当前页表项所代表的页面是否在主存中
     *
     * @return boolean true即代表在主存中
     */
    public boolean isInRam() {
        return inRam;
    }
}
