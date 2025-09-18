package ru.otus.java.basic;

class Bike extends Transport {
    @Override
    boolean canMove(Terrain terrain) {
        return !terrain.equals(Terrain.SWAMP);
    }

    @Override
    boolean move(int distance, Terrain terrain) {
        if (!canMove(terrain)) {
            System.out.println("Велосипед не может передвигаться по этому типу местности!");
            return false;
        }

        System.out.println("Проехал на велосипеде " + distance + " км по " + terrain);
        return true;
    }
}
