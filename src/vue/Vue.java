package vue;

import model.Jeu;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
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
        prevPiece.setSize(new Dimension(50,50));
        JPanel contenPrev = new JPanel(new BorderLayout());

        // Créer des JPanel neutres pour remplir
        JPanel northPanel = new JPanel();
        JPanel southPanel = new JPanel();
        JPanel eastPanel = new JPanel();
        JPanel westPanel = new JPanel();

        // Créer l'espace du bouton play-pause

        JPanel playPause = new JPanel();

        JLabel play = new JLabel("Play");
        JLabel pause = new JLabel("Pause");
        play.setBorder(new LineBorder(Color.BLACK));
        pause.setBorder(new LineBorder(Color.BLACK));

        // Assembler les composants

        playPause.add(play);

        northPanel.setPreferredSize(new Dimension(50,50));
        southPanel.setPreferredSize(new Dimension(50,50));
        eastPanel.setPreferredSize(new Dimension(50,50));
        westPanel.setPreferredSize(new Dimension(50,50));



        // Ajouter les éléments du BorderLayout
        contenPrev.add(prevPiece, BorderLayout.CENTER);
        contenPrev.add(northPanel, BorderLayout.NORTH);
        contenPrev.add(southPanel, BorderLayout.SOUTH);
        contenPrev.add(eastPanel, BorderLayout.EAST);
        contenPrev.add(westPanel, BorderLayout.WEST);

        borderPanel.add(gridPanel, BorderLayout.CENTER);
        borderPanel.add(contenPrev, BorderLayout.NORTH);
        borderPanel.add(new JPanel(), BorderLayout.EAST);
        borderPanel.add(new JPanel(), BorderLayout.WEST);
        borderPanel.add(playPause, BorderLayout.SOUTH);

        playPause.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                jeu.playPause();
            }
        });

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

//                c.addMouseListener(new MouseAdapter() {
//                    @Override
//                    public void mouseClicked(MouseEvent e) {
//                        System.out.println("row/col: " + row +"/"+col);
//                        jeu.set(row,col);
//                        update(new Observable(), jeu);
//                    }
//                });
                gridPanel.add(c);
            }
        }

        this.add(borderPanel);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void update(Observable o, Object arg) {
        //mettre en blanc le background de toutes les cases
        for(int i=0; i<20; i++){
            for(int j=0; j<10; j++){

                if(this.jeu.getGrille().getTab()[i][j]!=false){
                    tab[i][j].setBackground(Color.BLUE);
                }

            }
        }

        //mettre en bleu la case sélectionnée
//        if(jeu.getCaseSelectionnee()!=null){
//            tab[jeu.getCaseSelectionnee().getX()][jeu.getCaseSelectionnee().getY()].setBackground(Color.blue);
//        }

        }

    }

