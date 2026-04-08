package com.escape.game.service;

import com.escape.game.model.GameSession;
import com.escape.game.model.GameSession.GameStatus;
import com.escape.game.model.Puzzle;
import com.escape.game.model.Room;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class GameService {

    private final GameDataService gameDataService;
    private final Map<String, GameSession> sessions = new ConcurrentHashMap<>();

    public GameService(GameDataService gameDataService) {
        this.gameDataService = gameDataService;
    }

    public GameSession createNewGame(String playerName) {
        GameSession session = new GameSession();
        session.setSessionId(UUID.randomUUID().toString().substring(0, 8));
        session.setPlayerName(playerName);
        session.setRooms(gameDataService.createRooms());
        session.setCurrentRoomId("library");
        sessions.put(session.getSessionId(), session);
        return session;
    }

    public GameSession getSession(String sessionId) {
        return sessions.get(sessionId);
    }

    public Map<String, Object> submitAnswer(String sessionId, String puzzleId, String answer) {
        if (puzzleId == null || answer == null) {
            return Map.of("success", false, "message", "Missing puzzle ID or answer!");
        }

        GameSession session = sessions.get(sessionId);
        if (session == null) {
            return Map.of("success", false, "message", "Session not found!");
        }

        Room currentRoom = session.getCurrentRoom();
        if (currentRoom == null) {
            return Map.of("success", false, "message", "Room not found!");
        }

        Puzzle puzzle = currentRoom.getPuzzles().stream()
                .filter(p -> p.getId().equals(puzzleId))
                .findFirst()
                .orElse(null);

        if (puzzle == null) {
            return Map.of("success", false, "message", "Puzzle not found!");
        }

        if (puzzle.isSolved()) {
            return Map.of("success", false, "message", "Already solved!");
        }

        session.setTotalAttempts(session.getTotalAttempts() + 1);

        if (puzzle.checkAnswer(answer)) {
            puzzle.setSolved(true);
            int pointsEarned = puzzle.getCalculatedPoints();

            boolean roomComplete = currentRoom.areAllPuzzlesSolved();
            boolean gameComplete = false;

            if (roomComplete && currentRoom.getNextRoomId() != null) {
                session.getRooms().get(currentRoom.getNextRoomId()).setUnlocked(true);
            }

            if (roomComplete && currentRoom.getNextRoomId() == null) {
                gameComplete = true;
                session.setEndTime(LocalDateTime.now());
                session.setStatus(GameStatus.ESCAPED);
                session.calculateTotalScore();
            }

            return Map.of(
                    "success", true,
                    "message", "Correct! +" + pointsEarned + " points!",
                    "points", pointsEarned,
                    "roomComplete", roomComplete,
                    "gameComplete", gameComplete,
                    "solvedCount", currentRoom.getSolvedCount(),
                    "totalPuzzles", currentRoom.getPuzzles().size()
            );
        } else {
            return Map.of(
                    "success", false,
                    "message", "That's not right. Try again!",
                    "roomComplete", false,
                    "gameComplete", false
            );
        }
    }

    public Map<String, Object> getHint(String sessionId, String puzzleId) {
        if (puzzleId == null) {
            return Map.of("hint", "Missing puzzle ID!", "hintsUsed", 0, "maxHints", 0);
        }

        GameSession session = sessions.get(sessionId);
        if (session == null) {
            return Map.of("hint", "Session not found!", "hintsUsed", 0, "maxHints", 0);
        }

        Room currentRoom = session.getCurrentRoom();
        if (currentRoom == null) {
            return Map.of("hint", "Room not found!", "hintsUsed", 0, "maxHints", 0);
        }

        Puzzle puzzle = currentRoom.getPuzzles().stream()
                .filter(p -> p.getId().equals(puzzleId))
                .findFirst()
                .orElse(null);

        if (puzzle == null) {
            return Map.of("hint", "Puzzle not found!", "hintsUsed", 0, "maxHints", 0);
        }

        session.setTotalHintsUsed(session.getTotalHintsUsed() + 1);
        String hint = puzzle.getNextHint();

        return Map.of(
                "hint", hint,
                "hintsUsed", puzzle.getHintsUsed(),
                "maxHints", puzzle.getHints().size()
        );
    }

    public boolean moveToNextRoom(String sessionId) {
        GameSession session = sessions.get(sessionId);
        if (session == null) return false;

        Room currentRoom = session.getCurrentRoom();
        if (currentRoom.areAllPuzzlesSolved() && currentRoom.getNextRoomId() != null) {
            session.setCurrentRoomId(currentRoom.getNextRoomId());
            return true;
        }
        return false;
    }
}
