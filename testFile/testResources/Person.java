package testResources;

import arrayutil.ArrayUtil;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class Person implements Comparable<Person> {
    private String name;
    private int age;
    private Boolean mark;

    private final Random randomGenerator = new Random();

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person() {
        age = 0;
        name = "无";
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

    @Override
    public int compareTo(@NotNull Person o) {
        return Integer.compare(age, o.age);
    }

    public void setRandomAge() {
        age = randomGenerator.nextInt(1, 100);
    }

    public void setRandomName() {
        name = ArrayUtil.randomString(10);
    }

    public String outputHash() {
        return toString();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Person{");
        sb.append("name='").append(name).append('\'');
        sb.append(", age=").append(age);
        sb.append('}');
        return sb.toString();
    }
}
