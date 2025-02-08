package com.submission.mis.worksubmission.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Submission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String fileName;  // The name of the file submitted
    private String filePath;  // The path where the file is stored in the system
    private LocalDateTime submittedAt;  // The submission timestamp

    @ManyToOne
    private Student student;  // Reference to the student who submitted the work

    @ManyToOne
    private Assignment assignment;  // Reference to the assignment to which the submission belongs

    // Constructors
    public Submission() {}

    public Submission(String fileName, String filePath, LocalDateTime submittedAt, Student student, Assignment assignment) {
        this.fileName = fileName;
        this.filePath = filePath;
        this.submittedAt = submittedAt;
        this.student = student;
        this.assignment = assignment;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(LocalDateTime submittedAt) {
        this.submittedAt = submittedAt;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }
}
