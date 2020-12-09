package Lesson2;

public class MyArrayDataException extends RuntimeException{
        protected int i;
        protected int j;

        public MyArrayDataException(int a, int b) {
            i = a;
            j = b;
        }
    }

