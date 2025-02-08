package com.submission.mis.worksubmission.models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "students")
public class Student extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Column(nullable = false)
    private int age;
    public Student() {}
    public Student(int id, String firstName, String lastName, String email, String password, LocalDate birthDate,int age) {
        super(firstName, lastName, email, password);
        this.id = id;
        this.birthDate = birthDate;
        this.age = age;
    }
    public Student(String firstName, String lastName, String email, String password, LocalDate birthDate,int age) {
        super(firstName, lastName, email, password);
        this.birthDate = birthDate;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
