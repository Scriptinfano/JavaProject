/**
 * 这是一个临时的程序，用来测试各种代码
 */
package javaTest;

public class TestMain {

    public static void main(String[] args) throws CloneNotSupportedException {
        int sum = 0;
        int[] arr = {1, 3, 4, 7, 8, 2, 6, 9, 5};
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[i])
                    sum++;
            }
        }
        System.out.println(sum);
    }
}
