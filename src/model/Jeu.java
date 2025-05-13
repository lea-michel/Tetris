package model;

import java.util.Observable;

public class Jeu extends Observable implements Runnable{

    private PieceCourante pc;

    public Jeu() {
        new Ordonnanceur(this,1000).start();
        // appeler fonction demarrerPartie
    }

    @Override
    public void run() {

    }

    public void demarrerPartie (){
        //créer pièce courante

    }


}
