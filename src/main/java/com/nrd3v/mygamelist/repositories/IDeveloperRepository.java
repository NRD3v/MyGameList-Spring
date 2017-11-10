package com.nrd3v.mygamelist.repositories;

import com.nrd3v.mygamelist.entities.Developer;

import java.util.List;

public interface IDeveloperRepository {

    Developer findById(int id);
    List<Developer> findAll(String orderBy);
    Developer findByGiantbombId(String giantbombId);
}
