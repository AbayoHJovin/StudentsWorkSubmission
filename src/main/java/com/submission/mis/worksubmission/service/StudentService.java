package com.submission.mis.worksubmission.service;


import com.submission.mis.worksubmission.models.Student;
import com.submission.mis.worksubmission.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class StudentService {
    protected static SessionFactory sessionFactory= HibernateUtil.getSessionFactory();
    protected static Session session;
    protected static StudentService studentService;
    public static StudentService getInstance(){
        if(studentService==null){
            studentService=new StudentService();
            return studentService;
        }
        return studentService;
    }
    private StudentService(){}
    public boolean isStudentEmailExists(String email) {
        session = sessionFactory.openSession();
        try {
            Student student = session.createQuery("FROM Student WHERE email = :email", Student.class)
                    .setParameter("email", email)
                    .uniqueResult();
            return student != null;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
    public void addStudent(Student student){
        session=sessionFactory.openSession();
        session.beginTransaction();
        session.persist(student);
        session.getTransaction().commit();
    }
    public List<Student> getAllStudents(){
        session=sessionFactory.openSession();
        List<Student> students = session.createQuery("from Student").list();
        return students;
    }
    public Student getStudentByEmail(String email) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Student WHERE email = :email", Student.class)
                    .setParameter("email", email)
                    .uniqueResult();
        }
    }
}