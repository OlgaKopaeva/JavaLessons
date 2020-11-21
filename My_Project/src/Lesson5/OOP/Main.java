package Lesson5.OOP;

public class Main {
    public static void main(String[] args) {
        Employee[] employees = new Employee[5];

        employees[0] = new Employee("Ivan", "Ivanovich", "Ivanov", "manager", "ivanov@mail.ru", "712123434", 120, 30);
        employees[1] = new Employee("Petr", "Petrovich", "Petrov", "director", "petrov@mail.ru", "712123434", 140, 45);
        employees[2] = new Employee("Sergey", "Sergeevich", "Sergeev", "manager", "sergeev@mail.ru", "712534534", 110, 30);
        employees[3] = new Employee("Fedor", "Fedorovich", "Fedorov", "techical director", "fedorov@mail.ru", "714534534", 160, 48);
        employees[4] = new Employee("Nikolay", "Nikolaevich", "Nikolaev", "ofice manager", "nikolaev@mail.ru", "712123434", 120, 30);

        for (Employee empl : employees) {
            if (empl.age >= 40) {
                System.out.println(empl.toString());
            }
        }
    }
}
