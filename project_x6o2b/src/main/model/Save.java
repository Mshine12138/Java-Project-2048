package model;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class Save {
    public void saveBoard(Board board, String fileName) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter(fileName, "UTF-8");

        for (int y = 0; y < 4; y++) {
            Box box0 = board.get(4 * y);
            Box box1 = board.get(1 + 4 * y);
            Box box2 = board.get(2 + 4 * y);
            Box box3 = board.get(3 + 4 * y);
            writer.println(
                    box0.getValue() + ","
                            + box1.getValue() + ","
                            + box2.getValue() + ","
                            + box3.getValue());
        }
        writer.close();
    }

    public void saveScore(int score, String fileName) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter(fileName, "UTF-8");

        writer.println(score);
        writer.close();
    }
}
