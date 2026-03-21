package com.group3.compiler.backend;

/* MAIN CLASS: Lexical Analyzer
Description: The engine that scans source code character-by-character to generate tokens.
Member Note: "This holds the 'pointer' (index) for the string. It calls the 
    TokenFactory and ErrorHandler based on what CharMatcher returns." */

/* HELPER METHOD (private): Lookahead (Peek)
Description: Allows the Lexer to see the next character in the stream without advancing the pointer.
Member Note: "Essential for multi-character operators like '==' or '!='. Always verify 
    if (index + 1) < input.length() before peeking to avoid crashes." */

/* HELPER METHOD (private): Comment & Whitespace Stripper
Description: A pre-processor that skips over non-token elements (spaces, tabs, newlines, and comments).
Member Note: "Call this at the start of every 'getNextToken()' cycle. It cleans the 
    path so the scanner only lands on valid, categorizable characters." */

public class Lexer {
    
}
