package org.kata.exception;

public class FormatInputException extends Exception {
    public FormatInputException() {
        super("The input must contain only the characters A and B.");
    }
}
