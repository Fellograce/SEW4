package util;


import javafx.util.StringConverter;

import java.math.BigDecimal;
import java.text.NumberFormat;

/**
 * String-Konverter für Big-Decimals, der auch mit Null-Werten umgehen kann.
 */
public class NullableBigDecimalStringConverter extends StringConverter<BigDecimal> {
  private NumberFormat df;
  
  /**
   * Konstruktor
   * @param df Zahlenformat für Konvertierung
   */
  public NullableBigDecimalStringConverter(NumberFormat df) {
    this.df = df;
  }
  
  /**
   * Wandelt den Zahlenwert in einen String mit Hilfe des im Konstruktor übergebenen Zahlenformats um.
   * @param bigDecimal Zahlenwert
   * @return Zahlenwert als formatierter String
   */
  @Override
  public String toString(BigDecimal bigDecimal) {
    if (bigDecimal == null) {
      return "";
    }
    else {
      return df.format(bigDecimal);
    }
  }
  
  /**
   * Wandelt einen String in einem gültigen Zahlenformat (Konstruktor) in einen Zahlewert um.
   * @param string Zahlenwert als formatierter String
   * @return Zahlenwert
   */
  @Override
  public BigDecimal fromString(String string) {
    BigDecimal bigDecimal;
    try {
      if (string == null || string.length() == 0) {
        bigDecimal = null;
      }
      else {
        bigDecimal = new BigDecimal(df.parse(string).doubleValue());
        //bigDecimal = new BigDecimal(string);
        //bigDecimal = df.parse(string).doubleValue();
      }
    }
    catch (Exception ex) {
      bigDecimal = null;
    }
    return bigDecimal;
  }
}
