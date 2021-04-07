//package ui;
//
//import javax.swing.*;
//import javax.swing.border.EtchedBorder;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.KeyAdapter;
//import java.awt.event.KeyEvent;
//import java.util.Random;
//
//public class Java2048 extends JFrame {
//    public int score;
//    public JLabel jlabel = new JLabel();
//    private Card[][] cards = new Card[5][5];
//
//
//    public Java2048() {
//        this.setTitle("2048");
//        this.setBounds(2700, 600, 500, 480);
//
//        JPanel myJPanel = new JPanel();
//        myJPanel.setLayout(null);
//        this.setContentPane(myJPanel);
//
//        JLabel myJLabel = new JLabel("Welcome to 2048!");
//        myJLabel.setBounds(120, 100, 300, 50);
//        myJLabel.setFont(new Font("", Font.BOLD, 30));
//        myJLabel.setForeground(Color.BLUE);
//        myJPanel.add(myJLabel);
//
//        JButton myJButton = new JButton("Start Game");
//        myJButton.setBounds(50, 300, 100, 30);
//        myJPanel.add(myJButton);
//        myJButton.addActionListener(e -> {
//            // TODO Auto-generated method stub
//            createNewGame();
//        });
//        JButton myJButton1 = new JButton("Exit");
//        myJButton1.setBounds(300, 300, 100, 30);
//        myJPanel.add(myJButton1);
//        myJButton1.addActionListener(e -> {
//            // TODO Auto-generated method stub
//            System.exit(0);
//        });
//        this.setVisible(true);
//    }
//
//    public static void main(String[] args) {
//        new Java2048();
//    }
//
//    public void left() {
//        boolean merge = false;
//        for (int x = 0; x < 4; ++x) {
//            for (int y = 0; y < 4; ++y) {
//                for (int k = y + 1; k < 4; ++k) {
//                    if (cards[x][k].getNum() != 0) {
//                        if (cards[x][y].getNum() == 0) {
//                            cards[x][y].setNumber(cards[x][k].getNum());
//                            cards[x][k].setNumber(0);
//                            y--;
//                            merge = true;
//                        } else if (cards[x][y].getNum() == cards[x][k].getNum()) {
//                            cards[x][y].setNumber(cards[x][k].getNum() * 2);
//                            cards[x][k].setNumber(0);
//                            score += cards[x][y].getNum();
//                            jlabel.setText("Score:" + score);
//                            merge = true;
//                        }
//                        break;
//                    }
//                }
//            }
//        }
//        if (merge) {
//            autoCreateCard();
//        }
//    }
//
//    public void right() {
//        boolean merge = false;
//        for (int x = 0; x < 4; ++x) {
//            for (int y = 3; y >= 0; --y) {
//                for (int k = y - 1; k >= 0; --k) {
//                    if (cards[x][k].getNum() != 0) {
//                        if (cards[x][y].getNum() == 0) {
//                            cards[x][y].setNumber(cards[x][k].getNum());
//                            cards[x][k].setNumber(0);
//                            y++;
//                            merge = true;
//                        } else if (cards[x][y].getNum() == cards[x][k].getNum()) {
//                            cards[x][y].setNumber(cards[x][k].getNum() * 2);
//                            cards[x][k].setNumber(0);
//                            score += cards[x][y].getNum();
//                            jlabel.setText("Score:" + score);
//                            merge = true;
//                        }
//                        break;
//                    }
//                }
//            }
//        }
//        if (merge) {
//            autoCreateCard();
//        }
//    }
//
//    public void up() {
//        boolean merge = false;
//        for (int y = 0; y < 4; ++y) {
//            for (int x = 0; x < 4; ++x) {
//                for (int k = x + 1; k < 4; ++k) {
//                    if (cards[k][y].getNum() != 0) {
//                        if (cards[x][y].getNum() == 0) {
//                            cards[x][y].setNumber(cards[k][y].getNum());
//                            cards[k][y].setNumber(0);
//                            x--;
//                            merge = true;
//                        } else if (cards[x][y].getNum() == cards[k][y].getNum()) {
//                            cards[x][y].setNumber(cards[k][y].getNum() * 2);
//                            cards[k][y].setNumber(0);
//                            score += cards[x][y].getNum();
//                            jlabel.setText("Score:" + score);
//                            merge = true;
//                        }
//                        break;
//                    }
//                }
//            }
//        }
//        if (merge) {
//            autoCreateCard();
//        }
//    }
//
//    final public void down() {
//        boolean merge = false;
//        for (int y = 0; y < 4; ++y) {
//            for (int x = 3; x >= 0; --x) {
//                for (int k = x - 1; k >= 0; --k) {
//                    if (cards[k][y].getNum() != 0) {
//                        if (cards[x][y].getNum() == 0) {
//                            cards[x][y].setNumber(cards[k][y].getNum());
//                            cards[k][y].setNumber(0);
//                            x++;
//                            merge = true;
//                        } else if (cards[x][y].getNum() == cards[k][y].getNum()) {
//                            cards[x][y].setNumber(cards[k][y].getNum() * 2);
//                            cards[k][y].setNumber(0);
//                            score += cards[x][y].getNum();
//                            jlabel.setText("Score:" + score);
//                            merge = true;
//                        }
//                        break;
//                    }
//                }
//            }
//        }
//        if (merge) {
//            autoCreateCard();
//        }
//    }
//
//    public void autoCreateCard() {
//        Random ran = new Random();
//        int x = ran.nextInt(4);
//        int y = ran.nextInt(4);
//        if (cards[x][y].getNum() > 0) {
//            autoCreateCard();
//        } else {
//            cards[x][y].setNumber(ran.nextInt(2) < 1 ? 2 : 4);
//        }
//    }
//
//    public void startGame() {
//        score = 0;
//        JPanel myJPanel1 = new JPanel();
//        myJPanel1.setLayout(null);
//        myJPanel1.setBackground(new Color(187, 173, 160));
//        this.setContentPane(myJPanel1);
//        jlabel.setBounds(200, 5, 290, 40);
//        jlabel.setText("Score:" + score);
//        jlabel.setFont(new Font("HirakakuProN-W6", Font.PLAIN, 20));
//        myJPanel1.add(jlabel);
//        for (int i = 0; i < 4; ++i) {
//            for (int j = 0; j < 4; ++j) {
//                cards[i][j] = new Card();
//                cards[i][j].setBounds(75 + j * 85, 40 + i * 85, 80, 80);
//                cards[i][j].setNumber(0);
//                myJPanel1.add(cards[i][j]);
//            }
//        }
//        autoCreateCard();
//        autoCreateCard();
//        this.setVisible(true);
//        myJPanel1.requestFocus();
//        myJPanel1.addKeyListener(new KeyAdapter() {
//            public void keyPressed(KeyEvent e) {
//                checkGameEnd();
//                if (e.getKeyChar() == 'w' || e.getKeyCode() == KeyEvent.VK_UP) {
//                    up();
//                } else if (e.getKeyChar() == 'a' || e.getKeyCode() == KeyEvent.VK_LEFT) {
//                    left();
//
//                } else if (e.getKeyChar() == 'd' || e.getKeyCode() == KeyEvent.VK_RIGHT) {
//                    right();
//                } else if (e.getKeyChar() == 's' || e.getKeyCode() == KeyEvent.VK_DOWN) {
//                    down();
//                }
//            }
//        });
//        JButton myJButton = new JButton("Restart");
//        myJButton.setBounds(75, 400, 100, 30);
//        myJPanel1.add(myJButton);
//        myJButton.addActionListener(e -> {
//            // TODO Auto-generated method stub
//            startGame();
//        });
//        JButton myJButton1 = new JButton("Menu");
//        myJButton1.setBounds(310, 400, 100, 30);
//        myJPanel1.add(myJButton1);
//        myJButton1.addActionListener(e -> {
//            // TODO Auto-generated method stub
//            createNewGame();
//        });
//    }
//
//    public void createNewGame() {
//        JPanel myJPanel1 = new JPanel();
//        myJPanel1.setLayout(null);
//        this.setContentPane(myJPanel1);
//        JLabel myJLabel = new JLabel();
//        myJLabel.setBounds(50, 50, 400, 200);
//        myJLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.CYAN, null));
//        myJPanel1.add(myJLabel);
//        myJLabel.setFont(new Font("HirakakuProN-W6", Font.PLAIN, 16));
//        String string1 = "<html><body><h2 align=center>Instruction";
//        String string2 = "</h2>Press \"up\" \"down\" \"left\" or \"right\" to control <br/></body></html>";
//        myJLabel.setText(string1 + string2);
//        JButton myJButton = new JButton("Start Game");
//        myJButton.setBounds(60, 300, 100, 30);
//        myJPanel1.add(myJButton);
//        myJButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // TODO Auto-generated method stub
//                startGame();
//            }
//        });
//        JButton myJButton1 = new JButton("Exit");
//        myJButton1.setBounds(310, 300, 100, 30);
//        myJPanel1.add(myJButton1);
//        myJButton1.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // TODO Auto-generated method stub
//                System.exit(0);
//            }
//        });
//        this.setVisible(true);
//    }
//
//    public void checkGameEnd() {
//        boolean isGameOver = true;
//        for (int x = 0; x < 4; ++x) {
//            for (int y = 0; y < 4; ++y) {
//                if (cards[x][y].getNum() == 0
//                        || (x > 0 && cards[x][y].getNum() == cards[x - 1][y].getNum())
//                        || (x < 3 && cards[x][y].getNum() == cards[x + 1][y].getNum())
//                        || (y > 0 && cards[x][y].getNum() == cards[x][y - 1].getNum())
//                        || (y < 3 && cards[x][y].getNum() == cards[x][y + 1].getNum())) {
//                    isGameOver = false;
//                }
//            }
//        }
//        if (isGameOver) {
//            gameEnd();
//        }
//    }
//
//    public void gameEnd() {
//        String[] options = {"Restart", "Exit"};
//        if (response == 0) {
//            createNewGame();
//        } else {
//            System.exit(0);
//        }
//    }
//
//}