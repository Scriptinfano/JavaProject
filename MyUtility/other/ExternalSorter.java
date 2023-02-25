package other;

import arrayutil.ArrayUtil;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 外部排序中为降低初始归并段个数增加每个初始归并段长度而设计的置换-选择排序
 */
public class ExternalSorter {
    private final ArrayDeque<ArrayDeque<Integer>> parkingLot = new ArrayDeque<>();

    private final ArrayDeque<Integer> deque = new ArrayDeque<>();

    private final ArrayList<Integer> cache = new ArrayList<>();

    public ExternalSorter(ArrayList<Integer> theList) {
        deque.addAll(theList);
    }

    public void run() {
        //从序列中读取数据填满缓冲区
        //若没有任何车，则新建一辆车，从缓冲区中选择最小的且大于当前车辆最大元素的元素添加到当前车（每辆车最大的元素就是最后一个上车的元素）
        //再从序列中填满缓冲区
        //从缓冲区中选择一个大于该车中最大元素的最小值，若有则将该元素加入该车，否则换一辆新车然后将缓冲区中最小的元素加入该车
        //整个过程一直循环到序列中所有元素都被装进车
        do {
            int currentSize = 3 - cache.size();//算出目前缓冲区缺几个元素
            //用for循环填满缓冲区
            for (int j = 0; j < currentSize && !deque.isEmpty(); j++)
                cache.add(deque.removeFirst());//FIXME 查找队列deque为空的原因
            if (parkingLot.isEmpty()) parkingLot.add(new ArrayDeque<>());//在最开始，停车场中没有任何车，所以在一开始要新建一辆车
            if (parkingLot.getLast().isEmpty()) {
                //如果当前车辆中没有任何元素，说明这是一辆新车，要从缓冲区选一个最小的元素上车
                cache.stream().min(Integer::compareTo).ifPresent(parkingLot.getLast()::add);
            } else {
                //如果当前车辆中已经有元素了，此时要从缓冲区中选一个大于该车中最后一个元素的最小的元素，如果选不出该元素则要新建一辆车然后将缓冲区中最小的元素放入新车，如果选出了该元素，则将该元素放入该车中
                Optional<Integer> minValue = cache.stream().filter(integer -> integer.compareTo(parkingLot.getLast().getLast()) > 0).min(Integer::compareTo);
                if (minValue.isPresent()) {
                    //如果大于车中最后一个元素的最小元素存在，则将该元素加入到该车中，并从缓冲区中移除该元素
                    parkingLot.getLast().addLast(minValue.get());
                    cache.remove(minValue.get());
                } else {
                    //如果大于车中最后一个元素的最小元素不存在，则创建新车，将缓冲区中的最小元素放入新车并从缓冲区移除
                    parkingLot.addLast(new ArrayDeque<>());
                    cache.stream().min(Integer::compareTo).ifPresent(integer -> {
                        parkingLot.getLast().addLast(integer);
                        cache.remove(integer);
                    });
                }
            }
        } while (!cache.isEmpty());
    }

    public void outputCars() {
        for (ArrayDeque<Integer> arrayDeque : parkingLot) {
            System.out.println(arrayDeque);
        }
    }
}

class TestExternalSort {
    public static void main(String[] args) {
        Integer[] intArr = ArrayUtil.randomIntegerArray(100, 1, 1000);
        ArrayList<Integer> list = Arrays.stream(intArr).collect(Collectors.toCollection(ArrayList::new));
        ExternalSorter sorter = new ExternalSorter(list);
        sorter.run();
        sorter.outputCars();
    }
}