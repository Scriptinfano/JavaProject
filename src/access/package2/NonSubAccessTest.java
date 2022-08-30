//测试不同包下，普通类对其他包的类的访问权限问题
package access.package2;

import access.package1.AccessTestMain;

public class NonSubAccessTest {
    public static void main(String[] args) {
        AccessTestMain accessTest = new AccessTestMain();
        accessTest.publicVariable = 99;
        accessTest.publicMethod();
        //要访问不同包下的类只能访问其公有接口

    }
}
