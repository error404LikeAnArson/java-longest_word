package com.univamu.longestword;

import java.util.ArrayList;
import java.util.Arrays;

public class Controller {
    private Draw draw;

    public Controller(Draw draw) {
        this.draw = draw;
    }

    public Draw getDraw() {
        return draw;
    }

    private boolean isValidCodePoint(int codePoint, ArrayList<Character> remainingCharacters) {
        for (Character drawChar : remainingCharacters) {
            if (codePoint == Character.codePointAt(new char[]{drawChar}, 0))
                return true;
        }
        return false;
    }

    public boolean isValidWord(String word) {
        ArrayList<Character> remainingCharacters = new ArrayList<>(draw.getDrawArrayLength());
        remainingCharacters.addAll(Arrays.asList(draw.getDrawArray()));
        String strippedWord = Utils.stripAccentsHardcoded(word).toLowerCase();
        //System.out.println("stripped to: " + strippedWord);
        int offset = 0;
        final int strippedWordLength = strippedWord.length();
        while (offset < strippedWordLength) {
            int codePoint = strippedWord.codePointAt(offset);
            if (Character.isAlphabetic(Character.toChars(codePoint)[0])) {
                if (!isValidCodePoint(codePoint, remainingCharacters))
                    return false;
            }
            remainingCharacters.remove((Character) Character.toChars(codePoint)[0]);

            offset += Character.charCount(codePoint);
        }

        return true;
    }

    public ArrayList<String> findLongestValidWordsFromDictionary(Dictionary dictionary) {
        int longestWordsLength = 0;
        ArrayList<String> longestValidWords = new ArrayList<>();

        for (String word : dictionary) {
            int wordActualLength = Utils.getActualLength(word);
            if (wordActualLength >= longestWordsLength) {
                if (isValidWord(word)) {
                    if (wordActualLength > longestWordsLength) {
                        longestWordsLength = wordActualLength;
                        longestValidWords.clear();
                    }
                    longestValidWords.add(word);
                }
            }
        }

        return longestValidWords;
    }
}
