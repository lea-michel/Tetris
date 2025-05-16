package model;

import java.util.Observable;

public class Jeu extends Observable implements Runnable{

    private PieceCourante pc;
    private PieceCourante nextPc;
    private Case selectedCase;
    private Grille grille;

    private static int ROW = 20;
    private static int COL = 10;

    private boolean gameOver = false;

    public Jeu() {
        new Ordonnanceur(this,300).start();
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

    public PieceCourante getNextPc() {
        return nextPc;
    }

    @Override
    public void run() {

        if(pc!=null){

            if(pc.move()){
                if (nextPc==null) {
                    nextPc=getNouvellePiecePrev();
                }
                grille.movePieceDown();
            }else {
                System.out.println("piece blocked");
                this.grille.lockPiece();

                grille.initPosPc();
                pc = nextPc;
                grille.setPieceCourante(pc);


                nextPc=null;

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
                grille.setPcY(3);
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

    private  PieceCourante getNouvellePiecePrev(){
        PieceCourante newPieceCourante = new PieceCourante(this.grille);
        return newPieceCourante;
    }



}