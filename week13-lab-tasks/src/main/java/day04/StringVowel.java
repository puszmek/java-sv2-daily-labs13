package day04;

import java.util.Map;
import java.util.TreeMap;

public class StringVowel {

    private static final String VOWELS = "aeiou";

    public Map<Character, Integer> vowelCounter(String word) {
        Map<Character, Integer> result = new TreeMap<>();
        for (Character c : word.toCharArray()) {
            if (isVowel(c) && !result.containsKey(c)) {
                result.put(c, 1);
            } else if (isVowel(c)){
                result.put(c, result.get(c) + 1);
            }
        }
        return result;
    }

    private boolean isVowel(char c) {
        if (VOWELS.indexOf(c) >= 0 || VOWELS.toUpperCase().indexOf(c) >= 0) {
            return true;
        }
        return false;
    }
}
