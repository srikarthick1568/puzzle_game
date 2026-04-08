package com.escape.game.service;

import com.escape.game.model.Puzzle;
import com.escape.game.model.Puzzle.PuzzleType;
import com.escape.game.model.Room;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class GameDataService {

    public Map<String, Room> createRooms() {
        Map<String, Room> rooms = new LinkedHashMap<>();

        // === ROOM 1: The Mysterious Library ===
        List<Puzzle> libraryPuzzles = new ArrayList<>();
        libraryPuzzles.add(new Puzzle(
                "p1", "The Ancient Riddle",
                "A dusty scroll lies open on the table...",
                "I have cities, but no houses live there. I have mountains, but no trees grow there. I have water, but no fish swim there. What am I?",
                "map",
                List.of("Think about something you can look at but not live in.",
                        "It represents the world but isn't the world itself.",
                        "You might fold it or hang it on a wall."),
                PuzzleType.RIDDLE, 50
        ));
        libraryPuzzles.add(new Puzzle(
                "p2", "The Bookshelf Code",
                "Numbers are carved into the spines of five books...",
                "The books on the shelf show: 2, 4, 8, 16, ___. What is the next number?",
                "32",
                List.of("Each number has a relationship to the one before it.",
                        "Try multiplying...",
                        "Each number is doubled."),
                PuzzleType.PATTERN, 40
        ));
        libraryPuzzles.add(new Puzzle(
                "p3", "The Cryptic Note",
                "A crumpled note reads a strange message...",
                "Decode: UIJOL PVUTJEF UIF CPY. (Each letter is shifted by 1 in the alphabet)",
                "think outside the box",
                List.of("Shift each letter BACK by one position.",
                        "U->T, I->H, J->I, O->N, L->K...",
                        "The first word is THINK."),
                PuzzleType.CODE_CRACK, 60
        ));
        Room library = new Room("library", "The Mysterious Library",
                "Dust particles float in beams of moonlight. Towering bookshelves surround you. The door you entered through has vanished. A faint glow emanates from three objects...",
                "dark-academia", "library-bg", libraryPuzzles, "lab");
        library.setUnlocked(true);
        rooms.put("library", library);

        // === ROOM 2: The Alchemist's Lab ===
        List<Puzzle> labPuzzles = new ArrayList<>();
        labPuzzles.add(new Puzzle(
                "p4", "The Potion Formula",
                "Bubbling flasks line the workbench with a formula written in symbols...",
                "If a potion needs 3 drops of moonlight for every 2 drops of shadow, how many drops of moonlight do you need for 10 drops of shadow?",
                "15",
                List.of("Set up a ratio: 3/2 = ?/10",
                        "Cross multiply: 3 × 10 = 2 × ?",
                        "30 / 2 = ?"),
                PuzzleType.MATH, 40
        ));
        labPuzzles.add(new Puzzle(
                "p5", "The Element Grid",
                "A magical grid glows on the wall. Each row and column must follow a rule...",
                "In a 3x3 grid, rows sum to 15:\n[8, ?, 4]\n[3, 5, 7]\n[?, 9, 2]\nWhat are the two missing numbers? (answer as: first,second)",
                "3,4",
                List.of("Each row must add up to 15.",
                        "Row 1: 8 + ? + 4 = 15",
                        "Row 3: ? + 9 + 2 = 15"),
                PuzzleType.LOGIC, 50
        ));
        labPuzzles.add(new Puzzle(
                "p6", "The Word Transmutation",
                "The alchemist's journal says: 'To unlock the door, transmute the word...'",
                "Rearrange the letters of LISTEN to form another word meaning 'not speaking'.",
                "silent",
                List.of("The answer is an anagram of LISTEN.",
                        "Think of the opposite of noisy.",
                        "S-I-L-E-N-T"),
                PuzzleType.WORD, 45
        ));
        rooms.put("lab", new Room("lab", "The Alchemist's Lab",
                "Green and purple liquids bubble in glass vessels. Strange symbols cover the walls. The air smells of sulfur and old parchment. Three challenges guard the exit...",
                "mystical", "lab-bg", labPuzzles, "vault"));

        // === ROOM 3: The Final Vault ===
        List<Puzzle> vaultPuzzles = new ArrayList<>();
        vaultPuzzles.add(new Puzzle(
                "p7", "The Lock Sequence",
                "A heavy iron door has a combination lock with a sequence...",
                "Complete the sequence: 1, 1, 2, 3, 5, 8, 13, ___",
                "21",
                List.of("This is a famous mathematical sequence.",
                        "Each number is the sum of the two before it.",
                        "8 + 13 = ?"),
                PuzzleType.PATTERN, 45
        ));
        vaultPuzzles.add(new Puzzle(
                "p8", "The Mirror Riddle",
                "Words appear backwards in an enchanted mirror...",
                "I am not alive, but I grow. I don't have lungs, but I need air. I don't have a mouth, but water kills me. What am I?",
                "fire",
                List.of("Think of something in nature that isn't living.",
                        "It needs oxygen to survive.",
                        "Firefighters use water to stop it."),
                PuzzleType.RIDDLE, 50
        ));
        vaultPuzzles.add(new Puzzle(
                "p9", "The Final Key",
                "A glowing keypad demands the master code...",
                "A clock shows 3:15. What is the angle between the hour and minute hands?",
                "7.5",
                List.of("The minute hand is at 3 (90 degrees from 12).",
                        "The hour hand moves 0.5 degrees per minute past 3.",
                        "At 3:15, the hour hand has moved 15 × 0.5 = 7.5 degrees past the 3."),
                PuzzleType.MATH, 70
        ));
        rooms.put("vault", new Room("vault", "The Final Vault",
                "You've reached the innermost chamber. Golden light pulses from ancient mechanisms. Three final challenges stand between you and freedom. The exit shimmers just beyond reach...",
                "golden", "vault-bg", vaultPuzzles, null));

        return rooms;
    }
}
