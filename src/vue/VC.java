package vue;

import model.M;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;

public class VC extends JFrame implements Observer {

    public M m;

    JPanel[][] tab;

    public VC(M m) throws HeadlessException {
        this.m=m;
        this.setTitle("MVC Example");
        JPanel jp = new JPanel(new GridLayout(10,10));

        tab = new JPanel[10][10];

        for (int i=0; i<10; i++){
            for (int j=0; j<10; j++){
                JPanel c = new JPanel();
                c.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                tab[i][j]= c; //permet de sauvegarder la position de chaque case

                final int row = i;
                final int col = j;

                c.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        System.out.println("row/col: " + row +"/"+col);
                        m.set(row,col);
                    }
                });
                jp.add(c);

            }
        }

        this.add(jp);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    @Override
    public void update(Observable o, Object arg) {
        //mettre en blanc le background de toutes les cases
        for(int i=0; i<tab.length; i++){
            for(int j=0; j< tab.length; j++){
                tab[i][j].setBackground(Color.WHITE);
            }
        }

        //mettre en bleu la case sélectionnée
        if(m.getCaseSelectionnee()!=null){
            tab[m.getCaseSelectionnee().getX()][m.getCaseSelectionnee().getY()].setBackground(Color.blue);
        }

    }


}
