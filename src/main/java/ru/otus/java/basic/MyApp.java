package ru.otus.java.basic;

public class MyApp {
    public static void main(String[] args) {
        User User1 = new User("Nikita", "Shaitor", "Sergeevich", "19.10.2000", "nikitashaytor@yandex.ru");
        User User2 = new User("Ivan", "Ivanov", "Ivanovich", "01.01.1990", "ivanovich@yandex.ru");
        User1.info();
        User2.info();

    }
}
