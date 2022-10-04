package exception;

public class CollectionFullException extends RuntimeException {
    public CollectionFullException() {
        String message = "集合为空，无法执行相应操作";
        super.initCause(new RuntimeException(message));
    }

    public CollectionFullException(String message) {
        super.initCause(new RuntimeException(message));
    }

}
