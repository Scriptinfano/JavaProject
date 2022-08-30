//测试子类访问在其他包的父类的权限问题
package access.package2;

import access.package1.AccessTestMain;

public class SubAccessTestMain extends AccessTestMain {

    public void SubPublicMethod() {
        //其他包的子类只能访问父类的public和protected的属性和方法
        publicVariable = 1;
        publicMethod();
        protectedVariable = 2;
        protectedMethod();
    }


}
