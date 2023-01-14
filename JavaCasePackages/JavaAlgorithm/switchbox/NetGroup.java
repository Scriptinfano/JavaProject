package JavaAlgorithm.switchbox;

/**
 * 网组记录类，私有成员是两个管脚
 *
 * @param pinA 网组中的第一个管脚
 * @param pinB 网组中的第二个管脚
 */
public record NetGroup(int pinA, int pinB) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NetGroup group = (NetGroup) o;
        return pinA == group.pinA && pinB == group.pinB;
    }

}
