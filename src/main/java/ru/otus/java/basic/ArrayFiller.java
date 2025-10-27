package ru.otus.java.basic;

public class ArrayFiller implements Runnable {
    private final double[] array;
    private final int startIndex;
    private final int endIndex;

    public ArrayFiller(double[] array, int startIndex, int endIndex) {
        this.array = array;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    @Override
    public void run() {
        for (int i = startIndex; i < endIndex; i++) {
            array[i] = 1.14 * Math.cos(i) * Math.sin(i * 0.2) * Math.cos(i / 1.2);
        }
    }
}