package edu.wilmerbl.logic;

public class State {

    public int[][] field;
    public int score;
    public int highScore;
    public int level;
    public int speed;
    public boolean pause;


    public State(int[][] field, int score, int highScore, int level, int speed, boolean pause) {
        this.field = field;
        this.score = score;
        this.highScore = highScore;
        this.level = level;
        this.speed = speed;
        this.pause = pause;
    }
}
