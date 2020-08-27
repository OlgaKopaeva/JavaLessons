package Lesson1;

public class Main {

    public static void main(String[] args) {
//        Task 2
        byte numberByte = -127;
        short numberShort = 16_384;
        int numberInt = -1_073_741_823;
        long numberLong = 132_342_434_343L;

        float numberFloat = 897.0f;
        double numberDouble = -676_676_434_565.0;

        char symbolExample = 'y';
        boolean ifSymbolExampleIsY = true;

        System.out.println(mathTask(1, 2, 4.0, 2.0));
        System.out.println(ifSumInterval(2.6, 8.5));
        checkNumberSign(0);
        System.out.println("Число отрицательное? - " + checkNumberIsNegative(-55));
        helloName("Даша");
        checkLeapYear(2000);

    }

    public static double mathTask(int a, int b, double c, double d) {
//        Task 3
        return a * (b + (c / d));
    }

    public static boolean ifSumInterval(double a, double b) {
//        Task 4
        return (a + b) <= 20 && (a + b) >= 10;
    }

    public static void checkNumberSign(int n) {
//            Task 5
        if (n >= 0) {
            System.out.println(n + " - Число положительное.");
        } else {
            System.out.println(n + " - Число отрицательное.");
        }
    }

    public static boolean checkNumberIsNegative(int m) {
//        Task 6
        return m < 0;
    }

    public static void helloName(String str) {
//        Task 7
        System.out.println("Привет, " + str + '!');
    }

    public static void checkLeapYear(int year) {
//        Task 8

        if (year % 400 == 0 || year % 4 == 0 && year % 100 != 0) {
            System.out.println(year + " Год високосный!");
        } else {
            System.out.println(year + " Год невисокосный!");
        }
    }
}