package ru.otus.java.basic;

public class Person {
    private String name;
    private Transport currentTransport;

    public Person(String name) {
        this.name = name;
    }

    public void sitOnTransport(Transport transport) {
        if (this.currentTransport != null) {
            System.out.println("Вы уже находитесь на другом транспорте");
            return;
        }
        this.currentTransport = transport;
        System.out.println(name + " сел на " + currentTransport);
    }

    public void standOffTransport() {
        if (this.currentTransport == null) {
            System.out.println("Вы уже стоите на земле");
            return;
        }

        System.out.println(name + " слез с " + currentTransport.getClass().getSimpleName());
        this.currentTransport = null;
    }

    public boolean move(int distance, Terrain terrain) {
        if (currentTransport == null) {
            System.out.println(name + " прошёл пешком " + distance + " км по " + terrain);
            return true;
        }
        return currentTransport.move(distance, terrain);
    }
}
