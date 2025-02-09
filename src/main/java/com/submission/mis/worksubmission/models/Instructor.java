package com.submission.mis.worksubmission.models;

import jakarta.persistence.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//@Entity
//@Table(name = "instructors")
//public class Instructor extends User {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//
//    @Column(nullable = false, unique = true)
//    private String phoneNumber;
//
//    private static final String PHONE_REGEX = "^(\\+2507[238]\\d{7}|07[238]\\d{7})$";
//
//    @ManyToMany(mappedBy = "instructors") // Mirror the many-to-many relation in Course
//    private List<Course> courses;
//
//    public Instructor() {}
//
//    public Instructor(String firstName, String lastName, String email, String password, String phoneNumber, List<Course> courses) {
//        super(firstName, lastName, email, password);
//        setPhoneNumber(phoneNumber);
//        this.courses = courses;
//    }
//    public Instructor(String firstName, String lastName, String email, String password, String phoneNumber) {
//        super(firstName, lastName, email, password);  // Calls the constructor of the parent class (User)
//        setPhoneNumber(phoneNumber);  // Sets the phone number using validation



@Entity
@Table(name="instructors")
public class Instructor extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String phoneNumber;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "course_instructors",
            joinColumns = { @JoinColumn(name = "instructor_id") },
            inverseJoinColumns = { @JoinColumn(name = "course_id") }
    )
    private List<Course> courses;
    public Instructor() {}
    public Instructor(String firstName, String lastName, String email, String password, String phoneNumber) {
        super(firstName, lastName, email, password);
        this.phoneNumber = phoneNumber;
    }
    public Instructor(String firstName, String lastName, String email, String password, String phoneNumber, List<Course> courses) {
                super(firstName, lastName, email, password);
                this.phoneNumber = phoneNumber;
                this.courses = courses;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

        public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}