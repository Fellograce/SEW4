package model;

import javafx.util.StringConverter;

import java.math.BigDecimal;
import java.text.NumberFormat;

public class NullableBigDecimalStringConverter extends StringConverter<BigDecimal> {
    private NumberFormat df;

    public NullableBigDecimalStringConverter(NumberFormat df) {
        this.df = df;
    }

    public String toString(BigDecimal bigDecimal) {
        if (bigDecimal == null) {
            return "";
        } else {
            return df.format(bigDecimal);
        }
    }

    public BigDecimal fromString(String string) {
        BigDecimal bigDecimal;
        try {
            if (string == null || string.length() == 0) {
                bigDecimal = null;
            } else {
                bigDecimal = new BigDecimal(string);
//bigDecimal = df.parse(string).doubleValue();
            }
        } catch (Exception ex) {
            bigDecimal = null;
        }
        return bigDecimal;
    }
}
