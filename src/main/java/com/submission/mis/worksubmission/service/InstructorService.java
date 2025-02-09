package com.submission.mis.worksubmission.service;

import com.submission.mis.worksubmission.models.Instructor;
import com.submission.mis.worksubmission.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class InstructorService {

    protected static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    protected static Session session;
    protected static InstructorService instructorService;

    public static InstructorService getInstructorService() {
        if (instructorService == null) {
            instructorService = new InstructorService();
            return instructorService;
        }
        return instructorService;
    }
    private InstructorService() {}
    public boolean isInstructorEmailExists(String email) {
        try (Session session = sessionFactory.openSession()) {
            Instructor instructor = session.createQuery("FROM Instructor WHERE email = :email", Instructor.class)
                    .setParameter("email", email)
                    .uniqueResult();
            return instructor != null;
        }
    }

    public boolean isInstructorPhoneExists(String phoneNumber) {
        try (Session session = sessionFactory.openSession()) {
            Instructor instructor = session.createQuery("FROM Instructor WHERE phoneNumber = :phoneNumber", Instructor.class)
                    .setParameter("phoneNumber", phoneNumber)
                    .uniqueResult();
            return instructor != null;
        }
    }

    public void addInstructor(Instructor instructor) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.merge(instructor); // Use merge to handle detached entities
        session.getTransaction().commit();
        session.close();
    }
    public void updateInstructor(Instructor instructor) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.merge(instructor);
        session.getTransaction().commit();
    }
    public Instructor getInstructorByEmail(String email) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                            "SELECT i FROM Instructor i " +
                                    "LEFT JOIN FETCH i.courses " + // Eagerly fetch the courses
                                    "WHERE i.email = :email", Instructor.class)
                    .setParameter("email", email)
                    .uniqueResult();
        }
    }
}
