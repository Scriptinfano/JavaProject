package Math.exceptions;

/**
 * 类未执行run()方法就想返回运行结果时抛出此异常，以指示需要运行类
 *
 * @author Mingxiang
 * @date 2022/10/04
 */
public class UnRunException extends RuntimeException {
    public UnRunException(String cause) {
        super.initCause(new RuntimeException(cause));
    }

    public UnRunException() {
        String cause = "未执行run()方法，无法返回运行结果";
        super.initCause(new RuntimeException(cause));
    }
}
