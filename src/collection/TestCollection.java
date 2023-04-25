package collection;

import testResources.Person;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

public class TestCollection {
    public static void main(String[] args) {
        ArrayList<Person> personList = new ArrayList<>();
        personList.add(new Person("筛子", 12));
        personList.add(new Person("而锤子", 13));
        Iterator<Person> iter = personList.iterator();
        while (iter.hasNext()) {
            Person p = iter.next();
            System.out.println(p.getName() + " " + p.getAge());
        }
        //iter = personList.iterator();
        ListIterator<Person> listIter = personList.listIterator();
        while (listIter.hasNext()) {
            System.out.println(listIter.next().toString());
        }
        while (listIter.hasPrevious()) {
            System.out.println(listIter.previous().toString());
        }
    }
}
