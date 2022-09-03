/**
 * 这是一个临时的程序，用来测试各种代码
 */
package testEverything;

interface Carnivorous {
    default public void hunt() {
        System.out.println("捕猎前的准备工作");
    }
}

interface Herbivorous {
    public void DigestiveGrass();
}

abstract class Animal {
    public abstract void eat();

    public void run() {
        System.out.println("奔跑前的蓄力");
    }
}

class Sheep extends Animal implements Herbivorous {
    @Override
    public void eat() {
        System.out.println("羊吃草");
    }

    @Override
    public void DigestiveGrass() {
        System.out.println("羊消化吃掉的草");
    }

    @Override
    public void run() {
        super.run();
        System.out.println("羊奔跑");
    }
}

class Tiger extends Animal implements Carnivorous {
    @Override
    public void hunt() {
        Carnivorous.super.hunt();
        System.out.println("老虎正在捕猎");
    }

    @Override
    public void eat() {
        System.out.println("老虎吃饭");
    }

    @Override
    public void run() {
        super.run();
        System.out.println("老虎奔跑");
    }
}


public class TestMain {

    public static void main(String[] args) {
        Tiger tiger = new Tiger();
        tiger.run();
        tiger.hunt();
        tiger.eat();

    }

    public static void func(Animal obj) {
        obj.run();
    }
}
