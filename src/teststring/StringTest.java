package teststring;

public class StringTest {
    public static void main(String[] args) {
        //String可以和八种基本数据类型做连接运算，注意+号两边必须有一个变量是String类型
        int number = 1002;
        boolean judge = false;
        String as = "qwe";
        String result = number + as;
        result = result + judge;
        System.out.println(result);


        System.out.println("*   *");
        System.out.println('*' + '\t' + '*');//两个char做运算后转换为int,int又和char做加法运算又转换为int
        System.out.println('*' + "\t" + '*');
        System.out.println('*' + '\t' + "*");
        System.out.println('*' + ('\t' + "*"));

        //java的基本类型包装器都有相应的函数可以将String类型的数字转换对应的基本数据类型
        String num1 = "123.23";
        String num2 = "12";
        String num3 = "23.89";
        double d_num1 = Double.parseDouble(num1);
        int i_num2 = Integer.parseInt(num2);
        float f_num3 = Float.parseFloat(num3);

/*
        其他常用String成员函数
        boolean startsWith(String prefix)：判断此字符串是否以指定的前缀开始。
        boolean endsWith(String suffix)：判断此字符串是否以指定的后缀结束。
        boolean isEmpty() ：当且仅当 length() 为 0 时返回 true。
        boolean contains(CharSequence s)：当且仅当此字符串包含指定的 char 值序列时，返回 true。
        String toUpperCase()：使用默认语言环境的规则将此 String 中的所有字符都转换为大写。
        String toLowerCase()：使用默认语言环境的规则将此 String 中的所有字符都转换为小写。
*/
        String x = String.valueOf(12);// 基本数据类型转字符串
        System.out.println(x);

        // 基本数据类型转字符串，包装器类调用toString
        int a = 5;
        Integer i = a;
        String z = i.toString();
        double d_str = 12.3;
        Double d_str2 = d_str;
        String str2 = d_str2.toString();

        int y = Integer.parseInt("21"); // 字符串转基本数据类型
        System.out.println(y);

        String str3=new String("hello world");
        str3=str3.substring(3,5);
        System.out.println(str3);
        str3="hello";
        str3=str3.substring(2);
        System.out.println(str3);

    }
}
