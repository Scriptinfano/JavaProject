package abstractClass;

public class AbstractTest {
    public static void main(String[] args) {
        Person p = new Teacher("xiao", 12);
        p.walk();
    }
}

abstract class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public abstract void walk();//抽象类中的抽象方法，包含抽象方法的类必须是抽象类，但是抽象类可以没有抽象方法

}

class Teacher extends Person {
    public Teacher(String name, int age) {
        super(name, age);
    }

    @Override
    public void walk() {
        System.out.println("老师走路");
    }
}