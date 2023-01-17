package DataStructure.hashTable;

/**
 * 哈希表接口
 */
public interface HashTableInterFace {
   class FullHashTableException extends Exception {
        FullHashTableException(int num) {
            super("在插入" + num + "时，哈希表已经满了，无法再插入了");
        }


    }
    void add(Integer num)throws HashTable.FullHashTableException;
    boolean hashSearch(int num);
    int hashFunction(int element);
    double successASL();

    double failureASL();


}
