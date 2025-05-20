package vue;

import model.Jeu;
import model.PieceCourante;

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
    String playOrPause = "Pause";

    public void playPause() {
        if (playOrPause.equals("Play")) {
            playOrPause = "Pause";
        } else {
            playOrPause = "Play";
        }
    }

    public Vue(Jeu jeu) throws HeadlessException {
        this.jeu = jeu;
        this.setTitle("Taie Triste");
        JPanel borderPanel = new JPanel(new BorderLayout());

        // Créer la grille avec GridLayout
        JPanel gridPanel = new JPanel(new GridLayout(20, 10));
        gridPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
//        gridPanel.setBackground(Color.WHITE);

        // Créer la prévision des pièces
        JPanel prevPiece = new JPanel(new GridLayout(4, 4));
        prevPiece.setSize(new Dimension(50, 50));
        JPanel contenPrev = new JPanel(new BorderLayout());

        // Créer des JPanel neutres pour remplir
        JPanel northPanel = new JPanel();
        JPanel southPanel = new JPanel();
        JPanel eastPanel = new JPanel();
        JPanel westPanel = new JPanel();

        // Créer l'espace du bouton play-pause
        JPanel playPause = new JPanel();

        JLabel play = new JLabel(playOrPause);
        play.setBorder(new LineBorder(Color.BLACK));
        prevPiece.setBorder(new LineBorder(Color.BLACK));

        playPause.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("pause");
                jeu.playPause();
                playPause();
                play.setText(playOrPause);
                play.revalidate();  // Pour recalculer la mise en page
                play.repaint();
            }
        });

        // Assembler les composants

        playPause.add(play);

        northPanel.setPreferredSize(new Dimension(50, 50));
        southPanel.setPreferredSize(new Dimension(50, 50));
        eastPanel.setPreferredSize(new Dimension(50, 50));
        westPanel.setPreferredSize(new Dimension(50, 50));


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
                // jeu.playPause();
            }
        });

        // Remplissage des cases de prevPiece
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                JPanel c = new JPanel();
                c.setBorder(BorderFactory.createLineBorder(Color.WHITE));
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

        // Remplissage des cases de la Grille
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 10; j++) {
                JPanel c = new JPanel();
                c.setBackground(Color.WHITE);
                // c.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                tab[i][j] = c; //permet de sauvegarder la position de chaque case

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

        if(jeu.isGameOver()){
            System.out.println("game ovvvverrr");
            String[] options = { "quit", "play again" };
            int choice = JOptionPane.showOptionDialog(
                    null,
                    "Game Over!\nDo you want to play again?",
                    "Game Over",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[1]
            );

            if (choice == 1) {
                jeu.restartGame();
            } else {
                System.exit(0);
            }

        }else {

            // Réinitialiser toutes les cases
            for (int i = 0; i < 20; i++) {
                for (int j = 0; j < 10; j++) {
                    tab[i][j].setBackground(Color.WHITE);
                }
            }

            // Afficher les cases fixes (déjà dans la grille)
            boolean[][] grilleFixe = this.jeu.getGrille().getTab();
            for (int i = 0; i < 20; i++) {
                for (int j = 0; j < 10; j++) {
                    if (grilleFixe[i][j]) {
                        tab[i][j].setBackground(Color.BLUE);
                    }
                }
            }


            // Afficher la pièce à venir

            PieceCourante nextPc = jeu.getNextPc();
            if (nextPc != null) {
                int[][] motif = nextPc.getMotif();
                for (int i = 0; i < motif.length; i++) {
                    for (int j = 0; j < motif[0].length; j++) {
                        if (motif[i][j] != 0) {
                            prev[i][j].setBackground(Color.RED);
                        } else {
                            prev[i][j].setBackground(Color.WHITE);
                        }
                    }
                }
            }

            // ➕ Afficher la pièce courante
            PieceCourante pc = jeu.getPc();
            if (pc != null) {
                int[][] motif = pc.getMotif();
                int pcX = jeu.getGrille().getPcX();
                int pcY = jeu.getGrille().getPcY();

                for (int i = 0; i < motif.length; i++) {
                    for (int j = 0; j < motif[0].length; j++) {
                        if (motif[i][j] != 0) {
                            int x = pcX + i;
                            int y = pcY + j;

                            // Vérifier que les coordonnées sont dans les limites de la grille
                            if (x >= 0 && x < 20 && y >= 0 && y < 10) {
                                tab[x][y].setBackground(Color.RED); // ou une couleur selon motif[i][j]
                            }
                        }
                    }
                }
            }
        }

//        if(jeu.isGameOver()){
//            System.out.println("game ovvvverrr");
//            String[] options = { "quit", "play again" };
//            int choice = JOptionPane.showOptionDialog(
//                    null,
//                    "Game Over!\nDo you want to play again?",
//                    "Game Over",
//                    JOptionPane.YES_NO_OPTION,
//                    JOptionPane.QUESTION_MESSAGE,
//                    null,
//                    options,
//                    options[1]
//            );
//
//            if (choice == 1) {
//                jeu.restartGame();
//            } else {
//                System.exit(0);
//            }
//
//        }


    }
}