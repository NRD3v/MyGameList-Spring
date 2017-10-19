package com.nrd3v.mygamelist.repositories;

import com.nrd3v.mygamelist.entities.Developer;
import com.nrd3v.mygamelist.entities.Game;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GameRepository implements IGameRepository {

    @Override
    public List<Game> findAll() {
        SessionFactory factory = new Configuration()
                .configure()
                .addAnnotatedClass(Game.class)
                .addAnnotatedClass(Developer.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();
        List<Game> games = null;
        try {
            session.beginTransaction();
            games = session.createQuery("FROM Game").list();
            session.getTransaction().commit();
        }
        catch (Exception e) {
            e.getStackTrace();
        }
        finally {
            if (session.isOpen()) {
                session.close();
            }
            factory.close();
        }
        return games;
    }
}
