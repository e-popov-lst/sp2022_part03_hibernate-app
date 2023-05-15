package org.example;

import org.example.model.Actor;
import org.example.model.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Actor.class)
                .addAnnotatedClass(Movie.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();

        try (sessionFactory) {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();

/*

            Movie movie = new Movie("Pulp fiction Криминальное чтиво", 1994);
            Actor actor1 = new Actor("Harvey Keitel", 81);
            Actor actor2 = new Actor("Samuel L. Jackson", 72);

            // Arrays.asList()
            movie.setActors(new ArrayList<>(List.of(actor1, actor2)));

            actor1.setMovies(new ArrayList<>(Collections.singletonList(movie)));
            actor2.setMovies(new ArrayList<>(Collections.singletonList(movie)));

            session.save(movie);
            session.save(actor1);
            session.save(actor2);

            Movie movie2 = session.get(Movie.class, 1);
            System.out.println(movie2.getActors());

            Movie movie3 = new Movie("Reservoir Dogs", 1992);
            Actor actor3 = session.get(Actor.class, 1);

            movie3.setActors(new ArrayList<>(Collections.singletonList(actor3)));

            actor3.getMovies().add(movie3);

            session.save(movie3);
*/

            Actor actor4 = session.get(Actor.class, 2);
            System.out.println(actor4.getMovies());

            Movie movieToRemove = actor4.getMovies().get(0);

            actor4.getMovies().remove(0);
            movieToRemove.getActors().remove(actor4);


            session.getTransaction().commit();
        }
    }
}
