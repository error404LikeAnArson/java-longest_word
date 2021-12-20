package com.univamu.longestword;

import java.util.HashMap;
import java.util.Map;

public class LettersStatistics {
    public int vowelsFreqsSum = 0;
    public int consonantsFreqsSum = 0;
    public final Map<Character, Integer> vowelsFreqs;
    public final Map<Character, Integer> consonantsFreqs;

    public LettersStatistics(Map<Character, Integer> vowelsPercents, Map<Character, Integer> consonantsPercents) {
        this.vowelsFreqs = vowelsPercents;
        this.consonantsFreqs = consonantsPercents;
        updateTotalFreqs();
    }

    public LettersStatistics(char[] vowels, char[] consonants) {
        vowelsFreqs = new HashMap<>();
        for (char c: vowels)
            vowelsFreqs.put(c, 0);
        consonantsFreqs = new HashMap<>();
        for (char c: consonants)
            consonantsFreqs.put(c, 0);
    }

    public void updateTotalFreqs() {
        for (Integer percent: vowelsFreqs.values())
            vowelsFreqsSum += percent;
        for (Integer percent: vowelsFreqs.values())
            consonantsFreqsSum += percent;
    }

    public void addVowel(char c) {
        vowelsFreqs.put(c, vowelsFreqs.get(c) + 1);
    }

    public void addConsonant(char c) {
        consonantsFreqs.put(c, consonantsFreqs.get(c) + 1);
    }

    public static LettersStatistics getLettersStatisticsFromDictionary(Dictionary dictionary, Language language) {
        LettersStatistics lettersStatistics = new LettersStatistics(language.getVowels(), language.getConsonants());
        for (String word : dictionary) {
            String strippedWord = Utils.stripAccentsHardcoded(word).toLowerCase();
            //System.out.println("stripped to: " + strippedWord);
            int offset = 0;
            final int strippedWordLength = strippedWord.length();
            while (offset < strippedWordLength) {
                int codePoint = strippedWord.codePointAt(offset);
                if (Character.isAlphabetic(Character.toChars(codePoint)[0])) {
                    for (char vowel: language.getVowels()) {
                        if (vowel == Character.toChars(codePoint)[0])
                            lettersStatistics.addVowel(vowel);
                    }
                    for (char consonant: language.getConsonants()) {
                        if (consonant == Character.toChars(codePoint)[0])
                            lettersStatistics.addConsonant(consonant);
                    }
                }

                offset += Character.charCount(codePoint);
            }
        }
        lettersStatistics.updateTotalFreqs();
        return lettersStatistics;
    }
}
