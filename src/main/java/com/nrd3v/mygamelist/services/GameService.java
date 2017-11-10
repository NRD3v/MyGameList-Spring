package com.nrd3v.mygamelist.services;

import com.nrd3v.mygamelist.core.CoreService;
import com.nrd3v.mygamelist.entities.Game;
import org.springframework.stereotype.Service;

@Service
public class GameService extends CoreService {

    public Game create(Game game) {
        if (game != null) {
            this.createEntity(game);
        }
        return game;
    }

    public Game update(Game game) {
        if (game != null) {
            this.updateEntity(game);
        }
        return game;
    }

    public void delete(Game game) {
        if (game != null) {
            this.deleteEntity(game);
        }
    }
}
