package tests;

import model.Board;
import model.Box;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class
BoxTest {
    private Box box;
    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
        box = board.get(3);
    }

    @Test
    void testBlankIsBlank() {
        box.setValue(0);

        assertTrue(box.isBlank());

    }

    @Test
    void testNonBlankIsBlank() {
        box.setValue(2);

        assertFalse(box.isBlank());

    }

    @Test
    void testGetIndex() {
        assertEquals(3, box.getIndex());
    }

    @Test
    void testGetColumn() {
        assertEquals(3, box.getColumn());
    }

    @Test
    void testGetCRow() {
        assertEquals(0, box.getRow());
    }

}