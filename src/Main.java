import model.Jeu;
import vue.VueController;

public class Main {
    public static void main(String[] args) {
        //améliorer en créant un processus spécial pour effectuer les opérations graphiques
        Jeu jeu = new Jeu();
        VueController vue = new VueController(jeu);
        jeu.addObserver(vue);
        vue.setVisible(true);

    }
}