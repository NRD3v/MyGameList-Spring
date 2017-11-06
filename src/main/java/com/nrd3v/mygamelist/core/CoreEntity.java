package com.nrd3v.mygamelist.core;

import com.nrd3v.mygamelist.services.ToolService;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.ArrayList;

@Service
public abstract class CoreEntity extends CoreSession {

    protected Object createEntity(ArrayList<Class> entityClasses, Object object) {
        if (object != null) {
            Session session = this.getSession(entityClasses);
            try {
                session.beginTransaction();
                Method setCreatedAt = object.getClass().getMethod("setCreatedAt", String.class);
                if (setCreatedAt != null) {
                    setCreatedAt.invoke(object, ToolService.getTime());
                }
                session.save(object);
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
        }
        return object;
    }

    protected Object updateEntity(ArrayList<Class> entityClasses, Object object) {
        if (object != null) {
            Session session = this.getSession(entityClasses);
            try {
                session.beginTransaction();
                Method setUpdatedAt = object.getClass().getMethod("setUpdatedAt", String.class);
                if (setUpdatedAt != null) {
                    setUpdatedAt.invoke(object, ToolService.getTime());
                }
                session.update(object);
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
        }
        return object;
    }

    protected void deleteEntity(ArrayList<Class> entityClasses, Object object) {
        if (object != null) {
            Session session = this.getSession(entityClasses);
            try {
                session.beginTransaction();
                session.delete(object);
                session.getTransaction().commit();
            }
            catch(Exception e){
                e.getStackTrace();
            }
            finally{
                if (session.isOpen()) {
                    session.close();
                }
                this.getFactory(entityClasses).close();
            }
        }
    }
}
