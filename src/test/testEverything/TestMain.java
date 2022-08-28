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
        int[] array = {2, 7, 8, 9, 3, 5, 4, 6, 1};
        for (int x = 1; x + 1 <= array.length - 1; x++) {
            int i = x;
            while (i + 1 <= array.length - 1) {
                swapElement(array, i, i + 1);
                i += 2;
            }
        }
        System.out.print("{");
        for (int i : array) {
            System.out.print(i + ", ");
        }
        System.out.print("}");
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
