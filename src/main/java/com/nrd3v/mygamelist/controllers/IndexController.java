package com.nrd3v.mygamelist.controllers;

import com.nrd3v.mygamelist.entities.Game;
import com.nrd3v.mygamelist.repositories.IGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IndexController {

    private IGameRepository gameRepository;
//    private GameService gameService;

    @Autowired
    public IndexController(
            IGameRepository gameRepository
//            GameService gameService
    ) {
        this.gameRepository = gameRepository;
//        this.gameService = gameService;
    }

    @RequestMapping(name = "/")
    public List<Game> index() {
        return gameRepository.findAll();
    }

//    @RequestMapping(name = "/test")
//    public void test() {
//        Game game = new Game("SessionService");
//        gameService.create(game);
//    }
}
