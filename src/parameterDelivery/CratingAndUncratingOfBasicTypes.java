//本程序主要探讨基本类型的包装器(java中可以将基本数据类型包装成一个类对象)
package parameterDelivery;

import java.util.ArrayList;
import java.util.List;

public class CratingAndUncratingOfBasicTypes {
    public static void main(String[] args) {

        //基本类型的自动装箱与拆箱
        /*Java 编译器自动将基本类型包装到其包装器类型中，并且在适当的时候对它们解
        包。这个过程叫做基本类型的自动装箱和拆箱。*/
        List<Integer>myNums=new ArrayList<Integer>();
        myNums.add(12);
        int n= myNums.get(0);//基本类型值的自动拆箱

        Integer integer=5;//java直接将值5包装到Integer类中
        int i=new Integer(4);//将Integer对象解包为其基本类型值
    }
}
