package com.univamu.longestword;

public abstract class Language {
    protected LettersStatistics lettersStatistics;
    protected char[] vowels;
    protected char[] consonants;

    public LettersStatistics getLettersStatistics() {
        return lettersStatistics;
    }

    public char[] getVowels() {
        return vowels;
    }
    public char[] getConsonants() {
        return consonants;
    }
}
