//本程序测试包装器类的API
package basicTypeWrapperTest;

public class TestMain {
    public static void main(String[] args) {
        testClass();
    }

    public static void testCharacter() {
        Character c = 'f';
        char h = c.charValue();//将Character对象转换为char，要用到charValue方法
        boolean is_digit = Character.isDigit(c);//判断字符代表的是否是数字
        char a = Character.toLowerCase('W');//将大写字母转换为小写
        char b = Character.toUpperCase('w');//将小写字母转换为大写
    }

    public static void testClass() {
        String conutry = "America";
        Class myclass = conutry.getClass();
        System.out.println(myclass.getName());//输出Class对象表示的类的完全限定名

        //可在不使用new的情况下创建对象，调用Class类的forName()和newInstance()
        //newInstance()创建类的新实例，返回的是Object，所以需要向下转型为原始类型
        Class klass = null;
        try {
            klass = Class.forName("basicTypeWrapperTest.TestMain");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (klass != null) {
            try {
                TestMain test = (TestMain) klass.newInstance();
                test.testCharacter();
            } catch (InstantiationException e) {

            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        //如果在创建类时不知道类名可以考虑用上面的方式
    }

    public static void testSystem() {

    }
}
