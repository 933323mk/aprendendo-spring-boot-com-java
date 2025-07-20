package github.com._mk.request.converters;

import github.com._mk.exception.UnsupportedMathOperationException;

public class NumberConverter {

    //verificação de valores numericos
    public static boolean isNumeric(String strNumber) {
        if(strNumber == null || strNumber.isEmpty()) return false;
        String number = strNumber.replace(",", "."); // R$ 5,00 -> USD 5.00
        return number.matches("[-+]?[0-9]*\\.?[0-9]+");
    }

    //conversao dos valores para double
    public static double convertToDouble(String strNumber) {
        if(strNumber == null || strNumber.isEmpty())
            throw new UnsupportedMathOperationException("SET A NUMERIC VALUE!");
        String number = strNumber.replace(",", "."); // R$ 5,00 -> USD 5.00
        return Double.parseDouble(number);
    }
}
