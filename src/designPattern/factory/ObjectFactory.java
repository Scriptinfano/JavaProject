package designPattern.factory;

import java.util.LinkedList;
import java.util.Queue;

public class ObjectFactory {
    private static final ObjectFactory factory = null;

    public static ObjectFactory getInstance() {
        if (factory == null) return new ObjectFactory();
        else return factory;
    }

    private ObjectFactory() {
    }

    public class Person {
        private final int age;
        private final String name;

        private Person(int age, String name) {
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

    public ObjectFactory.Person getPerson(int age, String name) {
        return new ObjectFactory.Person(age, name);
    }
}
class TestFact{
    public static void main(String[] args) {
        Queue<ObjectFactory.Person> queue = new LinkedList<>();
        ObjectFactory factory = ObjectFactory.getInstance();
        queue.offer(factory.getPerson(12, "小红"));
        queue.offer(factory.getPerson(14, "小明"));
        queue.offer(factory.getPerson(45, "赵国豪"));
        queue.offer(factory.getPerson(34, "建国"));
        queue.offer(factory.getPerson(67, "拜登"));
        queue.offer(factory.getPerson(89, "二货"));
        queue.offer(factory.getPerson(78, "傻叉习近平"));
        System.out.println(queue.peek().getName());
    }
}
