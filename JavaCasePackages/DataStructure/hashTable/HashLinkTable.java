package DataStructure.hashTable;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 采用链地址法处理冲突的哈希表
 */
public class HashLinkTable implements HashTableInterFace {

    /**
     * 采用链地址法处理冲突所需要的嵌套容器
     */
    private final ArrayList<LinkedList<Integer>> lists;
    /**
     * 该哈希链表最多允许插入多少数字
     */
    private final int NUMSIZE;

    /**
     * 实时统计哈希表中的元素个数
     */
    private int elementCounter = 0;

    /**
     * 哈希链表构造器
     *
     * @param tableSize 表大小，指明该哈希表的表长是多少
     * @param numSize   num大小，指明该哈希链表允许加入多少元素，必须大于0
     */
    public HashLinkTable(int tableSize, int numSize) {
        lists = new ArrayList<>(tableSize);
        for (int i = 0; i < tableSize; i++)
            lists.add(new LinkedList<>());
        NUMSIZE = numSize;
    }

    @Override
    public void add(Integer num) throws FullHashTableException {
        if (elementCounter + 1 > NUMSIZE)
            throw new FullHashTableException(num);

    }

    @Override
    public boolean hashSearch(int num) {
        return false;
    }

    @Override
    public int hashFunction(int element) {
        return 0;
    }

    @Override
    public double successASL() {
        return 0;
    }

    @Override
    public double failureASL() {
        return 0;
    }
}
