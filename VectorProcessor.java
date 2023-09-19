package com.company;

import java.util.Vector;

public class VectorProcessor implements Runnable {
    private final Vector<Integer> vector;
    private final int start;
    private final int end;

    public VectorProcessor(Vector<Integer> vector, int start, int end) {
        this.vector = vector;
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        for (int i = start; i < end; i++) {
            int value = vector.get(i);
            vector.set(i, value * value);
        }
    }
}
