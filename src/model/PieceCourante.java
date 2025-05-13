package model;

public class PieceCourante {
    private boolean[][] motif;

    private Grille grille;

    public PieceCourante(boolean[][] motif, Grille grille) {
        this.motif = motif;
        this.grille = grille;
    }

    public boolean[][] getMotif() {
        return motif;
    }

    public boolean move(Direction direction){
        return true;
    }


}
