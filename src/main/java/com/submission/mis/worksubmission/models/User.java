package com.submission.mis.worksubmission.models;

import org.mindrot.jbcrypt.BCrypt; // Add BCrypt for hashing
import jakarta.persistence.*;

@MappedSuperclass
public class User {
    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    // Default constructor
    public User() {}

    // Constructor to initialize firstName, lastName, and email
    public User(String firstName, String lastName, String email) {
        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new IllegalArgumentException("Invalid email format");
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    // Constructor to initialize all fields (with password hashing)
    public User(String firstName, String lastName, String email, String password) {
        this(firstName, lastName, email);  // Call the other constructor for validation
        setPassword(password); // Hash password
    }

    // Getters and Setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new IllegalArgumentException("Invalid email format");
        }
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }
}
