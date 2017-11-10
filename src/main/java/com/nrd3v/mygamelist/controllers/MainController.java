package com.nrd3v.mygamelist.controllers;

import com.nrd3v.mygamelist.core.CoreRepository;
import com.nrd3v.mygamelist.entities.Developer;
import com.nrd3v.mygamelist.entities.Game;
import com.nrd3v.mygamelist.entities.User;
import com.nrd3v.mygamelist.repositories.IDeveloperRepository;
import com.nrd3v.mygamelist.repositories.IGameRepository;
import com.nrd3v.mygamelist.services.DeveloperService;
import com.nrd3v.mygamelist.services.GameService;
import com.nrd3v.mygamelist.services.ToolService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class MainController {

    private DeveloperService developerService;
    private GameService gameService;
    private IDeveloperRepository developerRepository;
    private IGameRepository gameRepository;

    @Autowired
    public MainController(DeveloperService developerService,
                          GameService gameService,
                          IDeveloperRepository developerRepository,
                          IGameRepository gameRepository) {
        this.developerService = developerService;
        this.gameService = gameService;
        this.developerRepository = developerRepository;
        this.gameRepository = gameRepository;
    }

    @RequestMapping(value = "")
    public String index(Model model) throws IOException {
        model.addAttribute("games", gameRepository.findAll(CoreRepository.ORDER_BY_NAME_ASC));
        model.addAttribute("newGame", new Game());
        model.addAttribute("newDeveloper", new Developer());
        return "index";
    }

    @RequestMapping(value = "/game/add", method = RequestMethod.POST)
    public RedirectView addGame(@Valid @ModelAttribute("newGame") Game game,
                                BindingResult resultGame,
                                @Valid @ModelAttribute("newDeveloper") Developer developer,
                                BindingResult resultDeveloper) {
        if (resultGame.hasErrors() || resultDeveloper.hasErrors()) {
            // TODO: Display error in front
        }

        SessionFactory factory = new Configuration()
                .configure()
                .addAnnotatedClass(Developer.class)
                .addAnnotatedClass(Game.class)
                .addAnnotatedClass(User.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();

            String dateTime = ToolService.getTime();
            game.setCreatedAt(dateTime);
            game.setUpdatedAt(dateTime);
            session.save(game);

            Integer numberOfParams = developer.getDeveloperName().split(",").length;
            for(int i = 0; i < numberOfParams; i++) {
                Developer newDeveloper = new Developer();
                newDeveloper.setDeveloperName(developer.getDeveloperName().split(",")[i]);
                newDeveloper.setDeveloperGiantbombId(developer.getDeveloperGiantbombId().split(",")[i]);
                newDeveloper.setCreatedAt(dateTime);
                newDeveloper.setUpdatedAt(dateTime);
                game.addDeveloper(newDeveloper);
                session.save(newDeveloper);
            }
            session.getTransaction().commit();
        }
        catch (Exception e) {
            e.getStackTrace();
        }
        finally {
            if (session.isOpen()) {
                session.close();
            }
            factory.close();
        }
        return new RedirectView("/");
    }

    @RequestMapping(value = "/game/{id}/delete", method = RequestMethod.GET)
    public RedirectView deleteGame(@PathVariable(name = "id") int id) {
        Game game = gameRepository.findById(id);
        if (game != null) {
            // TODO: Debug same id on all lines
            gameService.delete(game);
        }
        return new RedirectView("/");
    }
}
