package shang_ji_ti.teacherandstudent;

public class StudentHasQuestionException extends Exception {
    private final static String str = "学生已经有一个问题了，不能再给问题";

    public StudentHasQuestionException() {
        super(str);
    }

}
