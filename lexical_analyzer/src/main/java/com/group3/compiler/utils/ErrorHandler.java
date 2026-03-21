package com.group3.compiler.utils;

/* UTILITY CLASS: Error Collector
Description: Captures and stores 'Unknown' or malformed characters found during scanning.
Member Note: "Collect errors in an ArrayList so the GUI can display a full report 
    at the end of the tokenization process." */

public class ErrorHandler {
    public static void report(String message, int line, int col) {
        // For now, simply print to the standard error stream.
        // Later, your Frontend Team can redirect this to the GUI.
        System.err.println("[Lexical Error] Line " + line + ", Col " + col + ": " + message);
    }
}
