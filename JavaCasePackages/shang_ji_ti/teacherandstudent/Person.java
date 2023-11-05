package shang_ji_ti.teacherandstudent;

public class Person implements Complain {
    private String name;
    private Sex sex;

    public Person(String name, Sex sex) {
        this.name = name;
        this.sex = sex;
    }

    @Override
    public MyComment makeComments() {
        return MyComment.makeMyComment();
    }

    public String getName() {
        return name;
    }

    public enum Sex {
        Male, Female
    }
}
