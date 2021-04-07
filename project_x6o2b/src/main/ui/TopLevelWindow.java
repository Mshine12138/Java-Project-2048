package ui;

import model.Box;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static javax.swing.SwingConstants.CENTER;

public class TopLevelWindow extends JFrame {
    private static final int WIDTH = 1006;
    private static final int HEIGHT = 1051;
    private static final int INTERVAL = 10;
    private JLabel welcome = new JLabel("Welcome to 2048!");
    private JButton newGame = new JButton("New Game");
    private Game game;
    private JPanel newGameJpanel = new JPanel();
    private final MouseListener buttonNewGameMouseListener = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
            newGame();
        }

        @Override
        public void mousePressed(MouseEvent e) {
            newGame.setBackground(Color.BLUE);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            newGame.setBackground(Color.ORANGE);
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            newGame.setFont(new Font("", Font.BOLD, 33));
            int width = newGame.getWidth() + 20;
            int height = newGame.getHeight() + 5;
            newGame.setSize(width, height);

        }

        @Override
        public void mouseExited(MouseEvent e) {
            newGame.setFont(new Font("", Font.BOLD, 30));
            int width = newGame.getWidth() - 20;
            int height = newGame.getHeight() - 5;
            newGame.setSize(width, height);
        }
    };
    private KeyAdapter newGameJpanelKeyAdapter = new KeyAdapter() {
        public void keyPressed(KeyEvent e) {
            if (e.getKeyChar() == 'w' || e.getKeyCode() == KeyEvent.VK_UP) {
                game.board.mergeUp();
            } else if (e.getKeyChar() == 'a' || e.getKeyCode() == KeyEvent.VK_LEFT) {
                game.board.mergeLeft();
            } else if (e.getKeyChar() == 'd' || e.getKeyCode() == KeyEvent.VK_RIGHT) {
                game.board.mergeRight();
            } else if (e.getKeyChar() == 's' || e.getKeyCode() == KeyEvent.VK_DOWN) {
                game.board.mergeDown();
            }
            for (Box box : game.board) {
                setUpLabelBox(box);
                newGameJpanel.add(box);
            }
        }
    };
    private JLabel score = new JLabel();

    public TopLevelWindow(Game game) {
        //Create and set up the window.
        setTitle("2048");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        centreOnScreen();
        setLayout(null);
        setVisible(true);
        setGame(game);
        setScore(game.getScore());
    }

    public static void main(String[] args) {
        new TopLevelWindow(new Game()).setWindow();
    }

    private void newGame() {

        setContentPane(newGameJpanel);
        setLayout(null);
        newGameJpanel.setBounds(100, 100, 805, 805);
        newGameJpanel.setBackground(new Color(134, 134, 134));
        newGameJpanel.addKeyListener(newGameJpanelKeyAdapter);

        for (Box box : game.board) {
            setUpLabelBox(box);
            newGameJpanel.add(box);
        }
    }

    private void setUpLabelBox(Box box) {
        box.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.black, null));
        box.setText(String.valueOf(box.getValue()));
        box.setHorizontalAlignment(CENTER);
//        box.setBackground();
        box.setOpaque(true);
        box.setFont(new Font("", Font.BOLD, 100));
        box.setBounds(5 + box.getColumn() * 200, 5 + box.getRow() * 200, 195, 195);
    }

    private void centreOnScreen() {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screen.width - getWidth()) / 2, (screen.height - getHeight()) / 2);
    }

    private void setGame(Game game) {
        this.game = game;
    }

    private void setScore(int score) {
        this.score.setText("Score: " + score);
    }

    private void setWindow() {
        JPanel jpanel = new JPanel();

        setContentPane(jpanel);
        jpanel.setBackground(new Color(241,241,241));

        JLabel welcome = setUpLabelWelcome();
        jpanel.add(welcome);

        JButton newGame = setUpButtonNewGame();
        jpanel.add(newGame);
    }

    private JButton setUpButtonNewGame() {
        int width = 200;
        int height = 60;

        newGame.setFont(new Font("", Font.BOLD, 30));
        newGame.setBounds(WIDTH / 2 - width / 2, HEIGHT / 2, width, height);
        newGame.setBackground(Color.ORANGE);
        newGame.setFocusPainted(false);
        newGame.setBorderPainted(false);
        newGame.addMouseListener(buttonNewGameMouseListener);
        return newGame;
    }

    private JLabel setUpLabelWelcome() {
        welcome.setHorizontalAlignment(CENTER);
        welcome.setFont(new Font("", Font.BOLD, 60)); //set the font of the text
        return welcome;
    }
}
