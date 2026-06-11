package util;

import java.text.NumberFormat;
import java.util.Locale;

public class FormatadorMoeda {

    public static String formatar(double valor) {
        NumberFormat formato = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        return formato.format(valor);
    }
}