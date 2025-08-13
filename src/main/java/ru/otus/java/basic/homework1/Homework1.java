package ru.otus.java.basic.homework1;
import java.util.Scanner;

public class Homework1 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите целое число от 1 до 5\n1. Метод greetings.\n2. Метод checkSign.\n3. Метод selectColor\n4. Метод compareNumbers\n5. Метод addOrSubtractAndPrint");
        int choise = scanner.nextInt();
            if (choise == 1) {
                greetings();
            } else if (choise == 2) {
                checkSign(1,2,3);
            } else if (choise == 3) {
                selectColor(5);
            } else if (choise == 4) {
                compareNumbers(7, 2);
            } else if (choise == 5) {
                addOrSubtractAndPrint(14,2, true);
            } else {
                System.out.println("Число введено не верно");
            }
    }

    public static void greetings() {
        System.out.println("Hello");
        System.out.println("World");
        System.out.println("from");
        System.out.println("Java");
    }

    public static void checkSign(int a, int b, int c) {
        int sum = a + b + c;

        if (sum >= 0) {
            System.out.println("Сумма положительная");
        } else {
            System.out.println("Сумма отрицательная");
        }
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

    public static void addOrSubtractAndPrint(int initValue, int delta, boolean increment) {
        if (increment){
            int c = initValue + delta;
            System.out.println(c);
        } else {
            int d = initValue - delta;
            System.out.println(d);
        }

    }
}
