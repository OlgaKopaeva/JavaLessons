package Lesson1;

public class Human implements Jump, Run {
    protected String name;
    protected int maxHeight;
    protected int maxLength;

    protected Human(String name, int height, int length) {
        this.name = name;
        this.maxHeight = height;
        this.maxLength = length;
    }

    @Override
    public String toString() {
        return "Человек: " + name + ", maxHeight = " + maxHeight + ", maxLength = " + maxLength;
    }


        public void Jump(){
            System.out.println("Человек прыгает");
        }
        public void Run(){
            System.out.println("Человек бегает");
        }


    }
