package ru.otus.java.basic;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class MyApp {
    private static final Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
        StarterApp();
        choiseAndReadFile();
        writeToFIle();
    }

    public static void readFile(String filename) {
        try (InputStreamReader in = new InputStreamReader(new FileInputStream(filename), StandardCharsets.UTF_8)) {
            int n = in.read();
            while (n != -1) {
                System.out.print((char) n);
                n = in.read();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void StarterApp() {
        String dirFile = new File(".").getAbsolutePath();
        File directory = new File(dirFile);
        File[] filesInDirectory = directory.listFiles();

        boolean foundFile = false;

        if (filesInDirectory != null) {
            int count = 1;
            System.out.println("Список файлов: ");
            for (File file : filesInDirectory) {
                if (file.isFile() && file.getName().endsWith(".txt")) {
                    System.out.println(count + ") " + file.getName());
                    foundFile = true;
                    count++;
                }
            }
            if (!foundFile) {
                System.out.println("Текстовых файлов не обнаружено");
            }
        } else {
            System.out.println("Ошибка чтения");
        }


    }

    public static File findSelectedFile(int number, File[] allFiles) {
        int count = 1;
        for (File file : allFiles) {
            if (file.isFile() && file.getName().endsWith(".txt")) {
                if (number == count++)
                    return file;
            }
        }
        return null;
    }

    public static void choiseAndReadFile() {
        System.out.println("\nВыбери файл для открытия:\nВведи его порядковый номер");
        int choise = scanner.nextInt();
        scanner.nextLine();

        String dirFile = new File(".").getAbsolutePath();
        File directory = new File(dirFile);
        File[] filesInDirectory = directory.listFiles();

        if (filesInDirectory != null && choise > 0 && choise <= filesInDirectory.length){
            File selectedFile = findSelectedFile(choise, filesInDirectory);
            if (selectedFile != null) {
                setLastSelectedFile(selectedFile.getName());
                readFile(selectedFile.getName());
            } else {
                System.out.println("Файл не найден");
            }
        } else {
            System.out.println("Неверный номер файла");
        }
    }
    private static String lastSelectedFile = null;
    private static String findLastSelectedFile() {
        return lastSelectedFile;
    }
    public static void setLastSelectedFile(String fileName) {
        lastSelectedFile = fileName;
    }

    public static void writeToFIle(){
        System.out.println("\nВведите строку для записи в файл: ");
        String inputData = scanner.nextLine();

        String lastSelectedFile = findLastSelectedFile();
        if (lastSelectedFile != null) {
            try (BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(lastSelectedFile, true), StandardCharsets.UTF_8))){
             writer.write(inputData);
             writer.newLine();
             System.out.println("Данные записаны в файл: " + lastSelectedFile);
            } catch (IOException e){
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("Не выбран файл для записи");
        }
    }
}
