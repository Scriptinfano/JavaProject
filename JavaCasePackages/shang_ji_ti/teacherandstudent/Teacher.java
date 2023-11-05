package shang_ji_ti.teacherandstudent;

import myScannerAndPrinter.MyScanner;

public class Teacher extends Person {
    public Teacher(String name, Sex sex) {
        super(name, sex);
    }

    public Question makeQuestion() throws InvalidQuestionException {
        System.out.println("请输入老师的问题：");
        MyScanner Scanner = new MyScanner();
        String question_str = Scanner.nextLineNoEmpty();
        return Question.MakeQuestion(question_str, this);
    }

    /**
     * @param question 已经被学生回答好的问题
     * @param student  回答问题的学生
     * @throws StudentHasQuestionException
     */
    public void deliverQuestion(Question question, Student student) throws StudentHasQuestionException {
        if (student.hasQuestion()) {
            throw new StudentHasQuestionException();
        }
        student.setQuestion(question);
    }

    /**
     * 仅当传入的问题是
     *
     * @param question
     */
    public void scoringQuestion(Question question) {
        if (question != null && question.hasAnswered() && !question.hasScore()) {
            MyScanner scanner = new MyScanner();
            double score;
            while (true) {
                score = scanner.nextDouble();
                try {
                    question.setScore(ScoreRangeInTen.makeScore(score));
                    break;
                } catch (ScoreOutOfRangeException e) {
                    System.out.println(e.getMessage());
                    System.out.println("请重新输入分数：");
                }
            }
        }
    }
}
