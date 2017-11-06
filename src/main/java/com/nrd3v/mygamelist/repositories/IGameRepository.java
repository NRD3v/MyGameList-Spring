package com.nrd3v.mygamelist.repositories;

import com.nrd3v.mygamelist.entities.Game;

import java.util.ArrayList;

public interface IGameRepository {
    Game findById(int id);
    ArrayList<Game> findAll(String orderBy);
    Game findByGiantbombId(int giantbombId);
}
