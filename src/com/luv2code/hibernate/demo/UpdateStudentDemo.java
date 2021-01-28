package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UpdateStudentDemo {

    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();

        try {

            int studentId = 1;

            session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            System.out.println("\nGetting student with id: " + studentId);

            Student myStudent = session.get(Student.class, studentId);

            System.out.println("Updating student...");

            myStudent.setFirstName("Scooby");

            session.getTransaction().commit();

            session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            System.out.println("Update email for all students.");

            session.createQuery("update Student s set s.email='foo@gomail.com'").executeUpdate();

            session.getTransaction().commit();

            System.out.println("Done!");

        } finally {
            sessionFactory.close();
        }

    }
}
