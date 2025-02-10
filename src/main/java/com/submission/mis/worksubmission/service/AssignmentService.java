package com.submission.mis.worksubmission.service;

import com.submission.mis.worksubmission.models.Assignment;
import com.submission.mis.worksubmission.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

//public class AssignmentService {
//    private static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//    private static AssignmentService assignmentService;
//
//    public static AssignmentService getInstance() {
//        if (assignmentService == null) {
//            assignmentService = new AssignmentService();
//        }
//        return assignmentService;
//    }
//
//    private AssignmentService() {}
//
//    public void addAssignment(Assignment assignment) {
//        try (Session session = sessionFactory.openSession()) {
//            session.beginTransaction();
//            session.persist(assignment);
//            session.getTransaction().commit();
//        }
//    }
//
//    public List<Assignment> getAssignmentsByInstructor(int instructorId) {
//        try (Session session = sessionFactory.openSession()) {
//            return session.createQuery("FROM Assignment WHERE instructor.id = :instructorId", Assignment.class)
//                    .setParameter("instructorId", instructorId)
//                    .list();
//        }
//    }
//
//    public Assignment getAssignmentById(int id) {
//        try (Session session = sessionFactory.openSession()) {
//            return session.get(Assignment.class, id);
//        }
//    }
//
//    public void deleteAssignment(int id) {
//        try (Session session = sessionFactory.openSession()) {
//            session.beginTransaction();
//            Assignment assignment = session.get(Assignment.class, id);
//            if (assignment != null) {
//                session.remove(assignment);
//            }
//            session.getTransaction().commit();
//        }
//    }
//}


public class AssignmentService {
    private static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private static AssignmentService assignmentService;

    public static AssignmentService getInstance() {
        if (assignmentService == null) {
            assignmentService = new AssignmentService();
        }
        return assignmentService;
    }

    private AssignmentService() {}

    public void addAssignment(Assignment assignment) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(assignment);
            session.getTransaction().commit();
        }
    }

    public List<Assignment> getAssignmentsByInstructor(int instructorId) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Assignment WHERE instructor.id = :instructorId", Assignment.class)
                    .setParameter("instructorId", instructorId)
                    .list();
        }
    }

    public List<Assignment> getAssignmentsByCourse(int courseId) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Assignment WHERE course.id = :courseId", Assignment.class)
                    .setParameter("courseId", courseId)
                    .list();
        }
    }

    public Assignment getAssignmentById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Assignment.class, id);
        }
    }

    public void deleteAssignment(int id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Assignment assignment = session.get(Assignment.class, id);
            if (assignment != null) {
                session.remove(assignment);
            }
            session.getTransaction().commit();
        }
    }
}