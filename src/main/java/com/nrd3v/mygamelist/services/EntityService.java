//package com.nrd3v.mygamelist.services;
//
//import org.hibernate.Session;
//
//import java.util.ArrayList;
//import java.util.Map;
//
//abstract class EntityService extends SessionService {
//
//    public EntityService() {
//    }
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
//                if (values.containsKey("name")) {
////                    TODO: implementing object instanceof
////                    object.setName(values.get("name"));
//                }
////                game.setUpdatedAt(Date.from(Instant.now()));
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
////                TODO: implementing object instanceof
////                object.setGames(null);
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
//}
