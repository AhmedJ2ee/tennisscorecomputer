package org.kata;


import org.kata.exception.EmptyInputException;
import org.kata.exception.FormatInputException;

public class Main {
    public static void main(String[] args) throws EmptyInputException, FormatInputException {
        TennisScoreComputer tennisScoreComputer = new TennisScoreComputer();
        tennisScoreComputer.tennisScore("ABAABBBAAA");
    }
}