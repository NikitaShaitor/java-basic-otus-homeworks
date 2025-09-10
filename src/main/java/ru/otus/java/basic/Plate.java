package ru.otus.java.basic;

public class Plate {

    private int currentFood;
    private int maxFood;

    public Plate(int maxFood) {
        this.currentFood = maxFood;
        this.maxFood = maxFood;
    }

    public int getMaxFood() {
        return maxFood;
    }

    public void setMaxFood(int maxFood) {
        this.maxFood = maxFood;
    }

    public int getCurrentFood() {
        return currentFood;
    }

    public void setCurrentFood(int currentFood) {
        this.currentFood = currentFood;
    }

    public void info() {
        System.out.println("Сейчас в тарелке: " + currentFood + " еды");
    }

    public boolean decreaseFood(int amount) {
        if (currentFood - amount >= 0) {
            currentFood -= amount;
            return true;
        } else {
            return false;
        }
    }

    public void addFood(int quantity) {
        if (quantity + currentFood > maxFood) {
            System.out.println("В тарелку столько еды не влезет");
        } else {
            System.out.println("В тарелку добавили: " + quantity + " еды");
            currentFood += quantity;
        }
    }
}
