package ru.otus.java.basic.homework2;

import java.util.Arrays;
import java.util.Scanner;

public class homework2 {
    public static void main(String[] args) {
        // Выбор для заданий
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nВыбери какое задание хочешь посмотреть:\n1) lineOutput - выводится строка столько раз сколько захочешь\n2) sumArray - Считается сумма всего массива\n3) numberArray - заполнение массива числом\n4) Сумма половины массива ");
        System.out.println("\nЗадания повышенной сложности (со звёздочкой): \n5) Новый метод равный сумме входящих\n6) Проверка что в массиве есть точка\n7) Проверка массива на возростание или убывание");
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
        } else if (choise == 6) {
            balanceFinder(new int[]{1, 1, 1, 1, 3});
            balanceFinder(new int[]{1, 1, 1, 3});
            balanceFinder(new int[]{7, 2, 2, 2});
            balanceFinder(new int[]{9, 4});
        } else if (choise == 7) {
            ascendingArrays(new int[]{1, 2, 3, 4});
        } else if (choise == 8) {
            arraysReverse();
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

        int maxSize = Math.max(Math.max(firsArray.length, secondArray.length), thirdArray.length);
        int[] resultArray = new int[maxSize];

        for (int i = 0; i < maxSize; i++) {
            if (i < firsArray.length) {
                resultArray[i] += firsArray[i];
            }
            if (i < secondArray.length) {
                resultArray[i] += secondArray[i];
            }
            if (i < thirdArray.length) {
                resultArray[i] += thirdArray[i];
            }
        }
        System.out.println("Итоговый массив: " + Arrays.toString(resultArray));
    }

    public static void balanceFinder(int[] arrays) {
        if (arrays.length <= 1) {
            System.out.println("false");
            return;
        }
        int leftSum = 0;
        int rightSum = 0;

        for (int i = arrays.length - 1; i >= 0; i--) {
            rightSum += arrays[i];
        }
        for (int i = 0; i < arrays.length; i++) {
            rightSum -= arrays[i];
            leftSum += arrays[i];
            if (leftSum == rightSum) {
                System.out.println("true");
                return;
            }

        }
        System.out.println("false");
    }

    public static void ascendingArrays(int[] arrays) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Как хочешь проверить массив:\n1) По возрастанию\n2) По убыванию");
        int ascending = scanner.nextInt();


        if (ascending == 1) {
            for (int i = 0; i < arrays.length - 1; i++) {
                for (int j = i + 1; j < arrays.length; j++) {
                    if (arrays[i] < arrays[j]) {
                        System.out.println("Правда");
                        return;
                    } else {
                        System.out.println("Ложь");
                        return;
                    }
                }
            }
        } else if (ascending == 2) {
            for (int i = 0; i < arrays.length - 1; i++) {
                for (int j = i + 1; j < arrays.length; j++) {
                    if (arrays[i] > arrays[j]) {
                        System.out.println("Правда");
                        return;
                    } else {
                        System.out.println("Ложь");
                        return;
                    }
                }
            }

        }
    }

    public static void arraysReverse() {
        int[] arr = {1, 2, 3, 4};
        System.out.println("Исходный массив:" + Arrays.toString(arr));
        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
        System.out.println("Перевёрнутый массив: " + Arrays.toString(arr));
    }
}
