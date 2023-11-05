package shang_ji_ti.teacherandstudent;

public class ScoreOutOfRangeException extends Exception {
    private final static String message = "你设定的分数超出了要求";
    private double start;
    private double end;

    public ScoreOutOfRangeException(double start, double end) {
        super(message + ",规定的要求是在" + start + "和" + end + "之间");
    }
}
