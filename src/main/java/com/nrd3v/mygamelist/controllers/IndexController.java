package com.nrd3v.mygamelist.controllers;

import com.nrd3v.mygamelist.core.CoreRepository;
import com.nrd3v.mygamelist.entities.Developer;
import com.nrd3v.mygamelist.entities.Game;
import com.nrd3v.mygamelist.repositories.IDeveloperRepository;
import com.nrd3v.mygamelist.repositories.IGameRepository;
import com.nrd3v.mygamelist.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.io.IOException;

@Controller
public class IndexController {

    private GameService gameService;
    private IDeveloperRepository developerRepository;
    private IGameRepository gameRepository;

    @Autowired
    public IndexController(GameService gameService,
                           IDeveloperRepository developerRepository,
                           IGameRepository gameRepository) {
        this.gameService = gameService;
        this.developerRepository = developerRepository;
        this.gameRepository = gameRepository;
    }

    @RequestMapping(value = "/")
    public String index(Model model) throws IOException {
        model.addAttribute("games", gameRepository.findAll(CoreRepository.ORDER_BY_NAME_ASC));
        model.addAttribute("developers", developerRepository.findAll(CoreRepository.ORDER_BY_NAME_ASC));
        model.addAttribute("newGame", new Game());
        return "index";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public RedirectView addGame(@Valid @ModelAttribute("newGame") Game game,
                                @RequestParam(name = "developer", required=false) String developer_id,
                                BindingResult result) {
        if (result.hasErrors()) {
            // TODO
        }
        if (!developer_id.equals("NONE")) {
            Developer developer = developerRepository.findById(Integer.parseInt(developer_id));
            if (developer != null) {
                game.setDeveloper(developer);
            }
        } else {
            game.setDeveloper(null);
        }
        gameService.create(game);
        return new RedirectView("/");
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public String showGame(Model model, @PathVariable(name = "id") int id) throws IOException {
        Game game = gameRepository.findById(id);
        if (game != null) {
            model.addAttribute("game", game);
        }
        return "show";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public RedirectView deleteGame(@PathVariable(name = "id") int id) {
        Game game = gameRepository.findById(id);
        if (game != null) {
            gameService.delete(game);
        }
        return new RedirectView("/");
    }
}
