/**
 * 这是一个临时的程序，用来测试各种代码
 */
package testEverything;

import java.util.Calendar;

public class TestMain {

    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2003, 4, 29);
        System.out.printf("%tF", calendar.getTime());
    }
}
