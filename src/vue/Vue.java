package vue;

import model.Jeu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;

public class Vue extends JFrame implements Observer {

    public Jeu jeu;

    JPanel[][] tab = new JPanel[20][10];
    JPanel[][] prev = new JPanel[4][4];

    public Vue(Jeu jeu) throws HeadlessException {
        this.jeu = jeu;
        this.setTitle("Taie Triste");
        JPanel borderPanel = new JPanel(new BorderLayout());

        // Créer la grille avec GridLayout
        JPanel gridPanel = new JPanel(new GridLayout(20, 10));

        // Créer la prévision des pièces


        JPanel prevPiece = new JPanel(new GridLayout(4, 4));

        /*
        prevPiece.setPreferredSize(new Dimension(100, 100));
        prevPiece.setMinimumSize(new Dimension(100, 100));
        prevPiece.setMaximumSize(new Dimension(100, 100));
        prevPiece.setBackground(Color.RED);

        JPanel contenPrev = new JPanel(new BoxLayout(prevPiece));
        */

        // Ajouter les éléments du BorderLayout
        borderPanel.add(gridPanel, BorderLayout.CENTER);
        borderPanel.add(prevPiece, BorderLayout.NORTH);


        for (int i=0; i<4; i++) {
            for (int j = 0; j < 4; j++) {
                JPanel c = new JPanel();
                c.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                prev[i][j] = c; //permet de sauvegarder la position de chaque case

                final int row = i;
                final int col = j;

                c.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        System.out.println("row/col: " + row + "/" + col);
                        // jeu.set(row,col);
                    }
                });
                prevPiece.add(c);
            }
        }

        for (int i=0; i<20; i++){
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
                        // jeu.set(row,col);
                    }
                });
                gridPanel.add(c);
            }
        }

        this.add(borderPanel);
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


        }

    }

