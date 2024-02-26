package com.cnab.processor.util;

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

    public static Double parseStringToValueDecinal(String str){
        try {

            int value = Integer.parseInt(str);
            int decimalPart = value % 100;
            int integerPart = value / 100;
            return Double.parseDouble(integerPart + "." + decimalPart);

        }catch (NumberFormatException e){
            return 0.0;
        }
    }


}
