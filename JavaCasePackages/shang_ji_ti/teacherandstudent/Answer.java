package shang_ji_ti.teacherandstudent;

public class Answer {
    private final String answer;

    private Answer(String answer) {
        this.answer = answer;
    }

    public static Answer makeAnswer(String answer) throws InvalidAnswerException {
        if (checkAnswer()) {
            throw new InvalidAnswerException();
        }
        return new Answer(answer);
    }

    public static boolean checkAnswer() {
        //TODO 此处可以设置一些检查答案是否符合某些要求的代码
        return false;
    }

    public String getAnswer() {
        return answer;
    }

}
