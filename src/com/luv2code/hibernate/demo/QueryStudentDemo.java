package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class QueryStudentDemo {

    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();

        try {


            session.beginTransaction();

            List theStudent = session.createQuery("from Student").getResultList();

            displayStudents(theStudent);

            theStudent = session.createQuery("from Student s where s.lastName='Doe'").getResultList();

            System.out.println("\n\nStudents who have last name Doe");
            displayStudents(theStudent);


            theStudent = session.createQuery("from Student s where s.lastName='Doe' OR s.firstName='Duffy'").getResultList();

            System.out.println("\n\nStudents who have last name Doe or firstName Daffy");
            displayStudents(theStudent);

            theStudent = session.createQuery("from Student s where s.email LIKE '%luv2code.com'").getResultList();

            System.out.println("\n\nStudents who email ends with luv2code.com");
            displayStudents(theStudent);


            session.getTransaction().commit();

            System.out.println("Done!");

        }
        finally {
            sessionFactory.close();
        }

    }

    private static void displayStudents(List<Student> theStudent) {
        for(Student tempStudent: theStudent){
            System.out.println(tempStudent);
        }
    }
}
