package designPattern.templateMethod.SalarySystem;

public class SalariedEmployee extends Employee {

    private int monthlySalary;//月工资

    public SalariedEmployee(String name, int age, int year, int month, int day) {
        super(name, age, year, month, day);
    }

    @Override
    public double earnings() {
        return monthlySalary;
    }

    @Override
    public String toString() {
        return "SalariedEmployee:" + super.toString();

    }

}
