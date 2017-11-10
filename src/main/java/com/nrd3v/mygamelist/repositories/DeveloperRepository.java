package com.nrd3v.mygamelist.repositories;

import com.nrd3v.mygamelist.core.CoreRepository;
import com.nrd3v.mygamelist.entities.Developer;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DeveloperRepository extends CoreRepository implements IDeveloperRepository {

    public Developer findById(int id) {
        return (Developer) this.findEntityById(Developer.class,id);
    }

    @Override
    public List<Developer> findAll(String orderBy) {
        return (List<Developer>) this.findAllEntities(Developer.class, orderBy);
    }

    @Override
    public Developer findByGiantbombId(String giantbombId) {
        return (Developer) this.findEntityByGiantbombId(Developer.class, giantbombId);
    }
}
