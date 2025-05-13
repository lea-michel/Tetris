package model;

import java.util.Observable;

public class Jeu extends Observable implements Runnable{

    private PieceCourante pc;
    private Grille grille;

    private static int ROW = 20;
    private static int COL = 10;
    public Jeu() {
        new Ordonnanceur(this,1000).start();
        boolean[][] tab = new boolean[ROW][COL];
        this.grille = new Grille(tab);
        pc = getNouvellePiece();

    }

    @Override
    public void run() {

    }

    private PieceCourante getNouvellePiece(){
        return new PieceCourante(this.grille);
    }



}
