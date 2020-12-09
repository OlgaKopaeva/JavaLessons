package Lesson3;

public class Box {
    public static class BoxWithFruits<T extends Fruit> {
        private int quantity = 0;
        private double weight = 0.0;

        public int getQuantity() {
            return this.quantity;
        }

        public double getTotalWeight() {
            return this.weight;
        }

        public int addFruit(T fruit) {
            if (fruit != null)
            {
                this.quantity++;
                this.weight += fruit.getWeight();
            }
            return quantity;
        }

        public int addBox(BoxWithFruits<T> box) {
                this.quantity += box.getQuantity();
                this.weight += box.getTotalWeight();
                box.clearBox();
            return quantity;
        }

        public void clearBox(){
            this.quantity = 0;
            this.weight = 0.0;
        }

        public boolean compare(BoxWithFruits<?> box){
            return this.getTotalWeight() == box.getTotalWeight();
        }
    }
}
