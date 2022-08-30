//本程序主要介绍java中如何通过this来主动调用重载的构造函数
package constructor;

class Car {
    String model;
    int price;

    Car(String theModel, int thePrice) {
        this.model = theModel;
        this.price = thePrice;
    }

    //java中的构造函数的默认参数是通过重载的构造函数调用其他构造函数来实现的
    Car(String theModel) {
        this(theModel, 12000);//重载的构造函数中可以使用自引用方法来调用其所在类的另一个重载的构造函数
        //这种方法的好处在于，可以由一个构造函数完成所有复杂的准备工作，而另一个辅助构造函数则只是为该构造函数提供相应的参数。
    }
}


public class OverloadedConstructors {
    public static void main(String[] args) {
        Car car = new Car("蓝色小汽车");//调用第二个重载的构造函数，让重载的构造函数再调用其他构造函数完成变量的默认初始化

    }
}
