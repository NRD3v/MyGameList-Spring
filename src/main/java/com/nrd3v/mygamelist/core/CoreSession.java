package com.nrd3v.mygamelist.core;

import com.nrd3v.mygamelist.entities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class CoreSession {

    private static final List<Class> entitiesClasses = new ArrayList<>(Arrays.asList(
            Developer.class,
            Game.class,
            Manufacturer.class,
            Media.class,
            MediaType.class,
            Platform.class,
            Release.class,
            User.class
    ));

    public SessionFactory getFactory() {
        Configuration configuration = new Configuration().configure();
        for (Class entityClass: entitiesClasses) {
            configuration.addAnnotatedClass(entityClass);
        }
        return configuration.buildSessionFactory();
    }

    public Session getSession() {
        return this.getFactory().getCurrentSession();
    }
}
