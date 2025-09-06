package ru.otus.java.basic.Animal;

public class Cat extends Animal {

    public Cat(String name, int speedRun, int endurance) {
        super(name, speedRun, endurance);
    }

    @Override
    public void swim(int distance){
        System.out.println("Кошки не умеют плавать совсем!");
    }
}
