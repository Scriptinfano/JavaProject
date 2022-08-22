//本程序主要介绍java中构造函数和C++中构造函数不一样的地方
package JavaConstructor;

import javaException.*;


import java.awt.*;
import java.util.Hashtable;

class cars {
    String model;
    int door;

    cars(String theModel, int theDoor) {
        this.model = theModel;
        this.door = theDoor;
    }

    cars(String theModel) {
        this(theModel, 12);//重载的构造函数中可以使用自引用方法来调用其所在类的另一个重载的构造函数
        //这种方法的好处在于，可以由一个构造函数完成所有复杂的准备工作，而另一个辅助构造函数则只是为该构造函数提供相应的参数。
    }
}

//使用静态代码块为静态变量完成复杂的初始化工作
class ColorWheel {
    static Hashtable colors = new Hashtable();

    static {
        colors.put("red", Color.red);
        colors.put("green", Color.green);
    }
}

public class TestConstructor {
    public static void main(String[] args) {
        cars car = new cars("蓝色小汽车");
        TestException.main(args);

    }
}
