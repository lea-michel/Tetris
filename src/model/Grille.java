package model;

public class Grille {
    private boolean[][] tab;

    public Grille(boolean[][] tab) {
        this.tab = tab;
    }

    public boolean[][] getTab() {
        return tab;
    }

    public boolean move (PieceCourante pieceCourante, Direction direction){
        return true;
    }
}
