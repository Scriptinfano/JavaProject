/**
 * 这是一个临时的程序，用来测试各种代码
 */
package testEverything;

class TestClass {
    public enum childMark {
        LEFT,//该节点是父节点的左子
        RIGHT,//该节点是父节点的右子
        NONE//该节点没有父节点
    }

    private childMark mark;

    public childMark getMark() {
        return mark;
    }
}

public class TestMain {
    public static void main(String[] args) {
        TestClass test = new TestClass();
TestClass.childMark mark=test.getMark();
System.out.println(mark);
    }


}