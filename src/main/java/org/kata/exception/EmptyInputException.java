package org.kata.exception;

public class EmptyInputException extends Exception {
    public EmptyInputException() {
        super("The input must not be empty.");
    }
}
