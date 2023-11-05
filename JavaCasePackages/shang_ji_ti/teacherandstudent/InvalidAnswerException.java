package shang_ji_ti.teacherandstudent;

public class InvalidAnswerException extends Exception {
    private static final String str = "问题的答案不符合某些要求";

    public InvalidAnswerException() {
        super(str);
    }
}
