package com.nrd3v.mygamelist.core;

import com.nrd3v.mygamelist.services.ToolService;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;

@Service
public abstract class CoreEntity extends CoreSession {

    protected Object createEntity(ArrayList<Class> entityClasses, Object object) {
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

    protected Object updateEntity(ArrayList<Class> entityClasses, Object object, Map<String,?> params) {
        if (object != null && params != null) {
            Session session = this.getSession(entityClasses);
            try {
                session.beginTransaction();
                this.invokeSetters(object, params);
                session.saveOrUpdate(object);
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
            Method setterMethod = ReflectionUtils.findMethod(object.getClass(),
                    "set" + StringUtils.capitalize(param.getKey()),
                    param.getValue().getClass());
            if (setterMethod != null) {
                setterMethod.invoke(object, param.getValue());
            }
            try {
                Method setUpdatedAt = object.getClass().getMethod("setUpdatedAt", String.class);
                if (setUpdatedAt != null) {
                    setUpdatedAt.invoke(object, ToolService.getTime());
                }
            } catch(NoSuchMethodException e) {
                System.out.println(e.toString());
            }
        }
//        for (Map.Entry<String, ?> param: params.entrySet()) {
//            String methodName = "set" + StringUtils.capitalize(param.getKey());
//            for (Method method: object.getClass().getMethods()) {
//                if (method.getName().equals(methodName)) {
//                    method.invoke(object, param.getValue());
//                    try {
//                        Method setUpdatedAt = object.getClass().getMethod("setUpdatedAt", String.class);
//                        if (setUpdatedAt != null) {
//                            setUpdatedAt.invoke(object, ToolService.getTime());
//                        }
//                    } catch(NoSuchMethodException e) {
//                        System.out.println(e.toString());
//                    }
//                }
//            }
//        }
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
