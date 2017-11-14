package com.nrd3v.mygamelist.repositories;

import com.nrd3v.mygamelist.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User,Integer> {
    User findById(int id);
    User findByEmail(String email);
    User findByEmailAndPassword(String email, String password);
}
