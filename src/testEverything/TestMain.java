package testEverything;

class StaticSuper {
    static {
        System.out.println("super static block");
    }

    {
        System.out.println("super non-static block");
    }

    StaticSuper() {
        System.out.println("super constructor");
    }
}

public class TestMain extends StaticSuper {
    static {
        System.out.println("static block");
    }

    {
        System.out.println("non-static block");
    }

    TestMain() {
        System.out.println("main class constructor");
    }

    public static void main(String args[]) {
        System.out.println("in main");
        TestMain mainObj=new TestMain();

    }
}
