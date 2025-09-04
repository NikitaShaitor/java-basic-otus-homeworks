package ru.otus.java.basic;

public class User {
    public String firstName;
    public String lastName;
    public String middleName;
    public String yearBirth;
    public String email;

    public User(String firstName, String lastName, String middleName, String yearBirth, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.yearBirth = yearBirth;
        this.email = email;
    }

    public void info() {
        System.out.println("ФИО: " + lastName + " " + firstName + " " + lastName);
        System.out.println("Год рождения: " + yearBirth);
        System.out.println("E-mail: " + email + "\n");
    }
}
