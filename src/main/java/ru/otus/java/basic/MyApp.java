package ru.otus.java.basic;

import java.util.function.Supplier;

public class MyApp {
    private static final Object lock = new Object();
    private static volatile boolean isFirstFinished = false;
    private static volatile boolean isSecondFinished = false;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> printLetter('A', () -> !isFirstFinished));
        Thread t2 = new Thread(() -> printLetter('B', () -> isFirstFinished && !isSecondFinished));
        Thread t3 = new Thread(() -> printLetter('C', () -> isSecondFinished));

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();
    }

    private static void printLetter(char letter, Supplier<Boolean> condition) {
        synchronized (lock) {
            for (int i = 0; i < 5; i++) {
                while (!condition.get()) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e); // Обрабатываем исключение
                    }
                }
                System.out.print(letter);
                switch (letter) {
                    case 'A':
                        isFirstFinished = true;
                        break;
                    case 'B':
                        isSecondFinished = true;
                        break;
                    default:
                        isFirstFinished = false;
                        isSecondFinished = false;
                }
                lock.notifyAll();
            }
        }
    }
}
