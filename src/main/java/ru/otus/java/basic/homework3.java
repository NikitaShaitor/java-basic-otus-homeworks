package ru.otus.java.basic;

import java.util.Arrays;
import java.util.Scanner;

public class homework3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choise = scanner.nextInt();

        if (choise == 1) {
            int[][] arrayPos = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
            sumOfPositiveElements(arrayPos);
        } else if (choise == 2) {
            createBox(5);
        } else if (choise == 3) {
            int[][] arrayZero = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
            System.out.println("До изменений");
            displayZeroDiagonals(arrayZero);
            zeroDiagonals(arrayZero);
            System.out.println("\nПосле изменений");
            displayZeroDiagonals(arrayZero);
        } else if (choise == 4) {
            int[][] array = {{-1, -2, -3}, {-2, -5, -7}, {-3, -2}};
            findMax(array);
            int result = findMax(array);
            System.out.println("Максимальное значение: " + result);
        } else if (choise == 5) {
            int[][] array = {{1, 2, 3}, {1, 7, 10, 8, 4}, {10, 2, 6}, {123, 614}};
            if (array.length >= 2) {
                sumElementsArray(array);
            } else {
                int error = sumElementsArray(array);
                System.out.println("Вторая строка отсутствует результат: " + error);
            }
        }
    }

    public static void sumOfPositiveElements(int[][] arrayPos) {

        int sum = 0;
        for (int i = 0; i < arrayPos.length; i++) {
            for (int j = 0; j < arrayPos[i].length; j++) {
                if (arrayPos[i][j] >= 0) {
                    sum += arrayPos[i][j];
                }
            }
        }
        System.out.println(sum);
    }

    public static void createBox(int size) {
        if (size <= 0) {
            return;
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    public static void zeroDiagonals(int[][] arrayZero) {
        for (int i = 0; i < arrayZero.length; i++) {
            arrayZero[i][i] = 0;
            arrayZero[i][arrayZero.length - 1 - i] = 0;
        }
    }

    public static void displayZeroDiagonals(int[][] arrayZero) {
        for (int i = 0; i < arrayZero.length; i++) {
            for (int j = 0; j < arrayZero[i].length; j++) {
                System.out.print(arrayZero[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int findMax(int[][] array) {
        int max = array[0][0];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] > max) {
                    max = array[i][j];
                }
            }
        }
        return max;
    }

    public static int sumElementsArray(int[][] array) {
        if (array.length >= 2) {
            return -1;
        }
        int sum = 0;
        for (int i = 0; i < array[1].length; i++) {
            sum += array[1][i];
        }
        return sum;
    }
}
