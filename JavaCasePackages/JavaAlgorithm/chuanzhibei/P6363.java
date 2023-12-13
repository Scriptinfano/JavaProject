package JavaAlgorithm.chuanzhibei;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class Student implements Comparable<Student> {
    int id;
    int theoryScore;
    char team;

    public Student(int id, int theoryScore, char team) {
        this.id = id;
        this.theoryScore = theoryScore;
        this.team = team;
    }

    @Override
    public int compareTo(Student other) {
        // 按照得分从高到低排序，得分相同时按照队伍编号从小到大排序
        if (this.theoryScore != other.theoryScore) {
            return other.theoryScore - this.theoryScore;
        } else {
            return this.team - other.team;
        }
    }
}

public class P6363 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int k = scanner.nextInt();

        List<Student> students = new ArrayList<>();

        // 读取每位同学的理论成绩和队伍编号
        for (int i = 1; i <= n; i++) {
            int theoryScore = scanner.nextInt();
            char team = scanner.next().charAt(0);
            students.add(new Student(i, theoryScore, team));
        }

        int[][] teamScores = new int[k][k];

        // 读取队伍之间的评分
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                teamScores[i][j] = scanner.nextInt();
            }
        }

        // 计算每个队伍的项目得分
        double[] teamProjectScores = new double[k];
        for (int i = 0; i < k; i++) {
            teamProjectScores[i] = getRealAverage(i, teamScores);
        }

        // 计算每位同学的最终得分
        List<Student> result = new ArrayList<>();
        for (Student student : students) {
            double finalScore = 0.6 * student.theoryScore + 0.4 * teamProjectScores[student.team - 'A'];
            int roundedScore = (int) Math.round(finalScore);
            result.add(new Student(student.id, roundedScore, student.team));
        }

        // 按照规定的顺序输出结果
        Collections.sort(result);
        for (Student student : result) {
            System.out.println(student.theoryScore + " " + student.team);
        }
    }

    public static double getRealAverage(int index, int[][] teamScores) {
        int sum = 0;
        int rows = teamScores.length;
        for (int[] score : teamScores) {
            sum += score[index];
        }
        double average = (double) sum / rows;
        sum = 0;
        int tag = 0;
        for (int[] teamScore : teamScores) {
            if (Math.abs(teamScore[index] - average) <= 15) {
                sum += teamScore[index];
                tag++;
            }
        }
        double realAverage = (double) sum / tag;
        return Math.round(realAverage);
    }

}
