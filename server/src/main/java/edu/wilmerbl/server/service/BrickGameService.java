package edu.wilmerbl.server.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import edu.wilmerbl.logic.Action;
import edu.wilmerbl.logic.Field;
import edu.wilmerbl.logic.State;
import org.springframework.stereotype.Service;

@Service
public class BrickGameService {

    private Field field;
    private int highScore = 0;

    private int score = 0;
    private int speed = 500;

    private int boost = 0;
    private boolean pause = false;
    private boolean isRunning = false;

    public BrickGameService() {
        this.field = new Field();
        startGameLoop();
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void userInput(Action action) {
        boost = 0;
        switch (action) {
            case Left:
                field.getPlayer().moveLeft();
                break;
            case Right:
                field.getPlayer().moveRight();
                break;
            case Start:
                resetGame();
                break;
            case Pause:
                pause = !pause;
                break;
            case Action:
                boost = 150;
                break;
        }
    }

    public State getState() {
        int gameSpeed = 100000 / (speed - (field.getLevel() * 20) - boost);
        return new State(field.getField(), field.getScore(), highScore, field.getLevel(),
                gameSpeed,
                pause);
    }

    private void resetGame() {
        field = new Field();
        speed = 500;
        pause = false;
        isRunning = false;
    }

    private void startGameLoop() {
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(speed - (field.getLevel() * 20L) - boost);
                    if (!pause && !isRunning) {
                        update();
                        updateHighScore();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void update() {
        field.update();
        score = field.getScore();
        if (field.isGameOver()) {
            isRunning = true;
        }
        updateHighScore();
    }

    private void updateHighScore() {
        try {
            if (!Files.exists(Paths.get("score.txt"))) {
                Files.createFile(Paths.get("score.txt"));
                highScore = 0;
            } else {
                try (BufferedReader reader = new BufferedReader(new FileReader("score.txt"))) {
                    String line = reader.readLine();
                    if (line != null) {
                        highScore = Integer.parseInt(line);
                    } else {
                        highScore = 0;
                    }
                }
            }
            if (score > highScore) {
                highScore = score;
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("score.txt"))) {
                    writer.write(String.valueOf(highScore));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}