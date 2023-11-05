package shang_ji_ti.teacherandstudent;

import myScannerAndPrinter.MyScanner;

import java.util.Calendar;

public class MyComment {
    private String comment;
    private Calendar cal = Calendar.getInstance();

    private MyComment(String str) {
        comment = str;
    }

    public static MyComment makeMyComment() {
        MyScanner scanner = new MyScanner();
        String str = scanner.nextLineNoEmpty();
        return new MyComment(str);
    }

}
