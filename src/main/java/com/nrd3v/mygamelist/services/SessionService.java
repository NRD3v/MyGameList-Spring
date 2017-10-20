package com.nrd3v.mygamelist.services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
abstract class SessionService {

    public SessionFactory getFactory(ArrayList<Class> entityClasses) {
        Configuration configuration = new Configuration().configure();
        if (entityClasses != null) {
            for (Class entityClass: entityClasses) {
                configuration.addAnnotatedClass(entityClass);
            }
        }
        return configuration.buildSessionFactory();
    }

    public Session getSession(ArrayList<Class> entityClasses) {
        return this.getFactory(entityClasses).getCurrentSession();
    }
}
