package ru.otus.java.basic.homework1;
import java.util.Scanner;

public class Homework1 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Тут прошу ввести выбор задания (с пояснением) для облегчения поиска и выбора
        System.out.println("Введите целое число от 1 до 5\n1. Метод greetings.\n2. Метод checkSign.\n3. Метод selectColor\n4. Метод compareNumbers\n5. Метод addOrSubtractAndPrint");
        int choise = scanner.nextInt();
            if (choise == 1) {
                greetings();
            } else if (choise == 2) {
                // Для того что бы был рандом от -10 до 10 взял рандомные цифры от 0 до 20 и вычел 10
                checkSign((int) (Math.random() * 21) - 10,(int) (Math.random() * 21) - 10,(int) (Math.random() * 21) - 10);
            } else if (choise == 3) {
                // Взял для до 30. Т.к. По условиям задачи должно быть число больше 20
                selectColor((int) (Math.random() * 31));
            } else if (choise == 4) {
                // Тут так же взял для того что бы было от -10 до 10
                compareNumbers((int) (Math.random() * 21) - 10, (int) (Math.random() * 21) - 10);
            } else if (choise == 5) {
                // Для initValue и delta взял числа от 0 до 10. Для boolean increment взял два числа 0 и 1. Затем сравнил его с 0. Если == 0, то true, иначе false
                addOrSubtractAndPrint((int) (Math.random() * 11),(int) (Math.random() * 11), ((int) (Math.random() * 2)) == 0);
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
