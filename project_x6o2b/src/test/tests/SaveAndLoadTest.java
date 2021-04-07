package tests;

import model.Board;
import model.Box;
import model.Load;
import model.Save;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SaveAndLoadTest {
    private Save save;
    private Load load;
    private Board board;

    @BeforeEach
    void setUp() {
        save = new Save();
        load = new Load();
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
        /*
        2, 2, 0, 0;
        0, 4, 0, 2;
        0, 2, 0, 2;
        2, 0, 4, 4;
        */
    }

    @Test
    void testSaveAndLoadBoard() throws IOException {
        Board output;

        save.saveBoard(board, "data/storeBoardTest.txt");
        output = load.loadBoard("data/storeBoardTest.txt");

        assertEquals(output.isEmpty(), board.isEmpty());

        assertEquals(output.get(10).getValue(), board.get(10).getValue());
    }



    @Test
    void testSaveAndLoadScore() throws IOException {
        Load load = new Load();
        int output;

        save.saveScore(360, "data/storeScoreTest.txt");
        output = load.loadScore("data/storeScoreTest.txt");

        assertEquals(output, 360);
    }
}
