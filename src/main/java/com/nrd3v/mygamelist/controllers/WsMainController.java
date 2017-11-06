package com.nrd3v.mygamelist.controllers;

import com.nrd3v.mygamelist.core.CoreRepository;
import com.nrd3v.mygamelist.entities.Game;
import com.nrd3v.mygamelist.repositories.IGameRepository;
import com.nrd3v.mygamelist.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

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

    @RequestMapping(value = "")
    public ArrayList<Game> index() {
        return gameRepository.findAll(CoreRepository.ORDER_BY_NAME_ASC);
    }

    @RequestMapping(value = "/game/{id}/show")
    public Game getById(@PathVariable(name = "id") int id) {
        return gameRepository.findById(id);
    }

    @RequestMapping(value = "/game/{giantbombId}/giantbomb")
    public Game getByGiantbombId(@PathVariable(name = "giantbombId") int giantbombId) {
        return gameRepository.findByGiantbombId(giantbombId);
    }

    @RequestMapping(value = "/game/{id}/update", method = RequestMethod.POST)
    public Game test(@PathVariable(name = "id") int gameId,
                     @RequestParam(name = "name") String name,
                     @RequestParam(name = "giantbombId") String giantbombId) {
        Game game = gameRepository.findById(gameId);
        HashMap<String, String> params = new HashMap<>();
        params.put("name", name);
        params.put("giantbombId", giantbombId);
        return gameService.update(game, params);
    }

    @RequestMapping(value = "/game/{id}/delete", method = RequestMethod.GET)
    public void deleteGame(@PathVariable(name = "id") int id) {
        Game game = gameRepository.findById(id);
        if (game != null) {
            gameService.delete(game);
        }
    }
}
