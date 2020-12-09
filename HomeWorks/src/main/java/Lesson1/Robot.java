package Lesson1;

public class Robot implements Jump, Run {
    protected String name;
    protected int maxHeight;
    protected int maxLength;

    protected Robot(String name, int height, int length) {
        this.name = name;
        this.maxHeight = height;
        this.maxLength = length;
    }

    @Override
    public String toString() {
        return "Robot: " + name + ", maxHeight = " + maxHeight + ", maxLength = " + maxLength;
    }

    public void Jump(){
        System.out.println("Робот прыгает");
    }
    public void Run(){
        System.out.println("Робот бегает");
    }
}
