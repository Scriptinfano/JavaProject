package myformat;

import java.text.DecimalFormat;

public class Formatter {
    public DecimalFormat formatter;
    private String format = "#.";

    public Formatter() {
    }

    public void setPrecision(int thePrecision) {
        for (int i = 0; i < thePrecision; i++) {
            format += "0";
        }
        formatter = new DecimalFormat(format);
    }

    public DecimalFormat getFormatter() {
        return formatter;
    }
}
