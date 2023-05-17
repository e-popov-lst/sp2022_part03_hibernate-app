package org.example;

import org.example.model.Item;
import org.example.model.Person;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.w3c.dom.ls.LSOutput;

import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class)
                .addAnnotatedClass(Item.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();

        try (sessionFactory) {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            Person person = session.get(Person.class, 21);
            System.out.println("Получили человека");

            session.getTransaction().commit();
            // session.close() - вызывается автоматически при коммите транзакции

            System.out.println("Сессия завершена session.close()");

            // открываем сессию и транзакци еще раз
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            System.out.println("Внутри второй транзакции");

            // связываем объект с новой сессией - managed
            person = (Person) session.merge(person);

            Hibernate.initialize(person.getItems());


            // второй вариант с HQL, без вызова Hibernate.initialize(person.getItems())
            List<Item> items = session.createQuery("select i from Item i where i.owner.id=:personId", Item.class)
                            .setParameter("personId", person.getId()).getResultList();
            System.out.println(items);


            session.getTransaction().commit();

            System.out.println("Вне второй сессии");

            System.out.println(person.getItems());
        }
    }
}
