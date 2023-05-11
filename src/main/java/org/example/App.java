package org.example;

import org.example.model.Item;
import org.example.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class).addAnnotatedClass(Item.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();
/*

            Person person = session.get(Person.class, 3);
            System.out.println(person);
            List<Item> items = person.getItems();
            System.out.println(items);

            System.out.println("=========================");

            Item item = session.get(Item.class, 3);
            System.out.println(item);
            Person person1 = item.getOwner();
            System.out.println(person1);

            System.out.println("=========================");

            Person person2 = session.get(Person.class, 2);
            Item newItem = new Item("Item from Hibernate", person2);
            person2.getItems().add(newItem);  // эта строка не пораждает SQL-запроса, нужна для синхронизации кеша Hibernate
            session.save(newItem);

            System.out.println("=========================");

            Person person3 = new Person("Test person", 30);
            Item item3 = new Item("Item from Hibernate 2", person3);
            person3.setItems(new ArrayList<>(Collections.singletonList(item3)));  // для синхронизации
            session.save(person3);
            session.save(item3);

            System.out.println("=========================");

            Person person4 = session.get(Person.class, 3);
            List<Item> items4 = person4.getItems();
            for (Item item : items4)
                session.remove(item);

            person4.getItems().clear();  // синхронизация

            System.out.println("=========================");

            Person person5 = session.get(Person.class, 2);
            session.remove(person5);
            person5.getItems().forEach(i -> i.setOwner(null));  // синхронизация
*/

            System.out.println("=========================");

            Person person6 = session.get(Person.class, 4);
            Item item6 = session.get(Item.class , 1);
            item6.getOwner().getItems().remove(item6);  // синхронизация
            item6.setOwner(person6);
            person6.getItems().add(item6);  // синхронизация


            session.getTransaction().commit();

        } finally {
            sessionFactory.close();
        }
    }
}
