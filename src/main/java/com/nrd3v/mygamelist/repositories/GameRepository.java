package com.nrd3v.mygamelist.repositories;

import com.nrd3v.mygamelist.core.CoreRepository;
import com.nrd3v.mygamelist.entities.Game;
import com.nrd3v.mygamelist.services.GameService;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class GameRepository extends CoreRepository implements IGameRepository {

    private GameService gameService;

    public GameRepository(GameService gameService) {
        this.gameService = gameService;
    }

    public Game findById(int id) {
        return (Game) this.findEntityById(gameService.getEntities(), Game.class,id);
    }

    @Override
    public ArrayList<Game> findAll(String orderBy) {
        return (ArrayList<Game>) this.findAllEntities(gameService.getEntities(), Game.class, orderBy);
    }

    @Override
    public Game findByGiantbombId(int giantbombId) {
        return (Game) this.findEntityByGiantbombId(gameService.getEntities(), Game.class, giantbombId);
    }
}
