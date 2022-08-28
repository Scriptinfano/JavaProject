package javaTest.constructor;

class StaticSuper {

    //静态代码块在加载类时执行，一般用来初始化类中的静态常量，无需初始化即可执行
    static {
        System.out.println("super static block");

    }

    //非静态初始代码块只有在类实例化时才会执行，而且总是先于构造函数执行，因为一个类可以有多个构造函数，所以初始化代码块一般用来初始化不同对象中具有共性的内容
    {
        System.out.println("super non-static block");
    }

    StaticSuper() {
        System.out.println("super constructor");
    }
}

public class TestConstructor2 extends StaticSuper {
    //一个类中可以有多个静态代码块也可以有多个非静态代码块
    //静态代码块在类加载时执行，不需要创建实例也不需要用实例调用
    //非静态代码块仅在创建实例时调用，且总是先于构造器执行
    static {
        System.out.println("static block");
    }

    static {
        System.out.println("static block2");
    }

    {
        System.out.println("non-static block");
    }

    {
        System.out.println("non-static block2");

    }

    TestConstructor2() {
        System.out.println("main class constructor");
    }

    public static void main(String args[]) {
        System.out.println("in main");
        TestConstructor2 mainObj = new TestConstructor2();
    }
}


class DefaultConstructor {
    //注意：默认的构造器的权限取决于类的权限，如果类是public，则默认构造器的权限就是public；
}