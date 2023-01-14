package DataStructure.array;

import DataStructure.dataStructureInterfaces.LinearList;
import interfaces.IndexCheckable;

public class MyArrayList<T> implements LinearList<T>, IndexCheckable {

    //ADT接口实现

    /**
     * 数组长度，相当于容器的容量
     */
    private int arrayLength;
    /**
     * 数组中元素的个数，相当于容器中元素的个数
     */
    private int listSize = 0;
    /**
     * 内部维护的容器
     */
    private Object[] container;

    //构造器
    public MyArrayList(int capacity) {
        this.arrayLength = capacity;
        container = new Object[capacity];
    }

    /**
     * 重设指定元素的值
     *
     * @param index      指定的位置
     * @param theElement 要设为的元素
     */
    @Override
    public void set(int index, T theElement) {
        if (empty()) throw new UnsupportedOperationException("没有可以重设的值，容器为空");
        checkIndex(index, "replace");
        container[index - 1] = theElement;
    }

    /**
     * 检查索引是否符合某操作的要求
     *
     * @param index      要检查的索引值
     * @param actionType 操作类型，用字符串来表示，取值只能有以下几种：
     * @throws IndexOutOfBoundsException 索引越界异常，指示索引不合法
     */
    @Override
    public void checkIndex(int index, String actionType) throws IndexOutOfBoundsException, IllegalArgumentException {
        if (actionType.equals("insert")) {
            String message = "插入元素时，";
            if (index <= 0) message += "插入位置不得<=0";
            if (index > (size() + 1)) message += "插入位置不得>（数组元素个数+1）";
            throw new IndexOutOfBoundsException(message);
        } else if (actionType.equals("get") || actionType.equals("erase") || actionType.equals("replace")) {
            String message = "";
            if (index <= 0) {
                switch (actionType) {
                    case "get" -> message += "取得元素时，取得位置不得<=0";
                    case "erase" -> message += "删除元素时，删除位置不得<=0";
                    case "replace" -> message += "替换元素时，替换位置不得<=0";
                }
            } else if (index > size()) {
                switch (actionType) {
                    case "get" -> message += "取得元素时，取得位置不得>数组元素个数";
                    case "erase" -> message += "删除元素时，删除位置不得>数组元素个数";
                    case "replace" -> message += "替换元素时，替换位置不得>数组元素个数";
                }
            }
            throw new IndexOutOfBoundsException(message);
        } else {
            String message = "checkIndex第二个参数传入不正确，未指定正确的操作类型";
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 检测容器是否为空
     *
     * @return boolean 为true时表示容器为空，为false时表示容器未空
     */
    @Override
    public boolean empty() {
        return size() == 0;
    }

    /**
     * 返回容器的元素个数
     *
     * @return int 表示容器元素的个数
     */
    @Override
    public int size() {
        return this.listSize;
    }

    /**
     * 返回指定元素在容器内第一次出现的索引值
     *
     * @param theElement 指定的元素
     * @return int 指定元素在容器的索引值，如果容器没有该元素或者容器为空则返回-1
     */
    @Override
    public int indexOf(T theElement) {
        if (empty()) return -1;
        for (int i = 0; i < size(); i++) {
            if (theElement.equals(container[i]))
                return i;
        }
        return -1;
    }

    /**
     * 得到指定索引的元素值
     *
     * @param index 指定的索引值
     * @return {@link T}
     */
    @Override
    public T get(int index) {
        checkIndex(index, "get");
        return (T) container[index];
    }

    //私有属性

    /**
     * 删除指定索引的元素值
     *
     * @param index 这里的索引注意不是数组的下标，而是数组的第几个元素
     */
    @Override
    public void erase(int index) {
        checkIndex(index, "erase");
        System.arraycopy(container, index, container, index - 1, size() - index);
        listSize--;
    }

    /**
     * 在指定位置插入指定的元素
     *
     * @param index      这里的索引注意不是数组的下标，而是数组的第几个元素
     * @param theElement 要插入的元素
     */
    @Override
    public void insert(int index, T theElement) {
        if (size() == arrayLength)//将数组长度倍增
        {
            Object[] newContainer = new Object[2 * arrayLength];
            System.arraycopy(container, 0, newContainer, 0, size());
            arrayLength *= 2;
            container = newContainer;
        }
        checkIndex(index, "insert");
        System.arraycopy(container, index - 1, container, index, size() - index + 1);
        container[index - 1] = theElement;
        listSize++;
    }

    /**
     * 显示容器中所有元素的值
     */
    @Override
    public void show() {
        if (empty()) throw new UnsupportedOperationException("容器为空无法显示内容");
        for (int i = 0; i < size(); i++) {
            System.out.print(container[i]);
        }
        System.out.println();
    }

    /**
     * 清除容器中所有元素
     */
    @Override
    public void clear() {
        if (empty()) return;
        container = new Object[arrayLength];
        listSize -= size();
    }
    //重写clone方法

    public MyArrayList<T> myClone() {
        MyArrayList<T> newArray = new MyArrayList<>(arrayLength);
        Object[] newContainer = new Object[this.arrayLength];
        System.arraycopy(container, 0, newContainer, 0, size());
        newArray.setContainer(newContainer);
        newArray.setSize(size());
        return newArray;
    }

    private void setContainer(Object[] newContainer) {
        container = newContainer;
    }

    private void setSize(int newSize) {
        listSize = newSize;
    }
}
