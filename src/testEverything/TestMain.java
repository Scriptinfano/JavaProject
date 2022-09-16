/**
 * 这是一个临时的程序，用来测试各种代码
 */
package testEverything;

import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;

public class TestMain {

    public static void main(String[] args) {


        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);
        queue.offer(12);
        queue.offer(13);
        queue.offer(14);
        queue.offer(15);
        queue.offer(12);
        queue.offer(13);
        queue.offer(14);
        queue.offer(15);
        System.out.println(queue);
        boolean you = YouXv(queue);
        System.out.println(you);

    }

    public static boolean YouXv(ArrayBlockingQueue<Integer> queue) {
        while (!queue.isEmpty()) {
            Integer m1 = queue.poll();
            Integer m2 = queue.peek();
            if (m1.compareTo(Objects.requireNonNull(m2)) > 0) return false;
        }
        return true;
    }
}
