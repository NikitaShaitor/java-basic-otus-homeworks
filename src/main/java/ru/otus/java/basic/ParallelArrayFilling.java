package ru.otus.java.basic;

public class ParallelArrayFilling {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        double[] array = new double[100_000_000];
        Thread[] threads = new Thread[4];

        for (int i = 0; i < threads.length; i++) {
            int start = i * array.length / 4;
            int end = (i + 1) * array.length / 4;
            threads[i] = new Thread(new ArrayFiller(array, start, end));
            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        System.out.printf("Время выполнения параллельного заполнения: %d мс\n", duration);
    }
}
