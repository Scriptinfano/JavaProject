package DataStructure.dataStructureInterfaces;

public interface LinearList<T> {

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


    /**
     * 重设指定元素的值
     *
     * @param index      指定的位置
     * @param theElement 要设为的元素
     */
    void set(int index, T theElement);
}
