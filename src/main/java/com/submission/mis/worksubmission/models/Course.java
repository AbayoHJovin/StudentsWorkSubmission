package com.submission.mis.worksubmission.models;

import jakarta.persistence.*;
import java.util.List;

//@Entity
//@Table(name="courses")
//public class Course {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//    private String name;
//    @ManyToMany
//    @JoinTable(
//            name = "course_instructors",
//            joinColumns = @JoinColumn(name = "course_id"),
//            inverseJoinColumns = @JoinColumn(name = "instructor_id")
//    )
//    private List<Instructor> instructors; // Allows multiple instructors per course
//
//    @OneToMany(mappedBy = "course")
//    private List<Assignment> assignments;
//
//    public Course(String name, List<Instructor> instructors) {
//        this.name = name;
//        this.instructors = instructors;
//    }
//
//    public Course() {
//    }
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//    public List<Instructor> getInstructors() {
//        return instructors;
//    }
//
//    public void setInstructors(List<Instructor> instructors) {
//        this.instructors = instructors;
//    }
//
//    public List<Assignment> getAssignments() {
//        return assignments;
//    }
//
//    public void setAssignments(List<Assignment> assignments) {
//        this.assignments = assignments;
//    }
//}



@Entity
@Table(name="courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @ManyToMany(mappedBy = "courses")
    private List<Instructor> instructors; // Allows multiple instructors per course

    @OneToMany(mappedBy = "course")
    private List<Assignment> assignments;

    public Course() {}

    public Course(String name) {
        this.name = name;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Instructor> getInstructors() {
        return instructors;
    }

    public void setInstructors(List<Instructor> instructors) {
        this.instructors = instructors;
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<Assignment> assignments) {
        this.assignments = assignments;
    }
}