package com.escape.game.model;

import java.util.List;

public class Puzzle {

    private String id;
    private String title;
    private String description;
    private String question;
    private String answer;
    private List<String> hints;
    private PuzzleType type;
    private int points;
    private boolean solved;
    private int hintsUsed;

    public enum PuzzleType {
        RIDDLE, CODE_CRACK, LOGIC, PATTERN, WORD, MATH
    }

    public Puzzle() {}

    public Puzzle(String id, String title, String description, String question,
                  String answer, List<String> hints, PuzzleType type, int points) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.question = question;
        this.answer = answer;
        this.hints = hints;
        this.type = type;
        this.points = points;
        this.solved = false;
        this.hintsUsed = 0;
    }

    public boolean checkAnswer(String attempt) {
        if (attempt == null || answer == null) {
            return false;
        }
        return answer.equalsIgnoreCase(attempt.trim());
    }

    public String getNextHint() {
        if (hintsUsed < hints.size()) {
            return hints.get(hintsUsed++);
        }
        return "No more hints available!";
    }

    public int getCalculatedPoints() {
        return Math.max(points - (hintsUsed * 10), 10);
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getQuestion() { return question; }
    public void setQuestion(String question) { this.question = question; }
    public String getAnswer() { return answer; }
    public void setAnswer(String answer) { this.answer = answer; }
    public List<String> getHints() { return hints; }
    public void setHints(List<String> hints) { this.hints = hints; }
    public PuzzleType getType() { return type; }
    public void setType(PuzzleType type) { this.type = type; }
    public int getPoints() { return points; }
    public void setPoints(int points) { this.points = points; }
    public boolean isSolved() { return solved; }
    public void setSolved(boolean solved) { this.solved = solved; }
    public int getHintsUsed() { return hintsUsed; }
    public void setHintsUsed(int hintsUsed) { this.hintsUsed = hintsUsed; }
}
