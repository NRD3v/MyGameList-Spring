package com.nrd3v.mygamelist.core;

import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public abstract class CoreRepository extends CoreSession {

    public static final String ORDER_BY_NAME_ASC = "ORDER BY name ASC";

    protected Object findEntityById(Class classType, int id) {
        Session session = this.getSession();
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
            this.getFactory().close();
        }
        return object;
    }

    protected List<?> findAllEntities(Class classType, String orderBy) {
        Session session = this.getSession();
        List<?> objects = new ArrayList<>();
        try {
            session.beginTransaction();
            String query;
            if (orderBy != null) {
                query = String.format("FROM %s %s", classType.getName(), orderBy);
            } else {
                query = String.format("FROM %s", classType.getName());
            }
            objects = (List<?>) session.createQuery(query).list();
            session.getTransaction().commit();
        }
        catch (Exception e) {
            e.getStackTrace();
        }
        finally {
            if (session.isOpen()) {
                session.close();
            }
            this.getFactory().close();
        }
        return objects;
    }

    protected Object findEntityByGiantbombId(Class<?> classType, String giantbombId) {
        Session session = this.getSession();
        List<?> objects = new ArrayList<>();
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM " + classType.getName() + " WHERE giantbomb_id = :giantbomb_id ");
            query.setParameter("giantbomb_id", giantbombId);
            objects = (List<?>) query.list();
            session.getTransaction().commit();
        }
        catch (Exception e) {
            e.getStackTrace();
        }
        finally {
            if (session.isOpen()) {
                session.close();
            }
            this.getFactory().close();
        }
        Object object = null;
        if (objects.size() > 0) {
            object = objects.get(0);
        }
        return object;
    }
}
