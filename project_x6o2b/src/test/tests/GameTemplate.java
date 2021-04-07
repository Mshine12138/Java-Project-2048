package tests;

import model.Board;
import ui.Game;

public class GameTemplate extends Game {
    BoardTemplate boardTemplate;

    GameTemplate(int score, Board board) {
        super();
        this.score = score;
        this.board = board;
    }
}
