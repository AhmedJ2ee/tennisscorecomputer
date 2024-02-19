package org.kata;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kata.exception.EmptyInputException;
import org.kata.exception.FormatInputException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TennisScoreComputerTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testBWins() throws EmptyInputException, FormatInputException {
        TennisScoreComputer tennisScoreComputer = new TennisScoreComputer();
        tennisScoreComputer.tennisScore("ABBBB");
        assertThat("Player A : 15 / Player B : 0\n" +
                "Player A : 15 / Player B : 15\n" +
                "Player A : 15 / Player B : 30\n" +
                "Player A : 15 / Player B : 40\n" +
                "Player B wins the game\n").isEqualToIgnoringWhitespace(outContent.toString());
    }

    @Test
    public void testAWins() throws EmptyInputException, FormatInputException {
        TennisScoreComputer tennisScoreComputer = new TennisScoreComputer();
        tennisScoreComputer.tennisScore("ABAABA");
        assertThat("Player A : 15 / Player B : 0\n" +
                "Player A : 15 / Player B : 15\n" +
                "Player A : 30 / Player B : 15\n" +
                "Player A : 40 / Player B : 15\n" +
                "Player A : 40 / Player B : 30\n" +
                "Player A wins the game\n").isEqualToIgnoringWhitespace(outContent.toString());
    }

    @Test
    public void testALongMatch() throws EmptyInputException, FormatInputException {
        TennisScoreComputer tennisScoreComputer = new TennisScoreComputer();
        tennisScoreComputer.tennisScore("ABABABABABABABBB");
        assertThat("Player A : 15 / Player B : 0\n" +
                "Player A : 15 / Player B : 15\n" +
                "Player A : 30 / Player B : 15\n" +
                "Player A : 30 / Player B : 30\n" +
                "Player A : 40 / Player B : 30\n" +
                "Deuce\n" +
                "Advantage Player A\n" +
                "Deuce\n" +
                "Advantage Player A\n" +
                "Deuce\n" +
                "Advantage Player A\n" +
                "Deuce\n" +
                "Advantage Player A\n" +
                "Deuce\n" +
                "Advantage Player B\n" +
                "Player B wins the game\n").isEqualToIgnoringWhitespace(outContent.toString());
    }

    @Test
    public void testInputEmpty() {
        TennisScoreComputer tennisScoreComputer = new TennisScoreComputer();

        assertThatThrownBy(() -> {
            tennisScoreComputer.tennisScore("");
        })
                .isInstanceOf(EmptyInputException.class)
                .hasMessage("The input must not be empty.");
        assertThatThrownBy(() -> {
            tennisScoreComputer.tennisScore(null);
        })
                .isInstanceOf(EmptyInputException.class)
                .hasMessage("The input must not be empty.");
    }

    @Test
    public void testBadInput() {
        TennisScoreComputer tennisScoreComputer = new TennisScoreComputer();

        assertThatThrownBy(() -> {
            tennisScoreComputer.tennisScore("ABBZ");
        })
                .isInstanceOf(FormatInputException.class)
                .hasMessage("The input must contain only the characters A and B.");
        assertThatThrownBy(() -> {
            tennisScoreComputer.tennisScore("AB AB");
        })
                .isInstanceOf(FormatInputException.class)
                .hasMessage("The input must contain only the characters A and B.");
    }
}
