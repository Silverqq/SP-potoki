package com.company;

import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        Vector<Integer> vector = new Vector<>();
        for (int i = 1; i <= 1000000; i++) {
            vector.add(i);
        }
        int M = 4;

        ExecutorService executor = Executors.newFixedThreadPool(M);
        long startTime = System.currentTimeMillis();
        int chunkSize = vector.size()/M;

        for (int i = 0; i < M; i++) {
            int start = i * chunkSize;
            int end = start + chunkSize;
            VectorProcessor processor = new VectorProcessor(vector, start, end);
            executor.execute(processor);
        }

        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        System.out.println("Время выполнения программы: " + executionTime + " миллисекунд");
    }
}

