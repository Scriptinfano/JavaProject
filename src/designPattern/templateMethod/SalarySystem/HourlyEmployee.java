package designPattern.templateMethod.SalarySystem;

public class HourlyEmployee extends Employee {
    private int wage;//每小时的工资
    private int hour;//月工作的小时数

    public HourlyEmployee(String name, int age, int year, int month, int day, int wage, int hour) {
        super(name, age, year, month, day);
        this.wage = wage;
        this.hour = hour;
    }

    @Override
    public double earnings() {
        return wage * hour;
    }

    @Override
    public String toString() {
        return "HourlyEmployee:" + super.toString() + ",wage:" + wage + ",hour:" + hour;
    }
}
