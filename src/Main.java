import model.M;
import vuecontroller.VC;

public class Main {
    public static void main(String[] args) {
        //améliorer en créant un processus spécial pour effectuer les opérations graphiques
        M m = new M();
        VC vc = new VC(m);
        m.addObserver(vc);
        vc.setVisible(true);
    }
}