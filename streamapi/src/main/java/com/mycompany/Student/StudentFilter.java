/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Student;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StudentFilter {

    public static void main(String[] args) {
        // Sample list of students
        List<Student> students = Arrays.asList(
                new Student("Alice", "Ahmedabad", 20, 160.5, 55.0, "FEMALE"),
                new Student("Bob", "Ahmedabad", 19, 170.0, 68.0, "MALE"),
                new Student("Charlie", "Surat", 22, 175.0, 70.0, "MALE"),
                new Student("Daisy", "Ahmedabad", 21, 162.5, 60.0, "FEMALE"),
                new Student("Eve", "Vadodara", 18, 158.0, 50.0, "FEMALE"),
                new Student("Frank", "Ahmedabad", 18, 165.0, 72.0, "MALE")
        );

        // Scenario 1: FEMALE students from Ahmedabad
        List<Student> femaleAhmedabadStudents = students.stream()
                .filter(student -> student.studentGender.equals("FEMALE") && student.studentCity.equals("Ahmedabad"))
                .collect(Collectors.toList());

        System.out.println("FEMALE students from Ahmedabad:");
        femaleAhmedabadStudents.forEach(System.out::println);

        // Scenario 2: MALE students with weight between 65.0 to 75.0
        List<Student> maleStudentsWeightRange = students.stream()
                .filter(student -> student.studentGender.equals("MALE")
                && student.studentWeight >= 65.0
                && student.studentWeight <= 75.0)
                .collect(Collectors.toList());

        System.out.println("\nMALE students with weight between 65.0 to 75.0:");
        maleStudentsWeightRange.forEach(System.out::println);

        // Scenario 3: Students who have crossed age 18 and are MALE or FEMALE
        List<Student> studentsAbove18 = students.stream()
                .filter(student -> student.studentAge > 18)
                .collect(Collectors.toList());

        System.out.println("\nStudents who have crossed age 18:");
        studentsAbove18.forEach(System.out::println);
    }
}
