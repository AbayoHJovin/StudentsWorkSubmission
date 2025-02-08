package com.submission.mis.worksubmission.models;

import com.submission.mis.worksubmission.models.Instructor;
import com.submission.mis.worksubmission.models.Submission;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;  // The title of the assignment
    private String description;  // Description of the assignment
    private LocalDate deadline;  // The deadline for the assignment
    private String assignmentLink;  // Link to the assignment document or resource

    @ManyToOne
    private Instructor instructor;  // The instructor who created the assignment

    @ManyToOne
    private Course course;  // The course to which the assignment belongs

    @OneToMany(mappedBy = "assignment")
    private List<Submission> submissions;  // List of submissions for this assignment

    // Constructors
    public Assignment() {}

    public Assignment(String title, String description, LocalDate deadline, String assignmentLink, Instructor instructor, Course course) {
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.assignmentLink = assignmentLink;
        this.instructor = instructor;
        this.course = course;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public String getAssignmentLink() {
        return assignmentLink;
    }

    public void setAssignmentLink(String assignmentLink) {
        this.assignmentLink = assignmentLink;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<Submission> getSubmissions() {
        return submissions;
    }

    public void setSubmissions(List<Submission> submissions) {
        this.submissions = submissions;
    }
}
