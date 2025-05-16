package model;

public class PieceCourante {
    private int[][][] motif;

    private int currentPosition = 1;

    private Grille grille;

    public PieceCourante(Grille grille) {
        this.motif = genererMotif();
        this.grille = grille;
    }

    public void setGrille(Grille grille) {
        this.grille = grille;
    }

    public int[][] getMotif() {
        return motif[currentPosition];
    }

    public boolean move(){
        if(grille!=null){
            System.out.println("move");
            return grille.move(this);
        }
        else return false;
    }

    public boolean rotate(Direction direction){
        switch (direction){
            case DROITE : currentPosition = 1 ; break;
            case BAS : currentPosition = 0; break;
            case GAUCHE : currentPosition = 3;break;
            case HAUT : currentPosition = 2 ; break;
        }

        return true;
    }

    private int[][][] genererMotif () {
        //chaque chiffre dans une pièce correspond à une couleur différente
        int[][][] Piece1 = {{{0, 1, 0, 0}, {0, 1, 1, 0}, {0, 0, 1, 0}, {0, 0, 0, 0}}, {{0, 0, 0, 0}, {0, 0, 1, 1}, {0, 1, 1, 0}, {0, 0, 0, 0}}, {{0, 1, 0, 0}, {0, 1, 1, 0}, {0, 0, 1, 0}, {0, 0, 0, 0}}, {{0, 0, 0, 0}, {0, 0, 1, 1}, {0, 1, 1, 0}, {0, 0, 0, 0}}};
        int[][][] Piece2 = {{{0, 2, 2, 0}, {0, 2, 2, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}}, {{0, 2, 2, 0}, {0, 2, 2, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}}, {{0, 2, 2, 0}, {0, 2, 2, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}}, {{0, 2, 2, 0}, {0, 2, 2, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}}};
        int[][][] Piece3 = {{{0, 3, 0, 0}, {0, 3, 0, 0}, {0, 3, 0, 0}, {0, 3, 0, 0}}, {{3, 3, 3, 3}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}}, {{0, 3, 0, 0}, {0, 3, 0, 0}, {0, 3, 0, 0}, {0, 3, 0, 0}}, {{3, 3, 3, 3}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}}};
        int[][][] Piece4 = {{{0, 0, 4, 0}, {0, 4, 4, 0}, {0, 4, 0, 0}, {0, 0, 0, 0}}, {{0, 0, 0, 0}, {0, 4, 4, 0}, {0, 0, 4, 4}, {0, 0, 0, 0}}, {{0, 0, 4, 0}, {0, 4, 4, 0}, {0, 4, 0, 0}, {0, 0, 0, 0}}, {{0, 0, 0, 0}, {0, 4, 4, 0}, {0, 0, 4, 4}, {0, 0, 0, 0}}};
        int[][][] Piece5 = {{{0, 5, 0, 0}, {0, 5, 5, 0}, {0, 5, 0, 0}, {0, 0, 0, 0}}, {{0, 0, 0, 0}, {0, 0, 5, 0}, {0, 5, 5, 5}, {0, 0, 0, 0}}, {{0, 0, 0, 5}, {0, 0, 5, 5}, {0, 0, 0, 5}, {0, 0, 0, 0}}, {{0, 5, 5, 5}, {0, 0, 5, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}}};
        int[][][] Piece6 = {{{0, 0, 6, 0}, {0, 0, 6, 0}, {0, 6, 6, 0}, {0, 0, 0, 0}}, {{0, 0, 0, 0}, {0, 6, 6, 6}, {0, 0, 0, 6}, {0, 0, 0, 0}}, {{0, 6, 6, 0}, {0, 6, 0, 0}, {0, 6, 0, 0}, {0, 0, 0, 0}}, {{0, 0, 0, 0}, {0, 6, 0, 0}, {0, 6, 6, 6}, {0, 0, 0, 0}}};
        int[][][] Piece7 = {{{0, 7, 0, 0}, {0, 7, 0, 0}, {0, 7, 7, 0}, {0, 0, 0, 0}}, {{0, 0, 0, 0}, {0, 0, 0, 7}, {0, 7, 7, 7}, {0, 0, 0, 0}}, {{0, 7, 7, 0}, {0, 0, 7, 0}, {0, 0, 7, 0}, {0, 0, 0, 0}}, {{0, 0, 0, 0}, {0, 7, 7, 7}, {0, 7, 0, 0}, {0, 0, 0, 0}}};

        int[][][][] tabMotifs= { Piece1, Piece2, Piece3, Piece4, Piece5, Piece6, Piece7};

        int randomNum = (int)(Math.random() * tabMotifs.length);

        return tabMotifs[randomNum];

    }

}
