/**
 * 这是一个临时的程序，用来测试各种代码
 */
package testEverything;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;


class TestMain{
    public static void main(String[] args) {
        GregorianCalendar arriveTime=new GregorianCalendar();
        StringBuilder builder=new StringBuilder();
        builder.append(arriveTime.get(Calendar.YEAR));
        builder.append("年");
        builder.append(arriveTime.get(Calendar.MONTH));
        builder.append("月");
        builder.append(arriveTime.get(Calendar.DATE));
        builder.append("日");
        builder.append(arriveTime.get(Calendar.HOUR_OF_DAY));
        builder.append(":");
        builder.append(arriveTime.get(Calendar.MINUTE));
        System.out.println("到达时间："+ builder);

    }
}