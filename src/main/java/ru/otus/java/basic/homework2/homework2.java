package ru.otus.java.basic.homework2;

import java.util.Arrays;
import java.util.Scanner;

public class homework2 {
    public static void main(String[] args) {
        // Выбор для заданий
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nВыбери какое задание хочешь посмотреть:\n1) lineOutput - выводится строка столько раз сколько захочешь\n2) sumArray - Считается сумма всего массива\n3) numberArray - заполнение массива числом\n4) Сумма половины массива ");
        System.out.println("\nЗадания повышенной сложности (со звёздочкой): \n5) Новый метод равный сумме входящих");
        int choise = scanner.nextInt();
        if (choise == 1) {
            lineOutput();
        } else if (choise == 2) {
            sumArray();
        } else if (choise == 3) {
            numberArray();
        } else if (choise == 4) {
            halfSumArray();
        } else if (choise == 5) {
            arrNew();
        }
    }

    public static void lineOutput() {
        // 1 задание. Количество строк выбирает пользователь
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введи число сколько раз хочешь что бы строка была распечатана");
        int number = scanner.nextInt();
        for (int i = 1; i <= number; i++) {
            System.out.println("Строка: " + i);
        }
    }

    public static void sumArray() {
        // 2 задание. Сумма массива
        int[] arr = {1, 2, 3, 6, 4, 3};
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        System.out.println("Сумма = " + sum);

    }

    public static void numberArray() {
        // 3 задание. Заполнение массива определенной цифрой. Цифру и длину массива дал выбрать пользователю
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введи длину массива");
        int lengArr = scanner.nextInt(); // Жду от пользователя длину массива
        int[] arr = new int[lengArr]; // В массив засунул цифру которую, выбрал пользователь
        System.out.println("Введи число которым хочешь заполнить данный массив");
        int numberArr = scanner.nextInt();
        for (int i = 0; i < arr.length; i++) { // Тут прогоняю цифру по длине массива. Каждый раз переходя на следующую
            arr[i] = numberArr;
        }
        System.out.println(Arrays.toString(arr));
    }

    public static void halfSumArray() {
        // 4 задание. Считаю сумму элементов какой из половин массива больше
        int[] arr = {3, 8, 9, 9, 5, 9, 7, 5, 7, 8, 3, 8, 4, 5};
        int sum1 = 0;
        int sum2 = 0;
        for (int i = 0; i < arr.length / 2; i++) { // Прошелся по первой половины длины массива. И сумму закинул в переменную sum1
            sum1 += arr[i];
        }
        for (int i = arr.length / 2; i < arr.length; i++) { // Прошелся по второй половины длины массива. И сумму закинул в переменную sum2
            sum2 += arr[i];
        }
        if (sum1 > sum2) { // Сравнил суммы
            System.out.println("Сумма первой половины больше и ровна: " + sum1); // Если первая половина больше, то вывожу данный текс
        } else {
            System.out.println("Сумма второй половины больше и ровна: " + sum2); // Если вторая половина больше, то вывожу данный текс
        }
    }

    public static void arrNew() {
        int[] firsArray = {5, 1, 2, 3, 5};
        int[] secondArray = {4, 2, 3};
        int[] thirdArray = {7, 9, 3, 4};

        int maxSize = Math.max(Math.max(firsArray.length,secondArray.length),thirdArray.length);
        int[] resultArray = new int [maxSize];

        for (int i = 0; i < maxSize; i++) {
            if (i < firsArray.length) {
                resultArray[i] += firsArray[i];
            }if (i < secondArray.length) {
                resultArray[i] += secondArray[i];
            }if (i < thirdArray.length) {
                resultArray[i] += thirdArray[i];
            }
        }
        System.out.println("Итоговый массив: " + Arrays.toString(resultArray));
    }
}
