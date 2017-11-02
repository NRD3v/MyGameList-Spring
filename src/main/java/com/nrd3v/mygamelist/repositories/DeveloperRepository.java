package com.nrd3v.mygamelist.repositories;

import com.nrd3v.mygamelist.core.CoreRepository;
import com.nrd3v.mygamelist.entities.Developer;
import com.nrd3v.mygamelist.services.DeveloperService;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DeveloperRepository extends CoreRepository implements IDeveloperRepository {

    private DeveloperService developerService;

    public DeveloperRepository(DeveloperService developerService) {
        this.developerService = developerService;
    }

    public Developer findById(int id) {
        return (Developer) this.findEntityById(developerService.getEntities(), Developer.class,id);
    }

    @Override
    public List<Class> findAll(String orderBy) {
        return this.findAllEntities(developerService.getEntities(), Developer.class, orderBy);
    }
}
