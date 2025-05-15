package model;

public class Grille {
    private boolean[][] tab;
    private PieceCourante pc;
    private int pcX = 0;
    private int pcY = 0;

    public Grille(boolean[][] tab, PieceCourante pc) {
        this.tab = tab;
        this.pc= pc;
    }

    public boolean[][] getTab() {
        return tab;
    }

    public void setTab(boolean[][] tab) {
        this.tab = tab;
    }

    public PieceCourante getPc() {
        return pc;
    }

    public void setPc(PieceCourante pc) {
        this.pc = pc;
    }

    public boolean move (PieceCourante pieceCourante, Direction direction){
        return true;
    }

    public boolean[][] getGlobalState(){
        boolean[][] tabGlobal = tab.clone();
        //parcourir la petite grille de la pièce courante
        for(int i = 0; i<4; i++){
            for(int j=0; j<4; j++){
                if(pc.getMotif()[i][j]!=0){
                    //le +2 sur pcX+j permet de centrer la pièce au départ
                    tabGlobal[pcX+i][pcX+j+2]=true;
                }
            }
        }
        return tabGlobal;

    }


}
