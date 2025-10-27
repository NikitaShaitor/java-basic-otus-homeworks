package ru.otus.java.basic;

public class SequentialArrayFilling {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        double[] array = new double[100_000_000];

        for (int i = 0; i < array.length; i++) {
            array[i] = 1.14 * Math.cos(i) * Math.sin(i * 0.2) * Math.cos(i / 1.2);
        }

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        System.out.printf("Время выполнения последовательного заполнения: %d мс\n", duration);
    }
}
