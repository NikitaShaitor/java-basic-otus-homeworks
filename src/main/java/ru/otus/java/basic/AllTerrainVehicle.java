package ru.otus.java.basic;

class AllTerrainVehicle extends Transport {
    int fuel = 100;

    @Override
    boolean canMove(Terrain terrain) {
        return true;
    }

    @Override
    boolean move(int distance, Terrain terrain) {
        if (fuel >= distance * 15) {
            fuel -= distance * 15;
            System.out.println("Вездеход прошел " + distance + " км по " + terrain + ". Осталось топлива: " + fuel);
            return true;
        } else {
            System.out.println("Недостаточно топлива для путешествия на такое расстояние!");
            return false;
        }
    }
}