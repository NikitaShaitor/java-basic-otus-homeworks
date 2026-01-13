package ru.otus.java.basic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.Serial;
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

public class FileManagerGUI extends JFrame implements ActionListener {
    @Serial
    private static final long serialVersionUID = 1L;
    private static String CURRENT_DIR = System.getProperty("user.dir");
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    static JTextArea outputTextArea;
    JTextField inputField;
    JButton executeButton;
    JPanel panel;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            new FileManagerGUI().setVisible(true);
        });
    }

    public FileManagerGUI() {
        super("Файловый Менеджер");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        outputTextArea = new JTextArea();
        outputTextArea.setEditable(false);
        outputTextArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14)); // Моноширинный шрифт
        add(new JScrollPane(outputTextArea), BorderLayout.CENTER);

        panel = new JPanel();
        inputField = new JTextField(20);
        executeButton = new JButton("Выполнить");
        executeButton.addActionListener(this);
        panel.add(inputField);
        panel.add(executeButton);
        add(panel, BorderLayout.SOUTH);

        setPreferredSize(new Dimension(800, 600));
        pack();
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == executeButton) {
            String input = inputField.getText();
            try {
                clearOutput();
                processCommand(input.split("\\s+"));
            } catch (IOException ex) {
                appendOutput("Ошибка: " + ex.getMessage());
            }
            inputField.setText("");
        }
    }

    private static void appendOutput(String text) {
        outputTextArea.append(text + "\n");
    }

    private void clearOutput() {
        outputTextArea.setText("");
    }

    private static void processCommand(String[] commandArgs) throws IOException {
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
                appendOutput("Неверная команда. Напишите 'help', чтобы увидеть доступные команды.");
        }
    }

    private static void listFiles(boolean detailed) {
        File dir = new File(CURRENT_DIR);
        File[] files = dir.listFiles();

        if (files != null) {
            for (File f : files) {
                if (!detailed) {
                    appendOutput(f.getName());
                } else {
                    String sizeStr = f.isDirectory() ? "<DIR>" : Long.toString(f.length());
                    Date lastModified = new Date(f.lastModified());

                    appendOutput(String.format("%-20s %8s %-20s", f.getName(), sizeStr, sdf.format(lastModified)));
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

        appendOutput("Имя файла: " + file.getName());
        appendOutput("Размер: " + file.length() + " байт");
        appendOutput("Тип каталога: " + (file.isDirectory() ? "Директория" : "Файл"));
        appendOutput("Последнее изменение файла: " + getLastAccessTime(file));
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
                    appendOutput(f.getAbsolutePath());
                }
                if (f.isDirectory()) {
                    recursiveFind(f, filename);
                }
            }
        }
    }

    private static void printHelp() {
        appendOutput("Доступные команды:");
        appendOutput("ls [-i]: Показать файлы в текущем каталоге (-i для деталей)");
        appendOutput("cd [путь]: Перейти в другой каталог (cd .. для перехода в родительскую директорию");
        appendOutput("mkdir [название]: Создать новую директорию");
        appendOutput("rm [имя]: Удалить файл или директорию");
        appendOutput("mv [исходный_файл] [целевой_файл] [-f]: Переместить или переименовать файл или директорию");
        appendOutput("cp [исходный_файл] [целевой_файл] [-f]: Копировать файл");
        appendOutput("finfo [имя]: Информация о файле");
        appendOutput("find [имя]: Найти файл в текущем каталоге и подпапках");
        appendOutput("help: Вывести справку по доступным командам");
        appendOutput("exit: Закрыть приложение");
    }

    public static String getLastAccessTime(File file) throws IOException {
        Path path = file.toPath();
        BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class, LinkOption.NOFOLLOW_LINKS);

        Instant instant = attr.lastAccessTime().toInstant();
        LocalDateTime ldt = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());

        return DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss").format(ldt);
    }

}