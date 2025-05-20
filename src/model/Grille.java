package model;

public class Grille {
    private boolean[][] tab;
    private PieceCourante pc;
    private int pcX = 0;
    private int pcY = 3;

    private int lineDone = 0;

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

    public int getLineDone() {
        return lineDone;
    }

    public boolean checkMove(int x, int y, PieceCourante pieceCourante){
        return checkMovePossible( pieceCourante, x, y);
    }

    public boolean checkMovePossible(PieceCourante pc, int x, int y){
        int[][] motif = pc.getMotif();
        int motifSize = motif.length;

        //parcourir la petite grille de la pièce courante
        for(int i = 0; i<motifSize; i++){
            for(int j=0; j<motifSize; j++){
                if (motif[i][j]!=0){
                    int newX = pcX + i + x;
                    int newY = pcY + j + y;

                    //verify out of bounds
                    if(newX >= tab.length || newY<0 || newY>=tab[0].length){

                        System.out.println("Illégal");
                        return false;
                    }

                    //verify collision
                    if(tab[newX][newY] && newX>=0){
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

    public void checkIfRowCompleted(){
        int count =0;
        for(int i = 0; i<this.tab.length; i++){
            boolean full = true;
            for(int j=0; j<this.tab[i].length; j++){
                if(tab[i][j]==false){
                    full = false;
                    break;
                }

            }

            if(full){
                removeLine(i);
                movePiecesDownAfterLineRemoved(i);
                i--;
                count++;
            }
        }
        if(count>1){
            lineDone=(lineDone+20)^count;
        }else if (count==1){
            lineDone+=20;
        }
        System.out.println("lines done "+lineDone);

    }

    public void removeLine(int i){
        for(int j = 0; j<this.tab[i].length;j++){
            tab[i][j]=false;
        }
    }

    public void movePiecesDownAfterLineRemoved(int fromRow){
        for (int i = fromRow; i > 0; i--) {
            for (int j = 0; j < tab[i].length; j++) {
                tab[i][j] = tab[i - 1][j];
            }
        }

        // Clear top row
        for (int j = 0; j < tab[0].length; j++) {
            tab[0][j] = false;
        }
    }

    public boolean checkIfGridBlocked(){
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < tab[i].length; j++) {
                if (tab[i][j]) {
                    return true;
                }
            }
        }
        return false;
    }



    public void clear() {
        for(int i = 0; i<this.tab.length; i++){
            for(int j=0; j<this.tab[i].length; j++){
                this.tab[i][j]=false;
            }
        }
        pc= null;
        pcX=0;
        pcY=0;
        lineDone=0;



    }
}
