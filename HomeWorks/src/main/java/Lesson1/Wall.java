package Lesson1;

public class Wall {
    protected int height;

    protected Wall(int height){
        this.height = height;
    }

    @Override
    public String toString() {
        return "Стена, высота: " + height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Wall another = (Wall) o;
        return this.height == another.height;
    }

    @Override
    public int hashCode() {
        return height;
    }

}
