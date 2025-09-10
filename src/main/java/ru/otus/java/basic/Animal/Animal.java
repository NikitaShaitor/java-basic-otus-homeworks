package ru.otus.java.basic.Animal;

public class Animal {

    protected String name;
    private double speedRun; // скорость
    protected int endurance; // выносливость

    public Animal(String name, double speedRun, int endurance) {

        this.name = name;
        this.speedRun = speedRun;
        this.endurance = endurance;
    }

    public void info(){

        System.out.println("Имя: " + name + "\nВыносливость: " + endurance);

    }

    protected void reduceEndurance(int distance){

        endurance -= distance;

    }

    protected void returnTime(double time){

        if (time >= 0 ){

            System.out.println(name + " преодолел расстояние за  " + time);

        } else {

            System.out.println(name + " недостаточно выносливости");

        }

    }

    public void run(int distance){

        if (distance > endurance){

            System.out.println(name + " не сможет пробежать мало выносливости");

        } else {

            double time = (double) distance / speedRun;
            reduceEndurance(distance);
            returnTime(time);

        }
    }

    public void swim(int distance){

        System.out.println(name + " не умеет плавать");
        returnTime(-1);

    }
}
