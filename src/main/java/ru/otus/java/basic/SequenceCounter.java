package ru.otus.java.basic;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class SequenceCounter {

    public static void main(String[] args) throws IOException {

        System.out.print("Введите полный путь к файлу: ");
        String filePath = new BufferedReader(new InputStreamReader(System.in)).readLine();

        System.out.print("Введите последовательность символов для поиска: ");
        String searchSequence = new BufferedReader(new InputStreamReader(System.in)).readLine();

        int count = countOccurrences(filePath, searchSequence);

        if (count != -1) {
            System.out.println("Последовательность '" + searchSequence + "' найдена " + count + " раз.");
        } else {
            System.out.println("Ошибка открытия файла");
        }
    }

    private static int countOccurrences(String filePath, String searchSequence) {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8))) {

            int count = 0;
            String line;

            while ((line = reader.readLine()) != null) {

                int index = 0;

                while ((index = line.indexOf(searchSequence, index)) >= 0) {
                    count++;
                    index += searchSequence.length();
                }
            }

            return count;
        } catch (IOException e) {
            return -1;
        }
    }
}

