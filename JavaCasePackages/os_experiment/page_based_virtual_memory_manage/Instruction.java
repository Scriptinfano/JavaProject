package os_experiment.page_based_virtual_memory_manage;

import java.util.Objects;

/**
 * 指令模拟类
 *
 * @author Mingxiang
 */
public class Instruction {
    /**
     * 指令操作码，相同功能的指令，其操作码相同
     */
    private final int opCode;

    /**
     * 该指令在进程的逻辑地址空间中的哪一个页，即逻辑页号
     */
    private final int pageCode;

    /**
     * 页内偏移量
     */
    private int inPageOffset;

    /**
     * 指令的名字
     */
    private String name;

    /**
     * 新建一个指令
     *
     * @param pageCode     指令在进程的哪一个逻辑页面，即逻辑页号是多少
     * @param inPageOffset 指令在页面内的偏移量
     * @param name         指令名字
     */
    public Instruction(int pageCode, int inPageOffset, String name) {
        if (Objects.equals(name, "加指令")) {
            this.opCode = 1;
        } else if (Objects.equals(name, "减指令")) {
            this.opCode = 2;
        } else if (Objects.equals(name, "乘指令")) {
            this.opCode = 3;
        } else if (Objects.equals(name, "存指令")) {
            this.opCode = 4;
        } else if (Objects.equals(name, "取指令")) {
            this.opCode = 5;
        } else if (Objects.equals(name, "移位指令")) {
            this.opCode = 6;
        } else this.opCode = -1;

        this.pageCode = pageCode;
        this.inPageOffset = inPageOffset;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取指令所在页面的逻辑页号（不是在主存中的页框号）
     *
     * @return int 逻辑页号
     */
    public int getPageCode() {
        return pageCode;
    }

    /**
     * 获取页内偏移量
     *
     * @return int 页内偏移量
     */
    public int getInPageOffset() {
        return inPageOffset;
    }

    /**
     * 设置页内偏移量
     *
     * @param inPageOffset 在页面偏移中
     */
    public void setInPageOffset(int inPageOffset) {
        this.inPageOffset = inPageOffset;
    }

    /**
     * 判断指令是否为可写性指令，根据指令的操作码判断
     *
     * @return boolean
     */
    public boolean isWriteabilityInstruction() {
        return opCode == 4;
    }
}

