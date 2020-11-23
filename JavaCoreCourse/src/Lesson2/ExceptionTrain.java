package Lesson2;

public class ExceptionTrain {
    public static final int MAXSIZE_I = 4;
    public static final int MAXSIZE_J = 4;
    public static final int SIZE_I = 6;
    public static final int SIZE_J = 4;

    public static String[][] MyArray = new String[SIZE_I][SIZE_J];

    public static String[][] createMyArray() {
        for (int i = 0; i < SIZE_I; i++) {
            for (int j = 0; j < SIZE_J; j++) {
                MyArray[i][j] = "2";
            }
        }
        MyArray[1][1] = "Море";
        return MyArray;
    }

    public static boolean checkSizeArray(String[][] array) {
        if (array != null) {
            int rows = array.length;
            int columns = array[0].length;
            if (rows > MAXSIZE_I || columns > MAXSIZE_J) {
                throw new MyArraySizeException();
            } else return true;
        } else return false;
    }

    public static int sumMyArray(String[][] MyArray) {
        int sum = 0;
        try {
            for (int i = 0; i < MAXSIZE_I; i++) {
                for (int j = 0; j < MAXSIZE_J; j++) {
                    try {
                        sum += Integer.valueOf(MyArray[i][j]);
                    } catch (NumberFormatException e) {
                        throw new MyArrayDataException(i, j);
                    }
                }
            }
        } catch (MyArrayDataException ex) {
            System.out.println("Неверный тип данных. Элемент массива: i = " + ex.i + " j = " + ex.j);
        }
        return sum;
    }

    public static void main(String[] args) {
        createMyArray();
        try {
            checkSizeArray(MyArray);
        } catch (MyArraySizeException e) {
            System.out.println("Неверный размер массива.");
        }
        System.out.println("Сумма массива = " + sumMyArray(MyArray));
    }
}
