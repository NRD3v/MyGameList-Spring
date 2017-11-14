package com.nrd3v.mygamelist.controllers;

import com.nrd3v.mygamelist.core.CoreSession;
import com.nrd3v.mygamelist.entities.*;
import com.nrd3v.mygamelist.repositories.*;
import com.nrd3v.mygamelist.services.GameService;
import com.nrd3v.mygamelist.services.ToolService;
import org.hibernate.Session;
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

    private CoreSession coreSession;
    private GameService gameService;
    private IDeveloperRepository developerRepository;
    private IGameRepository gameRepository;
    private IGameReleaseRepository gameReleaseRepository;
    private IPlatformRepository platformRepository;
    private IUserRepository userRepository;

    @Autowired
    public MainController(CoreSession coreSession,
                          GameService gameService,
                          IDeveloperRepository developerRepository,
                          IGameRepository gameRepository,
                          IGameReleaseRepository gameReleaseRepository,
                          IPlatformRepository platformRepository,
                          IUserRepository userRepository) {
        this.coreSession = coreSession;
        this.gameService = gameService;
        this.developerRepository = developerRepository;
        this.gameRepository = gameRepository;
        this.gameReleaseRepository = gameReleaseRepository;
        this.platformRepository = platformRepository;
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "")
    public String index(Model model) throws IOException {
        User user = userRepository.findByEmailAndPassword("test@test.com", "123123");
//        User user = userRepository.findById(1);
//        List<GameRelease> releases = user.getGameReleases();
//        System.out.println(releases);
        model.addAttribute("gameReleases", user.getGameReleases());
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

        Session session = coreSession.getSession();
        try {
            session.beginTransaction();

            String dateTime = ToolService.getTime();
            Game existingGame = gameRepository.findByGameGiantbombId(game.getGameGiantbombId());
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
//                Platform existingPlatform = platformRepository.findByPlatformGiantbombId((platform.getPlatformGiantbombId().split(",")[i]));
//                if (existingPlatform != null) {
//                    List<GameRelease> mappedReleases = game.getGameReleases();
//                    for (GameRelease release: mappedReleases) {
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
                Developer existingDeveloper = developerRepository.findByDeveloperGiantbombId((developer.getDeveloperGiantbombId().split(",")[i]));
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
            coreSession.getFactory().close();
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
