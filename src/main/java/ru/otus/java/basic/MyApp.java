package ru.otus.java.basic;

public class MyApp {
    public static void main(String[] args) {
        User[] users = new User[10];

        users[0] = new User("Иван", "Иванов", "Иванович", "1980-01-01", "ivan@yandex.ru");   // Старше 40 лет
        users[1] = new User("Петр", "Петров", "Петрович", "1990-02-02", "petr@yandex.ru");  // Младше 40 лет
        users[2] = new User("Алексей", "Алексеев", "Алексеевич", "1975-03-03", "aleksey@yandex.ru"); // Старше 40 лет
        users[3] = new User("Сергей", "Сергеев", "Сергеевич", "1985-04-04", "sergey@yandex.ru"); // Старше 40 лет
        users[4] = new User("Дмитрий", "Дмитриев", "Дмитриевич", "1995-05-05", "dmitry@yandex.ru"); // Младше 40 лет
        users[5] = new User("Михаил", "Михайлов", "Михайлович", "1970-06-06", "misha@yandex.ru"); // Старше 40 лет
        users[6] = new User("Александр", "Александров", "Александрович", "1965-07-07", "sasha@yandex.ru"); // Старше 40 лет
        users[7] = new User("Елена", "Иванова", "Дмитриевна", "1988-08-08", "elena@yandex.ru"); // Старше 40 лет
        users[8] = new User("Анна", "Андреевна", "Андреевна", "1998-09-09", "anna@yandex.ru"); // Младше 40 лет
        users[9] = new User("Борис", "Борисов", "Борисович", "1960-10-10", "boris@yandex.ru"); // Старше 40 лет

        for (int i = 0; i < users.length; i++) {
            if (users[i].calculateAge() > 40) {
                users[i].info();

            }
        }
    }
}