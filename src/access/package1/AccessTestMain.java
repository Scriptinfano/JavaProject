/**
 * 本程序测试四种权限修饰符的使用
 */
package access.package1;

/*
 * protected访问权限只有同一个包的类和其他包的子类可以访问
 * default访问权限只有同一个包的类可以访问，其他均无法访问*/
public class AccessTestMain {
    public int publicVariable;
    protected int protectedVariable;
    int defaultVariable;
    private int privateVariable;

    private void privateMethod() {
        System.out.println("privateMethod调用");
    }

    void defaultMethod() {
        System.out.println("defaultMethod调用");

    }

    protected void protectedMethod() {
        System.out.println("protectedMethod调用");

    }

    public void publicMethod() {
        System.out.println("publicMethod调用");

    }


}
