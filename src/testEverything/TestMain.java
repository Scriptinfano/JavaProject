/**
 * 这是一个临时的程序，用来测试各种代码
 */
package testEverything;

class Person {
    int age;
    String name;

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

}

public class TestMain {

    public static void main(String[] args) {
        String binaryString = "11011000110";
        int decimalNum = Integer.parseInt(binaryString, 2);
        System.out.println(decimalNum);


    }
}
