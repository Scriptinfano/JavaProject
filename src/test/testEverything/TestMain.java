package test.testEverything;

interface learnable {
    public default void leaern() {
        System.out.println("学习");
    }

    public void shit();
}

class Person implements Cloneable {
    int age;
    String name;

    Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    protected Person clone() throws CloneNotSupportedException {
        return (Person) super.clone();
    }

    public void show() {
        System.out.println("年龄：" + age + " 姓名：" + name);
    }


}

class Student extends Person implements learnable {

    public Student(int age, String name) {
        super(age, name);
    }

    @Override
    public void leaern() {
        learnable.super.leaern();//调用父类接口提供的默认实现
    }

    @Override
    public void shit() {
        System.out.println("拉屎");
    }
}

public class TestMain {

    public static void main(String[] args) throws CloneNotSupportedException {
        Person p1 = new Student(12, "小明");
        p1.show();
        Person p2 = p1.clone();
        p2.show();
        Student stu = new Student(12, "小红");
        stu.leaern();

    }

    public static void testClone() throws CloneNotSupportedException {
        Person person = new Person(12, "ming");
        Person person2 = person.clone();
        System.out.println(person2);
        person2.show();
    }

    public static void swapElement(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
