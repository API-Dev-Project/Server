package com.graham.api;

import java.util.Random;

/**
 * Created by graham on 21/10/16.
 */
public class Randomizer {
    private static final char[] symbols;

    static {
        StringBuilder tmp = new StringBuilder();
        for (char ch = '0'; ch <= '9'; ++ch)
            tmp.append(ch);
        for (char ch = 'a'; ch <= 'z'; ++ch)
            tmp.append(ch);
        symbols = tmp.toString().toCharArray();
    }

    private final Random random = new Random();

    private final char[] buffer;

    public Randomizer(int length) {
        if (length < 1)
            throw new IllegalArgumentException("length < 1: " + length);
        buffer = new char[length];
    }

    public String getString() {
        for (int i = 0; i < buffer.length; ++i) {
            buffer[i] = symbols[random.nextInt(symbols.length)];
        }

        return new String(buffer);
    }
}
