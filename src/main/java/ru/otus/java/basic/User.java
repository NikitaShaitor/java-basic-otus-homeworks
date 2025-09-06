package ru.otus.java.basic;
import java.time.LocalDate;
import java.time.Period;

public class User {
    private String firstName;
    private String lastName;
    private String middleName;
    private LocalDate yearBirth;
    private String email;

    public User(String firstName, String lastName, String middleName, String yearBirth, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.yearBirth = LocalDate.parse(yearBirth);
        this.email = email;
    }

    public int calculateAge() {
        return Period.between(yearBirth, LocalDate.now()).getYears(); // Используем библиотеку Java Time API
    }

    public void info() {
        System.out.println("ФИО: " + lastName + " " + firstName + " " + middleName);
        System.out.println("Возраст: " + calculateAge());
        System.out.println("E-mail: " + email + "\n");
    }
}
