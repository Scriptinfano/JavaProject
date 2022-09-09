//本程序测试包装器类的API
package basicTypeWrapperTest;

public class TestMain {
    public static void main(String[] args) {
        practice();
    }

    public static void testCharacter() {
        Character c = 'f';
        char h = c.charValue();//将Character对象转换为char，要用到charValue方法
        boolean is_digit = Character.isDigit(c);//判断字符代表的是否是数字
        char a = Character.toLowerCase('W');//将大写字母转换为小写
        char b = Character.toUpperCase('w');//将小写字母转换为大写
    }

    //测试基本数据类型，包装类，String类的相互转化
    public static void testTransform() {

        //基本数据类型转换为包装类
        int intVariable = 1;
        Integer intVariableObj = intVariable;//自动装箱

        //包装类转换为基本数据类型
        int intVariableVal2 = intVariableObj.intValue();//拆箱操作
        intVariableVal2 = intVariableObj;//自动拆箱操作

        //包装类转换为String类
        String str = intVariableObj.toString();

        //String类转换为包装类
        String str2 = "123";
        Integer intVariableVal3 = Integer.parseInt(str2);

        //String转换为基本数据类型
        int intVariableVal = Integer.parseInt(str2);

        //基本数据类型转换为String类
        int intVariable5 = 14;
        String str3 = String.valueOf(intVariable5);
    }


    public static void practice() {
        //下面两个输出是否相同
        Object o2 = Integer.valueOf(12);
        System.out.println(o2);
    }
}
