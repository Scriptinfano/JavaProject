package os_experiment.page_based_virtual_memory_manage;

/**
 * 页面
 *
 * @author Mingxiang
 */
public class Page {
    /**
     * 页面大小，最大可以存放多少个指令
     */
    public static final int pageSize = 100;

    /**
     * 页面内的指令集合
     */
    private final Instruction[] instructions;

    /**
     * 该页原先在磁盘中的绝对地址
     */
    private final int diskAddress;

    /**
     * 构造一个新页面
     *
     * @param diskAddress 设定该页在外存的地址
     */
    public Page(int diskAddress) {
        this.diskAddress = diskAddress;
        this.instructions = new Instruction[pageSize];
    }

    public int getDiskAddress() {
        return diskAddress;
    }

    /**
     * 获取页面中的指令
     *
     * @param i 指令在页面内部的偏移量
     * @return {@link Instruction} 返回的指令，如果指定位置没有指令则会返回null
     */
    public Instruction getInstruction(int i) {
        return instructions[i];
    }

    /**
     * 为页内的指定位置
     *
     * @param i           指令页内偏移量
     * @param instruction 要存放的指令
     */
    public void setInstruction(int i, Instruction instruction) {
        instructions[i] = instruction;
        instruction.setInPageOffset(i);

    }

    /**
     * 检查指定位置是否有指令
     *
     * @param i 指令页内偏移量
     * @return boolean 返回true则标识有指令
     */
    public boolean hasInstruction(int i) {
        return instructions[i] != null;
    }


}
