package model;

public class PieceCourante {
    private int[][][] motif;

    private int currentPosition = 1;

    private Grille grille;

    public PieceCourante(Grille grille) {
        this.motif = genererMotif();
        this.grille = grille;
    }

    public void rotation(Direction direction) {
        if (direction == Direction.DROITE) {
            currentPosition--;
            currentPosition = ((currentPosition % 4) + 4) % 4;

        } else {
            currentPosition++;
            currentPosition = ((currentPosition % 4) + 4) % 4;
        }

    }

    public int[][] getMotif() {
        return motif[currentPosition];
    }

    public int[][] getMotifGauche () {
        int positionTest = currentPosition + 1;
        positionTest = ((positionTest % 4) + 4) % 4;
        return motif[positionTest];
    }

    public int[][] getMotifDroite () {
        int positionTest = currentPosition - 1;
        positionTest = ((positionTest % 4) + 4) % 4;
        return motif[positionTest];
    }


    public boolean printMove(int x, int y){
        if(grille!=null){
            return grille.checkMove(x, y, this);
        }
        else return false;
    }

    private int[][][] genererMotif () {
        //chaque chiffre dans une pièce correspond à une couleur différente
        int[][][] Piece1 = {{
                {0, 1, 0, 0},
                {0, 1, 1, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 0}},
        {
                {0, 0, 0, 0},
                {0, 0, 1, 1},
                {0, 1, 1, 0},
                {0, 0, 0, 0}},

        {
                {0, 1, 0, 0},
                {0, 1, 1, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 0}},
        {
                {0, 0, 0, 0},
                {0, 0, 1, 1},
                {0, 1, 1, 0},
                {0, 0, 0, 0}}};

        int[][][] Piece2 = {{{0, 2, 2, 0}, {0, 2, 2, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}}, {{0, 2, 2, 0}, {0, 2, 2, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}}, {{0, 2, 2, 0}, {0, 2, 2, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}}, {{0, 2, 2, 0}, {0, 2, 2, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}}};

        int[][][] Piece3 = {{
                {0, 3, 0, 0},
                {0, 3, 0, 0},
                {0, 3, 0, 0},
                {0, 3, 0, 0}},
        {
                {3, 3, 3, 3},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}},
                {{0, 3, 0, 0}, {0, 3, 0, 0}, {0, 3, 0, 0}, {0, 3, 0, 0}}, {{3, 3, 3, 3}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}}};
        int[][][] Piece4 = {{{0, 0, 4, 0}, {0, 4, 4, 0}, {0, 4, 0, 0}, {0, 0, 0, 0}}, {{0, 0, 0, 0}, {0, 4, 4, 0}, {0, 0, 4, 4}, {0, 0, 0, 0}}, {{0, 0, 4, 0}, {0, 4, 4, 0}, {0, 4, 0, 0}, {0, 0, 0, 0}}, {{0, 0, 0, 0}, {0, 4, 4, 0}, {0, 0, 4, 4}, {0, 0, 0, 0}}};

        int[][][] Piece5 = {
        {
                {0, 5, 0, 0},
                {0, 5, 5, 0},
                {0, 5, 0, 0},
                {0, 0, 0, 0}},

        {       {0, 0, 0, 0},
                {0, 0, 5, 0},
                {0, 5, 5, 5},
                {0, 0, 0, 0}}, {{0, 0, 0, 5}, {0, 0, 5, 5}, {0, 0, 0, 5}, {0, 0, 0, 0}}, {{0, 5, 5, 5}, {0, 0, 5, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}}};
        int[][][] Piece6 = {{{0, 0, 6, 0}, {0, 0, 6, 0}, {0, 6, 6, 0}, {0, 0, 0, 0}}, {{0, 0, 0, 0}, {0, 6, 6, 6}, {0, 0, 0, 6}, {0, 0, 0, 0}}, {{0, 6, 6, 0}, {0, 6, 0, 0}, {0, 6, 0, 0}, {0, 0, 0, 0}}, {{0, 0, 0, 0}, {0, 6, 0, 0}, {0, 6, 6, 6}, {0, 0, 0, 0}}};
        int[][][] Piece7 = {{{0, 7, 0, 0}, {0, 7, 0, 0}, {0, 7, 7, 0}, {0, 0, 0, 0}}, {{0, 0, 0, 0}, {0, 0, 0, 7}, {0, 7, 7, 7}, {0, 0, 0, 0}}, {{0, 7, 7, 0}, {0, 0, 7, 0}, {0, 0, 7, 0}, {0, 0, 0, 0}}, {{0, 0, 0, 0}, {0, 7, 7, 7}, {0, 7, 0, 0}, {0, 0, 0, 0}}};

        int[][][][] tabMotifs= { Piece1, Piece2, Piece3, Piece4, Piece5, Piece6, Piece7};

        int randomNum = (int)(Math.random() * tabMotifs.length);

        return tabMotifs[randomNum];

    }

}
