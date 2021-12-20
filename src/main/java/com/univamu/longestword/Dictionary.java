package com.univamu.longestword;

import java.io.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Dictionary implements Iterable<String> {
    private final String path;

    public Dictionary(String path) {
        this.path = path;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<String> iterator() {
        try {
            return new DictionaryIterator(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Iterator<String>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public String next() {
                throw new NoSuchElementException();
            }
        };
    }
}
