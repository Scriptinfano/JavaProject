package shang_ji_ti.teacherandstudent;

import myScannerAndPrinter.MyScanner;
import myScannerAndPrinter.NoMoreScanException;

import java.util.ArrayList;

public class Main {
    public static final MyScanner scanner = new MyScanner();
    public static ArrayList<Question> questions = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("开始创建一个老师");
        System.out.println("请输入老师的名字");
        String teacherName = scanner.nextLineNoEmpty();
        System.out.println("请输入老师的性别");
        Teacher teacher = new Teacher(teacherName, getSex());

        System.out.println("开始创建一个学生");
        System.out.println("请输入学生的名字");
        String studentName = scanner.nextLineNoEmpty();
        System.out.println("请输入学生的性别");
        Student student = new Student(studentName, getSex());

        while (true) {
            System.out.println("老师可以开始提出问题");
            Question question = null;
            while (true) {
                try {
                    //老师做出一个问题，并将问题传递给一个学生
                    question = teacher.makeQuestion();
                    teacher.deliverQuestion(question, student);
                    break;
                } catch (InvalidQuestionException e) {
                    System.out.println(e.getMessage());
                    System.out.println("请重新输入问题");
                } catch (StudentHasQuestionException e) {
                    System.out.println(e.getMessage());
                    break;
                }
            }
            System.out.println("学生开始回答问题");
            student.answerQuestion();
            System.out.println("请老师对本题做出打分");
            teacher.scoringQuestion(question);
            System.out.println("请老师对此次问答做出评论");
            question.addTeacherComments(teacher.makeComments());
            System.out.println("请学生对此次问答做出评论");
            question.addStudentComments(student.makeComments());
            System.out.println("是否要提出下一个问题");
            questions.add(question);
            try {
                scanner.noMoreScan();
            } catch (NoMoreScanException e) {
                break;
            }
        }

        System.out.println("开始输出各问题的信息");
        for (Question question : questions) {
            System.out.println(question);
        }
    }

    private static Person.Sex getSex() {
        String gender = scanner.nextLineNoEmpty();
        Person.Sex sex;
        while (true) {
            if (gender.equals("男")) {
                sex = Person.Sex.Male;
                break;
            } else if (gender.equals("女")) {
                sex = Person.Sex.Female;
                break;
            } else {
                System.out.println("你只能输男或女，请重新输入");
                gender = scanner.nextLineNoEmpty();
            }
        }
        return sex;
    }
}
