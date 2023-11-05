package shang_ji_ti.teacherandstudent;

/**
 * 该分数类为
 *
 * @author Mingxiang
 */
public abstract class Score {
    protected static double start;//圈定该分数允许范围的起始值
    protected static double end;//圈定该分数范围的终止值
    protected double score;

    public Score(double theScore) {
        score = theScore;
    }

    public double getScore() {
        return score;
    }
}

/**
 * 一个范围固定在-10分之间的分数
 *
 * @author Mingxiang
 */
class ScoreRangeInTen extends Score {
    //静态代码块会先于任何静态方法执行，来直接改写分数的范围
    static {
        start = 0;
        end = 10;
    }

    private ScoreRangeInTen(double theScore) {
        super(theScore);
    }


    public static Score makeScore(double theScore) throws ScoreOutOfRangeException {
        if (theScore >= start && start <= end) {
            return new ScoreRangeInTen(theScore);
        } else {
            throw new ScoreOutOfRangeException(start, end);
        }
    }

}
//TODO 如果想制定更多的分制，请继承Score