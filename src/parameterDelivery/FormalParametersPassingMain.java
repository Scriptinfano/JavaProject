package parameterDelivery;

import java.io.*;

class FormalParametersPassing {
    public static void swap(Integer a, Integer b) {
        System.out.println("已经进入swap函数");
        System.out.println("在swap前：");
        System.out.println("形参a的hash:" + System.identityHashCode(a));
        System.out.println("形参b的hash:" + System.identityHashCode(b));

        Integer temp = a;
        a = b;
        b = temp;
        System.out.println("在swap后：");
        System.out.println("形参a的hash:" + System.identityHashCode(a));
        System.out.println("形参b的hash:" + System.identityHashCode(b));

        int a_a = a.intValue();
        int b_b = b.intValue();
        System.out.println("在swap中显示两个形参的值：" + a_a + " " + b_b);
    }

    public static void test1() {
        int a = 12, b = 113;
        Integer aObj = 12, bObj = 113;
        System.out.println("在main方法中");
        System.out.println("a的hash:" + System.identityHashCode(a));
        System.out.println("b的hash:" + System.identityHashCode(b));
        System.out.println("aObj的hash:" + System.identityHashCode(aObj));
        System.out.println("bObj的hash:" + System.identityHashCode(bObj));

        swap(a, b);
        swap(aObj, bObj);
        System.out.println("执行完swap后回到了main方法");
        System.out.println("a=" + a + " b=" + b);
        System.out.println("aObj=" + aObj + " bObj=" + bObj);
        System.out.println("在main方法中再次查看hash:");
        System.out.println("a的hash:" + System.identityHashCode(a));
        System.out.println("b的hash:" + System.identityHashCode(b));
        System.out.println("aObj的hash:" + System.identityHashCode(aObj));
        System.out.println("bObj的hash:" + System.identityHashCode(bObj));


    }
}

public class FormalParametersPassingMain {
    public static void main(String[] args) throws IOException {
    }
}
