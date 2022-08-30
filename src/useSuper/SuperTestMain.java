//本程序演示super关键字的使用
package useSuper;

class Person {
    String name;
    int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person() {

    }

    public void eat() {
        System.out.println("人，吃饭");

    }
}

class Student extends Person {
    String major;

    public Student() {

    }

    public Student(String major) {
        this.major = major;
    }

    @Override
    public void eat() {
        System.out.println("学生，吃饭");
    }

    public void study() {
        System.out.println("学生学习");
    }

    public void show() {
        System.out.println("姓名：" + super.name + " 年龄：" + super.age + " 专业：" + this.major);
    }
}

public class SuperTestMain {
}
