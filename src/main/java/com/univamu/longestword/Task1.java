package com.univamu.longestword;

import java.io.IOException;
import java.util.ArrayList;

public class Task1 {
    public static void main(String[] args) throws IOException {
        Draw draw = new Draw("resources/tirages.txt");
        Controller controller = new Controller(draw);

        ArrayList<String> longestValidWords = controller.findLongestValidWordsFromDictionary(new Dictionary("resources/dict.txt"));

        ValidWordsPrinter printer = new ValidWordsPrinter("resources/mots-trouves.txt");
        printer.writeValidWords(longestValidWords);
        printer.closePrinter();
    }
}