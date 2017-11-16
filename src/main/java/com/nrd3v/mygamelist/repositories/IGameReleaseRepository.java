package com.nrd3v.mygamelist.repositories;

import com.nrd3v.mygamelist.entities.GameRelease;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IGameReleaseRepository extends JpaRepository<GameRelease,Integer> {
    List<GameRelease> findAll();
    GameRelease findByGameIdAndPlatformId(int gameId, int platformId);
}
