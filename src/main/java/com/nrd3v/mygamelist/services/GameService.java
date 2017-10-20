//package com.nrd3v.mygamelist.services;
//
//import com.nrd3v.mygamelist.entities.Developer;
//import com.nrd3v.mygamelist.entities.Game;
//import org.hibernate.Session;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.time.Instant;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Map;
//
//public class GameService {
//
//    private SessionService sessionService;
//
//    @Autowired
//    public GameService(SessionService sessionService) {
//        this.sessionService = sessionService;
//    }
//
//    private ArrayList<Class> getEntities() {
//        ArrayList<Class> entityClasses = new ArrayList<>();
//        entityClasses.add(Developer.class);
//        entityClasses.add(Game.class);
//        return entityClasses;
//    }
//
//    public void create(Game game) {
//        if (game != null) {
//            Session session = sessionService.getSession(this.getEntities());
//            try {
//                session.beginTransaction();
//                session.save(game);
//                session.getTransaction().commit();
//            }
//            catch (Exception e) {
//                e.getStackTrace();
//            }
//            finally {
//                if (session.isOpen()) {
//                    session.close();
//                }
//                sessionService.getFactory(this.getEntities()).close();
//            }
//        }
//    }
//
//    public void update(Game game, Map<String,String> values) {
//        if (values != null) {
//            Session session = sessionService.getSession(this.getEntities());
//            try {
//                session.beginTransaction();
//                if (values.containsKey("name")) {
//                    game.setName(values.get("name"));
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
//                sessionService.getFactory(this.getEntities()).close();
//            }
//        }
//    }
//
//    public void delete(Game game) {
//        if (game != null) {
//            Session session = sessionService.getSession(this.getEntities());
//            try {
//                session.beginTransaction();
//                session.delete(game);
//                session.getTransaction().commit();
//            }
//            catch(Exception e){
//                e.getStackTrace();
//            }
//            finally{
//                if (session.isOpen()) {
//                    session.close();
//                }
//                sessionService.getFactory(this.getEntities()).close();
//            }
//        }
//    }
//}
