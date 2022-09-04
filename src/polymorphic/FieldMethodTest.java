package polymorphic;

class Base {
    int count = 10;

    public void display() {
        System.out.println(this.count);
    }
}

class Sub extends Base {
    int count = 20;

    public void display() {
        System.out.println(this.count);
    }
}

public class FieldMethodTest {
    public static void main(String[] args) {
        Sub s = new Sub();
        System.out.println(s.count);//20
        s.display();//20
        Base b = s;
        System.out.println(b == s);//true
        //==对于引用数据类型来讲比较的是两个引用数据类型的变量的地址值是否相同
        System.out.println(b.count);//10
        b.display();//20
    }
}
