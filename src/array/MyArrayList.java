package array;

interface LinearList<T> {

    /**
     * 检测容器是否为空
     *
     * @return boolean 为true时表示容器为空，为false时表示容器未空
     */
    boolean empty();

    /**
     * 返回容器的元素个数
     *
     * @return int 表示容器元素的个数
     */
    int size();

    /**
     * 返回指定元素在容器内第一次出现的索引值
     *
     * @param theElement 指定的元素
     * @return int 指定元素在容器的索引值，如果容器没有该元素则返回-1
     */
    int indexOf(T theElement);

    /**
     * 得到指定索引的元素值
     *
     * @param index 指定的索引值
     * @return {@link T}
     */
    T get(int index);

    /**
     * 删除指定索引的元素值
     *
     * @param index 这里的索引注意不是数组的下标，而是数组的第几个元素
     */
    void erase(int index);

    /**
     * 在指定位置插入指定的元素
     *
     * @param index      这里的索引注意不是数组的下标，而是数组的第几个元素
     * @param theElement 要插入的元素
     */
    void insert(int index, T theElement);

    /**
     * 显示容器中所有元素的值
     */
    void show();

    /**
     * 清除容器中所有元素
     */
    void clear();
}

public class MyArrayList<T> implements LinearList<T>, IndexCheckable {

    //ADT接口实现

    /**
     * 数组长度，相当于容器的容量
     */
    private int arrayLength;
    /**
     * 数组中元素的个数，相当于容器中元素的个数
     */
    private int listSize;
    private Object[] container;

    //构造器
    public MyArrayList() {
    }

    /**
     * 检查索引是否符合某操作的要求
     *
     * @param index      要检查的索引值
     * @param actionType 操作类型，用字符串来表示，取值只能有以下几种：
     * @throws IndexOutOfBoundsException 索引越界异常，指示索引不合法
     */
    @Override
    public void checkIndex(int index, String actionType) throws IndexOutOfBoundsException {

    }

    /**
     * 检测容器是否为空
     *
     * @return boolean 为true时表示容器为空，为false时表示容器未空
     */
    @Override
    public boolean empty() {
        return false;
    }

    /**
     * 返回容器的元素个数
     *
     * @return int 表示容器元素的个数
     */
    @Override
    public int size() {
        return 0;
    }

    /**
     * 返回指定元素在容器内第一次出现的索引值
     *
     * @param theElement 指定的元素
     * @return int 指定元素在容器的索引值，如果容器没有该元素则返回-1
     */
    @Override
    public int indexOf(T theElement) {
        return 0;
    }

    /**
     * 得到指定索引的元素值
     *
     * @param index 指定的索引值
     * @return {@link T}
     */
    @Override
    public T get(int index) {
        return null;
    }

    //私有属性

    /**
     * 删除指定索引的元素值
     *
     * @param index 这里的索引注意不是数组的下标，而是数组的第几个元素
     */
    @Override
    public void erase(int index) {

    }

    /**
     * 在指定位置插入指定的元素
     *
     * @param index      这里的索引注意不是数组的下标，而是数组的第几个元素
     * @param theElement 要插入的元素
     */
    @Override
    public void insert(int index, T theElement) {

    }

    /**
     * 显示容器中所有元素的值
     */
    @Override
    public void show() {

    }

    /**
     * 清除容器中所有元素
     */
    @Override
    public void clear() {

    }
    //重写clone方法

    @Override
    protected Object clone() throws CloneNotSupportedException {
        container = new Object[this.arrayLength];

        return (Object) container;
    }


}
