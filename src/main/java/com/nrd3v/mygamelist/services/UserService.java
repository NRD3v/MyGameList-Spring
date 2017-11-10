package com.nrd3v.mygamelist.services;

import com.nrd3v.mygamelist.core.CoreService;
import com.nrd3v.mygamelist.entities.User;
import org.springframework.stereotype.Service;

@Service
public class UserService extends CoreService {

    public User create(User user) {
        if (user != null) {
            this.createEntity(user);
        }
        return user;
    }

    public User update(User user) {
        if (user != null) {
            this.updateEntity(user);
        }
        return user;
    }

    public void delete(User user) {
        if (user != null) {
            this.deleteEntity(user);
        }
    }
}
