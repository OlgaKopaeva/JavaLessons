package Lesson2;

import java.util.Arrays;

import static java.lang.Math.abs;

public class Main {

    private static final int ARRAY_SIZE = 8;

    public static void main(String[] args) {
        // Task1
        int[] dataTask1 = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        for (int i = 0; i < dataTask1.length; i++) {
            if (dataTask1[i] == 0) dataTask1[i] = 1;
            else dataTask1[i] = 0;
        }
        System.out.println(Arrays.toString(dataTask1));

        // Task2
        int[] dataTask2 = new int[ARRAY_SIZE];
        for (int i = 0; i < ARRAY_SIZE; i++) {
            dataTask2[i] = i * 3;
        }
        System.out.println(Arrays.toString(dataTask2));

        // Task3
        int[] dataTask3 = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        for (int i = 0; i < dataTask3.length; i++) {
            if (dataTask3[i] < 6) dataTask3[i] *= 2;
        }
        System.out.println(Arrays.toString(dataTask3));

        //Task4
        int[][] dataTask4 = new int[ARRAY_SIZE][ARRAY_SIZE];
        for (int i = 0; i < ARRAY_SIZE; i++) {
            for (int j = 0; j < ARRAY_SIZE; j++) {
                if (j == i || i + j == ARRAY_SIZE - 1) dataTask4[i][j] = 1;
            }
        }
        for (int[] ints : dataTask4) {
            for (int anInt : ints) {
                System.out.print(anInt);
            }
            System.out.println();
        }

        //Task5
        int[] dataTask5 = new int[ARRAY_SIZE];
        for (int i = 0; i < ARRAY_SIZE; i++) {
            dataTask5[i] = ARRAY_SIZE - i;
        }
        System.out.println(Arrays.toString(dataTask5));
        int minElement = dataTask5[1];
        int minOrderNumber = 0;
        int maxElement = dataTask5[1];
        int maxOrderNumber = 0;
        for (int i = 0; i < ARRAY_SIZE; i++) {
            if (dataTask5[i] < minElement) {
                minElement = dataTask5[i];
                minOrderNumber = i + 1;
            }
            if (dataTask5[i] > maxElement) {
                maxElement = dataTask5[i];
                maxOrderNumber = i + 1;
            }
        }
        System.out.println("max Element = " + maxElement + " " + "Order number = " + maxOrderNumber);
        System.out.println("min Element = " + minElement + " " + "Order number = " + minOrderNumber);

        //Task6
        int[] myArray = {3, 5, 6, 3, 3, 8};
        System.out.println("If Array is like mirror?  -  " + ifMirrorArray(myArray));

        //Task7
        int[] arrayToChange = {1, 2, 3, 4, 5};
        arrayChangedOrder(arrayToChange, 12);
        System.out.println(Arrays.toString(arrayToChange));

    }

    public static boolean ifMirrorArray(int[] myArray) {
        //Task6
        System.out.println(Arrays.toString(myArray));
        int sumPart1Array = 0;
        int sumPart2Array = 0;
        for (int i = 0; i < myArray.length; i++) {
            sumPart1Array += myArray[i];
            for (int j = i + 1; j < myArray.length; j++) {
                sumPart2Array += myArray[j];
            }
            if (sumPart1Array == sumPart2Array) {
                System.out.println(sumPart1Array);
                return true;
            }
            sumPart2Array = 0;
        }
        return false;
    }

    public static int[] arrayChangedOrder(int[] arrayToChange, int n) {
        if (n > 0) {
            for (int i = 1; i <= n; i++) {
                //сдвиг массива вправо
                int lastElement = arrayToChange[arrayToChange.length - 1];
                for (int j = arrayToChange.length - 1; j > 0 ; j--) {
                    arrayToChange[j] = arrayToChange[j - 1];
                }
                arrayToChange[0] = lastElement;
            }
        } else {
            //сдвиг массива влево
            for (int i = 1; i <= abs(n); i++) {
                int firstElement = arrayToChange[0];
                for (int j = 0; j < arrayToChange.length - 1; j++) {
                    arrayToChange[j] = arrayToChange[j + 1];
                }
                arrayToChange[arrayToChange.length - 1] = firstElement;
            }
        }
        return arrayToChange;
    }
}

