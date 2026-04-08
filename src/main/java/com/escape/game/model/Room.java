package com.escape.game.model;

import java.util.List;

public class Room {

    private String id;
    private String name;
    private String description;
    private String theme;
    private String backgroundImage;
    private List<Puzzle> puzzles;
    private String nextRoomId;
    private boolean unlocked;

    public Room() {}

    public Room(String id, String name, String description, String theme,
                String backgroundImage, List<Puzzle> puzzles, String nextRoomId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.theme = theme;
        this.backgroundImage = backgroundImage;
        this.puzzles = puzzles;
        this.nextRoomId = nextRoomId;
        this.unlocked = false;
    }

    public boolean areAllPuzzlesSolved() {
        return puzzles.stream().allMatch(Puzzle::isSolved);
    }

    public int getSolvedCount() {
        return (int) puzzles.stream().filter(Puzzle::isSolved).count();
    }

    public int getTotalPoints() {
        return puzzles.stream()
                .filter(Puzzle::isSolved)
                .mapToInt(Puzzle::getCalculatedPoints)
                .sum();
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getTheme() { return theme; }
    public void setTheme(String theme) { this.theme = theme; }
    public String getBackgroundImage() { return backgroundImage; }
    public void setBackgroundImage(String backgroundImage) { this.backgroundImage = backgroundImage; }
    public List<Puzzle> getPuzzles() { return puzzles; }
    public void setPuzzles(List<Puzzle> puzzles) { this.puzzles = puzzles; }
    public String getNextRoomId() { return nextRoomId; }
    public void setNextRoomId(String nextRoomId) { this.nextRoomId = nextRoomId; }
    public boolean isUnlocked() { return unlocked; }
    public void setUnlocked(boolean unlocked) { this.unlocked = unlocked; }
}
