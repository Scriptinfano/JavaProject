/**
 * 编写工资系统，实现不同类型员工（多态）地按月发工资，如果当月出现某个Employee对象的
 * 生日，则该雇员的工资增加100元
 * 说明：
 * 1、定义Employee类，该类包含：
 * private成员变量：name,number,birthday（Calender对象）
 * abstract方法：earnings()
 * toString方法：输出对象的name,number,birthday
 * 2、定义SalariedEmployee类继承Employee类，实现按月计算工资的员工处理，该类包括：
 * private成员变量：monthlySalary
 * 实现父类抽象方法earnings(),该方法返回monthlySalary值
 * toString方法输出员工类型信息以及员工的name,number,birthday
 */


package designPattern.templateMethod.SalarySystem;

import myscan.ScannerPlus;

import java.util.Calendar;

public class SalarySystemMain {
    Employee employee = new SalariedEmployee("小明", 12, 2003, 3, 29);
    private static ScannerPlus scanner = new ScannerPlus();

    public static void main(String[] args) {
        System.out.print("输入当月的月份");
        int month = scanner.nextInt();
        Employee[] employees = new Employee[3];
        employees[0] = new SalariedEmployee("小明", 12, 2002, 3 - 1, 29);
        employees[1] = new SalariedEmployee("马森", 23, 2004, 5 - 1, 12);
        employees[2] = new HourlyEmployee("小王", 31, 1998, 8 - 1, 14, 100, 240);
        System.out.println("所有员工的信息如下");
        for (Employee e : employees) {
            System.out.print(e.toString());
            if (month == e.getBirthday().get(Calendar.MONTH)) {
                System.out.print(" 该员工生日快乐，奖励100元");
            }
            System.out.println();
        }


    }
}
