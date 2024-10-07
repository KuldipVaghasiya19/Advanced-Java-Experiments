/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.Student;

class Student {
    String studentName;
    String studentCity;
    int studentAge;
    double studentHeight;
    double studentWeight;
    String studentGender;

    public Student(String studentName, String studentCity, int studentAge, double studentHeight, double studentWeight, String studentGender) {
        this.studentName = studentName;
        this.studentCity = studentCity;
        this.studentAge = studentAge;
        this.studentHeight = studentHeight;
        this.studentWeight = studentWeight;
        this.studentGender = studentGender;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentName='" + studentName + '\'' +
                ", studentCity='" + studentCity + '\'' +
                ", studentAge=" + studentAge +
                ", studentHeight=" + studentHeight +
                ", studentWeight=" + studentWeight +
                ", studentGender='" + studentGender + '\'' +
                '}';
    }

    // Getters for each attribute (not shown for brevity)
}
