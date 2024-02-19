package org.kata;

import org.apache.commons.lang3.StringUtils;
import org.kata.exception.EmptyInputException;
import org.kata.exception.FormatInputException;
import org.kata.utils.Constant;


public class de {

    public void tennisScore(String input) throws EmptyInputException, FormatInputException {
        if (StringUtils.isEmpty(input)) {
            throw new EmptyInputException();
        }
        int scoreA = 0;
        int scoreB = 0;
        for (char s : input.toCharArray()) {
            if (s == 'A') {
                scoreA++;
            } else if (s == 'B') {
                scoreB++;
            } else {
                throw new FormatInputException();
            }
            showScore(scoreA, scoreB);
        }
        ;
    }

    private void showScore(int scoreA, int scoreB) {
        if (scoreA >= 3 && scoreB >= 3) {
            showDeuceOrAdvantageScore(scoreA, scoreB);
        } else {
            showBeforDeuceScore(scoreA, scoreB);
        }
    }

    private static void showBeforDeuceScore(int scoreA, int scoreB) {
        if (scoreA == 4 || scoreB == 4) {
            System.out.printf("Player %s wins the game", scoreA > scoreB ? "A" : "B");
        } else {
            System.out.printf("Player A : %s / Player B : %s%n", Constant.SCORE_MAP.get(scoreA), Constant.SCORE_MAP.get(scoreB));
        }
    }

    private static void showDeuceOrAdvantageScore(int scoreA, int scoreB) {
        if (scoreA == scoreB) {
            System.out.println("Deuce");
        } else if (scoreA == scoreB + 1 || scoreB == scoreA + 1) {
            System.out.printf("Advantage Player %s", scoreA > scoreB ? "A" : "B");
        } else if (scoreA == scoreB + 2 || scoreB == scoreA + 2) {
            System.out.printf("Player %s wins the game", scoreA > scoreB ? "A" : "B");
        }
    }
}
