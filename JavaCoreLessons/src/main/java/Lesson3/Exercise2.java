package Lesson3;

public class Exercise2 {
    public static Box.BoxWithFruits<Orange> box1 = new Box.BoxWithFruits<>();
    public static Box.BoxWithFruits<Orange> box3 = new Box.BoxWithFruits<>();
    public static Box.BoxWithFruits<Apple> box2 = new Box.BoxWithFruits<>();

    public static void main(String[] args) {
        Orange orange = new Orange();
        for (int i = 0; i < 4; i++) {
            box1.addFruit(orange);
        }
        Apple apple = new Apple();
        for (int i = 0; i < 6; i++) {
            box2.addFruit(apple);
        }

        System.out.println("Вес коробки №1 с апельсинами = " + box1.getTotalWeight());
        System.out.println("Всего апельсинов в коробке №1: " + box1.getQuantity());
        System.out.println("Вес коробки №2 с яблоками = " + box2.getTotalWeight());
        System.out.println("Всего яблок в коробке №2: " + box2.getQuantity());

        System.out.println("Всего апельсинов в 3-й коробке: " + box3.getQuantity());
        System.out.println("Вес 3-й коробки с апельсинами = " + box3.getTotalWeight());

        System.out.println("Коробки №1 и №2 равны? - " + box1.compare(box2));

        box3.addBox(box1);
        System.out.println("Коробку №1 пересыпали в коробку №3. После пересыпки:");

        System.out.println("Всего апельсинов в 3-й коробке: " + box3.getQuantity());
        System.out.println("Вес 3-й коробки с апельсинами = " + box3.getTotalWeight());
        System.out.println("Вес коробки №1 с апельсинами = " + box1.getTotalWeight());
        System.out.println("Всего апельсинов в коробке №1: " + box1.getQuantity());

    }
}
