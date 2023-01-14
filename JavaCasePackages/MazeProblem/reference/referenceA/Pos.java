package MazeProblem.reference.referenceA;

import java.util.Objects;

public class Pos implements Comparable<Pos> {
    //x坐标
    private final int x;
    //y坐标
    private final int y;
    //当前的消耗
    private double g;
    //预估的消耗
    private double h;
    //从哪里来的，指向上一个节点
    private Pos parent;

    public Pos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Pos o) {
        return (int) ((this.g + this.h) - (o.g + o.h));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pos pos = (Pos) o;
        return x == pos.x && y == pos.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setH(double diagonal) {
        h = diagonal;
    }

    public Pos getParent() {
        return parent;
    }

    public void setParent(Pos remove) {
        parent = remove;
    }

    public double getG() {
        return g;
    }

    public void setG(double v) {
        g = v;
    }
}
