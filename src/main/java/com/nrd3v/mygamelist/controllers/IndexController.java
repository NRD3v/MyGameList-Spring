package com.nrd3v.mygamelist.controllers;

import com.nrd3v.mygamelist.entities.Game;
import com.nrd3v.mygamelist.repositories.IGameRepository;
import com.nrd3v.mygamelist.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {

    private GameService gameService;
    private IGameRepository gameRepository;

    @Autowired
    public IndexController(GameService gameService, IGameRepository gameRepository) {
        this.gameService = gameService;
        this.gameRepository = gameRepository;
    }

    @RequestMapping(value = "/")
    public ModelAndView index() {
        HashMap<String, Object> model = new HashMap<>();
        List<Game> games = gameRepository.findAll();
        model.put("games", games);
        return new ModelAndView("index", model);
    }

    @RequestMapping(value = "/test")
    public Game test() {
        Game game = new Game("SessionService");
        return gameService.create(game);
    }
}
