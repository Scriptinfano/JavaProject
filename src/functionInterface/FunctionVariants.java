package functionInterface;

import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.LongFunction;

class Foo {

}

class Bar {
    Foo f;

    Bar(Foo f) {
        this.f = f;
    }
}

class IBaz {
    int i;

    IBaz(int i) {
        this.i = i;
    }
}

class LBaz {
    long l;

    LBaz(long l) {
        this.l = l;

    }
}

class DBaz {
    double d;

    DBaz(double d) {
        this.d = d;

    }
}


public class FunctionVariants {
    static Function<Foo, Bar> f1 = Bar::new;
    static IntFunction<IBaz> f2 = IBaz::new;

    static LongFunction<LBaz> f3 = LBaz::new;

    public static void main(String[] args) {
        LBaz az = f3.apply(2);
    }
}
