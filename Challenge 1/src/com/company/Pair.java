package com.company;

/**
 * A simple entity for pairing integers
 */
public class Pair {
    private Integer x1;
    private Integer x2;

    Pair(int x1) {
        this.x1 = x1;
        this.x2 = null;
    }

    Pair(int x1, int x2) {
        this.x1 = x1;
        this.x2 = x2;
    }

    @Override
    public String toString() {
        if (x2 == null) {
            return "(" + x1 + ") ";
        } else {
            return "(" + x1 + ", " + x2 + ") ";
        }
    }
}
