/*
* 可变长度的参数列表
* java方法可以有可变长度的参数列表，别名叫做varargs，允许方法在调用的时候接受任意数目的参数
* 语法：在参数列表中应该使用数组的方括号的位置使用省略号*/

package JavaParameterDelivery;

public class VariableLengthArgumentList {
public static void printObjects(Object ...list)
{
    //在 printObjects()方法中，变量 list 实际上是一个 Object []类型
    for(Object o:list)
    {
        System.out.println(o);
    }
    System.out.println("一共传递了"+list.length+"个参数");
    //注意：一个方法只能有一个 varargs 声明，并且，它必须在方法签名的最后
}


    public static void main(String[] args) {
        printObjects("12","13");
    }
}
