package exceptions;

/**
 * 当类中的某些设置没有完成或某些方法没有执行时，就执行某操作可能会造成某些未知错误，故设置此异常类，提示客户端先执行哪些操作
 *
 * @author Mingxiang
 */
public class RequiredActionNotExcuteException extends RuntimeException {
    /**
     * 通用的构造函数，内置消息传递：必要的设置未完成，无法执行类的相关操作
     */
    public RequiredActionNotExcuteException() {
        String message = "必要的设置或操作未完成，无法执行相关操作";
        super.initCause(new RuntimeException(message));
    }

    /**
     * 自定义传递消息
     *
     * @param message 具体的错误信息
     */
    public RequiredActionNotExcuteException(String message) {
        super.initCause(new RuntimeException(message));
    }
}

