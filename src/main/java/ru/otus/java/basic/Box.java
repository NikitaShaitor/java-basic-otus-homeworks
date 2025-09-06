package ru.otus.java.basic;

public class Box {
    private int length;
    private int width;
    private int height;
    private String color;
    private boolean open;
    private String item;

    public Box(int length, int width, int height, String color) {
        this.length = length;
        this.width = width;
        this.height = height;
        this.color = color;
        this.open = false;
        this.item = null;
    }

    /**
     * @apiNote Метод для открывания коробки
     */

    public void open() {
        if (!open) {
            System.out.println("Коробка была закрыта и её открыли");
            open = true;
        } else {
            System.out.println("Коробка уже открыта");
        }
    }

    /**
     * @apiNote Метод для закрывания коробки
     */

    public void close() {
        if (open) {
            System.out.println("Коробка была открыта и её закрыли");
            open = false;
        } else {
            System.out.println("Коробка уже закрыта");
        }
    }

    /**
     * @apiNote Метод для перекрашивания коробки
     */

    public void paint(String newColor) {
        color = newColor;
        System.out.println("Коробка была перекрашена в " + newColor);
    }

    /**
     * @apiNote Метод для помещения предмета в коробку
     */

    public void putItem(String item) {
        if (open && this.item == null) {
            this.item = item;
            System.out.println("В коробку положили предмет: " + item);
        } else if (!open) {
            System.out.println("Сначала открой коробку");
        } else {
            System.out.println("В коробке уже находится предмет");
        }
    }

    /**
     * @apiNote Метод для взятия предмета из коробки
     */

    public void getItem() {
        if (open && this.item != null) {
            System.out.println("Из коробки достали: " + item);
            this.item = null;
        } else if (!open) {
            System.out.println("Сначала открой коробку");
        } else {
            System.out.println("В коробке ничего нет");
        }
    }

    /**
     * @apiNote Метод для показа инфо коробки
     */

    public void showInfo() {
        System.out.println("Размеры\nШирина: " + width + "\nВысота: " + height + "\nДлина: " + length);
        System.out.println("Цвет: " + color);
        if (open) {
            System.out.println("Состояние: открыта");
        } else {
            System.out.println("Состояние: закрыта");
        }
        if (item == null) {
            System.out.println("Содержимое: пусто");
        } else {
            System.out.println("Содержимое: " + item);
        }
    }
}
