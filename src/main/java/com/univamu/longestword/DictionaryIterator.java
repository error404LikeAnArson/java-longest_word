package com.univamu.longestword;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DictionaryIterator implements Iterator<String> {
    private BufferedReader buff;
    private String nextWord;

    public DictionaryIterator(String path) throws IOException {
        buff = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
        nextWord = buff.readLine();
    }

    /**
     * Returns {@code true} if the iteration has more elements.
     * (In other words, returns {@code true} if {@link #next} would
     * return an element rather than throwing an exception.)
     *
     * @return {@code true} if the iteration has more elements
     */
    @Override
    public boolean hasNext() {
        return nextWord != null;
    }

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration
     * @throws NoSuchElementException if the iteration has no more elements
     */
    @Override
    public String next() {
        if (!hasNext())
            throw new NoSuchElementException();
        String currentWord = nextWord;
        try {
            nextWord = buff.readLine();
            if (nextWord == null)
                buff.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return currentWord;
    }
}
