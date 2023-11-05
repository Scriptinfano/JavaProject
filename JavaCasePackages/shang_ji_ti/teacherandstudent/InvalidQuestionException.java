package shang_ji_ti.teacherandstudent;

public class InvalidQuestionException extends Exception {
    private final static String message = "你提出的问题不符合某些要求";

    public InvalidQuestionException() {
        super(message);
    }
}
