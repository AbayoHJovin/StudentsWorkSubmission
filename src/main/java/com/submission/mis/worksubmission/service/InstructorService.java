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
    public void addInstructor(Instructor instructor) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(instructor);
        session.getTransaction().commit();
    }
    public void updateInstructor(Instructor instructor) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.merge(instructor);
        session.getTransaction().commit();
    }
}
