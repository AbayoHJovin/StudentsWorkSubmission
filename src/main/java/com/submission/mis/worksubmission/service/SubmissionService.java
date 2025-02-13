package com.submission.mis.worksubmission.service;

import com.submission.mis.worksubmission.models.Submission;
import com.submission.mis.worksubmission.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class SubmissionService {
    private static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private static SubmissionService submissionService;

    public static SubmissionService getInstance() {
        if (submissionService == null) {
            submissionService = new SubmissionService();
        }
        return submissionService;
    }

    private SubmissionService() {}

    public void addSubmission(Submission submission) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(submission);
            session.getTransaction().commit();
        }
    }
    public List<Submission> getSubmissionsByStudent(int studentId) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Submission WHERE student.id = :studentId", Submission.class)
                    .setParameter("studentId", studentId)
                    .list();
        }
    }

    public List<Submission> getSubmissionsByAssignment(int assignmentId) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Submission WHERE assignment.id = :assignmentId", Submission.class)
                    .setParameter("assignmentId", assignmentId)
                    .list();
        }
    }

    public Submission getSubmissionById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Submission.class, id);
        }
    }
}