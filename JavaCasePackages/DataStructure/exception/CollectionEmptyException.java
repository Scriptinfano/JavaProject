package exception;

public class CollectionEmptyException extends RuntimeException {
    public CollectionEmptyException() {
        String message = "集合为空，无法执行相应操作";
        super.initCause(new RuntimeException(message));
    }

    public CollectionEmptyException(String message) {
        super.initCause(new RuntimeException(message));
    }
}
