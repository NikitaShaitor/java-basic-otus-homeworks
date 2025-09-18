package ru.otus.java.basic;

public class Car extends Transport{
    int fuel = 100; // Ограниченное количество топлива

    @Override
    boolean canMove(Terrain terrain) {
        return !(terrain.equals(Terrain.FOREST) || terrain.equals(Terrain.SWAMP));
    }

    @Override
    boolean move(int distance, Terrain terrain) {
        if (!canMove(terrain)) {
            System.out.println("Машина не может передвигаться по этому типу местности!");
            return false;
        }

        if (fuel >= distance * 10) { // Расход топлива 10 единиц на километр
            fuel -= distance * 10;
            System.out.println("Машина прошла " + distance + " км по " + terrain + ". Осталось топлива: " + fuel);
            return true;
        } else {
            System.out.println("Недостаточно топлива для путешествия на такое расстояние!");
            return false;
        }
    }
}
