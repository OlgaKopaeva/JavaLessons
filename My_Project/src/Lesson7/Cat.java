package lesson7.oop;

public class Cat {

    protected final String name;
    private int appetite;
    private boolean satiety = false;


    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
    }

    public String getName() {
        return name;
    }

    public int getAppetite() {
        return appetite;
    }

    public void eat(Plate plate) {
        System.out.println("Пушистый ест");
        if (plate.getFood() >= appetite) {
            satiety = true;
            doEat(plate);
        }
    }

    private void doEat(Plate plate) {
        plate.decreaseFood(appetite);
    }
}
