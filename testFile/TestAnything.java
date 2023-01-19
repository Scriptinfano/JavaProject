import java.util.ArrayList;

public class TestAnything {


    public static void main(String[] args) {
        ArrayList<Integer> cache = new ArrayList<>();
        cache.add(12);
        cache.add(2);
        cache.add(101);
        cache.add(1000);
        Integer value = cache.get(1);
        cache.remove(value);
        System.out.println(cache);
        /*ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.add(2000);


        Optional<Integer> minValue= cache.stream().filter(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                if(integer.compareTo(deque.getLast())>0)
                    return true;
                else return false;
            }
        }).min(Integer::compareTo);
System.out.println(minValue.isPresent());
        //minValue.ifPresent(System.out::println);*/
    }


}

