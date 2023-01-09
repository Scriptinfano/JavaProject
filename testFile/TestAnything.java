import testResources.Person;

public class TestAnything {
    private final Person person = new Person("小明", 21);

    public void transform(Another another) {
        another.setPerson(person);
    }

    public void showPerson() {
        person.output();
    }
}

class Another {
    public Person person;

    public void setPerson(Person person) {
        this.person = person;
    }

    public void newPerson() {
        person = new Person("小红", 13);
    }

}

class TestMain {
    public static void main(String[] args) {
        TestAnything any = new TestAnything();
        Another another = new Another();
        any.transform(another);
        another.newPerson();
        any.showPerson();
    }
}
