package interfaces;

public interface IndexCheckable {
    /**
     * 检查索引是否符合某操作的要求
     *
     * @param index      要检查的索引值
     * @param actionType 操作类型，用字符串来表示，取值只能有以下几种：
     * @throws IndexOutOfBoundsException 索引越界异常，指示索引不合法
     */
    void checkIndex(int index, String actionType) throws IndexOutOfBoundsException;
}
