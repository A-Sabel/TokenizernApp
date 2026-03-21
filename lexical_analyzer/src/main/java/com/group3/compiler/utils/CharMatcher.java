package com.group3.compiler.utils;

/* UTILITY CLASS: Character Predicates
Description: Contains boolean logic to identify character types (isAlpha, isDigit, isSymbol).
Member Note: "Since we aren't using Regex API, all 'scratch' logic for character 
    validation goes here." */

public class CharMatcher {
    public static boolean isAlpha(char c) {
        return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') || c == '_';
    }

    public static boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    public static boolean isAlphaNumeric(char c) {
        return isAlpha(c) || isDigit(c);
    }

    public static boolean isDecimalDigit(char c) {
        return c >= '0' && c <= '9';
    }

    public static boolean isOperator(char c) {
        return "+-*/%=&|!<>:".indexOf(c) != -1;
    }

    public static boolean isSpecialSymbol(char c) {
        return "(){}[]@".indexOf(c) != -1;
    }

    public static boolean isPunctuation(char c) {
        return (c == ';' || c == ',' || c == '.');
    }

    public static boolean isWhitespace(char c) {
        return c == ' ' || c == '\t' || c == '\n' || c == '\r';
    }

    public static boolean isQuote(char c) {
        return c == '"' || c == '\'';
    }

    public static boolean isEscapeChar(char c) {
        return c == '\\';
    }

    public static boolean isValidCharacter(char c) {
        return isAlpha(c) || isDigit(c) || isAlphaNumeric(c) || isOperator(c) || isSpecialSymbol(c) || 
        isPunctuation(c) || isWhitespace(c) || isQuote(c) || isEscapeChar(c);
    }
}
