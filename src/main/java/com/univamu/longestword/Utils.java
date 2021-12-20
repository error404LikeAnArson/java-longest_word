package com.univamu.longestword;

import java.util.ArrayList;

public class Utils {
  public static boolean isVowel(int codePoint) {
    switch (codePoint) {
      case 'a':
      case 'e':
      case 'i':
      case 'o':
      case 'u':
      case 'y':
        return true;
      default:
        return false;
    }
  }

  public static boolean isConsonant(int codePoint) {
    return Character.isAlphabetic(codePoint) && !isVowel(codePoint);
  }

  public static String stripAccentsHardcoded(String s) {
    return s.replace("á", "a")
            .replace("à", "a")
            .replace("â", "a")
            .replace("ä", "a")
            .replace("ã", "a")
            .replace("é", "e")
            .replace("è", "e")
            .replace("è", "e")
            .replace("ê", "e")
            .replace("ë", "e")
            .replace("î", "i")
            .replace("ï", "i")
            .replace("ó", "o")
            .replace("ò", "o")
            .replace("ô", "o")
            .replace("ö", "o")
            .replace("õ", "o")
            .replace("ú", "u")
            .replace("ù", "u")
            .replace("û", "u")
            .replace("ü", "u")
            .replace("æ", "ae")
            .replace("œ", "oe")
            .replace("ç", "c")
            .replace("ÿ", "y")
            ;
  }

  public static int getActualLength(String word) {
    String strippedWord = Utils.stripAccentsHardcoded(word).toLowerCase();
    //System.out.println("stripped to: " + strippedWord);
    int size = 0;
    int offset = 0;
    final int strippedWordLength = strippedWord.length();
    while (offset < strippedWordLength) {
      int codePoint = strippedWord.codePointAt(offset);
      if (Character.isAlphabetic(Character.toChars(codePoint)[0]))
        size++;

      offset += Character.charCount(codePoint);
    }
    //return word.codePointCount(0, word.length());
    return size;
  }

  public static boolean isCorrectAnswer(String word) {
    if (word.length() == 0)
      return false;
    for(int i = 0; i < word.length(); i++)
      if (!(word.charAt(i) == '\u0020' || word.charAt(i) == '\u002d' || (word.charAt(i) >= '\u0041' && word.charAt(i) <= '\u005a') || (word.charAt(i) >= '\u0061' && word.charAt(i) <= '\u007a')))
        return false;
    return true;
  }
}