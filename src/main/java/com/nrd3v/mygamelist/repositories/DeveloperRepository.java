package com.nrd3v.mygamelist.repositories;

import com.nrd3v.mygamelist.entities.Developer;
import com.nrd3v.mygamelist.entities.Game;
import com.nrd3v.mygamelist.entities.User;
import com.nrd3v.mygamelist.entities.UserAggregate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DeveloperRepository implements IDeveloperRepository {

    public Developer findById(int id) {
        SessionFactory factory = new Configuration()
                .configure()
                .addAnnotatedClass(Developer.class)
                .addAnnotatedClass(Game.class)
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(UserAggregate.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();
        Developer developer = null;
        try {
            session.beginTransaction();
            developer = session.get(Developer.class, id);
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
        return developer;
    }

    @Override
    public List<Developer> findAll() {
        SessionFactory factory = new Configuration()
                .configure()
                .addAnnotatedClass(Developer.class)
                .addAnnotatedClass(Game.class)
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(UserAggregate.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();
        List<Developer> developers = null;
        try {
            session.beginTransaction();
            developers = session.createQuery("FROM Developer ORDER BY name ASC").list();
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
        return developers;
    }
}
