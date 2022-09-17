package designPattern.singleton;

//饿汉式单例设计，为什么叫饿汉就是因为类实例急于初始化，以至于在类加载时就进行了初始化
class SingletonInHungryStyle {
    private static SingletonInHungryStyle instance = new SingletonInHungryStyle();

    private SingletonInHungryStyle() {

    }

    public static SingletonInHungryStyle getInstance() {
        return instance;
    }
}

//懒汉式单例设计，懒汉式设计不急于初始化，注意懒汉式线程不安全问题
class SingletonInLazyStyle {
    private static SingletonInLazyStyle instance = null;

    //私有化构造器是单例模式的关键，即禁止外部调用构造器实例化类对象
    private SingletonInLazyStyle() {

    }

    //只能通过类的静态方法返回一个实例，而且仅在第一次调用静态方法时实例化内部对象
    public static SingletonInLazyStyle getInstance() {
        if (instance == null)//仅在第一次调用静态构造方法时调用构造函数
            instance = new SingletonInLazyStyle();//私有化构造器仅在类内部使用
        return instance;//返回内部的静态实例变量
    }
}


public class SingletonTestMain {

    public static void main(String[] args) {
        //就算有多个调用静态方法的语句，仍然只有一个对象，因为这个对象作为类中的静态变量是属于整个类的
        SingletonInHungryStyle single = SingletonInHungryStyle.getInstance();
        SingletonInHungryStyle single2 = SingletonInHungryStyle.getInstance();
        System.out.println(single2 == single);//输出true,说明两个引用指向一个对象，==比较引用时比较的是引用的指向是否相同
        SingletonInLazyStyle singletonInLazyStyle2 = SingletonInLazyStyle.getInstance();
        SingletonInLazyStyle singletonInLazyStyle = SingletonInLazyStyle.getInstance();
        System.out.println(singletonInLazyStyle == singletonInLazyStyle2);

    }

}
