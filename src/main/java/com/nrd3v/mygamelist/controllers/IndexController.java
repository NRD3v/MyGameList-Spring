package com.nrd3v.mygamelist.controllers;

import com.google.gson.Gson;
import com.nrd3v.mygamelist.entities.Developer;
import com.nrd3v.mygamelist.entities.Game;
import com.nrd3v.mygamelist.repositories.IDeveloperRepository;
import com.nrd3v.mygamelist.repositories.IGameRepository;
import com.nrd3v.mygamelist.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
public class IndexController {

    private GameService gameService;
    private IDeveloperRepository developerRepository;
    private IGameRepository gameRepository;

    @Autowired
    public IndexController(
            GameService gameService,
            IDeveloperRepository developerRepository,
            IGameRepository gameRepository
    ) {
        this.gameService = gameService;
        this.developerRepository = developerRepository;
        this.gameRepository = gameRepository;
    }

    @RequestMapping(value = "/")
    public String index(Model model) throws IOException {
        model.addAttribute("games", gameRepository.findAll());
        model.addAttribute("developers", developerRepository.findAll());
        model.addAttribute("newGame", new Game());
        model.addAttribute("apiGames", this.igdb(null, "halo 3"));
        return "index";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public RedirectView addGame(
            @Valid @ModelAttribute("newGame") Game game,
            @RequestParam("developer") String developer_id,
            BindingResult result) {
        if (result.hasErrors()) {
            return new RedirectView("/");
        }
        System.out.println(game);
        System.out.println(developer_id);
        if (!developer_id.equals("NONE")) {
            Developer developer = developerRepository.findById(Integer.parseInt(developer_id));
            if (developer != null) {
                game.setDeveloper(developer);
            }
        }
        gameService.create(game);
        return new RedirectView("/");
    }

    private List<String> igdb(String gameId, String gameName) {
        String searchName = null;
        if (gameName != null) {
            searchName = "?search=" + gameName + "&fields=*";
        } else {
            searchName = gameId + "?fields=*";
        }
        String theUrl = "https://api-2445582011268.apicast.io/games/" + searchName;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-key", "57fba5a28de85e10d8c6ae0c5750ca43");
        headers.add("Accept", MediaType.APPLICATION_JSON.getType());
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        ResponseEntity<String> response = restTemplate.exchange(theUrl, HttpMethod.GET, entity, String.class);
        System.out.println("Result - status ("+ response.getStatusCode() + ") has body: " + response.hasBody());
        return new Gson().fromJson(response.getBody(), List.class);
    }
}
