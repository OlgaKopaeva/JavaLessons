package Lesson3;

public class Exercise1 {


    public static void main(String[] args) {
        Array.ArrayToChange<String> MyArray1 = new Array.ArrayToChange<String>("summer", "autumn", "winter");

        MyArray1.sout();

        MyArray1.changeMyArray(0, 1);
        System.out.println("После смены мест элементов массива:");
        MyArray1.sout();



    }


}
