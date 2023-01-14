//定义括号匹配中所涉及的异常类
package JavaAlgorithm.bracketmatch;

/**
 * 括号匹配中，匹配器未初始化异常
 *
 * @author Mingxiang
 * @date 2022/09/12
 */
class UnInitializeException extends RuntimeException {

    public UnInitializeException() {
        super("在匹配器未执行初始化的情况下使用是非法的，请调用reset函数执行初始化");
    }


    public UnInitializeException(String message) {
        super(message);
    }


    public UnInitializeException(String message, Throwable cause) {
        super(message, cause);
    }


    public UnInitializeException(Throwable cause) {
        super(cause);
    }
}

/**
 * 括号匹配中，重复初始化匹配器异常
 *
 * @author Mingxiang
 * @date 2022/09/12
 */

class EmptyCharArrayException extends RuntimeException {

    public EmptyCharArrayException() {
        super("用来初始化匹配器的char数组不能为空");
    }


    public EmptyCharArrayException(String message) {
        super(message);
    }


    public EmptyCharArrayException(String message, Throwable cause) {
        super(message, cause);
    }


    public EmptyCharArrayException(Throwable cause) {
        super(cause);
    }
}

class InvalidCharArrayException extends RuntimeException {

    public InvalidCharArrayException() {
        super("用来初始化匹配器的char数组中有不是括号的字符");
    }


    public InvalidCharArrayException(String message) {
        super(message);
    }


    public InvalidCharArrayException(String message, Throwable cause) {
        super(message, cause);
    }


    public InvalidCharArrayException(Throwable cause) {
        super(cause);
    }
}