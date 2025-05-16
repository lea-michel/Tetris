package model;

import java.util.Observable;

public class Jeu extends Observable implements Runnable{

    private PieceCourante pc;
    private Case selectedCase;
    private Grille grille;

    private static int ROW = 20;
    private static int COL = 10;

    private Ordonnanceur ordo = new Ordonnanceur(this,1000);

    public Jeu() {
        ordo.start();
        boolean[][] tab = new boolean[ROW][COL];
        pc = getNouvellePiece();
        this.grille = new Grille(tab, pc);
        //this.grille.setTab(this.grille.getGlobalState());

    }

    public Grille getGrille() {
        return grille;
    }

    public void playPause() {
        ordo.onOff();
    }

    @Override
    public void run() {
        
        if(grille!=null){
            this.grille.setTab(this.grille.getGlobalState());
        }
        //pc.move();


        setChanged();
        notifyObservers();
    }

    private PieceCourante getNouvellePiece(){
        return new PieceCourante(this.grille);
    }



}
