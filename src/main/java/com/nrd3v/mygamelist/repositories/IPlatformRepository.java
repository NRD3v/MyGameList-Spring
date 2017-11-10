package com.nrd3v.mygamelist.repositories;

import com.nrd3v.mygamelist.entities.Platform;

import java.util.List;

public interface IPlatformRepository {
    Platform findById(int id);
    List<Platform> findAll(String orderBy);
    Platform findByGiantbombId(String giantbombId);
}
