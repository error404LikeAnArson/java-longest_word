package com.univamu.longestword;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class ValidWordsPrinter {
    final private PrintWriter writer;

    public ValidWordsPrinter(String path) throws FileNotFoundException, UnsupportedEncodingException {
        writer = new PrintWriter(path, "ISO_8859_1");
    }

    public void writeValidWord(String word) {
        writer.println(word);
    }

    public void writeValidWords(ArrayList<String> words) {
        for (String longestWord : words)
            writeValidWord(longestWord);
    }

    public void closePrinter() {
        writer.close();
    }
}
