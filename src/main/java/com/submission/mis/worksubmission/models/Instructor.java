package com.submission.mis.worksubmission.models;

import jakarta.persistence.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
@Table(name = "instructors")
public class Instructor extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    private static final String PHONE_REGEX = "^(\\+2507[238]\\d{7}|07[238]\\d{7})$";

    @ManyToMany(mappedBy = "instructors") // Mirror the many-to-many relation in Course
    private List<Course> courses;

    public Instructor() {}

    public Instructor(String firstName, String lastName, String email, String password, String phoneNumber, List<Course> courses) {
        super(firstName, lastName, email, password);
        setPhoneNumber(phoneNumber);
        this.courses = courses;
    }
    public Instructor(String firstName, String lastName, String email, String password, String phoneNumber) {
        super(firstName, lastName, email, password);  // Calls the constructor of the parent class (User)
        setPhoneNumber(phoneNumber);  // Sets the phone number using validation
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
        if (phoneNumber != null && !phoneNumber.isEmpty()) {
            Pattern pattern = Pattern.compile(PHONE_REGEX);
            Matcher matcher = pattern.matcher(phoneNumber);
            if (!matcher.matches()) {
                throw new IllegalArgumentException("Invalid phone number format. Please enter a valid Rwanda phone number.");
            }
        }
        this.phoneNumber = phoneNumber;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
