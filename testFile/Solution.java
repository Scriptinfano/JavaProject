import java.util.Stack;

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        while (list1 != null && list2 != null) {
            if (list2.val >= list1.val && list2.val < list1.next.val)
                insert(list1, list2);

        }
    }

    /**
     * 将node2插入到node1节点之后
     *
     * @param node1 node1
     * @param node2 node2
     */
    private void insert(ListNode node1, ListNode node2) {
        node1.next = new ListNode(node1.val, node1.next);
    }

}

class Tester2 {
    public static void main(String[] args) {
    }
}