package Lesson1;

public class Cat implements Jump, Run {
    protected String name;
    protected int maxHeight;
    protected int maxLength;

    protected Cat(String name, int height, int length) {
        this.name = name;
        this.maxHeight = height;
        this.maxLength = length;
    }

    @Override
    public String toString() {
        return "Cat: " + name + ", maxHeight = " + maxHeight + ", maxLength = " + maxLength;
    }


    public void Jump(){
        System.out.println("Котик прыгает");
    }
    public void Run(){
        System.out.println("Котик бегает");
    }

}
