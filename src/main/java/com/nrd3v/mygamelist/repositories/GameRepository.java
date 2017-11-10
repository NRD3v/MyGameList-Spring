package com.nrd3v.mygamelist.repositories;

import com.nrd3v.mygamelist.core.CoreRepository;
import com.nrd3v.mygamelist.entities.Game;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GameRepository extends CoreRepository implements IGameRepository {

    public Game findById(int id) {
        return (Game) this.findEntityById(Game.class,id);
    }

    @Override
    public List<Game> findAll(String orderBy) {
        return (List<Game>) this.findAllEntities(Game.class, orderBy);
    }

    @Override
    public Game findByGiantbombId(String giantbombId) {
        return (Game) this.findEntityByGiantbombId(Game.class, giantbombId);
    }
}
