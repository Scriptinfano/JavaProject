package polymorphic;

class Base2 {
    public void add(int a, int... arr) {
        System.out.println("base");
    }
}

class Sub2 extends Base2 {
    public void add(int a, int[] array) {
        System.out.println("sub_1");
    }

    public void add(int a, int b, int c) {
        System.out.println("sub_2");
    }
}

//编译器认为形参int[]array和int...arr在本质上是相同的
public class Practice2 {
    public static void main(String[] args) {
        Base2 base = new Sub2();
        base.add(1, 2, 3);

        Sub2 s = (Sub2) base;//强制转为子类后只会调用子类的方法
        s.add(1, 2, 3);
    }
}
