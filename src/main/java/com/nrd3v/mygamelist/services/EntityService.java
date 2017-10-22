package com.nrd3v.mygamelist.services;

import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;

@Service
abstract class EntityService extends SessionService {

    public Object create(ArrayList<Class> entityClasses, Object object) {
        if (object != null) {
            Session session = this.getSession(entityClasses);
            try {
                session.beginTransaction();
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

    public Object update(ArrayList<Class> entityClasses, Object object, Map<String,?> params) {
        if (object != null && params != null) {
            Session session = this.getSession(entityClasses);
            try {
                session.beginTransaction();
                this.invokeSetters(object, params);
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
        return object;
    }

    private Object invokeSetters(Object object, Map<String, ?> params) throws IllegalAccessException, InvocationTargetException {
        for (Map.Entry<String, ?> param: params.entrySet()) {
            String methodName = "set" + StringUtils.capitalize(param.getKey());
            for (Method method: object.getClass().getMethods()) {
                if (method.getName().equals(methodName)) {
                    method.invoke(object, param.getValue());
                    try {
                        Method setUpdatedAt = object.getClass().getMethod("setUpdatedAt", (Class[]) null);
                        if (setUpdatedAt != null) {
                            setUpdatedAt.invoke(object, null);
                        }
                    } catch(NoSuchMethodException e) {
                        System.out.println(e.toString());
                    }
                }
            }
        }
//        for (Map.Entry<String, String> param: params.entrySet()) {
//            Method setterMethod = ReflectionUtils.findMethod(object.getClass(),
//                    "set" + StringUtils.capitalize(param.getKey()));
//            if (setterMethod != null) {
//                setterMethod.invoke(object, param.getValue());
//            }
//        }
        return object;
    }

    public void delete(ArrayList<Class> entityClasses, Object object) {
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
