package com.cnab.processor.util;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Classe utilitária com métodos para processamento de strings.
 * @Author: Leonardo
 */

public class ProcessorUtils {

    /**
     * Verifica se a string é numérica.
     *
     * @param str A string a ser verificada.
     * @return true se a string contiver apenas dígitos numéricos; caso contrário, false.
     */
    public static boolean isNumeric(String str) {
        return str.matches("\\d+");
    }

    /**
     * Verifica se a string é alfanumérica.
     *
     * @param str A string a ser verificada.
     * @return true se a string contiver apenas caracteres alfanuméricos; caso contrário, false.
     */
    public static boolean isAlphanumeric(String str) {
        return str.matches("[a-zA-Z0-9]+");
    }

    /**
     * Verifica se a string é alfanumérica e contém espaços.
     *
     * @param str A string a ser verificada.
     * @return true se a string contiver apenas caracteres alfanuméricos e espaços; caso contrário, false.
     */
    public static boolean isAlphanumericWithSpaces(String str) {
        return str.matches("[a-zA-Z0-9\\s]+");
    }

    /**
     * Verifica se a string é alfanumérica e contém caracteres especiais e espaços.
     *
     * @param str A string a ser verificada.
     * @return true se a string contiver apenas caracteres alfanuméricos, '@', '$', '-' e espaços; caso contrário, false.
     */
    public static boolean isAlphanumericWithSpecialCharAndSpaces (String str){ return str.matches("^[a-zA-Z0-9@$\\-\\s]+$");}

    /**
     * Verifica se a string é composta apenas por espaços em branco.
     *
     * @param str A string a ser verificada.
     * @return true se a string contiver apenas espaços em branco; caso contrário, false.
     */
    public static boolean isWhitespace(String str) {
        return str.trim().isEmpty();
    }

    /**
     * Verifica se a string tem o comprimento correto.
     *
     * @param str A string a ser verificada.
     * @return true se a string tiver um comprimento de 80 caracteres; caso contrário, false.
     */
    public static boolean isCorrectlength(String str) {
        return str.length() == 80;
    }

    /**
     * Converte uma string em formato numérico para um BigDecimal.
     *
     * @param str A string numérica a ser convertida.
     * @return O BigDecimal representando o valor da string.
     */
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

    /**
     * Remove os espaços em branco adicionais após a última letra da string.
     *
     * @param str A string da qual os espaços em branco devem ser removidos.
     * @return A string resultante após a remoção dos espaços em branco adicionais.
     */
    public static String removeTrailingSpaces(String str) {
        return str.replaceAll("\\s+$", "");
    }

    /**
     * Verifica se uma string contém apenas o caractere '0'.
     *
     * @param str A string a ser verificada.
     * @return true se a string contém apenas o caractere '0', caso contrário, false.
     */
    public static boolean containsOnlyZero(String str) {  return str.matches( "^0*$");  }

}
