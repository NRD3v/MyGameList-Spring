//package com.nrd3v.mygamelist.services;
//
//import com.nrd3v.mygamelist.entities.Developer;
//import com.nrd3v.mygamelist.entities.Game;
//
//import java.util.ArrayList;
//import java.util.Map;
//
//public class GameService extends EntityService {
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
//            this.create(this.getEntities(), game);
//        }
//    }
//
//    public void update(Game game, Map<String,String> values) {
//        if (game != null && values != null) {
//            this.update(this.getEntities(), game, values);
//        }
//    }
//
//    public void delete(Game game) {
//        if (game != null) {
//            this.delete(this.getEntities(), game);
//        }
//    }
//}
