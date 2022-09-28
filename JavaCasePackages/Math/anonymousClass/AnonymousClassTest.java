package anonymousClass;

abstract class Animal {
    String name;

    public Animal(String name) {
        this.name = name;
    }

    public abstract void walk();
}

class Sheep extends Animal {
    public Sheep(String name) {
        super(name);
    }

    @Override
    public void walk() {
        System.out.println("羊走路");
    }
}

//创建匿名子类对象
public class AnonymousClassTest {
    public static void main(String[] args) {
        Animal animation = new Animal("shit") {
            @Override
            public void walk() {
                System.out.println("匿名动物走路");
            }
        };//创建了一个匿名子类对象
        animation.walk();


    }

}
