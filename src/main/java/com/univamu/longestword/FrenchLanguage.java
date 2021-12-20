package com.univamu.longestword;

import java.util.AbstractMap;
import java.util.Map;

public class FrenchLanguage extends Language {
    public FrenchLanguage() {
        vowels = new char[]{'a', 'e', 'i', 'o', 'u', 'y'};
        consonants = new char[]{'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'p', 'q', 'r', 's', 't', 'v', 'w', 'x', 'z'};
        Map<Character, Integer> vowelsPercents = Map.of('a', 841, 'e', 1726, 'i', 734, 'o', 526, 'u', 574, 'y', 30);
        Map<Character, Integer> consonantsPercents = Map.ofEntries(new AbstractMap.SimpleEntry<>('b', 107), new AbstractMap.SimpleEntry<>('c', 303), new AbstractMap.SimpleEntry<>('d', 418), new AbstractMap.SimpleEntry<>('f', 112), new AbstractMap.SimpleEntry<>('g', 127), new AbstractMap.SimpleEntry<>('h', 92), new AbstractMap.SimpleEntry<>('j', 31), new AbstractMap.SimpleEntry<>('k', 5), new AbstractMap.SimpleEntry<>('l', 601), new AbstractMap.SimpleEntry<>('m', 296), new AbstractMap.SimpleEntry<>('n', 714), new AbstractMap.SimpleEntry<>('p', 301), new AbstractMap.SimpleEntry<>('q', 99), new AbstractMap.SimpleEntry<>('r', 655), new AbstractMap.SimpleEntry<>('s', 808), new AbstractMap.SimpleEntry<>('t', 707), new AbstractMap.SimpleEntry<>('v', 132), new AbstractMap.SimpleEntry<>('w', 4), new AbstractMap.SimpleEntry<>('x', 45), new AbstractMap.SimpleEntry<>('z', 12));
        lettersStatistics = new LettersStatistics(vowelsPercents, consonantsPercents);
    }
}