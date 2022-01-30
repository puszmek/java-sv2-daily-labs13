package day04;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class StringVowelTest {

    @Test
    void testVowelCounter() {
        StringVowel stringVowel = new StringVowel();
        Map<Character, Integer> result = stringVowel.vowelCounter("exception");

        assertEquals(2, result.get('e'));
        assertEquals(1, result.get('i'));
        assertNull(result.get('a'));
    }

    @Test
    public void mapOrderingByValue() {
        StringVowel stringVowel = new StringVowel();
        Map<Character, Integer> result = stringVowel.vowelCounter("exception");
        List<Map.Entry<Character, Integer>> entryList = new LinkedList<>(result.entrySet());
        Collections.sort(entryList, Comparator.comparing(Map.Entry::getValue));

        for (Map.Entry<Character, Integer> actual : entryList) {
            System.out.println(actual.getKey() + " " + actual.getValue());
        }
    }
}