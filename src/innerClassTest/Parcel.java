package innerClassTest;

//如何通过外部类对象直接创建内部类对象的方法，使用p.new 内部类() 语法（p是外部类对象名）
//该方法可以省略掉了在外部类中定义方法返回内部类对象的步骤
public class Parcel {
    class Contents {
        private int i = 11;

        public int value() {
            return i;
        }

    }

    class Destination {
        private String label;

        Destination(String whereTo) {
            label = whereTo;
        }

        String readLabel() {
            return label;
        }
    }


}

class TestParcel {
    public static void main(String[] args) {
        Parcel p = new Parcel();
        Parcel.Contents c = p.new Contents();
        Parcel.Destination d = p.new Destination("Tasmania");
    }
}

//除非已经有一个外部类对象，否则创建内部类对象是不可能的，因为内部类对象总是要链接到它的外部类对象，如果是嵌套类（static修饰的内部类）则不需要
//指向外部的引用