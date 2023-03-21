import DataStructure.linklist.LinkList;

public class TestAnyThing {
    public static void main(String[] args) {
        Integer[] arr = {1, 2, 3, 4, 52, 32, 4, 12, 4, 5432, 4, 123, 4512, 4};
        LinkList<Integer> list = new LinkList<>(arr);
        list.deleteNodeByElem(4);
        list.output();
    }
}