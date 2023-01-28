package DataStructure.exception;

public class NodeNotFoundException extends Exception {
    /**
     * 未找到值为value的异常
     */
    private Object value;

    public NodeNotFoundException(Object theValue) {
        super("未找到值为" + theValue + "的异常");
    }
}
