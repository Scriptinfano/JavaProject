/**
 * 这是一个临时的程序，用来测试各种代码
 */
package testEverything;
import java.util.*;

class Person {
    private final int age;
    private final String name;

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }
}

class PersonFact {
    public static Person getPerson(int age, String name) {
        return new Person(age, name);
    }
}

class TestMain {
    public static void main(String[] args) {
/*
        Stack<Person> stack = new Stack<Person>();
        stack.push(PersonFact.getPerson(12, "小红"));
        stack.push(PersonFact.getPerson(14, "小明"));
        stack.push(PersonFact.getPerson(45, "赵国豪"));
        stack.push(PersonFact.getPerson(34, "建国"));
        stack.push(PersonFact.getPerson(67, "拜登"));
        stack.push(PersonFact.getPerson(89, "二货"));
        stack.push(PersonFact.getPerson(78, "傻叉习近平"));
        boolean result = stack.stream().anyMatch(person -> person.getAge() > 30);
        System.out.println(result);
*/

        Queue<Person> queue = new LinkedList<>();
        queue.offer(PersonFact.getPerson(12, "小红"));
        queue.offer(PersonFact.getPerson(14, "小明"));
        queue.offer(PersonFact.getPerson(45, "赵国豪"));
        queue.offer(PersonFact.getPerson(34, "建国"));
        queue.offer(PersonFact.getPerson(67, "拜登"));
        queue.offer(PersonFact.getPerson(89, "二货"));
        queue.offer(PersonFact.getPerson(78, "傻叉习近平"));
        System.out.println(queue.peek().getName());
    }
}