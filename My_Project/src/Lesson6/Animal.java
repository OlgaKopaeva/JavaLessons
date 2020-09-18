package Lesson6;

public abstract class Animal implements Jump, Run, Swim {
    protected String name;
    protected String color;
    protected int age;

    public Animal(String name, String color, int age) {
        this.name = name;
        this.color = color;
        this.age = age;
    }

    public Animal() {
    }

    public void printInfo(){
        System.out.printf("Animal name = %s, color = %s, age = %d %n", name, color, age);
    }

    public double Jump(int height) {
        return 0;
    };
    public int Run(int runDistance) {
        return 0;
    };
    public int Swim(int swimDistance) {
        return 0;
    };
}
