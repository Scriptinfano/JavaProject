//测试同一个包下，对其他类的访问权限
package access.package1;

public class AccessTest {
    public static void main(String[] args) {
        AccessTestMain accessTestMain = new AccessTestMain();
        accessTestMain.defaultVariable = 12;
        accessTestMain.protectedVariable = 13;
        accessTestMain.publicVariable = 14;
        accessTestMain.defaultMethod();
        accessTestMain.protectedMethod();
        accessTestMain.publicMethod();

        //同一个包中的其他类不能调用此类中私有的属性和方法

    }
}
