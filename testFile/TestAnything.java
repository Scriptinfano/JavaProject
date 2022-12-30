class Person{
   private String name;
   private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private Boolean mark;

    public void setMark(Boolean mark) {
        this.mark = mark;
    }
}
public class TestAnything {
    private final Person[][]persons;
    public TestAnything(){
        persons=new Person[3][3];
        for(int i = 0; i < persons.length; i++){
            for (int j = 0; j< persons[i].length; j++){
                persons[i][j]=new Person("小明",12);
            }
        }
    }

    public void  setPerson(int i,int j,String name,int age){
        persons[i][j].setAge(age);
        persons[i][j].setName(name);
    }
    public void setMark(int i,int j,boolean mark){
        persons[i][j].setMark(mark);
    }
    public static void main(String[] args) {
        TestAnything any=new TestAnything();
        any.setPerson(0,0,"goushi",13);
        any.setMark(0,1,true);
        int a =13;
        System.out.println(a);
    }
}
