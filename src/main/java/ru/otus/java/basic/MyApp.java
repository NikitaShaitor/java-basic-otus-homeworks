package ru.otus.java.basic;

public class MyApp {
    public static void main(String[] args) {
        Box<Apple> appleBox = new Box<>();
        Box<Orange> orangeBox = new Box<>();
        Box<Fruit> mixedBox = new Box<>();

        appleBox.addFruit(new Apple());
        appleBox.addFruit(new Apple());
        System.out.println("Вес коробки с яблоками: " + appleBox.calculateWeight());

        orangeBox.addFruit(new Orange());
        orangeBox.addFruit(new Orange());
        System.out.println("Вес коробки с апельсинами: " + orangeBox.calculateWeight());

        mixedBox.addFruit(new Apple());
        mixedBox.addFruit(new Orange());
        System.out.println("Вес смешанной коробки: " + mixedBox.calculateWeight());

        System.out.println(appleBox.compare(mixedBox));


        try {
            appleBox.transferFruits(mixedBox);
            System.out.println("Количество яблок в смешанной коробке теперь: " + mixedBox.countFruits()); // Должно стать больше
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }
}
