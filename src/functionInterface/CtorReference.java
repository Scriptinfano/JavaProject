package functionInterface;

class Dog {
    String name;
    int age = -1;

    Dog() {
        name = "stray";
    }

    Dog(String name) {
        this.name = name;
    }

    Dog(String name, int years){
        this.name=name;
        age=years;
    }
}

interface MakeNoArgs{
    Dog make();
}

interface MakeNameDog{
    Dog make(String name);
}

interface MakeNameAgeDog{
    Dog make(String name,int age);
}
public class CtorReference {
    public static void main(String[] args) {
        //与构造器签名相同的函数接口关联到了Dog内的构造器，通过调用接口中的方法达到调用构造函数的目的
        MakeNoArgs dog1=Dog::new;
        MakeNameDog dog2=Dog::new;
        MakeNameAgeDog dog3=Dog::new;

        Dog d=dog1.make();
        Dog d2=dog2.make("shit");
        Dog d3=dog3.make("mud",12);
    }
}
