//package com.nrd3v.mygamelist.services;
//
//import org.hibernate.Session;
//import org.springframework.stereotype.Service;
//import org.springframework.util.ReflectionUtils;
//import org.springframework.util.StringUtils;
//
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.time.Instant;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.Map;
//
//@Service
//abstract class EntityService extends SessionService {
//
//    public void create(ArrayList<Class> entityClasses, Object object) {
//        if (object != null) {
//            Session session = this.getSession(entityClasses);
//            try {
//                session.beginTransaction();
//                session.save(object);
//                session.getTransaction().commit();
//            }
//            catch (Exception e) {
//                e.getStackTrace();
//            }
//            finally {
//                if (session.isOpen()) {
//                    session.close();
//                }
//                this.getFactory(entityClasses).close();
//            }
//        }
//    }
//
//    public void update(ArrayList<Class> entityClasses, Object object, Map<String,String> values) {
//        if (object != null && values != null) {
//            Session session = this.getSession(entityClasses);
//            try {
//                session.beginTransaction();
//                this.applySetters(object, values);
//                Method setUpdatedAt = ReflectionUtils.findMethod(object.getClass(),"setUpdatedAt");
//                setUpdatedAt.invoke(object, Date.from(Instant.now()));
//                session.getTransaction().commit();
//            }
//            catch(Exception e){
//                e.getStackTrace();
//            }
//            finally{
//                if (session.isOpen()) {
//                    session.close();
//                }
//                this.getFactory(entityClasses).close();
//            }
//        }
//    }
//
//    public void delete(ArrayList<Class> entityClasses, Object object) {
//        if (object != null) {
//            Session session = this.getSession(entityClasses);
//            try {
//                session.beginTransaction();
//                session.delete(object);
//                session.getTransaction().commit();
//            }
//            catch(Exception e){
//                e.getStackTrace();
//            }
//            finally{
//                if (session.isOpen()) {
//                    session.close();
//                }
//                this.getFactory(entityClasses).close();
//            }
//        }
//    }
//
//    private void applySetters(Object object, Map<String, String> values) throws IllegalAccessException, InvocationTargetException {
//        for (Map.Entry<String, String> value: values.entrySet()) {
//            Method setterMethod = ReflectionUtils.findMethod(object.getClass(),
//                    "set" + StringUtils.capitalize(value.getKey()));
//            setterMethod.invoke(object, value.getValue());
//        }
//    }
//}
