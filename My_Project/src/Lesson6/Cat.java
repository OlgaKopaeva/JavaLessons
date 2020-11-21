package Lesson6;

public class Cat extends Animal {
    public static final int MAX_HEIGHT = 2;
    public static final int MAX_DISTANCE = 200;


    public Cat(String name, String color, int age) {
        super(name, color, age);
    }

    @Override
    public double Jump(int height) {
        if (height >= MAX_HEIGHT) {
            return MAX_HEIGHT;
        }
        return height;
    }

    @Override
    public int Run(int runDistance) {
        if (runDistance >= MAX_DISTANCE) {
            return MAX_DISTANCE;
        }
        return runDistance;
    }

}
