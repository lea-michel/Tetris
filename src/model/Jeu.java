package model;

import java.util.Observable;

public class Jeu extends Observable implements Runnable{

    private PieceCourante pc;
    private Case selectedCase;
    private Grille grille;

    private static int ROW = 20;
    private static int COL = 10;

    private boolean gameOver = false;

    public Jeu() {
        new Ordonnanceur(this,1000).start();
        boolean[][] tab = new boolean[ROW][COL];
        this.grille = new Grille(tab);
        this.pc = getNouvellePiece();
        grille.setPieceCourante(pc);

    }

    public Grille getGrille() {
        return grille;
    }

    public PieceCourante getPc() {
        return pc;
    }

    @Override
    public void run() {
        if(pc!=null){
            if(pc.move()){
                grille.movePieceDown();
            }else {
                System.out.println("piece blocked");
                pc=getNouvellePiece();

            }
        }else{
            pc=getNouvellePiece();
        }
        setChanged();
        notifyObservers();
    }

    private PieceCourante getNouvellePiece(){
        try{
            if (this.grille != null){
                PieceCourante newPieceCourante = new PieceCourante(this.grille);
                grille.setPieceCourante(newPieceCourante);
                grille.setPcX(0);
                grille.setPcY(0);
                if(!grille.checkMovePossible(newPieceCourante, 0, COL/2)){
                    gameOver = true;
                    System.out.println("Game over :(");
                }
                return newPieceCourante;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }



}