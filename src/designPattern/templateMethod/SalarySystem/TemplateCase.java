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

public class TemplateCase {
    Employee employee = new SalariedEmployee("小明", 12, 2003, 3, 29);

}
