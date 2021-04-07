package ui;

import java.io.IOException;

public class RunGame {

    public static void main(String[] args) {
        Game game = new Game();

        programStart();

        try {
            game.runGame();
        } catch (IOException e) {
            e.printStackTrace();
        }

        programEnd();
    }

    private static void programStart() {
        System.out.println("Program started.");
    }

    private static void programEnd() {
        System.out.println("Program closed.");
    }
}