package com.univamu.longestword;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Task2 {
    private static int vowelsNb;
    private static int consonantsNb;

    private static void askLettersNb() {
        vowelsNb = -1;
        consonantsNb = -1;
        Scanner scanner = new Scanner(System.in);
        while (vowelsNb < 0 || vowelsNb > Draw.MAX_DRAW_SIZE) {
            System.out.println("Please choose vowels number: between 0 and " + Draw.MAX_DRAW_SIZE);
            if (scanner.hasNextInt())
                vowelsNb = scanner.nextInt();
            else
                scanner.nextLine();
        }
        scanner.nextLine();
        if (vowelsNb == Draw.MAX_DRAW_SIZE)
            consonantsNb = 0;
        int minConsonantsNb = 0;
        if (vowelsNb == 0)
            minConsonantsNb = 1;
        while (vowelsNb + consonantsNb > Draw.MAX_DRAW_SIZE || consonantsNb < minConsonantsNb ) {
            System.out.println("Please choose consonants number: between " + minConsonantsNb + " and " + (Draw.MAX_DRAW_SIZE - vowelsNb));
            if (scanner.hasNextInt())
                consonantsNb = scanner.nextInt();
            else
                scanner.nextLine();
        }
    }

    private static boolean askGiveUpDraw() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Retry this draw ? y/n");
            String yesnoAnswer = scanner.nextLine();
            if (yesnoAnswer.toLowerCase().equals("y"))
                return false;
            else if (yesnoAnswer.toLowerCase().equals("n"))
                return true;
        }
    }

    private static void askAnswer(Draw draw, int longestValidWordsLength) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("This is your draw: " + draw.getDrawString());
            System.out.println("Please enter the longest word embedding only these letters.");
            String word = scanner.nextLine();
            if(Utils.isCorrectAnswer(word)) {
                 if (draw.isValidWord(word) && word.length() != 0 && draw.isInDictionary(word)) {
                     System.out.println("Congratulation !");
                     int wordActualLength = Utils.getActualLength(word);
                     if (wordActualLength > longestValidWordsLength) {
                         System.out.println("Your word is even longer than those found in the dictionary!");
                         break;
                     } else if (wordActualLength == longestValidWordsLength)
                         break;
                     else {
                         System.out.println("However your word is not the longest possible...");
                         if (askGiveUpDraw())
                             return;
                     }
                 } else {
                     System.out.println("Wrong answer...");
                     if (askGiveUpDraw())
                         return;
                 }
             }
             else
                 System.out.println("Your answer is incorrect.");
        }
    }

    private static boolean askNewGame() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Start a new game ? y/n");
            String yesnoAnswer = scanner.nextLine();
            if (yesnoAnswer.toLowerCase().equals("y"))
                return true;
            else if (yesnoAnswer.toLowerCase().equals("n"))
                return false;
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to French scrabble game.\nEasy mode: up to " + Draw.MAX_DRAW_SIZE + " letters.");
        do {
            askLettersNb();

            //LettersStatistics frenchLettersStatistics = new FrenchLanguage().getLettersStatistics();
            LettersStatistics dictionaryLettersStatistics = LettersStatistics.getLettersStatisticsFromDictionary(new Dictionary("resources/dict.txt"), new FrenchLanguage());

            Draw draw = new Draw(vowelsNb, consonantsNb, dictionaryLettersStatistics);

            ArrayList<String> longestValidWords = draw.findLongestValidWordsFromDictionary(new Dictionary("resources/dict.txt"));

            int longestWordsActualLength = 0;
            if (longestValidWords.size() != 0)
                longestWordsActualLength = Utils.getActualLength(longestValidWords.get(0));
            askAnswer(draw, longestWordsActualLength);

            System.out.println("Longest solutions:");
            for (String longestValidWord : longestValidWords)
                System.out.println(longestValidWord);

            ValidWordsPrinter printer = new ValidWordsPrinter("resources/mots-trouves.txt");
            printer.writeValidWords(longestValidWords);
            printer.closePrinter();
        } while (askNewGame());
        System.out.println("See you soon!");
    }
}