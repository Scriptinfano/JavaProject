/**
 * 这是一个临时的程序，用来测试各种代码
 */
package testEverything;

import java.util.*;

public class TestMain {
    public static void main(String[] args) {
        Map<Integer, Integer> sorter = new HashMap<Integer, Integer>();
        sorter.put(1, 12);
        sorter.put(2, 41);
        sorter.put(3, 23);
        sorter.put(4, 14);
        sorter.put(5, 5);
        sorter.put(6, 15);
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(sorter.entrySet());
        list.sort(new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return Integer.compare(o2.getValue() - o1.getValue(), 0);
            }
        });

        for (Map.Entry<Integer, Integer> entry : list)
            System.out.println(entry.getKey() + "=" + entry.getValue());


    }


}