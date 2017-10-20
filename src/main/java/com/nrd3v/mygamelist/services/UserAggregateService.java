//package com.nrd3v.mygamelist.services;
//
//import com.nrd3v.mygamelist.entities.User;
//import com.nrd3v.mygamelist.entities.UserAggregate;
//
//import java.util.ArrayList;
//import java.util.Map;
//
//public class UserAggregateService extends EntityService {
//
//    private ArrayList<Class> getEntities() {
//        ArrayList<Class> entityClasses = new ArrayList<>();
//        entityClasses.add(User.class);
//        entityClasses.add(UserAggregate.class);
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
