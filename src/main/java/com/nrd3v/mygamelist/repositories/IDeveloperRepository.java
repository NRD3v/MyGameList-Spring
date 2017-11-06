package com.nrd3v.mygamelist.repositories;

import com.nrd3v.mygamelist.entities.Developer;

import java.util.ArrayList;

public interface IDeveloperRepository {

    Developer findById(int id);
    ArrayList<Developer> findAll(String orderBy);
}
