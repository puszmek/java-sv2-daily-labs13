package day02;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.TreeMap;

public class Quiz {

    private String correctAnswers;
    private final Map<String, String> contestantAnswers = new TreeMap<>();

    public Quiz(Path path) {
        readContestantAnswersFromFile(path);
    }

    private void readContestantAnswersFromFile(Path path) {
        try (BufferedReader bf = Files.newBufferedReader(path)) {
            correctAnswers = bf.readLine();
            while (bf.ready()) {
                parseLine(bf.readLine());
            }
        } catch (IOException e) {
            throw new IllegalStateException("Cannot read file", e);
        }
    }

    private void parseLine(String line) {
        String[] fields = line.split(" ");
        String contestantCode = fields[0];
        if (contestantAnswers.containsKey(contestantCode)) {
            contestantAnswers.replace(contestantCode, contestantAnswers.get(contestantCode).concat(fields[1]));
        } else {
            contestantAnswers.put(contestantCode, fields[1]);
        }
    }

    public Map<String, String> getContestantAnswers() {
        return contestantAnswers;
    }

    public boolean isAnswerCorrect(String contestantCode, int answerNumber) {
        if (!contestantAnswers.containsKey(contestantCode))
            throw new IllegalArgumentException("Invalid contestant code");
        if (answerNumber < 1 || answerNumber > correctAnswers.length())
            throw new IllegalArgumentException("Invalid answer number");
        if (answerNumber > contestantAnswers.get(contestantCode).length())
            return false;
        return contestantAnswers.get(contestantCode).charAt(answerNumber - 1) == correctAnswers.charAt(answerNumber - 1);
    }

    public String getWinner() {
        String winner = null;
        int maxPoints = 0;
        for (String contestantCode : contestantAnswers.keySet()) {
            int points = getTotalPoints(contestantCode);
            if (points > maxPoints) {
                maxPoints = points;
                winner = contestantCode;
            }
        }
        if (winner == null) {
            throw new IllegalStateException("No winner");
        }
        return winner;
    }

    private int getTotalPoints(String contestantCode) {
        int points = 0;
        for (int i = 0; i < contestantAnswers.get(contestantCode).length(); i++) {
            if (contestantAnswers.get(contestantCode).charAt(i) == correctAnswers.charAt(i)) {
                points += i + 1;
            } else if (contestantAnswers.get(contestantCode).charAt(i) != 'X') {
                points -= 2;
            }
        }
        return points;
    }
}