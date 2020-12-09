package Lesson1;

public class Treadmill {
    protected int length;

    protected Treadmill(int length){
        this.length = length;
    }

    @Override
    public String toString() {
        return "Беговая дорожка, длина: " + length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Treadmill another = (Treadmill)o;
        return this.length == another.length;
    }

    @Override
    public int hashCode() {
        return length;
    }

}
