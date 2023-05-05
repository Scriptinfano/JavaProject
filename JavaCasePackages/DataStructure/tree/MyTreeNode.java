package DataStructure.tree;
public abstract class MyTreeNode<T extends Comparable<T>> {
    protected T value;

    @Override
    public abstract boolean equals(Object obj);

    public abstract int height();

    public final T getValue() {
        return value;
    }

    public final void setValue(T value) {
        this.value = value;
    }
}
