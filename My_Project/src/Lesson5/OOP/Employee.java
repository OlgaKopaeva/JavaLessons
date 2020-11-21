package Lesson5.OOP;

public class Employee {
    String name;
    String secondName;
    String surName;
    String position;
    String email;
    String phoneNumber;
    int salary;
    int age;

    public Employee(String name, String secondName, String surName, String position, String email, String phoneNumber, int salary, int age) {
        this.name = name;
        this.secondName = secondName;
        this.surName = surName;
        this.position = position;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
        this.age = age;
    }


    public Employee(String name){
        this.name = name;
    }

    public Employee() {
        this(null);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", secondName='" + secondName + '\'' +
                ", surName='" + surName + '\'' +
                ", position='" + position + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", salary=" + salary +
                ", age=" + age +
                '}';
    }
}
