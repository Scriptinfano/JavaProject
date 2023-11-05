package shang_ji_ti.teacherandstudent;

import myScannerAndPrinter.MyScanner;

public class Student extends Person {
    private Question question;

    public Student(String name, Sex sex) {
        super(name, sex);
    }

    public void setQuestion(Question question) {
        if (this.question == null || question.hasAnswered() && question.hasScore()) {
            //学生已经得到了问题而且已经回答了而且已经得到了分数，就可以接受下一个问题
            this.question = question;
            this.question.setAnswerer(this);
        } else if (question.hasAnswered() && !question.hasScore()) {
            //学生得到了问题并且已经回答，但是还没有被老师打分，不能接受
            System.out.println("学生得到了问题但还没有被打分，老师无法提出了下一个问题，请老师打分之后再提出下一个问题");
        }

    }

    public boolean hasQuestion() {
        return question != null;
    }

    /**
     * 返回作答好之后的问题
     *
     * @return {@link Question}
     */
    public void answerQuestion() {
        if (question == null) {
            System.out.println("学生没有任何问题可以回答");
        }
        MyScanner scanner = new MyScanner();
        Answer answer1;
        while (true) {
            String answer = scanner.nextLineNoEmpty();
            try {
                answer1 = Answer.makeAnswer(answer);
            } catch (InvalidAnswerException e) {
                System.out.println(e.getMessage());
                continue;
            }
            break;
        }

        question.setAnswer(answer1);
        System.out.println("学生答题完成");
    }
}
