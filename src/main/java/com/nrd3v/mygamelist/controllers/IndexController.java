package com.nrd3v.mygamelist.controllers;

import com.nrd3v.mygamelist.entities.Game;
import com.nrd3v.mygamelist.repositories.IGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IndexController {

    private IGameRepository iGameRepository;

    @Autowired
    public IndexController(IGameRepository iGameRepository) {
        this.iGameRepository = iGameRepository;
    }

    @RequestMapping(name = "/")
    public List<Game> index() {
        return iGameRepository.findAll();
    }
}
