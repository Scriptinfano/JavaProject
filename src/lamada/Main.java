package lamada;

interface Callable {
    void call(String s);
}

class Describe {
    void show(String msg) {
        System.out.println(msg);
    }
}
class X{
    String f(){
        return "lamada.X::f()";
    }
}
interface MakeString{
    String make();
}
interface TransformX{
    String transform(X x);
}
public class Main {
    static void hello(String name) {
        System.out.println("Hello," + name);
    }

    static class Description {
        String about;

        Description(String msg) {
            about = msg;
        }

        void help(String msg) {
            System.out.println(about + " " + msg);
        }

    }

    static class Helper {
        static void assist(String msg) {
            System.out.println("assist: " + msg);
        }

    }

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
//lamada.MakeString ms=lamada.X::f;
TransformX sp=X::f;
X x=new X() ;
System.out.println(sp.transform(x));
System.out.println(x.f());

    }

    public static void test1(){
        Describe d = new Describe();
        Callable c = d::show;//将Describe对象的一个方法引用赋值给一个Callable
        c.call("call()");

        c = Main::hello;
        c.call("Bob");

        c = new Description("valuable")::help;
        c.call("information");

        c=Helper::assist;
        c.call("Help!");

    }

    public static void test2(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello world");
            }
        }).start();

        new Thread(()->System.out.println("lambda牛逼")).start();

        new Thread(Go::go).start();


    }
}

class Go{
    static void go(){
        System.out.println("lamada.Go::go()");
    }
}