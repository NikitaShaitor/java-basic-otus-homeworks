package ru.otus.java.basic.Animal;

public class Dog extends Animal{

    final static int COST_SWIM = 2;

    public Dog (String name, int speedRun, int endurance) {
        super(name, speedRun, endurance);
    }

    @Override
    public void swim(int distance){
        if ((COST_SWIM * distance) > endurance) {
            System.out.println(name + " не сможет проплыть, мало выносливости");
        } else {
            double time = (double) distance / endurance;
            reduceEndurance(COST_SWIM * distance);
            returnTime(time);
        }
    }
}
