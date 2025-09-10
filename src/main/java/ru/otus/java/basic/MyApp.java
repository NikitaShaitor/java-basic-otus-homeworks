package ru.otus.java.basic;

public class MyApp {
    public static void main(String[] args) {
        Cat smoke = new Cat("Смоки", true, 60);
        Cat mouse = new Cat("Мышик", true, 40);
        Cat [] cats = {smoke,mouse};
        Plate plate = new Plate(100);
//
//        smoke.info();
//        mouse.info();
//        plate.info();
//        plate.addFood(60);
//
//        for (int i = 0; i < cats.length; i++) {
//            cats[i].eat(plate);
//        }
//
//        smoke.info();
//        mouse.info();
//
//        plate.addFood(50);
//        plate.info();
//        plate.addFood(40);
//        plate.info();

        plate.decreaseFood(100);
        plate.info();
        plate.decreaseFood(10);
        plate.info();


    }

}
