package com.nrd3v.mygamelist.controllers;

import com.nrd3v.mygamelist.core.CoreRepository;
import com.nrd3v.mygamelist.entities.Game;
import com.nrd3v.mygamelist.repositories.IGameRepository;
import com.nrd3v.mygamelist.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;

@RestController
@RequestMapping("/api")
public class WsMainController {

    private GameService gameService;
    private IGameRepository gameRepository;

    @Autowired
    public WsMainController(GameService gameService,
                            IGameRepository gameRepository) {
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
        game.setName(name);
        game.setGiantbombId(Integer.parseInt(giantbombId));
        return gameService.create(game);
    }

    @RequestMapping(value = "/game/{id}/delete", method = RequestMethod.GET)
    public void deleteGame(@PathVariable(name = "id") int id) {
        Game game = gameRepository.findById(id);
        if (game != null) {
            gameService.delete(game);
        }
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ResponseEntity<String> search(@RequestParam(name = "name", required = false) String name,
                                         @RequestParam(name = "id", required = false) Integer id) {
        String url = "https://www.giantbomb.com/api/";
        String apiKey = "21de7a214765bf8391668c73ac45f913aff0d9c6";
        String fields = "id,name,original_release_date,image,platforms";
        if (name != null) {
            url = url + "search/?api_key=" + apiKey +
                    "&format=json&query=" + name + "&resources=game" +
                    "&field_list=" + fields;
        } else {
            url = url + "game/3030-" + id + "/?api_key=" + apiKey +
                    "&format=json&field_list=" + fields + ",developers";
        }
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        return restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
    }
}
