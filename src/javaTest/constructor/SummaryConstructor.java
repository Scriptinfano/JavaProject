package javaTest.constructor;

public class SummaryConstructor {
    /*
     * static final 成员变量
     */
    static final int field_d1 = 5;
    //报错：未初始化 。The blank final field field_d2 may not have been initialized
    //static final int field_d2;
    //报错：未初始化 。The blank final field field_d3 may not have been initialized
    //static final int field_d3;
    static final int field_d4;
    //报错：不能再静态代码块中使用非静态变量。Cannot make a static reference to the non-static field field_b4
    //final int  field_b4;
    //报错：未初始化 。The blank final field field_b5 may not have been initialized
    //final int  field_b5;
    /*
     * static成员变量
     */
    static int field_c1 = 5;
    static int field_c2;
    static int field_c3;
    static int field_c4;
    static int field_c5;

    //静态代码块
    static {
        //field_a4 = 5;
        //field_b4 = 5;
        field_c4 = 5;
        field_d4 = 5;
    }

    /*
     * final 成员变量
     */
    final int field_b1 = 5;
    final int field_b2;
    final int field_b3;
    /*
     * 定义成员变量
     * 尾数为1表示定义时进行初始化赋值
     * 尾数为2表示在代码块中进行初始化赋值
     * 尾数为3表示在构造函数中进行初始化赋值
     * 尾数为4表示在静态代码块中进行初始化赋值
     * 尾数为5表示不初始化赋值
     */
    /*
     * 普通成员变量
     */
    int field_a1 = 5;
    int field_a2;
    int field_a3;
    //报错：未初始化 。The blank final field field_d5 may not have been initialized
    //static final int field_d5;
    //报错：不能再静态代码块中使用非静态变量。 Cannot make a static reference to the non-static field field_a4
    //int field_a4;
    int field_a5;

    //代码块
    {

        field_a2 = 5;
        field_b2 = 5;
        field_c2 = 5;
        //field_d2 = 5;
    }

    //构造函数
    public SummaryConstructor() {
        field_a3 = 5;
        field_b3 = 5;
        field_c3 = 5;
        //field_d3 = 5;

    }


    public static void main(String[] args) {

    }
}

//如果对上面的类进行反编译，则会得到下面这个等价的类
class TestConstructor3 {

    static final int field_d1 = 5;
    static final int field_d4 = 5;
    static int field_c1 = 5;
    static int field_c2;
    static int field_c3;
    static int field_c4 = 5;
    static int field_c5;
    final int field_b1 = 5;
    final int field_b2 = 5;
    final int field_b3 = 5;
    int field_a1;
    int field_a2;
    int field_a3;
    int field_a5;

    public TestConstructor3() {
        field_a1 = 5;
        field_a2 = 5;
        field_c2 = 5;
        field_a3 = 5;
        field_c3 = 5;
    }

}