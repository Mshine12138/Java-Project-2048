package model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Load {

    public Board loadBoard(String fileName) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(fileName));
        Board board = new Board();
        int y = 0;
        for (String line : lines) {
            ArrayList<String> partsOfLine = splitOnSpace(line);
            for (int x = 0; x < 4; x++) {
                board.get(x + 4 * y).setValue(Integer.parseInt(partsOfLine.get(x)));
            }
            y++;
        }
        return board;
    }

    private static ArrayList<String> splitOnSpace(String line) {
        String[] splits = line.split(",");
        return new ArrayList<>(Arrays.asList(splits));
    }

    public int loadScore(String fileName) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(fileName));
        return Integer.parseInt(String.valueOf(lines.get(0)).trim());
    }
}
