package innerClassTest;

public class DotThis {
    private int i;

    public DotThis(int x) {
        i = x;
    }

    void f() {
        System.out.println("DoThis.f():" + i);
    }

    public Inner inner() {
        return new Inner();
    }

    public class Inner {
        public DotThis outer() {
            return DotThis.this;//返回的是外部对象的引用
        }
    }

}

class DotNew {
    public class Inner {
    }
}

class TestDotThis {
    public static void main(String[] args) {
        DotThis dt = new DotThis(12);
        DotThis.Inner dti = dt.inner();
        dti.outer().f();

        DotThis dt2 = new DotThis(13);
        DotThis.Inner dti2 = dt2.inner();
        dti2.outer().f();
    }
}
