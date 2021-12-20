package com.univamu.longestword;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class UtilsTest {

  @Test
  public void isVowel() {
    assertTrue(Utils.isVowel('a'));
    assertTrue(Utils.isVowel('e'));
    assertTrue(Utils.isVowel('i'));
    assertTrue(Utils.isVowel('o'));
    assertTrue(Utils.isVowel('u'));
    assertTrue(Utils.isVowel('y'));
    assertFalse(Utils.isVowel('b'));
    assertFalse(Utils.isVowel('g'));
    assertFalse(Utils.isVowel('h'));
    assertFalse(Utils.isVowel('n'));
    assertFalse(Utils.isVowel('s'));
    assertFalse(Utils.isVowel('z'));
  }

  @Test
  public void isConsonant() {
    assertTrue(Utils.isConsonant('z'));
    assertTrue(Utils.isConsonant('r'));
    assertTrue(Utils.isConsonant('t'));
    assertTrue(Utils.isConsonant('p'));
    assertTrue(Utils.isConsonant('q'));
    assertTrue(Utils.isConsonant('s'));
    assertFalse(Utils.isConsonant('a'));
    assertFalse(Utils.isConsonant('e'));
    assertFalse(Utils.isConsonant('y'));
    assertFalse(Utils.isConsonant('u'));
    assertFalse(Utils.isConsonant('i'));
    assertFalse(Utils.isConsonant('o'));
    assertFalse(Utils.isConsonant('-'));
    assertFalse(Utils.isConsonant('!'));
    assertFalse(Utils.isConsonant('&'));
  }

  @Test
  public void stripAccentsHardcoded() {
    assertEquals("elephant", Utils.stripAccentsHardcoded("éléphant"));
    assertEquals("cueeo", Utils.stripAccentsHardcoded("çùéèô"));
  }

  @Test
  public void getActualLength() {
    assertEquals(3, Utils.getActualLength("éèê"));
    assertEquals(4, Utils.getActualLength("àçôù"));
    assertEquals(0, Utils.getActualLength(""));
  }

  @Test
  public void getLettersFreqsFromDictionary() {
    LettersStatistics frenchLettersStatistics = new FrenchLanguage().getLettersStatistics();
    LettersStatistics dictionaryLettersStatistics = LettersStatistics.getLettersStatisticsFromDictionary(new Dictionary("resources/dict.txt"), new FrenchLanguage());

    for (Map.Entry<Character, Integer> entry : frenchLettersStatistics.vowelsFreqs.entrySet()) {
      double frenchLetterPercent = (double) (entry.getValue() * 100) / frenchLettersStatistics.vowelsFreqsSum;
      double dictionaryLetterPercent = (double) (dictionaryLettersStatistics.vowelsFreqs.get(entry.getKey()) * 100) / dictionaryLettersStatistics.vowelsFreqsSum;
      System.out.println(Math.abs(frenchLetterPercent - dictionaryLetterPercent));
      assertTrue(Math.abs(frenchLetterPercent - dictionaryLetterPercent) < 6.0);
    }
  }

  @Test
  public void Draw() {
    LettersStatistics frenchLettersStatistics = new FrenchLanguage().getLettersStatistics();
    LettersStatistics drawLettersStatistics = new LettersStatistics(new FrenchLanguage().getVowels(), new FrenchLanguage().getConsonants());
    for (int i = 0; i < 10000; i++) {
      Draw draw = new Draw(5, 5, frenchLettersStatistics);
      String word = draw.getDrawString();
      String strippedWord = Utils.stripAccentsHardcoded(word).toLowerCase();
      //System.out.println("stripped to: " + strippedWord);
      int offset = 0;
      final int strippedWordLength = strippedWord.length();
      while (offset < strippedWordLength) {
        int codePoint = strippedWord.codePointAt(offset);
        if (Character.isAlphabetic(Character.toChars(codePoint)[0])) {
          for (char vowel : new FrenchLanguage().getVowels()) {
            if (vowel == Character.toChars(codePoint)[0])
              drawLettersStatistics.addVowel(vowel);
          }
          for (char consonant : new FrenchLanguage().getConsonants()) {
            if (consonant == Character.toChars(codePoint)[0])
              drawLettersStatistics.addConsonant(consonant);
          }
        }

        offset += Character.charCount(codePoint);
      }
    }
    drawLettersStatistics.updateTotalFreqs();

    for (Map.Entry<Character, Integer> entry : frenchLettersStatistics.vowelsFreqs.entrySet()) {
      double frenchLetterPercent = (double) (entry.getValue() * 100) / frenchLettersStatistics.vowelsFreqsSum;
      double drawLetterPercent = (double) (drawLettersStatistics.vowelsFreqs.get(entry.getKey()) * 100) / drawLettersStatistics.vowelsFreqsSum;
      System.out.println(Math.abs(frenchLetterPercent - drawLetterPercent));
      assertTrue(Math.abs(frenchLetterPercent - drawLetterPercent) < 1.0);
    }
  }
}