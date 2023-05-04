package org.example;

import org.example.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            Person person1 = session.get(Person.class, 2);
            person1.setName("New name3");

            Person person2 = session.get(Person.class, 3);
            if (person2 != null)
                session.delete(person2);

            Person person3 = new Person("Some name", 60);
            session.save(person3);

            session.getTransaction().commit();

            System.out.println("person3.id = " + person3.getId());
        } finally {
            sessionFactory.close();
        }
    }
}
