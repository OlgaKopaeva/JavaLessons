package Lesson6;

public class Main {
    public static final int RUN_DISTANCE = 800;
    public static final int HEIGHT = 10;
    public static final int SWIM_DISTANCE = 15;

    public static void main(String[] args) {
        Cat cat1 = new Cat("Барсик", "Белый", 3);
        Dog dog1 = new Dog("Шарик", "Черный", 5, "Серебристый");

        cat1.printInfo();
        dog1.printInfo();
        System.out.println("Котик прыгает, высота препятствия = " + HEIGHT + "   Котик подпрыгнул на " + cat1.Jump(HEIGHT));
        System.out.println("Котик бежит, дистанция " + RUN_DISTANCE + "   Котик пробежал " + cat1.Run(RUN_DISTANCE));
        System.out.println("Собака бежит, дистанция " + RUN_DISTANCE + "   Собака пробежала " + dog1.Run(RUN_DISTANCE));
        System.out.println("Собака прыгает, высота препятствия = " + HEIGHT + "   Собака подпрыгнула на " + dog1.Jump(HEIGHT));
        System.out.println("Собака плывет, дистанция " + SWIM_DISTANCE + "   Собака проплыла " + dog1.Swim(SWIM_DISTANCE));

    }

}