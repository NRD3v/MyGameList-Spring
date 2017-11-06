package com.nrd3v.mygamelist.services;

import com.nrd3v.mygamelist.core.CoreEntity;
import com.nrd3v.mygamelist.entities.Developer;
import com.nrd3v.mygamelist.entities.Game;
import com.nrd3v.mygamelist.entities.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class GameService extends CoreEntity {

    public ArrayList<Class> getEntities() {
        ArrayList<Class> entityClasses = new ArrayList<>();
        entityClasses.add(Developer.class);
        entityClasses.add(Game.class);
        entityClasses.add(User.class);
        return entityClasses;
    }

    public Game create(Game game) {
        if (game != null) {
            this.createEntity(this.getEntities(), game);
        }
        return game;
    }

    public Game update(Game game) {
        if (game != null) {
            this.updateEntity(this.getEntities(), game);
        }
        return game;
    }

    public void delete(Game game) {
        if (game != null) {
            this.deleteEntity(this.getEntities(), game);
        }
    }
}
