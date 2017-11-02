package com.nrd3v.mygamelist.core;

import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public abstract class CoreRepository extends CoreSession {

    public static final String ORDER_BY_NAME_ASC = "ORDER BY name ASC";

    public Object findEntityById(ArrayList<Class> entityClasses, Class classType, int id) {
        Session session = this.getSession(entityClasses);
        Object object = null;
        try {
            session.beginTransaction();
            object = session.get(classType, id);
            session.getTransaction().commit();
        }
        catch (Exception e) {
            e.getStackTrace();
        }
        finally {
            if (session.isOpen()) {
                session.close();
            }
            this.getFactory(entityClasses).close();
        }
        return object;
    }

    public List<Class> findAllEntities(ArrayList<Class> entityClasses, Class classType, String orderBy) {
        Session session = this.getSession(entityClasses);
        List<Class> objects = null;
        try {
            session.beginTransaction();
            String query;
            if (orderBy != null) {
                query = String.format("FROM %s %s", classType.getName(), orderBy);
            } else {
                query = String.format("FROM %s", classType.getName());
            }
            objects = session.createQuery(query).list();
            session.getTransaction().commit();
        }
        catch (Exception e) {
            e.getStackTrace();
        }
        finally {
            if (session.isOpen()) {
                session.close();
            }
            this.getFactory(entityClasses).close();
        }
        return objects;
    }
}
