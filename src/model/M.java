package model;

import java.util.Observable;

public class M extends Observable implements Runnable{

    private Case caseSelectionnee;


    public M() {
        new Ordonnanceur(this,1000).start();
    }

    public Case getCaseSelectionnee() {
        return caseSelectionnee;
    }

    public void set(int x, int y){
        caseSelectionnee = new Case(x,y);
        setChanged();
        notifyObservers();
    }

    @Override
    public void run() {
        
        if(caseSelectionnee!=null && caseSelectionnee.getX()<9){
            caseSelectionnee.setX(caseSelectionnee.getX()+1);
        }
        setChanged();
        notifyObservers();
    }
}
