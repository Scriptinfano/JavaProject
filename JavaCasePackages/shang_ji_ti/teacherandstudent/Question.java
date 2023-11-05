package shang_ji_ti.teacherandstudent;

import java.util.ArrayList;

/**
 * 问题类，包括具体的问题，以及其回答，还有最后的得分
 *
 * @author Mingxiang
 */
public class Question {


    public final ArrayList<MyComment> teacherComments = new ArrayList<>();
    public final ArrayList<MyComment> studentComments = new ArrayList<>();
    private final String question;//问题一旦确定就不可以更改
    private final Teacher problemGiver;
    private Answer answer;//答案可以修改
    private Score score;//分数可以修改
    private Student problemSolver;

    private Question(String question, Teacher teacher) {
        this.question = question;
        this.problemGiver = teacher;
    }

    public static Question MakeQuestion(String str, Teacher teacher) throws InvalidQuestionException {
        if (checkQuestion()) {
            throw new InvalidQuestionException();
        }
        return new Question(str, teacher);
    }

    /**
     * 返回false表示没有任何问题，返回true表示有问题
     *
     * @return boolean
     */
    private static boolean checkQuestion() {
        //TODO 这里可以定义一些检查问题是否符合某些要求的代码
        return false;
    }

    public String getQuestion() {
        return question;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public boolean hasAnswered() {
        return answer != null;
    }

    public boolean hasScore() {
        return score != null;
    }

    public void addTeacherComments(MyComment myComment) {
        teacherComments.add(myComment);
    }

    public void addStudentComments(MyComment myComment) {
        studentComments.add(myComment);
    }

    @Override
    public String toString() {
        return "问题：" + question + "\n问题答案：" + answer.getAnswer() + "\n提出问题者：" + problemGiver.getName() + "\n解决问题者：" + problemSolver.getName() + "\n问题的得分：" + score.getScore() + "\n";
    }

    public void setAnswerer(Student student) {
        problemSolver = student;
    }
}
