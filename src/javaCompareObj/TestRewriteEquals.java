package javaCompareObj;

/**
 * 本程序将演示自定义的类如何通过重写equals函数实现比较对象的内容是否相同*/

class Person{
    private int id;
    private String name;

    public Person(int id,String name)
    {
        this.id=id;
        this.name=name;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==null)
            return false;
        else{
            if(obj instanceof Person)
            {
                Person person=(Person)obj;
                //比较字符串对象时一定要使用equals，String重写了equal比较的是字符串的内容是否相同
                if(person.getId()==id&&person.getName().equals(name))
                    return true;
            }
        }
        return false;
    }

    public int getId()
    {return id;}
    public String getName()
    {return name;}

}

public class TestRewriteEquals {
    public static void main(String[] args) {
        //instanceof只能检查变量是否是类的对象，不能检查基本数据类型
        int a=12;
        Integer a2=12;
        boolean is_String= "hello" instanceof String;
        boolean is_int=a2 instanceof Integer;

        Person p1=new Person(001,"张三");
        Person p2=new Person(002,"李四");
        System.out.println(p1==p2);
        System.out.println(p1.equals(p2));

        String s1=new String("大数据编程");
        String s2=new String("大数据编程");
        System.out.println(s1==s2);//比较的是字符串对象的地址是否相同
        System.out.println(s1.equals(s2));//比较的是内容是否相同



    }
}
