package com.escape.game.controller;

import com.escape.game.model.GameSession;
import com.escape.game.service.GameService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @PostMapping("/start")
    public String startGame(@RequestParam String playerName, Model model) {
        GameSession session = gameService.createNewGame(playerName);
        return "redirect:/game/" + session.getSessionId();
    }

    @GetMapping("/game/{sessionId}")
    public String gamePage(@PathVariable String sessionId, Model model) {
        GameSession session = gameService.getSession(sessionId);
        if (session == null) {
            return "redirect:/";
        }

        if (session.getStatus() == GameSession.GameStatus.ESCAPED) {
            model.addAttribute("session", session);
            return "victory";
        }

        model.addAttribute("session", session);
        model.addAttribute("room", session.getCurrentRoom());
        model.addAttribute("roomNumber", session.getRoomNumber());
        model.addAttribute("totalRooms", session.getRooms().size());
        return "game";
    }

    @GetMapping("/victory/{sessionId}")
    public String victoryPage(@PathVariable String sessionId, Model model) {
        GameSession session = gameService.getSession(sessionId);
        if (session == null) {
            return "redirect:/";
        }
        if (session.getStatus() != GameSession.GameStatus.ESCAPED) {
            return "redirect:/game/" + sessionId;
        }
        model.addAttribute("session", session);
        return "victory";
    }
}
