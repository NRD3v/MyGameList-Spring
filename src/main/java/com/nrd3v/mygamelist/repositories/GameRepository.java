package com.nrd3v.mygamelist.repositories;

import com.nrd3v.mygamelist.core.CoreRepository;
import com.nrd3v.mygamelist.entities.Game;
import com.nrd3v.mygamelist.services.GameService;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    public List<Class> findAll(String orderBy) {
        return this.findAllEntities(gameService.getEntities(), Game.class, orderBy);
    }
}
