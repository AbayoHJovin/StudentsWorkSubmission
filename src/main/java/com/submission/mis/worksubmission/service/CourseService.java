package com.submission.mis.worksubmission.service;

import com.submission.mis.worksubmission.models.Course;
import com.submission.mis.worksubmission.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class CourseService {
    private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private static CourseService courseService;

    public static CourseService getCourseService() {
        if (courseService == null) {
            courseService = new CourseService();
        }
        return courseService;
    }

    private CourseService() {}

    public Course getCourseByName(String name) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            return session.createQuery("FROM Course WHERE name = :courseName", Course.class)
                    .setParameter("courseName", name)
                    .uniqueResult();
        } catch (Exception e) {
            System.err.println("Error fetching course by name: " + name);
            e.printStackTrace();
            return null;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
    public List<Course> getAllCoursesWithInstructors() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                            "SELECT c FROM Course c " +
                                    "LEFT JOIN FETCH c.instructors", Course.class)
                    .getResultList();

        }
    }

    public Course getCourseById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Course.class, id);
        }
    }


    public void addCourse(Course course) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.persist(course);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null && session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}