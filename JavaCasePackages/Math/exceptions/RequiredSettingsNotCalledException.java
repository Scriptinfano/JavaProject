package exceptions;

/**
 * 当类中的某些设置没有完成时，就执行某操作可能会造成某些未知错误，故设置此异常类
 *
 * @author Mingxiang
 */
public class RequiredSettingsNotCalledException extends RuntimeException {
    /**
     * 通用的构造函数，内置消息传递：必要的设置未完成，无法执行类的相关操作
     */
    public RequiredSettingsNotCalledException() {
        String message = "必要的设置未完成，无法执行类的相关操作";
        super.initCause(new RuntimeException(message));
    }

    /**
     * 自定义传递消息
     *
     * @param message 具体的错误信息
     */
    public RequiredSettingsNotCalledException(String message) {
        super.initCause(new RuntimeException(message));
    }
}

