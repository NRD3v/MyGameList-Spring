package com.nrd3v.mygamelist.services;

import com.nrd3v.mygamelist.entities.Developer;
import com.nrd3v.mygamelist.entities.Game;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;

@Service
public class DeveloperService extends EntityService {

    private ArrayList<Class> getEntities() {
        ArrayList<Class> entityClasses = new ArrayList<>();
        entityClasses.add(Developer.class);
        entityClasses.add(Game.class);
        return entityClasses;
    }

    public Developer create(Developer developer) {
        if (developer != null) {
            this.create(this.getEntities(), developer);
        }
        return developer;
    }

    public Developer update(Developer developer, Map<String,?> params) {
        if (developer != null && params != null) {
            this.update(this.getEntities(), developer, params);
        }
        return developer;
    }

    public void delete(Developer developer) {
        if (developer != null) {
            this.delete(this.getEntities(), developer);
        }
    }
}
