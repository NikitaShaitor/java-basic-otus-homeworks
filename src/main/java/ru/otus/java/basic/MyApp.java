package ru.otus.java.basic;

import java.util.ArrayList;
import java.util.List;

public class MyApp {

    public static void main(String[] args) {
        System.out.println("Генерация диапазона:");
        ArrayList<Integer> range = generateRange(1, 5);
        System.out.println(range);

        System.out.println("\nСумма элементов больше 5:");
        ArrayList<Integer> numbers = new ArrayList<>(List.of(1, 2, 8, 10));
        int totalSum = sumGreaterThanFive(numbers);
        System.out.println(totalSum);

        System.out.println("\nЗаполняем весь список одинаковым значением:");
        ArrayList<Integer> myList = new ArrayList<>(List.of(1, 2, 3));
        fillAllCells(10, myList);
        System.out.println(myList);

        System.out.println("\nПрибавляем значение ко всем элементам списка:");
        ArrayList<Integer> values = new ArrayList<>(List.of(1, 2, 3));
        incrementEachElement(5, values);
        System.out.println(values);

        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Иван Иванов", 30));
        employees.add(new Employee("Марина Петрова", 25));
        employees.add(new Employee("Сергей Кузнецов", 40));


        System.out.println(getNames(employees));
        System.out.println(filterEmployeesByMinAge(employees, 30));
        System.out.println(checkAverageAge(employees, 30));
        System.out.println(findYoungestEmployee(employees).getName());
    }

    /**
     * Метод 1: генерирую диапозон целых чисел
     */

    public static ArrayList<Integer> generateRange(int min, int max) {
        ArrayList<Integer> result = new ArrayList<>();

        for (int i = min; i <= max; i++) {
            result.add(i);
        }

        return result;
    }

    /**
     * Метод 2: суммирую элементы списка значение которых больше 5
     */

    public static int sumGreaterThanFive(List<Integer> list) {
        int sum = 0;

        for (Integer num : list) {
            if (num > 5) {
                sum += num;
            }
        }

        return sum;
    }

    /**
     * Метод 3: заменяю все элементы списка указанным числом
     */

    public static void fillAllCells(int value, List<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            list.set(i, value);
        }
    }

    /**
     * Метод 4: увеличивает каждый элемент списка на указанное число
     */

    public static void incrementEachElement(int incrementBy, List<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            list.set(i, list.get(i) + incrementBy);
        }
    }

    public static List<String> getNames(List<Employee> employees) {
        List<String> names = new ArrayList<>();
        for (Employee employee : employees) {
            names.add(employee.getName());
        }
        return names;
    }

    public static List<Employee> filterEmployeesByMinAge(List<Employee> employees, int minAge) {
        List<Employee> filteredEmployees = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getAge() >= minAge) {
                filteredEmployees.add(employee);
            }
        }
        return filteredEmployees;
    }

    public static boolean checkAverageAge(List<Employee> employees, double minAvgAge) {
        if (employees.isEmpty()) {
            throw new IllegalArgumentException("Список сотрудников пуст");
        }

        int totalAge = 0;
        for (Employee employee : employees) {
            totalAge += employee.getAge();
        }

        double averageAge = (double) totalAge / employees.size();
        return averageAge >= minAvgAge;
    }

    public static Employee findYoungestEmployee(List<Employee> employees) {
        if (employees.isEmpty()) {
            throw new IllegalArgumentException("Список сотрудников пуст");
        }

        Employee youngest = employees.get(0);
        for (Employee employee : employees) {
            if (employee.getAge() < youngest.getAge()) {
                youngest = employee;
            }
        }
        return youngest;
    }
}
