package com.nrd3v.mygamelist.core;

import com.nrd3v.mygamelist.services.ToolService;
import org.hibernate.Session;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class CoreService extends CoreSession {

    protected Object createEntity(Object object) {
        if (object != null) {
            Session session = this.getSession();
            try {
                session.beginTransaction();
                this.setDatation(object);
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
                this.getFactory().close();
            }
        }
        return object;
    }

    protected Object updateEntity(Object object) {
        if (object != null) {
            Session session = this.getSession();
            try {
                session.beginTransaction();
                this.setDatation(object);
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
                this.getFactory().close();
            }
        }
        return object;
    }

    protected void deleteEntity(Object object) {
        if (object != null) {
            Session session = this.getSession();
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
                this.getFactory().close();
            }
        }
    }

    private void setDatation(Object object) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method setCreatedAt = object.getClass().getMethod("setCreatedAt", String.class);
        if (setCreatedAt != null) {
            setCreatedAt.invoke(object, ToolService.getTime());
        }
        Method setUpdatedAt = object.getClass().getMethod("setUpdatedAt", String.class);
        if (setUpdatedAt != null) {
            setUpdatedAt.invoke(object, ToolService.getTime());
        }
    }
}
