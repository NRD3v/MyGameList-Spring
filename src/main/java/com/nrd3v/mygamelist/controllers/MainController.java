package com.nrd3v.mygamelist.controllers;

import com.nrd3v.mygamelist.core.CoreRepository;
import com.nrd3v.mygamelist.entities.*;
import com.nrd3v.mygamelist.repositories.IDeveloperRepository;
import com.nrd3v.mygamelist.repositories.IGameRepository;
import com.nrd3v.mygamelist.repositories.IPlatformRepository;
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
import java.util.List;

@Controller
public class MainController {

    private GameService gameService;
    private IDeveloperRepository developerRepository;
    private IGameRepository gameRepository;
    private IPlatformRepository platformRepository;

    @Autowired
    public MainController(GameService gameService,
                          IDeveloperRepository developerRepository,
                          IGameRepository gameRepository,
                          IPlatformRepository platformRepository) {
        this.gameService = gameService;
        this.developerRepository = developerRepository;
        this.gameRepository = gameRepository;
        this.platformRepository = platformRepository;
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
                                BindingResult resultDeveloper,
                                @Valid @ModelAttribute("newPlatform") Platform platform,
                                BindingResult resultPlatform) {
        if (resultGame.hasErrors() ||
            resultDeveloper.hasErrors() ||
            resultPlatform.hasErrors()) {
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
            Game existingGame = gameRepository.findByGiantbombId(game.getGameGiantbombId());
            if (existingGame != null) {
                game.setUpdatedAt(dateTime);
                session.update(game);
            } else {
                game.setCreatedAt(dateTime);
                game.setUpdatedAt(dateTime);
                session.save(game);
            }

//            Integer numberOfPlatformParams = platform.getPlatformGiantbombId().split(",").length;
//            for (int i = 0; i < numberOfPlatformParams; i++) {
//                String paramPlatformName = platform.getPlatformName().split(",")[i];
//                Platform existingPlatform = platformRepository.findByGiantbombId((platform.getPlatformGiantbombId().split(",")[i]));
//                if (existingPlatform != null) {
//                    List<Release> mappedReleases = game.getReleases();
//                    for (Release release: mappedReleases) {
//                        if (!release.getPlatform().getPlatformName().equals(existingPlatform.getPlatformName())) {
//                            existingPlatform.setPlatformName(paramPlatformName);
//                        }
//                    }
//
//                    // TODO: check cardinalities
//
//                    existingPlatform.setUpdatedAt(dateTime);
//                    session.update(existingPlatform);
//                } else {
//                    Platform newPlatform = new Platform();
//                    newPlatform.setPlatformName(developer.getDeveloperName().split(",")[i]);
//                    newPlatform.setPlatformGiantbombId(developer.getDeveloperGiantbombId().split(",")[i]);
//                    newPlatform.setCreatedAt(dateTime);
//                    newPlatform.setUpdatedAt(dateTime);
//
//                    // TODO: add cardinality
//
//                    session.save(newPlatform);
//                }
//            }

            Integer numberOfDeveloperParams = developer.getDeveloperGiantbombId().split(",").length;
            for (int i = 0; i < numberOfDeveloperParams; i++) {
                String paramDeveloperName = developer.getDeveloperName().split(",")[i];
                Developer existingDeveloper = developerRepository.findByGiantbombId((developer.getDeveloperGiantbombId().split(",")[i]));
                if (existingDeveloper != null) {
                    List<Developer> mappedDevelopers = game.getDevelopers();
                    if (!existingDeveloper.getDeveloperName().equals(paramDeveloperName)) {
                        existingDeveloper.setDeveloperName(paramDeveloperName);
                    }
                    for (Developer mappedDeveloper: mappedDevelopers) {
                        if (!mappedDeveloper.getDeveloperGiantbombId().equals(existingDeveloper.getDeveloperGiantbombId())) {
                            game.addDeveloper(existingDeveloper);
                        }
                    }
                    existingDeveloper.setUpdatedAt(dateTime);
                    session.update(existingDeveloper);
                } else {
                    Developer newDeveloper = new Developer();
                    newDeveloper.setDeveloperName(developer.getDeveloperName().split(",")[i]);
                    newDeveloper.setDeveloperGiantbombId(developer.getDeveloperGiantbombId().split(",")[i]);
                    newDeveloper.setCreatedAt(dateTime);
                    newDeveloper.setUpdatedAt(dateTime);
                    game.addDeveloper(newDeveloper);
                    session.save(newDeveloper);
                }
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
