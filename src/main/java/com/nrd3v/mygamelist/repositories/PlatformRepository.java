package com.nrd3v.mygamelist.repositories;

import com.nrd3v.mygamelist.core.CoreRepository;
import com.nrd3v.mygamelist.entities.Platform;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PlatformRepository extends CoreRepository implements IPlatformRepository {

    public Platform findById(int id) {
        return (Platform) this.findEntityById(Platform.class,id);
    }

    @Override
    public List<Platform> findAll(String orderBy) {
        return (List<Platform>) this.findAllEntities(Platform.class, orderBy);
    }

    @Override
    public Platform findByGiantbombId(String giantbombId) {
        return (Platform) this.findEntityByGiantbombId(Platform.class, giantbombId);
    }
}
