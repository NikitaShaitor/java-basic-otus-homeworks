package ru.otus.java.basic;

abstract class Transport {
    abstract boolean canMove(Terrain terrain);

    abstract boolean move(int distance, Terrain terrain);
}
