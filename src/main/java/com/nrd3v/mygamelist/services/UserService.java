package com.nrd3v.mygamelist.services;

import com.nrd3v.mygamelist.core.CoreEntity;
import com.nrd3v.mygamelist.entities.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;

@Service
public class UserService extends CoreEntity {

    private ArrayList<Class> getEntities() {
        ArrayList<Class> entityClasses = new ArrayList<>();
        entityClasses.add(User.class);
        return entityClasses;
    }

    public User create(User user) {
        if (user != null) {
            this.createEntity(this.getEntities(), user);
        }
        return user;
    }

    public User update(User user, Map<String,?> params) {
        if (user != null && params != null) {
            this.updateEntity(this.getEntities(), user, params);
        }
        return user;
    }

    public void delete(User user) {
        if (user != null) {
            this.deleteEntity(this.getEntities(), user);
        }
    }
}
