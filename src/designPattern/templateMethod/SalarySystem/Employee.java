package designPattern.templateMethod.SalarySystem;

import java.util.Calendar;

public abstract class Employee {
    private final String name;
    private final int age;
    private final Calendar birthday = Calendar.getInstance();

    public Employee(String name, int age, int year, int month, int day) {
        this.name = name;
        this.age = age;
        birthday.set(year, month - 1, day);
    }

    public abstract double earnings();

    @Override
    public String toString() {
        String birthdayInfo = String.format("%tF", birthday.getTime());
        return "[name=" + name + ", age=" + age + ", birthday=" + birthdayInfo + "]";
    }

    public Calendar getBirthday() {
        return birthday;
    }
}

