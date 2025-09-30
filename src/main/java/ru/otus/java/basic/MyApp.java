package ru.otus.java.basic;

public class MyApp {
    public static void main(String[] args) {

        PhoneBook book = new PhoneBook();

        book.add("Иванов Иван Иванович", "+79211234567");
        book.add("Иванова Анна Ивановна", "+79217654321");
        book.add("Иванов Иван Иванович", "+79219876543"); // У Петра два номера

        System.out.println(book.find("Иванов Иван Иванович"));
        System.out.println(book.find("Иванова Анна Ивановна"));
        System.out.println(book.find("Оборин Алексей Иванович"));

        System.out.println(book.containsPhoneNumber("+79211234567"));
        System.out.println(book.containsPhoneNumber("+79210000000"));
    }

}
