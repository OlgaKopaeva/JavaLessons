package Lesson6;

public class Dog extends Animal {
    protected String collar;
    public static final double MAX_HEIGHT_DOG = 0.5;
    public static final int MAX_DISTANCE_DOG = 500;
    public static final int MAX_DISTANCE_SWIM_DOG = 10;


    public Dog(String name, String color, int age, String collar) {
        this.name = name;
        this.color = color;
        this.age = age;
        this.collar = collar;
    }
    @Override
    public double Jump(int height) {

        if (height >= MAX_HEIGHT_DOG) {
            return MAX_HEIGHT_DOG;
        }
        return height;
    }

    @Override
    public int Run(int runDistance) {
        if (runDistance >= MAX_DISTANCE_DOG) {
            return MAX_DISTANCE_DOG;
        }
        return runDistance;
    }

    @Override
    public int Swim(int swimDistance) {
        if (swimDistance >= MAX_DISTANCE_SWIM_DOG) {
            return MAX_DISTANCE_SWIM_DOG;
        }
        return swimDistance;
    }
}
