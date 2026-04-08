package com.escape.game.controller;

import com.escape.game.service.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/game")
public class GameApiController {

    private final GameService gameService;

    public GameApiController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/{sessionId}/solve")
    public ResponseEntity<Map<String, Object>> submitAnswer(
            @PathVariable String sessionId,
            @RequestBody Map<String, String> payload) {
        String puzzleId = payload.get("puzzleId");
        String answer = payload.get("answer");
        Map<String, Object> result = gameService.submitAnswer(sessionId, puzzleId, answer);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/{sessionId}/hint")
    public ResponseEntity<Map<String, Object>> getHint(
            @PathVariable String sessionId,
            @RequestBody Map<String, String> payload) {
        String puzzleId = payload.get("puzzleId");
        Map<String, Object> result = gameService.getHint(sessionId, puzzleId);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/{sessionId}/next-room")
    public ResponseEntity<Map<String, Object>> nextRoom(@PathVariable String sessionId) {
        boolean moved = gameService.moveToNextRoom(sessionId);
        return ResponseEntity.ok(Map.of("success", moved));
    }

    @GetMapping("/{sessionId}/status")
    public ResponseEntity<Map<String, Object>> getStatus(@PathVariable String sessionId) {
        var session = gameService.getSession(sessionId);
        if (session == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(Map.of(
                "elapsed", session.getElapsedTime(),
                "score", session.getTotalScore(),
                "hints", session.getTotalHintsUsed(),
                "attempts", session.getTotalAttempts(),
                "status", session.getStatus().name()
        ));
    }
}
