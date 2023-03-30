import DataStructure.linklist.LinkList;
import arrayutil.ArrayUtil;

import java.util.ArrayList;

public class TestAnyThing {
    public static void main(String[] args) {
        Integer[] arr = ArrayUtil.randomIntegerArray(19, 1, 100);
        LinkList<Integer> list = new LinkList<>(arr);
        list.output();
        ArrayList<LinkList<Integer>> lists = list.divide();
        lists.get(0).output();
        lists.get(1).output();
    }
}