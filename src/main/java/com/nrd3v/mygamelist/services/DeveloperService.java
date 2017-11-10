package com.nrd3v.mygamelist.services;

import com.nrd3v.mygamelist.core.CoreService;
import com.nrd3v.mygamelist.entities.Developer;
import org.springframework.stereotype.Service;

@Service
public class DeveloperService extends CoreService {

    public Developer create(Developer developer) {
        if (developer != null) {
            this.createEntity(developer);
        }
        return developer;
    }

    public Developer update(Developer developer) {
        if (developer != null) {
            this.updateEntity(developer);
        }
        return developer;
    }

    public void delete(Developer developer) {
        if (developer != null) {
            this.deleteEntity(developer);
        }
    }
}
