package com.cnab.processor.util;

import java.math.BigDecimal;
import java.math.BigInteger;

public class ProcessorUtils {

    public static boolean isNumeric(String str) {
        return str.matches("\\d+");
    }

    public static boolean isAlphanumeric(String str) {
        return str.matches("[a-zA-Z0-9]+");
    }

    public static boolean isAlphanumericWithSpaces(String str) {
        return str.matches("[a-zA-Z0-9\\s]+");
    }

    public static boolean isAlphanumericWithSpecialCharAndSpaces (String str){ return str.matches("^[a-zA-Z0-9@$\\-\\s]+$");}

    public static boolean isWhitespace(String str) {
        return str.trim().isEmpty();
    }

    public static boolean isCorrectlength(String str) {
        return str.length() == 80;
    }

    public static BigDecimal parseStringToValueDecinal(String str){
        try {

            BigInteger value = new BigInteger(str);
            BigInteger integerPart2 = value.divide(new BigInteger("100"));
            BigInteger decimalPart2 = value.remainder(new BigInteger("100"));

            String allPart = integerPart2.toString()
                    .concat(".")
                    .concat((decimalPart2.toString().equals("0")) ? "00": decimalPart2.toString());;

            return new BigDecimal(allPart);

        }catch (NumberFormatException e){
            return new BigDecimal("0.00");
        }
    }


}
