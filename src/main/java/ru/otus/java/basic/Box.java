package ru.otus.java.basic;

import java.util.ArrayList;
import java.util.List;

class Box<T extends Fruit> {
    private List<T> fruits = new ArrayList<>();

    public void addFruit(T fruit) {
        fruits.add(fruit);
    }

    public double calculateWeight() {
        return fruits.stream().mapToDouble(Fruit::getWeight).sum();
    }

    public boolean compare(Box<?> anotherBox) {
        return Math.abs(this.calculateWeight() - anotherBox.calculateWeight()) < 0.001;
    }

    @SuppressWarnings("unchecked")
    public void transferFruits(Box<? super T> targetBox) {
        if (!targetBox.getClass().isAssignableFrom(getClass())) {
            throw new IllegalArgumentException("Коробки разных типов!");
        }

        targetBox.fruits.addAll(fruits);
        fruits.clear(); // Очищаем исходную коробку
    }

    public int countFruits() {
        return fruits.size();
    }

}

