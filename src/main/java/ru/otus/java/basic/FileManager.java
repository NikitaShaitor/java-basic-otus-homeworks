package ru.otus.java.basic;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

public class FileManager {
    private static String CURRENT_DIR = System.getProperty("user.dir");
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print(CURRENT_DIR + "> ");
            String input = scanner.nextLine();

            if ("exit".equals(input)) break;

            try {
                processCommand(input.split("\\s+"));
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    private static void processCommand(String[] commandArgs) throws Exception {
        switch (commandArgs.length > 0 ? commandArgs[0].toLowerCase() : "") {
            case "ls":
                listFiles(commandArgs.length > 1 && "-i".equals(commandArgs[1]));
                break;

            case "cd":
                changeDirectory(commandArgs.length > 1 ? commandArgs[1] : "");
                break;

            case "mkdir":
                createDir(commandArgs.length > 1 ? commandArgs[1] : "");
                break;

            case "rm":
                removeFileOrDir(commandArgs.length > 1 ? commandArgs[1] : "");
                break;

            case "mv":
                moveRenameFile(commandArgs);
                break;

            case "cp":
                copyFile(commandArgs);
                break;

            case "finfo":
                fileInfo(commandArgs.length > 1 ? commandArgs[1] : "");
                break;

            case "find":
                findFile(commandArgs.length > 1 ? commandArgs[1] : "");
                break;

            case "help":
                printHelp();
                break;

            default:
                System.out.println("Неверная команда. Напишите 'help', чтобы увидеть доступные команды.");
        }
    }

    private static void listFiles(boolean detailed) {
        File dir = new File(CURRENT_DIR);
        File[] files = dir.listFiles();

        if (files != null) {
            for (File f : files) {
                if (!detailed) {
                    System.out.println(f.getName());
                } else {
                    String sizeStr = f.isDirectory() ? "<DIR>" : Long.toString(f.length());
                    Date lastModified = new Date(f.lastModified());

                    System.out.printf("%-20s %8s %-20s\n",
                            f.getName(), sizeStr, sdf.format(lastModified));
                }
            }
        }
    }

    private static void changeDirectory(String path) {
        if ("..".equals(path)) {
            int idx = CURRENT_DIR.lastIndexOf(File.separatorChar);
            if (idx >= 0) {
                CURRENT_DIR = CURRENT_DIR.substring(0, idx);
            }
        } else if (!"".equals(path)) {
            File targetDir = new File(CURRENT_DIR, path);
            if (targetDir.exists() && targetDir.isDirectory()) {
                CURRENT_DIR = targetDir.getAbsolutePath();
            } else {
                throw new IllegalArgumentException("Каталог не найден или неверный путь.");
            }
        }
    }

    private static void createDir(String name) throws IOException {
        File dir = new File(CURRENT_DIR, name);
        boolean created = dir.mkdir();
        if (!created) {
            throw new IOException("Не удалось создать директорию.");
        }
    }

    private static void removeFileOrDir(String path) throws IOException {
        Path p = Paths.get(CURRENT_DIR, path);
        Files.delete(p);
    }

    private static void moveRenameFile(String[] args) throws IOException {
        if (args.length < 3 || args.length > 4) {
            throw new IllegalArgumentException("Использование: mv источник назначение [-f]");
        }

        String srcPath = args[1];
        String destPath = args[2];
        boolean forceOverwrite = false;

        if (args.length == 4 && "-f".equals(args[3])) {
            forceOverwrite = true;
        }

        File srcFile = new File(CURRENT_DIR, srcPath);
        File destFile = new File(CURRENT_DIR, destPath);

        if (destFile.exists() && !forceOverwrite) {
            throw new IOException("Файл или папка в месте назначения уже существуют. Используйте '-f' для переопределения.");
        }

        if (!srcFile.renameTo(destFile)) {
            throw new IOException("Не удалось переместить или переименовать файл.");
        }
    }

    private static void copyFile(String[] args) throws IOException {
        if (args.length < 3 || args.length > 4) {
            throw new IllegalArgumentException("Использование: cp исходный_файл целевой_файл [-f]");
        }

        String srcPath = args[1];
        String destPath = args[2];
        boolean forceOverwrite = false;

        if (args.length == 4 && "-f".equals(args[3])) {
            forceOverwrite = true;
        }

        File srcFile = new File(CURRENT_DIR, srcPath);
        File destFile = new File(CURRENT_DIR, destPath);

        if (destFile.exists() && !forceOverwrite) {
            throw new IOException("Файл в месте назначения уже существует. Используйте '-f' для переопределения.");
        }

        Files.copy(srcFile.toPath(), destFile.toPath());
    }


    private static void fileInfo(String filename) throws IOException {
        File file = new File(CURRENT_DIR, filename);

        if (!file.exists()) {
            throw new IllegalArgumentException("Файл не найден.");
        }

        System.out.println("Имя файла: " + file.getName());
        System.out.println("Размер: " + file.length() + " байт");
        System.out.println("Тип каталога: " + (file.isDirectory() ? "Директория" : "Файл"));
        System.out.println("Последнее изменение файла: " + getLastAccessTime(file));
    }

    private static void printHelp() {
        System.out.println("Доступные команды:");
        System.out.println("ls [-i]: Показать файлы в текущем каталоге (-i для деталей)");
        System.out.println("cd [путь]: Перейти в другой каталог (cd .. для перехода в родительскую директорию");
        System.out.println("mkdir [название]: Создать новую директорию");
        System.out.println("rm [имя]: Удалить файл или директорию");
        System.out.println("mv [исходный_файл] [целевой_файл] [-f]: Переместить или переименовать файл или директорию");
        System.out.println("cp [исходный_файл] [целевой_файл] [-f]: Копировать файл");
        System.out.println("finfo [имя]: Информация о файле");
        System.out.println("find [имя]: Найти файл в текущем каталоге и подпапках");
        System.out.println("help: Вывести справку по доступным командам");
        System.out.println("exit: Завершить работу");
    }

    private static void findFile(String filename) {
        File root = new File(CURRENT_DIR);
        recursiveFind(root, filename);
    }

    private static void recursiveFind(File dir, String filename) {
        File[] files = dir.listFiles();

        if (files != null) {
            for (File f : files) {
                if (f.getName().contains(filename)) {
                    System.out.println(f.getAbsolutePath());
                }
                if (f.isDirectory()) {
                    recursiveFind(f, filename);
                }
            }
        }
    }

    public static String getLastAccessTime(File file) throws IOException {
        Path path = file.toPath();
        BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class, LinkOption.NOFOLLOW_LINKS);

        Instant instant = attr.lastAccessTime().toInstant();
        LocalDateTime ldt = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());

        return DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss").format(ldt);
    }
}