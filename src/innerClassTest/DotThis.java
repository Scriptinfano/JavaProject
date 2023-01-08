package innerClassTest;

public class DotThis {
    private int i;

    public DotThis(int x) {
        i = x;
    }

    void f() {
        System.out.println("DoThis.f():" + i);
    }

    /**
     * 在外部类中定义方法，在其中创建内部类对象并返回，这是通过外部类对象创建内部类对象的其中一种方法
     *
     * @return {@link Inner}
     */
    public Inner inner() {
        return new Inner();
    }

    public class Inner {
        /**
         * 通过在内部类中定义方法返回外部类引用
         *
         * @return {@link DotThis}
         */
        public DotThis outer() {
            return DotThis.this;//返回的是外部对象的引用
        }
    }

}
class TestDotThis {
    public static void main(String[] args) {
        DotThis dt = new DotThis(12);
        DotThis.Inner dti = dt.inner();//使用外部类对象调用方法创建内部类对象
        dti.outer().f();

        DotThis dt2 = new DotThis(13);
        DotThis.Inner dti2 = dt2.inner();
        dti2.outer().f();//内部类对象同过内部定义的方法获得指向外部类对象的引用
    }
}
