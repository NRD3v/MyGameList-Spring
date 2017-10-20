//package com.nrd3v.mygamelist.services;
//
//import com.nrd3v.mygamelist.entities.User;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.Map;
//
//@Service
//public class UserService extends EntityService {
//
//    private ArrayList<Class> getEntities() {
//        ArrayList<Class> entityClasses = new ArrayList<>();
//        entityClasses.addGame(User.class);
//        return entityClasses;
//    }
//
//    public void create(User user) {
//        if (user != null) {
//            this.create(this.getEntities(), user);
//        }
//    }
//
//    public void update(User user, Map<String,String> values) {
//        if (user != null && values != null) {
//            this.update(this.getEntities(), user, values);
//        }
//    }
//
//    public void delete(User user) {
//        if (user != null) {
//            this.delete(this.getEntities(), user);
//        }
//    }
//}
