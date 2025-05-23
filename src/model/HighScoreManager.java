package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class HighScoreManager {
    private static final String FILE_PATH = "highscores.tar";
    private List<ScoreEntry> highScores = new ArrayList<>();

    public HighScoreManager() {
        loadScores();
    }

    /**
     * Ajoute un nouveau score à la liste, trie, limite à 10, et sauvegarde.
     *
     * @param name  Nom du joueur
     * @param score Score obtenu
     */
    public void addScore(String name, int score) {
        highScores.add(new ScoreEntry(name, score));
        // Tri décroissant par score
        highScores.sort((a, b) -> b.getScore() - a.getScore());
        // Garde uniquement les 10 meilleurs scores
        if (highScores.size() > 10) {
            highScores = highScores.subList(0, 10);
        }
        // Sauvegarde dans le fichier
        saveScores();
    }

    /**
     * Retourne la liste des meilleurs scores.
     *
     * @return Liste des meilleurs scores
     */
    public List<ScoreEntry> getHighScores() {
        return highScores;
    }

    /**
     * Sauvegarde les scores dans un fichier texte (format : nom:score).
     */
    private void saveScores() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            out.writeObject(highScores);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadScores() {
        File file = new File(FILE_PATH);
        if (file.exists()) {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
                highScores = (List<ScoreEntry>) in.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}

