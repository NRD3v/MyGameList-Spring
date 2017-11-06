package com.nrd3v.mygamelist.services;

import com.nrd3v.mygamelist.core.CoreEntity;
import com.nrd3v.mygamelist.entities.Developer;
import com.nrd3v.mygamelist.entities.Game;
import com.nrd3v.mygamelist.entities.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class DeveloperService extends CoreEntity {

    public ArrayList<Class> getEntities() {
        ArrayList<Class> entityClasses = new ArrayList<>();
        entityClasses.add(Developer.class);
        entityClasses.add(Game.class);
        entityClasses.add(User.class);
        return entityClasses;
    }

    public Developer create(Developer developer) {
        if (developer != null) {
            this.createEntity(this.getEntities(), developer);
        }
        return developer;
    }

    public Developer update(Developer developer) {
        if (developer != null) {
            this.updateEntity(this.getEntities(), developer);
        }
        return developer;
    }

    public void delete(Developer developer) {
        if (developer != null) {
            this.deleteEntity(this.getEntities(), developer);
        }
    }
}
