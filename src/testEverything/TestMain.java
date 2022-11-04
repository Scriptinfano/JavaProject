/**
 * 这是一个临时的程序，用来测试各种代码
 */
package testEverything;

import java.util.ArrayList;

public class TestMain {
    public static void main(String[] args) {
        int size = 4;
        ArrayList<ArrayList<Integer>> list;
        list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(new ArrayList<>(size));
            for (int j = 0; j < size; j++) {
                list.get(i).add(null);
            }

        }
        System.out.println(list.get(0).size());

    }


}