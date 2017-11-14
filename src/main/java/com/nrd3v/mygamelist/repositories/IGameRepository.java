package com.nrd3v.mygamelist.repositories;

import com.nrd3v.mygamelist.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IGameRepository extends JpaRepository<Game,Integer> {
    Game findById(int id);
    List<Game> findAllByOrderByGameNameAsc();
    Game findByGameGiantbombId(String giantbombId);
}
