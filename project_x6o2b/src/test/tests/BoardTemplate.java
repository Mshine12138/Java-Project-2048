package tests;

import model.Board;
import model.Box;

public class BoardTemplate {
    private Board board;
    private String print;

    public Board fullBoard() {
        print = "2,4,2,4,\n" +
                "4,2,4,2,\n" +
                "2,4,2,4,\n" +
                "4,2,4,2,\n";

        board = new Board();
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                if (x == y || (y - x) == 2 || (x - y) == 2) {
                    board.get(4 * y + x).setValue(2);
                } else {
                    board.get(4 * y + x).setValue(4);
                }
            }
        }
        return board;
    }

    public Board horizontallyNotMergeable() {
        print = "0,0,0,0,\n" +
                "0,0,0,0,\n" +
                "0,0,0,0,\n" +
                "4,2,4,2,\n";

        board = new Board();
        for (int i = 0; i < 4; i++) {
            board.get(12 + i).setValue(i + 1);
        }
        return board;

    }

    public Board verticallyNotMergeable() {
        print = "2,0,0,0,\n" +
                "4,0,0,0,\n" +
                "2,0,0,0,\n" +
                "4,0,0,0,\n";

        board = new Board();
        for (int i = 0; i < 4; i++) {
            board.get(4 * i).setValue(i + 1);
        }
        return board;

    }

    Board normalBoard() {

        /*
        2, 2, 0, 0;
        0, 4, 0, 2;
        0, 2, 0, 2;
        2, 0, 4, 4;
        */
        print = "2,2,0,0,\n" +
                "0,4,0,2,\n" +
                "0,2,0,2,\n" +
                "2,0,4,4,\n";

        board = new Board();
        Box box = new Box(board);
        box.setValue(2);
        board.set(0, box);
        box = new Box(board);
        box.setValue(2);
        board.set(1, box);
        box = new Box(board);
        box.setValue(4);
        board.set(5, box);
        box = new Box(board);
        box.setValue(2);
        board.set(7, box);
        box = new Box(board);
        box.setValue(2);
        board.set(9, box);
        box = new Box(board);
        box.setValue(2);
        board.set(11, box);
        box = new Box(board);
        box.setValue(2);
        board.set(12, box);
        box = new Box(board);
        box.setValue(4);
        board.set(14, box);
        box = new Box(board);
        box.setValue(4);
        board.set(15, box);
        return board;
    }
}
