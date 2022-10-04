package myformat;

import java.text.DecimalFormat;

public class Formatter {
    public DecimalFormat formatter;
    private String format = "#.";
    private int precision = 0;

    public Formatter() {
    }

    public void setPrecision(int thePrecision) {
        precision = thePrecision;
        for (int i = 0; i < precision; i++) {
            format += "0";
        }
        formatter = new DecimalFormat(format);
    }

    public DecimalFormat getFormatter() {
        return formatter;
    }
}
