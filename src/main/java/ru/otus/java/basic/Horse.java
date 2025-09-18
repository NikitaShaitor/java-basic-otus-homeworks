package ru.otus.java.basic;

class Horse extends Transport {
    int strength = 100; // Силы лошади

    @Override
    boolean canMove(Terrain terrain) {
        return !terrain.equals(Terrain.SWAMP);
    }

    @Override
    boolean move(int distance, Terrain terrain) {
        if (!canMove(terrain)) {
            System.out.println("Лошадь не может передвигаться по этому типу местности!");
            return false;
        }

        if (strength >= distance * 5) {
            strength -= distance * 5;
            System.out.println("Лошадь прошла " + distance + " км по " + terrain + ". Осталось сил: " + strength);
            return true;
        } else {
            System.out.println("У лошади недостаточно сил для такого пути!");
            return false;
        }
    }
}
