package com.nrd3v.mygamelist.controllers;

import com.nrd3v.mygamelist.core.CoreRepository;
import com.nrd3v.mygamelist.entities.Game;
import com.nrd3v.mygamelist.repositories.IGameRepository;
import com.nrd3v.mygamelist.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api")
public class WsIndexController {

    private GameService gameService;
    private IGameRepository gameRepository;

    @Autowired
    public WsIndexController(GameService gameService, IGameRepository gameRepository) {
        this.gameService = gameService;
        this.gameRepository = gameRepository;
    }

    @RequestMapping(value = "/")
    public List<Class> index() {
        return gameRepository.findAll(CoreRepository.ORDER_BY_NAME_ASC);
    }

    @RequestMapping(value = "/update/{id}")
    public Game test(@PathVariable(name = "id") int gameId) {
        Game game = gameRepository.findById(gameId);
        HashMap<String, String> params = new HashMap<>();
        params.put("name", "Team Fortress");
        return gameService.update(game, params);
    }
}
