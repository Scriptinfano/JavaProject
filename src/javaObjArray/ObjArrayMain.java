package javaObjArray;

import java.util.Random;

class Student {
    /**
     * 随机数生成器
     */
    private static final Random numGenerator = new Random();
    private static final int STATE_BEGIN = 1;
    private static final int STATE_END = 6;
    private static final int SCORE_BEGIN = 60;
    private static final int SCORE_END = 100;
    private static int numberSequences = 0;
    int number;//学号
    int state;//年级
    int score;//成绩

    public Student(int number, int state, int score) {
        this.number = number;
        this.state = state;
        this.score = score;
    }

    public Student() {
        this.number = numberSequences;
        numberSequences++;
        this.state = numGenerator.nextInt(STATE_BEGIN, STATE_END + 1);
        this.score = numGenerator.nextInt(SCORE_BEGIN, SCORE_END + 1);
    }

    public void showInformation() {
        System.out.println("学号：" + number + " 年级：" + state + " 成绩：" + score);
    }

    public int getScore() {
        return score;
    }

    public int getNumber() {
        return number;
    }

    public int getState() {
        return state;
    }
}

public class ObjArrayMain {
    public static void main(String[] args) {
        Student[] students = new Student[20];
        for (int i = 0; i < students.length; i++) {
            students[i] = new Student();
        }
        for (Student stu : students) {
            stu.showInformation();
        }
        System.out.println("打印出所有三年级的学生：");
        for (Student stu : students) {
            if (stu.getState() == 3) stu.showInformation();
        }
        System.out.println("按学生成绩按小到大的顺序排列：");
        bubbleSortStudent(students);
        for (Student stu : students) {
            stu.showInformation();
        }


    }

    /**
     * 冒泡排序学生
     * 按成绩高低对学生数组进行排序
     *
     * @param students 学生数组
     */
    public static void bubbleSortStudent(Student[] students) {
        for (int i = 0; i < students.length - 1; i++) {
            for (int j = 0; j < students.length - i - 1; j++) {
                if (students[j].getScore() > students[j + 1].getScore())
                    swapStudent(students, j, j + 1);
            }
        }
    }

    /**
     * 交换学生
     *
     * @param stuArray 学生数组
     * @param i        第一个要交换的数组元素的下标
     * @param j        第二个要交换的数组元素的下标
     */
    private static void swapStudent(Student[] stuArray, int i, int j) {
        Student student = stuArray[i];
        stuArray[i] = stuArray[j];
        stuArray[j] = student;
    }

}
