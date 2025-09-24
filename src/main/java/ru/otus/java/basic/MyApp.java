package ru.otus.java.basic;

public class MyApp {
    public static void main(String[] args) {

        String[][] array = {{"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "15", "16"}};

        try {
            System.out.println("Сумма элементов массива равна: " + sumElements(array));
        } catch (AppArraySizeException e) {
            System.err.println(e.getMessage());
        } catch (AppArrayDataException e) {
            System.err.println(e.getMessage());
        }
    }

    public static int sumElements(String[][] arr) throws AppArraySizeException, AppArrayDataException {
        if (arr.length != 4 || arr[0].length != 4)
            throw new AppArraySizeException("Размер массива должен быть 4x4");

        int result = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                try {
                    result += Integer.parseInt(arr[i][j]);
                } catch (NumberFormatException ex) {
                    throw new AppArrayDataException("Ошибка преобразования элемента [" + i + ", " + j + "] в число.");
                }
            }
        }
        return result;
    }
}

class AppArraySizeException extends Exception {
    public AppArraySizeException(String message) {
        super(message);
    }
}

class AppArrayDataException extends Exception {
    public AppArrayDataException(String message) {
        super(message);
    }
}
