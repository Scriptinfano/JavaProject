package testResources;

public class Person {
    private String name;
    private int age;
    private Boolean mark;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setMark(Boolean mark) {
        this.mark = mark;
    }

    public void output() {
        System.out.println("姓名：" + name + ",年龄：" + age);
    }
}
