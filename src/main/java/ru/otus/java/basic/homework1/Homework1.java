package ru.otus.java.basic.homework1;

public class Homework1 {
    public static void main(String[] args) {
        greetings();
        checkSign(1, 2, 3);
        selectColor(5);
        compareNumbers(7, 2);
    }

    public static void greetings() {
        System.out.println("Hello");
        System.out.println("World");
        System.out.println("from");
        System.out.println("Java");
    }

    public static void checkSign(int a, int b, int c) {
        int sum = a + b + c;

        if (sum >= 0)
            System.out.println("Сумма положительная");
        else
            System.out.println("Сумма отрицательная");

    }

    public static void selectColor(int data) {
        if (data <= 20 && data >= 10) {
            System.out.println("Желтый");
        } else if (data < 10) {
            System.out.println("Зеленый");
        } else {
            System.out.println("Зеленый");
        }
    }

    public static void compareNumbers(int a, int b) {
        if (a >= b) {
            System.out.println("a >= b");
        } else {
            System.out.println("a < b");
        }

    }
}
