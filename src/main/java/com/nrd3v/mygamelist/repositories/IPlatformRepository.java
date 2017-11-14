package com.nrd3v.mygamelist.repositories;

import com.nrd3v.mygamelist.entities.Platform;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPlatformRepository extends JpaRepository<Platform,Integer> {
    Platform findById(int id);
    List<Platform> findAllByOrderByPlatformNameAsc();
    Platform findByPlatformGiantbombId(String giantbombId);
}
