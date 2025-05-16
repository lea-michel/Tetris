package model;

public class Grille {
    private boolean[][] tab;
    private PieceCourante pc;
    private int pcX = 0;
    private int pcY = 3;



    public Grille(boolean[][] tab) {
        this.tab = tab;

    }

    public boolean[][] getTab() {
        return tab;
    }

    public void setTab(boolean[][] tab) {
        this.tab = tab;
    }

    public void setPieceCourante(PieceCourante pc) {
        this.pc = pc;
    }

    public PieceCourante getPc() {
        return pc;
    }

    public int getPcX() {
        return pcX;
    }

    public int getPcY() {
        return pcY;
    }

    public void setPcX(int pcX) {
        this.pcX = pcX;
    }

    public void setPcY(int pcY) {
        this.pcY = pcY;
    }

    public void initPosPc(){
        this.pcX=0;
        this.pcY=3;
    }

    public boolean move (PieceCourante pieceCourante){
        return checkMovePossible( pc, 1, 0);
    }

    public boolean checkMovePossible(PieceCourante pc, int x, int y){
        int[][] motif = pc.getMotif();
        int motifSize = motif.length;

        //parcourir la petite grille de la pièce courante
        for(int i = 0; i<motifSize; i++){
            for(int j=0; j<motifSize; j++){
                if (motif[i][j]!=0){
                    int newX = pcX + i + x;
                    int newY = pcY +j + j;

                    //verify out of bounds
                    if(newX >= tab.length || newY<0 || newY>=tab[0].length){
                        return false;
                    }

                    //verify collision
                    if(tab[newX][newY]){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void movePieceDown() {
        pcX++;
    }

    public boolean lockPiece(){
        int[][] motif = pc.getMotif();
        int motifSize = motif.length;
        //parcourir la petite grille de la pièce courante
        for(int i = 0; i<motifSize; i++){
            for(int j=0; j<motifSize; j++){
                if (motif[i][j]!=0){
                    this.tab[pcX+i][pcY+j]=true;
                }
            }
        }
        return true;


    }

    public boolean checkIfRowCompleted(){
        return true;
    }

    public boolean checkIfGridBlocked(){

        return true;
    }

    public void emptyGrille(){
        for(int i = 0; i<this.tab.length; i++){
            for(int j=0; j<this.tab.length; j++){
                this.tab[pcX+i][pcY+j]=false;
            }
        }
    }




}
