package tests;

import model.Board;
import model.Box;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    private Board board;
    private String print;
    private Box box1;
    private Box box2;
    private BoardTemplate boardTemplate = new BoardTemplate();

    @BeforeEach
    void setUp() {
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

    }

    @Test
    void testConstructor() {
        assertEquals(7, board.blankBoxes().size());
        assertFalse(board.didMove());
        board.setScoreEarned(60);
        assertEquals(60,board.getScoreEarned());
    }

    @Test
    void testPrintBoard() {
        board.printBoard();

        assertEquals(print, board.printOut);

    }

    @Test
    void testMergeLeft() {
        assertTrue(board.mergeLeft());

        board.printBoard();

        assertEquals(4, board.get(0).getValue());
        assertEquals(4, board.get(4).getValue());
        assertEquals(2, board.get(5).getValue());
        assertEquals(4, board.get(8).getValue());
        assertEquals(2, board.get(12).getValue());
        assertEquals(8, board.get(13).getValue());
        /*
         * should print:
         * 4, 0, 0, 0;
         * 4, 2, 0, 0;
         * 4, 0, 0, 0;
         * 2, 8, 0, 0;
         * */
    }

    @Test
    void testMergeRight() {
        assertTrue(board.mergeRight());

        board.printBoard();

        assertEquals(4, board.get(3).getValue());
        assertEquals(4, board.get(6).getValue());
        assertEquals(2, board.get(7).getValue());
        assertEquals(4, board.get(11).getValue());
        assertEquals(2, board.get(14).getValue());
        assertEquals(8, board.get(15).getValue());
        /*
         * should print:
         * 0, 0, 0, 4;
         * 0, 0, 4, 2;
         * 0, 0, 0, 4;
         * 0, 0, 2, 8;
         * */
    }

    @Test
    void testMergeUp() {
        assertTrue(board.mergeUp());

        board.printBoard();

        assertEquals(4, board.get(0).getValue());
        assertEquals(2, board.get(1).getValue());
        assertEquals(4, board.get(2).getValue());
        assertEquals(4, board.get(3).getValue());
        assertEquals(4, board.get(5).getValue());
        assertEquals(4, board.get(7).getValue());
        assertEquals(2, board.get(9).getValue());
        /*
         * should print:
         * 4, 2, 4, 4;
         * 0, 4, 0, 4;
         * 0, 2, 0, 0;
         * 0, 0, 0, 0;
         * */
    }

    @Test
    void testMergeDown() {
        assertTrue(board.mergeDown());

        board.printBoard();

        assertEquals(4, board.get(12).getValue());
        assertEquals(2, board.get(13).getValue());
        assertEquals(4, board.get(9).getValue());
        assertEquals(4, board.get(14).getValue());
        assertEquals(4, board.get(15).getValue());
        assertEquals(4, board.get(11).getValue());
        assertEquals(2, board.get(5).getValue());
        /*
         * should print:
         * 0, 0, 0, 0;
         * 0, 2, 0, 0;
         * 0, 4, 0, 4;
         * 4, 2, 4, 4;
         * */
    }

    @Test
    void testMerge2BlankBoxes() {
        //(0, 0)
        box1 = new Box(board);
        box2 = new Box(board);

        box1.setValue(0);
        box2.setValue(0);
        assertFalse(board.merge2Boxes(box1, box2));
        assertFalse(board.didMove());
    }

    @Test
    void testMergeNumbedBoxToBlankBox() {
        //(0, 2)
        box1 = new Box(board);
        box2 = new Box(board);

        box1.setValue(0);
        box2.setValue(2);
        assertFalse(board.merge2Boxes(box1, box2));
        assertTrue(board.didMove());
    }

    @Test
    void testMergeBlankBoxToNumberedBox() {
        //(2, 0)
        box1 = new Box(board);
        box2 = new Box(board);

        box1.setValue(2);
        box2.setValue(0);
        assertFalse(board.merge2Boxes(box1, box2));
        assertFalse(board.didMove());
    }

    @Test
    void testMergeNumberedBoxToNumberedBox() {
        box1 = new Box(board);
        box2 = new Box(board);

        //(2, 4)
        box1.setValue(2);
        box2.setValue(4);
        assertTrue(board.merge2Boxes(box1, box2));
        assertFalse(board.didMove());


        //(2, 2)
        box1.setValue(2);
        box2.setValue(2);
        assertTrue(board.merge2Boxes(box1, box2));
        assertTrue(board.didMove());
    }

    @Test
    void testNextBoard() {
        int i = board.blankBoxes().size();

        board.nextBoard();
        assertEquals(i - 1, board.blankBoxes().size() );

        while (board.blankBoxes().size() != 0) {
            board.nextBoard();
        }
        assertEquals(0, board.blankBoxes().size());
    }

    @Test
    void testRandomBlankIndex() {
        assertTrue(board.get(board.randomBlankIndex()).isBlank());
    }

    @Test
    void testHasNextBoard() {
        assertTrue(board.hasNextBoard());

        board = boardTemplate.fullBoard();
        assertFalse(board.hasNextBoard());
    }

    @Test
    void testCheckHorizontally() {
        assertTrue(board.checkHorizontally());

        board = boardTemplate.horizontallyNotMergeable();
        board.printBoard();
        assertFalse(board.checkHorizontally());
    }

    @Test
    void testCheckVertically() {
        assertTrue(board.checkVertically());

        board = boardTemplate.verticallyNotMergeable();
        board.printBoard();
        assertFalse(board.checkVertically());
    }

    @Test
    void testMoveSelect() {
        assertTrue(board.moveSelect("w"));
        assertTrue(board.moveSelect("s"));
        assertTrue(board.moveSelect("a"));
        assertTrue(board.moveSelect("d"));
        assertFalse(board.moveSelect("f"));
    }
}

