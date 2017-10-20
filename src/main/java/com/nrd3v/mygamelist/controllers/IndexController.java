package com.nrd3v.mygamelist.controllers;

import com.nrd3v.mygamelist.entities.Game;
import com.nrd3v.mygamelist.repositories.IGameRepository;
import com.nrd3v.mygamelist.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IndexController {

    private GameService gameService;
    private IGameRepository gameRepository;

    @Autowired
    public IndexController(GameService gameService, IGameRepository gameRepository) {
        this.gameService = gameService;
        this.gameRepository = gameRepository;
    }

    @RequestMapping(value = "/")
    public List<Game> index() {
        return gameRepository.findAll();
    }

    @RequestMapping(value = "/test")
    public Game test() {
        Game game = new Game("SessionService");
        return gameService.create(game);
    }
}
