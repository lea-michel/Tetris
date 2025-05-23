package model;

import java.util.Observable;

public class Jeu extends Observable implements Runnable {
    private PieceCourante pc;
    private PieceCourante nextPc;
    private Grille grille;
    private Ordonnanceur ordonnanceur;
    private static int ROW = 20;
    private static int COL = 10;
    private boolean gameOver = false;
    private int nextPosX;
    private int nextPosY;
    private ScoreEntry score;
    private HighScoreManager highScoreManager;

    public Jeu() {
        boolean[][] tab = new boolean[ROW][COL];

        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                tab[i][j] = false;
            }
        }

        this.grille = new Grille(tab);
        this.pc = getNouvellePiece();
        this.score = new ScoreEntry("", 0);
        this.highScoreManager = new HighScoreManager();

        if (grille.checkIfGridBlocked()) {
            gameOver = true;
            System.out.println("Cannot start game, grid already blocked");
        } else {
            ordonnanceur = new Ordonnanceur(this, 1000);
            ordonnanceur.start();
        }
    }

    public Grille getGrille() {
        return grille;
    }

    public PieceCourante getPc() {
        return pc;
    }

    public PieceCourante getNextPc() {
        return nextPc;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void playPause() {
        ordonnanceur.onOff();
    }

    public ScoreEntry getScore() {
        return score;
    }

    public HighScoreManager getHighScoreManager() {
        return highScoreManager;
    }

    public void movePc(int x, int y) {


       if(pc!=null){
           if (pc.printMove(x, y)) {
               this.grille.setPcX(this.grille.getPcX() + x);
               this.grille.setPcY(this.grille.getPcY() + y);
               setChanged();
               notifyObservers();
           }
       }

    }

    public void rotaPc(Direction direction) {
        if (grille.checkRotaPossible(pc, direction)) {
            pc.rotation(direction);
            setChanged();
            notifyObservers();
        }
    }

    @Override
    public void run() {
        if (gameOver) return;

        if (pc != null) {
            if (grille.checkIfRowCompleted()) {
                decreasePauseOrdonnanceur();
                this.score.setScore(this.grille.getScore());
                System.out.println(this.score.getScore() + "score jeu");
            }

            if (pc.printMove(1, 0)) {
                if (nextPc == null) {
                    nextPc = getNouvellePiecePrev();
                }
                grille.movePieceDown();
            } else {
                //System.out.println("X | Y : " + grille.getPcX() + grille.getPcY());
                //System.out.println("piece blocked");
                this.grille.lockPiece();

                //place the next piece
                grille.setPcY(nextPosY);
                grille.setPcX(nextPosX);
                pc = nextPc;
                grille.setPieceCourante(pc);

                nextPc = null;

                if (grille.checkIfGridBlocked()) {
                    gameOver = true;
                    setChanged();
                    notifyObservers();
                }

                if (gameOver) {
                    return;
                }
            }
        } else {
            pc = getNouvellePiece();
            grille.setPieceCourante(pc);
        }

        setChanged();
        notifyObservers();

    }

    private PieceCourante getNouvellePiece() {
        try {
            if (this.grille != null) {
                PieceCourante newPieceCourante = new PieceCourante(this.grille);

                //find first non-empty row in motif
                int[][] motif = newPieceCourante.getMotif();
                int firstNonEmptyRow = 0;

                for (int i = 0; i < motif.length; i++) {
                    boolean hasBlock = false;
                    for (int j = 0; j < motif[i].length; j++) {
                        if (motif[i][j] != 0) {
                            hasBlock = true;
                            break;
                        }
                    }
                    if (hasBlock) {
                        firstNonEmptyRow = i;
                        break;
                    }
                }

                nextPosX = -firstNonEmptyRow;
                nextPosY = COL / 2 - 2;

                grille.setPcX(nextPosX);
                grille.setPcY(nextPosY);

                if (grille.checkIfGridBlocked()) {
                    gameOver = true;
                    System.out.println("Game over :(");
                } else {
                    grille.setPieceCourante(newPieceCourante);
                    return newPieceCourante;
                }


            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private PieceCourante getNouvellePiecePrev() {
        PieceCourante newPieceCourante = new PieceCourante(this.grille);
        //find first non-empty row in motif
        int[][] motif = newPieceCourante.getMotif();
        int firstNonEmptyRow = 0;

        for (int i = 0; i < motif.length; i++) {
            boolean hasBlock = false;
            for (int j = 0; j < motif[i].length; j++) {
                if (motif[i][j] != 0) {
                    hasBlock = true;
                    break;
                }
            }
            if (hasBlock) {
                firstNonEmptyRow = i;
                break;
            }
        }

        nextPosX = -firstNonEmptyRow;
        nextPosY = COL / 2 - 2;

        return newPieceCourante;
    }

    public void restartGame() {
        grille.clear();
        pc = null;
        nextPc = null;
        gameOver = false;
        score.setScore(0);

        this.pc = getNouvellePiece();
        // Start the game again
        if (grille.checkIfGridBlocked()) {
            gameOver = true;
            System.out.println("Cannot start game, grid already blocked");
        } else {
            if (ordonnanceur != null) {
                ordonnanceur.restart();
            }
        }
    }

    private void decreasePauseOrdonnanceur() {
        if (grille.getLineDone() > 0 && grille.getLineDone() % 10 == 0 && ordonnanceur.getPause() > 100) {
            ordonnanceur.setPause(ordonnanceur.getPause() - 50L);
        }
    }

}