package com.nrd3v.mygamelist.repositories;

import com.nrd3v.mygamelist.entities.Developer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IDeveloperRepository extends JpaRepository<Developer,Integer> {

    Developer findById(int id);
    List<Developer> findAllByOrderByDeveloperNameAsc();
    Developer findByDeveloperGiantbombId(String giantbombId);
}
