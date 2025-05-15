package model;

import java.util.Observable;

public class Jeu extends Observable implements Runnable{

    private PieceCourante pc;
    private Case selectedCase;
    private Grille grille;

    private static int ROW = 20;
    private static int COL = 10;
    public Jeu() {
        new Ordonnanceur(this,1000).start();
        boolean[][] tab = new boolean[ROW][COL];
        this.grille = new Grille(tab);
        pc = getNouvellePiece();

    }

    public void set(int row, int col) {
        selectedCase = new Case(row, col);
    }

    public Case getCaseSelectionnee() {
        return this.selectedCase;
    };

    @Override
    public void run() {

    }

    private PieceCourante getNouvellePiece(){
        return new PieceCourante(this.grille);
    }



}
