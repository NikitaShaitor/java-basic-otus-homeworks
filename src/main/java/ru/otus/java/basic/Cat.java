package ru.otus.java.basic;

public class Cat {

    private String name;
    private boolean isHungry;
    private int satiety;


    public Cat(String name, boolean isHungry, int satiety) {
        this.name = name;
        this.isHungry = isHungry;
        this.satiety = satiety;
    }

    public Cat() {
    }

    public boolean isHungry() {
        return isHungry;
    }

    public void setHungry(boolean hungry) {
        isHungry = hungry;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSatiety() {
        return satiety;
    }

    public void setSatiety(int satiety) {
        this.satiety = satiety;
    }

    public void info() {
        System.out.println("Кот: " + name + " Сытость: " + satiety + " Голодный: " + isHungry);
    }

    public void eat(Plate plate) {
        if (isHungry && plate.decreaseFood(satiety)) {
            System.out.println("Кот " + name + " поел");
            isHungry = false;
        } else {
            System.out.println("Коту " + name + " не хватил еды");
        }
    }
}
