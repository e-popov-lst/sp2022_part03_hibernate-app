package org.example;

import org.example.model.Passport;
import org.example.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class)
                .addAnnotatedClass(Passport.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();
/*

            Person person = new Person("Test person", 50);
            Passport passport = new Passport(12345);

            person.setPassport(passport);  // синхронизация

            session.save(person);

            Person person2 = session.get(Person.class, 19);
            person2.getPassport().setPassportNumber(77777);
            System.out.println(person2.getPassport().getPassportNumber());
*/

            Person person3 = session.get(Person.class, 19);
            session.remove(person3);

            session.getTransaction().commit();

        } finally {
            sessionFactory.close();
        }
    }
}
