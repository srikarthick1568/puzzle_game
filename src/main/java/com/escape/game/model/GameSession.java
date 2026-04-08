package com.escape.game.model;

import java.time.LocalDateTime;
import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.Map;

public class GameSession {

    private String sessionId;
    private String playerName;
    private Map<String, Room> rooms;
    private String currentRoomId;
    private int totalScore;
    private int totalHintsUsed;
    private int totalAttempts;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private GameStatus status;

    public enum GameStatus {
        IN_PROGRESS, ESCAPED, GAVE_UP
    }

    public GameSession() {
        this.rooms = new LinkedHashMap<>();
        this.status = GameStatus.IN_PROGRESS;
        this.startTime = LocalDateTime.now();
    }

    public String getElapsedTime() {
        LocalDateTime end = (endTime != null) ? endTime : LocalDateTime.now();
        Duration duration = Duration.between(startTime, end);
        long minutes = duration.toMinutes();
        long seconds = duration.toSecondsPart();
        return String.format("%02d:%02d", minutes, seconds);
    }

    public long getElapsedSeconds() {
        LocalDateTime end = (endTime != null) ? endTime : LocalDateTime.now();
        return Duration.between(startTime, end).getSeconds();
    }

    public boolean isGameComplete() {
        return rooms.values().stream().allMatch(Room::areAllPuzzlesSolved);
    }

    public void calculateTotalScore() {
        this.totalScore = rooms.values().stream().mapToInt(Room::getTotalPoints).sum();
        // Time bonus: extra points for finishing quickly
        long seconds = getElapsedSeconds();
        if (seconds < 300) totalScore += 200;       // Under 5 min
        else if (seconds < 600) totalScore += 100;   // Under 10 min
        else if (seconds < 900) totalScore += 50;    // Under 15 min
    }

    public Room getCurrentRoom() {
        return rooms.get(currentRoomId);
    }

    public int getRoomNumber() {
        int index = 1;
        for (String roomId : rooms.keySet()) {
            if (roomId.equals(currentRoomId)) return index;
            index++;
        }
        return 1;
    }

    // Getters and Setters
    public String getSessionId() { return sessionId; }
    public void setSessionId(String sessionId) { this.sessionId = sessionId; }
    public String getPlayerName() { return playerName; }
    public void setPlayerName(String playerName) { this.playerName = playerName; }
    public Map<String, Room> getRooms() { return rooms; }
    public void setRooms(Map<String, Room> rooms) { this.rooms = rooms; }
    public String getCurrentRoomId() { return currentRoomId; }
    public void setCurrentRoomId(String currentRoomId) { this.currentRoomId = currentRoomId; }
    public int getTotalScore() { return totalScore; }
    public void setTotalScore(int totalScore) { this.totalScore = totalScore; }
    public int getTotalHintsUsed() { return totalHintsUsed; }
    public void setTotalHintsUsed(int totalHintsUsed) { this.totalHintsUsed = totalHintsUsed; }
    public int getTotalAttempts() { return totalAttempts; }
    public void setTotalAttempts(int totalAttempts) { this.totalAttempts = totalAttempts; }
    public LocalDateTime getStartTime() { return startTime; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }
    public LocalDateTime getEndTime() { return endTime; }
    public void setEndTime(LocalDateTime endTime) { this.endTime = endTime; }
    public GameStatus getStatus() { return status; }
    public void setStatus(GameStatus status) { this.status = status; }
}
