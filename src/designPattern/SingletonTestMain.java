package designPattern;

//饿汉式单例设计
class SingletonInHungryStyle {
    private static SingletonInHungryStyle instance = new SingletonInHungryStyle();

    private SingletonInHungryStyle() {

    }

    public static SingletonInHungryStyle getInstance() {
        return instance;
    }
}

class SingletonInLazyStyle {
    private static SingletonInLazyStyle instance = null;

    private SingletonInLazyStyle() {

    }

    public static SingletonInLazyStyle getInstance() {
        if (instance == null)
            instance = new SingletonInLazyStyle();
        return instance;
    }
}


public class SingletonTestMain {

    public static void main(String[] args) {
        //就算有多个调用静态方法的语句，仍然只有一个对象，因为这个对象作为类中的静态变量是属于整个类的
        SingletonInHungryStyle single = SingletonInHungryStyle.getInstance();
        SingletonInHungryStyle single2 = SingletonInHungryStyle.getInstance();
        System.out.println(single2 == single);//输出true,说明是同一份对象
        SingletonInLazyStyle singletonInLazyStyle2 = SingletonInLazyStyle.getInstance();
        SingletonInLazyStyle singletonInLazyStyle = SingletonInLazyStyle.getInstance();
        System.out.println(singletonInLazyStyle == singletonInLazyStyle2);

    }

}
