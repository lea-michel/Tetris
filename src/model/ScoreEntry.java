package model;

import java.io.Serializable;

public class ScoreEntry implements Serializable {
    private String name;
    private int score;

    public ScoreEntry(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() { return name; }

    public int getScore() { return score; }

    public void setScore(int score){
        this.score = score;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String toStringScore() {
        return String.valueOf(score);
    }

    @Override
    public String toString() {
        return name + ": " + score;
    }
}

