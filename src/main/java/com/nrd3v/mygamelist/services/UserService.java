package com.nrd3v.mygamelist.services;

import com.nrd3v.mygamelist.core.CoreService;
import com.nrd3v.mygamelist.entities.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService extends CoreService {

    private ArrayList<Class> getEntities() {
        ArrayList<Class> entityClasses = new ArrayList<>();
        entityClasses.add(User.class);
        return entityClasses;
    }

    public User create(User user, Object relation) {
        if (user != null) {
            this.createEntity(this.getEntities(), user);
        }
        return user;
    }

    public User update(User user) {
        if (user != null) {
            this.updateEntity(this.getEntities(), user);
        }
        return user;
    }

    public void delete(User user) {
        if (user != null) {
            this.deleteEntity(this.getEntities(), user);
        }
    }
}
