import model.M;
import vue.VC;
import model.Jeu;
import vue.Vue;

public class Main {
    public static void main(String[] args) {
        //améliorer en créant un processus spécial pour effectuer les opérations graphiques
        Jeu jeu = new Jeu();
        Vue vue = new Vue(jeu);
        jeu.addObserver(vue);
        vue.setVisible(true);


    }
}