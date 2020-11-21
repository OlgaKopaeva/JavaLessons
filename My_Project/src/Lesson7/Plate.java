package lesson7.oop;

public class Plate {

    private int food;

    public Plate(int food) {
        this.food = food;
    }

    public Plate() {
    }

    public int getFood() {
        return food;
    }

    public void addFood(int foodCount) {
        this.food += foodCount;
    }

    public void decreaseFood(int foodCount) {
        this.food -= foodCount;

    }

    public void info() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Plate{" +
                "food=" + food +
                '}';
    }
}
