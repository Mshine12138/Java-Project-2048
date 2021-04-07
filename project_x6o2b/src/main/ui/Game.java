package ui;

import model.Board;
import model.Box;
import model.Load;
import model.Save;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

import static javax.swing.SwingConstants.CENTER;

public class Game extends JFrame {
    private static final int WIDTH = 1006;
    private static final int HEIGHT = 1051;
    public Board board;
    protected int score;
    String input;
    private boolean exitMenu;
    private Scanner scanner = new Scanner(System.in);
    private Save save = new Save();
    private Load load = new Load();
    private TopLevelWindow topLevelWindow;
    private String userActivity;
    private KeyAdapter newGameJpanelKeyAdapter = new KeyAdapter() {
        public void keyPressed(KeyEvent e) {
            if (e.getKeyChar() == 'w' || e.getKeyCode() == KeyEvent.VK_UP) {

                input = "w";
            } else if (e.getKeyChar() == 'a' || e.getKeyCode() == KeyEvent.VK_LEFT) {
                input = "a";
            } else if (e.getKeyChar() == 'd' || e.getKeyCode() == KeyEvent.VK_RIGHT) {
                input = "d";
            } else if (e.getKeyChar() == 's' || e.getKeyCode() == KeyEvent.VK_DOWN) {
                input = "s";
            }

            if (!isOver()) {
                try {
                    swipe();
                } catch (FileNotFoundException | UnsupportedEncodingException ex) {
                    ex.printStackTrace();
                }
            }
        }
    };

    public Game() {
        score = 0;
        board = new Board();
        gameWindowSetup();
    }

    private void gameWindowSetup() {
        setTitle("2048");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        centreOnScreen();
        setLayout(null);
        setVisible(true);
    }

    private void centreOnScreen() {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screen.width - getWidth()) / 2, (screen.height - getHeight()) / 2);
    }

    private void menu() {
        JPanel menu = new JPanel();

        menu.setLayout(null);
        setContentPane(menu);

        JLabel welcome = new JLabel("Welcome to 2048!");
        welcome.setHorizontalAlignment(CENTER);
        welcome.setFont(new Font("", Font.BOLD, 60)); //set the font of the text
        menu.add(welcome);

        JButton newGame = new JButton("New Game");
        int width = 200;
        int height = 60;
        newGame.setFont(new Font("", Font.BOLD, 30));
        newGame.setBounds(403, 400, width, height);
        newGame.setBackground(Color.ORANGE);
        newGame.setFocusPainted(false);
        newGame.setBorderPainted(false);
        this.setVisible(true);
        menu.add(newGame);
        newGame.addActionListener(e -> newGame());
    }

    private void newGame() {
        JPanel gameInterface = new JPanel();
        gameInterface.setLayout(null);
        gameInterface.setBounds(100, 100, 805, 805);
        gameInterface.setBackground(new Color(241, 241, 241));
        this.setContentPane(gameInterface);
        newGameStuffs(gameInterface);

        gameInterface.setVisible(true);
        gameInterface.requestFocus();
        try {
            playing(gameInterface);
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private void newGameStuffs(JPanel gameInterface) {
        JLabel scoreLabel = new JLabel();
        gameInterface.add(scoreLabel);
        scoreLabel.setBounds(200, 5, 290, 40);
        scoreLabel.setText("Score: " + score);
        scoreLabel.setFont(new Font("", Font.BOLD, 20));

        newGameStuffsBoxes(gameInterface);
        makeNextBoard(true);
        makeNextBoard(true);
    }

    private void newGameStuffsBoxes(JPanel gameInterface) {
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                Box box = new Box(board);
                gameInterface.add(box);
                box.setHorizontalAlignment(CENTER);
                box.setOpaque(true);
                box.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.black, null));
                box.setValue(0);
                box.setFont(new Font("", Font.BOLD, 100));
                box.setBounds(5 + i * 200, 5 + j * 200, 195, 195);
                board.set(i + j * 4, box);
            }
        }
    }

    public int getScore() {
        return score;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    void runGame() throws IOException {
        menu();
//        playing();
        System.out.println("Game Over!");
        System.out.println("You got " + score + " points!");
    }

    private void playing(JPanel gameInterface) throws FileNotFoundException, UnsupportedEncodingException {
        System.out.println("Score: " + score);
        board.printBoard();
        gameInterface.addKeyListener(newGameJpanelKeyAdapter);
    }

    private boolean isOver() {
        return (board.blankBoxes().isEmpty() && !board.hasNextBoard());
    }

    private void swipe() throws FileNotFoundException, UnsupportedEncodingException {
        System.out.println("Score: " + score);
        board.setScoreEarned(0);
        boolean hasNextBoard = board.move(input);
        board.printBoard();
        makeNextBoard(hasNextBoard);
        score += board.getScoreEarned();
        saveGame();
    }

    private void makeNextBoard(boolean hasNextBoard) {
        if (hasNextBoard) {
            board.nextBoard();
        } else {
            System.out.println("Cannot move any box.");
        }
    }

    private void saveGame() throws FileNotFoundException, UnsupportedEncodingException {
        save.saveBoard(board, "data/userBoardStorage.txt");
        save.saveScore(score, "data/userScoreStorage.txt");
    }

    private String getUserResponse() {
        String inputLine = scanner.nextLine();
        return inputLine.trim();
    }

    public class Menu extends JPanel {
        int actionCode;

        void setActionCode(int actionCode) {
            this.actionCode = actionCode;
        }

        void options() {
        }

        private void loadGame() throws IOException {
            board = load.loadBoard("data/userBoardStorage.txt");
            score = load.loadScore("data/userScoreStorage.txt");
            exitMenu = true;
        }

        private int menu() {
            System.out.println("-----------MENU------------");
            System.out.println("1. New Game");
            System.out.println("2. Load Game");
            System.out.println("3. Top Rank");
            System.out.println("4. Settings");
            System.out.println("5. Exit");
            System.out.println("6. Set Name");
            System.out.print(">>>");

            try {
                return Integer.parseInt(getUserResponse());
            } catch (NumberFormatException e) {
                System.out.println("Please make a selection.");
                return menu();
            }
        }
    }
}
