package day02;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class QuizTest {

    Quiz quiz = new Quiz(Path.of("src/test/resources/result.txt"));

    @Test
    void testCreate() {
        assertEquals(4, quiz.getContestantAnswers().size());
        assertEquals("ACCBX", quiz.getContestantAnswers().get("AB123"));
    }

    @Test
    void testCreateInvalidPath() {
        Exception e = assertThrows(IllegalStateException.class, () -> new Quiz(Path.of("src/test/resources/result_.txt")));
        assertEquals("Cannot read file", e.getMessage());
    }

    @Test
    void isAnswerCorrect() {
        assertFalse(quiz.isAnswerCorrect("AH2", 1));
        assertTrue(quiz.isAnswerCorrect("AH2", 2));
    }

    @Test
    void isAnswerCorrectInvalidContestantCode() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> quiz.isAnswerCorrect("AH2_", 1));
        assertEquals("Invalid contestant code", e.getMessage());
    }

    @Test
    void isAnswerCorrectAnswerNumberTooLow() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> quiz.isAnswerCorrect("AH2", 0));
        assertEquals("Invalid answer number", e.getMessage());
    }

    @Test
    void isAnswerCorrectAnswerNumberTooHigh() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> quiz.isAnswerCorrect("AH2", 6));
        assertEquals("Invalid answer number", e.getMessage());
    }

    @Test
    void getWinner() {
        assertEquals("GH1234", quiz.getWinner());
    }
}