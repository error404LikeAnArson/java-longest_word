package com.univamu.longestword;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Draw {
    private Character[] drawArray;
    public static final int MAX_DRAW_SIZE = 10;
    private int drawArrayLength = 0;

    public Draw(String path) {
        int lineNumber = (int)(Math.random() * 90099) + 1;
        //System.out.println(String.format("lineNumber: %d", lineNumber));
        try {
            BufferedReader buff = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
            String line;
            int i = 0;
            while ((line = buff.readLine()) != null) {
                i++;
                if (i == lineNumber) {
                    System.out.println("draw: " + line);
                    setDrawString(line);
                }
            }
            buff.close();
        } catch (Exception e) { System.out.println(e.toString()); }
    }

    public Draw(int vowelsNb, int consonantsNb, LettersStatistics lettersStatistics) {
        setDrawString(genRandomLettersDraw(vowelsNb, consonantsNb, lettersStatistics));
    }

    private static String genRandomDrawWithLetters(String result, int nb, int percentsSum, Map<Character, Integer> freqs) {
        StringBuilder resultBuilder = new StringBuilder(result);
        for (int i = 0; i < nb; i++) {
            int index = (int) (Math.random() * percentsSum);
            for (Map.Entry<Character, Integer> entry: freqs.entrySet()) {
                index -= entry.getValue();
                if (index < 0) {
                    resultBuilder.append(entry.getKey());
                    break;
                }
            }
        }
        return resultBuilder.toString();
    }

    private static String genRandomLettersDraw(int vowelsNb, int consonantsNb, LettersStatistics lettersStatistics){
        String drawString = "";
        drawString = genRandomDrawWithLetters(drawString, vowelsNb, lettersStatistics.vowelsFreqsSum, lettersStatistics.vowelsFreqs);
        drawString = genRandomDrawWithLetters(drawString, consonantsNb, lettersStatistics.consonantsFreqsSum, lettersStatistics.consonantsFreqs);
        return drawString;
    }

    private void setDrawString(String drawString) {
        drawArrayLength = Math.min(drawString.length(), MAX_DRAW_SIZE);
        drawArray = new Character[drawArrayLength];
        for (int i = 0; i < drawArrayLength; i++)
            drawArray[i] = drawString.charAt(i);
    }

    public String getDrawString() {
        Stream<Character> charStream = Arrays.stream(drawArray);
        return charStream.map(String::valueOf).collect(Collectors.joining());
    }

    private boolean isValidCodePoint(int codePoint, ArrayList<Character> remainingCharacters) {
        for (Character drawChar : remainingCharacters) {
            if (codePoint == Character.codePointAt(new char[]{drawChar}, 0))
                return true;
        }
        return false;
    }

    public boolean isValidWord(String word) {
        ArrayList<Character> remainingCharacters = new ArrayList<>(drawArrayLength);
        remainingCharacters.addAll(Arrays.asList(drawArray));
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

    protected boolean isInDictionary (String answer){
        Dictionary dictionary = new Dictionary("resources/dict.txt");
        for (String word : dictionary){
            if (answer == word)
                return true;
        }
        return false;
    }

    protected ArrayList<String> findLongestValidWordsFromDictionary(Dictionary dictionary) {
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