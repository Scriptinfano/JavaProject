package javaClass;
class car{
    public void show()
    {
        System.out.println("drive");
    }

}
public class TestClass {
    public static void main(String[] args) throws ClassNotFoundException {
        Class testClass= TestClass.class;
        testClass.getClasses();
    }
}
