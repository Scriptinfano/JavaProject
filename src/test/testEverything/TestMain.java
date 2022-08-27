package test.testEverything;

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

public class TestMain {

    public static void main(String[] args) throws CloneNotSupportedException {


    }

    public static void testClone() throws CloneNotSupportedException {
        Person person = new Person(12, "ming");
        Person person2 = person.clone();
        System.out.println(person2);
        person2.show();

    }
}
