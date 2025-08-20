package ru.otus.java.basic.homework2;
import java.util.Arrays;
import java.util.Scanner;

public class homework2 {
    public static void main(String[] args) {
        // Выбор для заданий
        Scanner scanner = new Scanner(System.in);
        System.out.println("Выбери какое задание хочешь посмотреть:\n1) lineOutput - выводится строка столько раз сколько захочешь\n2) sumArray - Считается сумма всего массива\n3) numberArray - заполнение массива числом");
        int choise = scanner.nextInt();
        if (choise == 1) {
            lineOutput();
        } else if (choise == 2) {
            sumArray();
        } else if (choise == 3) {
            numberArray();
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
        for (int i = 0; i < arr.length; i++){
            sum += arr[i];
        }
        System.out.println("Сумма = " + sum);

    }

    public static void numberArray(){
        // 3 задание. Заполнение массива определенной цифрой. Цифру и длину массива дал выбрать пользователю
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введи длину массива");
        int lengArr = scanner.nextInt(); // Жду от пользователя длину массива
        int [] arr = new int[lengArr]; // В массив засунул цифру которую, выбрал пользователь
        System.out.println("Введи число которым хочешь заполнить данный массив");
        int numberArr = scanner.nextInt();
        for (int i = 0; i <arr.length; i++){ // Тут прогоняю цифру по длине массива. Каждый раз переходя на следующую
            arr[i] = numberArr;
        }
        System.out.println(Arrays.toString(arr));
    }
}
