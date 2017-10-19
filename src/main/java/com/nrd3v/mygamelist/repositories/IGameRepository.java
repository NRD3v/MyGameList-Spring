package com.nrd3v.mygamelist.repositories;

import com.nrd3v.mygamelist.entities.Game;

import java.util.List;

public interface IGameRepository {

    public List<Game> findAll();
}
