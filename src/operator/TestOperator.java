package operator;

public class TestOperator {
    public static void main(String[] args) {
        //instanceof只能检查变量是否是类的对象，不能检查基本数据类型
        int a=12;
        Integer a2=12;
        boolean is_String= "hello" instanceof String;
        boolean is_int=a2 instanceof Integer;

/*Object方法equals()方法和==运算符的区别
        一、对象类型不同

        1、equals()：是超类Object中的方法。

        2、==：是操作符。

        二、比较的对象不同

        1、equals()：用来检测两个对象是否相等，即两个对象的内容是否相等。

        2、==：用于比较引用和比较基本数据类型时具有不同的功能，具体如下：

         （1）、基础数据类型：比较的是他们的值是否相等，比如两个int类型的变量，比较的是变量的值是否一样。

         （2）、引用数据类型：比较的是引用的地址是否相同，比如说新建了两个User对象，比较的是两个User的地址是否一样。

        三、运行速度不同

        1、equals()：没有==运行速度快。

        2、==：运行速度比equals()快，因为==只是比较引用。

        扩展资料：
        equals()和==的源码定义：
        public boolean equals(Object obj) {
        return (this == obj);
        }
        由equals的源码可以看出这里定义的equals与==是等效的（Object类中的equals没什么区别），不同的原因就在于有些类（像String、Integer等类）对equals进行了重写。
        但是没有对equals进行重写的类就只能从Object类中继承equals方法，其equals方法与==就也是等效的，除非在此类中重写equals。

        对equals重新需要注意五点：

        1、自反性：对任意引用值X，x.equals(x)的返回值一定为true；

        2、对称性：对于任何引用值x,y,当且仅当y.equals(x)返回值为true时，x.equals(y)的返回值一定为true；

        3、传递性：如果x.equals(y)=true, y.equals(z)=true,则x.equals(z)=true ；

        4、 一致性：如果参与比较的对象没任何改变，则对象比较的结果也不应该有任何改变；

        5、非空性：任何非空的引用值X，x.equals(null)的返回值一定为false 。

        对==的说明：
        == 比较的是变量(栈)内存中存放的对象的(堆)内存地址，用来判断两个对象的地址是否相同，即是否是指相同一个对象。比较的是真正意义上的指针操作。

        1、比较的是操作符两端的操作数是否是同一个对象。

        2、两边的操作数必须是同一类型的（可以是父子类之间）才能编译通过。

        3、比较的是地址，如果是具体的阿拉伯数字的比较，值相等则为true，如：

        int a=10 与 long b=10L 与 double c=10.0都是相同的（为true），因为他们都指向地址为10的堆。

        对equals()的说明
        equals用来比较的是两个对象的内容是否相等，由于所有的类都是继承自java.lang.Object类的，所以适用于所有对象，如果没有对该方法进行覆盖的话，调用的仍然是Object类中的方法，而Object中的equals方法返回的却是==的判断。

        String s="abce"是一种非常特殊的形式,和new 有本质的区别。它是java中唯一不需要new 就可以产生对象的途径。

        以String s="abce";形式赋值在java中叫直接量,它是在常量池中而不是象new一样放在压缩堆中。这种形式的字符串，在JVM内部发生字符串拘留，即当声明这样的一个字符串后，JVM会在常量池中先查找有有没有一个值为"abcd"的对象。

        如果有，就会把它赋给当前引用.即原来那个引用和现在这个引用指点向了同一对象，如果没有，则在常量池中新创建一个“abcd"”，下一次如果有Strings1="abcd"；又会将s1指向“abcd”这个对象，即以这形式声明的字符串，只要值相等，任何多个引用都指向同一对象。

        而String s=new String（"abcd”）；和其它任何对象一样.每调用一次就产生一个对象，只要它们调用。

        也可以这么理解：String str="hello”；先在内存中找是不是有“hello”这个对象，如果有，就让str指向那个“hello”。

        如果内存里没有"hello"，就创建一个新的对象保存"hello”.String str=new String（“hello"）就是不管内存里是不是已经有"hello"这个对象，都新建一个对象保存"hello"。
*/
    }
}
