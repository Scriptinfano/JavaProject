package DataStructure.hashTable;

import Math.mathmetic.MathUtil;
import myScannerAndPrinter.NoMoreScanException;
import myScannerAndPrinter.ScannerPlus;

import java.util.ArrayList;

/**
 * 存储数据元素为Integer的线性定长哈希表，支持查找，插入。采用线性探测法处理冲突，哈希函数采用除留余数法（余数设定为不大于表长的最大质数）
 */
public class HashTable implements HashTableInterFace{
    private final Integer[] hashArray;

    /**
     * 除留余数法中的余数，在构造器中计算一次节省性能
     */
    private final int remainder;

    /**
     * 插入哈希表的元素在比较了几次才插入哈希表，这个类对象表示一种二元关系，表示num在插入哈希表时比较了count次才插入哈希表。
     */
    private static class ConflictCounter {
        /**
         * 代表插入哈希表的数字
         */
        private final Integer num;
        /**
         * 在将num插入哈希表后共比较了几次才插入哈希表
         */
        private Integer count;

        /**
         * 构造器
         *
         * @param num 插入哈希表的数字
         */
        public ConflictCounter(int num) {
            count = 0;
            this.num = num;
        }

        public void setCount(Integer count) {
            this.count = count;
        }

        public void addCount() {
            count++;
        }
    }

    private final ArrayList<ConflictCounter> counters = new ArrayList<>();

    public HashTable(int size) {
        if(size<2)throw new IllegalArgumentException("采用除留余数法的哈希表长不能小于2");
        hashArray = new Integer[size];
        remainder = MathUtil.maxPrime(size);
    }

    /**
     * 向哈希表中添加元素
     *
     * @param num 元素
     * @throws FullHashTableException 哈希表已满异常，无法继续添加
     */
    public void add(Integer num) throws FullHashTableException {
        int address = hashFunction(num);
        ConflictCounter newCounter = new ConflictCounter(num);
        if (hashArray[address] == null) {
            hashArray[address] = num;

            newCounter.setCount(1);
            counters.add(newCounter);
        } else {

            for (int i = 1; i <= hashArray.length - 1; i++) {
                int newAddress = (address + i) % hashArray.length;
                if (hashArray[newAddress] == null) {
                    //在线性探测时不再发生冲突的情况
                    hashArray[newAddress] = num;
                    newCounter.setCount(i + 1);
                    counters.add(newCounter);
                    return;
                }
            }
            throw new FullHashTableException(num);//哈希表已经满了，无法再插入了
        }
    }

    /**
     * 散列搜索
     *
     * @param num 指定的要搜索的数字
     * @return boolean 若搜索到指定元素，则返回为true，否则返回false
     */
    public boolean hashSearch(int num) {
        int address = hashFunction(num);

        if (hashArray[address] == null)
            return false;
        else if(hashArray[address]==num)
            return true;
        else {
            //需要继续向后线性探测查找待查元素
            for (int i = 1; i <= hashArray.length - 1; i++) {
                int newAddress = (address + i) % hashArray.length;
                if(hashArray[newAddress]==null)
                    return false;
                if (hashArray[newAddress] == num)
                    return true;
            }
            return false;
        }

    }


    /**
     * 此哈希表采用的哈希函数，采用除留余数法映射地址
     *
     * @return int 返回的地址
     */
    public int hashFunction(int element) {
        return (3*element) % remainder;
    }

    /**
     * 求查找成功的平均查找长度
     *
     * @return double 返回查找成功的平均查找长度
     */
    public double successASL() {
        int num = 0;
        for (ConflictCounter counter : counters) {
            num += counter.count;
        }
        return num / (double) counters.size();
    }

    /**
     * 求查找失败的平均查找长度
     *
     * @return double 返回查找失败的平均查找长度
     */
    public double failureASL() {
        int outerSum = 0;
        for (int i = 0; i <= remainder - 1; i++) {
            int j = i, sum = 0, k = 0;
            while (hashArray[j] != null && j + 1 != i) {
                sum++;
                k++;
                j = (i + k) % hashArray.length;
            }
            outerSum += (sum + 1);
        }
        return outerSum / (double) remainder;
    }

}

class HashTest {
    private static final ScannerPlus scanner = new ScannerPlus();

    public static void main(String[] args) {
        HashTable hashTable = new HashTable(10);
        try {
            hashTable.add(7);
            hashTable.add(8);
            hashTable.add(30);
            hashTable.add(11);
            hashTable.add(18);
            hashTable.add(9);
            hashTable.add(14);
        } catch (HashTable.FullHashTableException e) {
            System.out.println(e.getMessage());
        }
        while (true) {
            System.out.println("输入你想要查找的数字：");
            int num = scanner.nextInt();

            if (hashTable.hashSearch(num)) {
                System.out.println("找到了");
            } else System.out.println("未找到");
            try {
                scanner.noMoreScan();
            } catch (NoMoreScanException e) {
                break;
            }
        }

        System.out.printf("查找成功的平均查找长度=%.2f",hashTable.successASL());
        System.out.printf("查找失败的平均查找长度=%.2f",hashTable.failureASL());



    }
}
