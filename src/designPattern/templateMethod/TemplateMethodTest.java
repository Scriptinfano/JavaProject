//本程序介绍模板方法设计模式
package designPattern.templateMethod;

abstract class TimeCalculator {
    //计算某段代码执行所花费的时间
    public double getAlgorithmTime() {
        long begin = System.nanoTime();
        code();//模板方法设计模式中易变的部分,code是抽象方法，调用会根据父类引用指向子类对象的不同而不同
        long end = System.nanoTime();
        return (end - begin) / 1000000d;
    }

    public abstract void code();
}

class SubTemplate extends TimeCalculator {
    @Override
    public void code() {
        System.out.println("输出3到1000的所有质数");
        for (int i = 2; i <= 10000; i++) {
            boolean isFlag = true;
            for (int j = 2; j <= Math.sqrt(i); j++) {
                if (i % j == 0) {
                    isFlag = false;
                    break;
                }

            }
            if (isFlag) System.out.println(i);
        }
    }
}

public class TemplateMethodTest {
    public static void main(String[] args) {
        TimeCalculator t = new SubTemplate();
        double algorithmTime = t.getAlgorithmTime();
        System.out.println(algorithmTime);
    }
}
