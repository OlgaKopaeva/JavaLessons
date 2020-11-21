package lesson7.oop;

public class Test {


    public static void main(String[] args) {
        Cat cat = new Cat("Мартин", 5);
        Plate plate = new Plate(5);


        plate.info();
        cat.eat(plate);

        plate.info();
        plate.addFood(7);
        plate.info();
        cat.eat(plate);
        plate.info();

    }

}
