package vue;

import model.Direction;
import model.Jeu;
import model.PieceCourante;
import model.ScoreEntry;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class VueController extends JFrame implements Observer, KeyListener {

    public Jeu jeu;

    JPanel[][] tab = new JPanel[20][10];
    JPanel[][] prev = new JPanel[4][4];
    String playOrPause = "Pause";

    String score = "0";
    private JLabel currentScore;

    public void playPause() {
        if (playOrPause.equals("Play")) {
            playOrPause = "Pause";
        } else {
            playOrPause = "Play";
        }
    }

    public VueController(Jeu jeu) throws HeadlessException {
        this.jeu = jeu;
        this.setTitle("Tetris");
        JPanel borderPanel = new JPanel(new BorderLayout());

        setFocusable(true);
        addKeyListener(this);

        // Créer la barre de menu
        JMenuBar menubar = new JMenuBar();
        // Créer le menu
        JMenu menu = new JMenu("Menu");
        JMenuItem e1 = new JMenuItem("Highest Scores");

        e1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showHighScores();  // Display high scores window
            }
        });

        menu.add(e1);
        menubar.add(menu);

        this.setJMenuBar(menubar);

        // Créer la grille avec GridLayout
        JPanel gridPanel = new JPanel(new GridLayout(20, 10));
        gridPanel.setBorder(new LineBorder(Color.BLACK));
        gridPanel.setBackground(Color.WHITE);


        //Current Score Title
        JLabel currentScoreTitle = new JLabel("Current Score", SwingConstants.CENTER);
        currentScoreTitle.setFont(new Font("Arial", Font.BOLD, 20));
        currentScoreTitle.setBorder(BorderFactory.createEmptyBorder(40, 0, 10, 0));
        currentScoreTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Current score
        currentScore = new JLabel(score, SwingConstants.CENTER);
        currentScore.setFont(new Font("Arial", Font.PLAIN, 16));
        currentScore.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create a vertical panel for title score and score
        JPanel scorePanel = new JPanel();
        scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.Y_AXIS));
        scorePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add components
        scorePanel.add(currentScoreTitle);
        scorePanel.add(Box.createRigidArea(new Dimension(0, 2)));
        scorePanel.add(currentScore);

        // Title label
        JLabel previewTitle = new JLabel("Next Piece", SwingConstants.CENTER);
        previewTitle.setFont(new Font("Arial", Font.BOLD, 20));
        previewTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        // Créer la grille de prévision des pièces
        JPanel previewPanel = new JPanel(new GridLayout(4, 4));
        previewPanel.setBorder(new LineBorder(Color.BLACK));

        //ratio panel - preview
        AspectRatioPanel previewGridWrapper = new AspectRatioPanel(1.0, previewPanel);
        previewGridWrapper.setPreferredSize(new Dimension(100, 100));

        //vertical panel - title and preview
        JPanel titleAndGridPanel = new JPanel(new BorderLayout());
        titleAndGridPanel.add(previewTitle, BorderLayout.NORTH);
        titleAndGridPanel.add(previewGridWrapper, BorderLayout.CENTER);
        titleAndGridPanel.add(scorePanel, BorderLayout.SOUTH);

        // Padding around the whole preview section
        JPanel paddedPreview = new JPanel(new BorderLayout());
        paddedPreview.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        paddedPreview.add(titleAndGridPanel, BorderLayout.CENTER);

        // Center vertically & horizontally in EAST
        JPanel centerInEast = new JPanel(new GridBagLayout());
        centerInEast.add(paddedPreview);
        centerInEast.setPreferredSize(new Dimension(200, 0));

        //add padded preview panel to border panel
        borderPanel.add(centerInEast, BorderLayout.EAST);

        // Créer le panel du bouton play-pause
        JPanel playPause = new JPanel(new FlowLayout(FlowLayout.CENTER));
        playPause.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 150));

        JLabel play = new JLabel(playOrPause);
        play.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(Color.BLACK), // outer border
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        play.setFont(new Font("Arial", Font.BOLD, 20));

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

        playPause.add(play);
        borderPanel.add(playPause, BorderLayout.SOUTH);

        // Remplissage de la grille piece preview
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                JPanel c = new JPanel();
                c.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                prev[i][j] = c;

                final int row = i;
                final int col = j;

                previewPanel.add(c);
            }
        }

        // Remplissage des cases de la Grille
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 10; j++) {
                JPanel c = new JPanel();
                tab[i][j] = c; //permet de sauvegarder la position de chaque case

                final int row = i;
                final int col = j;

                c.setBorder(BorderFactory.createLineBorder(Color.WHITE));

                gridPanel.add(c);
            }
        }

        JPanel paddedMainGridWrapper = new JPanel(new BorderLayout());
        paddedMainGridWrapper.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 0));


        // Wrap the Grid panel in a ratio respecting container
        AspectRatioPanel centeredGridWrapper = new AspectRatioPanel(10.0 / 20.0, gridPanel);
        centeredGridWrapper.setPreferredSize(new Dimension(300, 600));

        paddedMainGridWrapper.add(centeredGridWrapper, BorderLayout.CENTER);
        borderPanel.add(paddedMainGridWrapper, BorderLayout.CENTER);

        this.add(borderPanel);
        setLocationRelativeTo(null);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private void showHighScores() {
        List<ScoreEntry> scores = jeu.getHighScoreManager().getHighScores();
        StringBuilder message = new StringBuilder("High Scores:\n");

        for (ScoreEntry entry : scores) {
            message.append(entry.getName()).append(" - ").append(entry.getScore()).append("\n");
        }

        JOptionPane.showMessageDialog(null, message.toString(), "High Scores", JOptionPane.INFORMATION_MESSAGE);

    }

    public void update(Observable o, Object arg) {
        if (jeu.isGameOver()) {
            System.out.println("game ovvvverrr");

            String name = JOptionPane.showInputDialog(null, "Game Over!\nEnter your name:", "Game Over", JOptionPane.PLAIN_MESSAGE);
            if (name != null && !name.trim().isEmpty()) {
                jeu.getHighScoreManager().addScore(name, jeu.getScore().getScore());
            }

            String[] options = {"quit", "play again"};
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
                score = "";
                currentScore.setText(score);
            } else {
                System.exit(0);
            }

        } else {
            //update du score
            score = jeu.getScore().toStringScore();
            currentScore.setText(score);

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

            // Afficher la pièce courante
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

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        switch (code) {
            case KeyEvent.VK_LEFT:
                jeu.movePc(0, -1);
                break;
            case KeyEvent.VK_RIGHT:
                jeu.movePc(0, 1);
                break;
            case KeyEvent.VK_W:
                System.out.println("DROITE");
                jeu.rotaPc(Direction.DROITE);
                break;
            case KeyEvent.VK_X:
                jeu.rotaPc(Direction.GAUCHE);
                break;
            case KeyEvent.VK_DOWN:
                jeu.movePc(1, 0);
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();


    }
}