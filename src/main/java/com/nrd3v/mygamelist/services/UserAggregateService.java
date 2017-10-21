package com.nrd3v.mygamelist.services;

import com.nrd3v.mygamelist.entities.UserAggregate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;

@Service
public class UserAggregateService extends EntityService {

    private ArrayList<Class> getEntities() {
        ArrayList<Class> entityClasses = new ArrayList<>();
        entityClasses.add(UserAggregate.class);
        return entityClasses;
    }

    public void create(UserAggregate userAggregate) {
        if (userAggregate != null) {
            this.create(this.getEntities(), userAggregate);
        }
    }

    public void update(UserAggregate userAggregate, Map<String,String> params) {
        if (userAggregate != null && params != null) {
            this.update(this.getEntities(), userAggregate, params);
        }
    }

    public void delete(UserAggregate userAggregate) {
        if (userAggregate != null) {
            userAggregate.getUser().setUserAggregate(null);
            this.delete(this.getEntities(), userAggregate);
        }
    }
}
