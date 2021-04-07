package model;

import java.util.ArrayList;
import java.util.Random;

public class Board extends ArrayList<Box> /*implements Loadable*/ {
    public String printOut;
    private Merge merge;
    private boolean moved;
    private boolean skipped;
    private int scoreEarned;

    //MODIFIES: this
    public Board() {
        for (int i = 0; i < 16; i++) {
            add(new BlankBox(this));
        }
        moved = false;
        skipped = false;
    }

    public void printBoard() {
        StringBuilder thisLine = new StringBuilder("______");
        printOut = "";

        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                Box b = get(4 * y + x);
                printOut = printOut + (b.getValue() + ",");
                thisLine.append(b.getValue()).append("___");
            }
            printOut = printOut + "\n";
            System.out.println(thisLine);
            thisLine = new StringBuilder("______");
        }
    }

    //REQUIRES: this
    //MODIFIES: this
    //EFFECTS: produce a new box and add it to the board.
    public void nextBoard() {
        Box box = new Box(this);

        if (blankBoxes().isEmpty()) {
            System.out.println("No more moves.");
        } else {
            set(randomBlankIndex(), box);
        }
        giveBoxARandomValue(box);
    }

    //EFFECTS: return a random index which a blank box is placed at.
    public int randomBlankIndex() {
        Random rand = new Random();
        int i = rand.nextInt(16);

        while (!get(i).isBlank()) {
            i = rand.nextInt(16);
        }
        return i;
    }

    //REQUIRES: a box in the board
    //MODIFIES: box
    //EFFECTS: give the box a random value
    private void giveBoxARandomValue(Box box) {
        Random rand = new Random();
        box.setValue((rand.nextInt(2) + 1) * 2);
    }

    //REQUIRES: blankBoxes() is Empty
    //MODIFIES: Nothing
    //EFFECTS: return if no more boxes can be neither moved nor merged.
    public boolean hasNextBoard() {
        if (blankBoxes().isEmpty()) {
            return checkHorizontally() || checkVertically();
        }
        return true;
    }

    public boolean checkHorizontally() {
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 3; x++) {
                int a = get(4 * y + x).getValue();
                int b = get(4 * y + x + 1).getValue();

                if (a == b) {
                    if (a != 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean checkVertically() {
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 3; y++) {
                int a = get(4 * y + x).getValue();
                int b = get(4 * (y + 1) + x).getValue();

                if (a == b) {
                    if (a != 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public ArrayList<Box> blankBoxes() {
        ArrayList<Box> blankBoxes = new ArrayList<>();

        for (Box box : this) {
            if (box.isBlank()) {
                blankBoxes.add(box);
            }
        }
        return blankBoxes;
    }

    //REQUIRES: this
    //MODIFIES: this
    //EFFECTS: merge the same boxes and move zeros behind
    public boolean mergeLeft() {
        moved = false;
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                for (int n = x + 1; n < 4; n++) {
                    merge = new Merge(y, x, n);
                    if (merge.horizontally()) {
                        break;
                    }
                }
            }
        }
        return didMove();
    }

    public boolean mergeRight() {
        moved = false;
        for (int y = 0; y < 4; y++) {
            for (int x = 3; x >= 0; x--) {
                for (int n = x - 1; n >= 0; n--) {
                    merge = new Merge(y, x, n);
                    if (merge.horizontally()) {
                        break;
                    }
                }
            }
        }
        return didMove();
    }

    public boolean mergeUp() {
        moved = false;
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                for (int n = y + 1; n < 4; n++) {
                    merge = new Merge(y, x, n);
                    if (merge.vertically()) {
                        break;
                    }
                }
            }
        }
        return didMove();
    }

    public boolean mergeDown() {
        moved = false;
        for (int x = 0; x < 4; x++) {
            for (int y = 3; y >= 0; y--) {
                for (int n = y - 1; n >= 0; n--) {
                    merge = new Merge(y, x, n);
                    if (merge.vertically()) {
                        break;
                    }
                }
            }
        }
        return didMove();
    }

    public boolean didMove() {
        return moved;
    }

    public int getScoreEarned() {
        return scoreEarned;
    }

    public void setScoreEarned(int scoreEarned) {
        this.scoreEarned = scoreEarned;
    }

    public boolean merge2Boxes(Box box1, Box box2) {
        if (box1.getValue() == 0) {
            skipped = (box2.getValue() != 0);
            if (skipped) {
                box1.setValue(box1.getValue() + box2.getValue());
                box2.setValue(0);
                moved = true;
            }
        } else if (box1.getValue() != 0) {
            if (box1.getValue() == box2.getValue()) {
                box1.setValue(box1.getValue() + box2.getValue());
                box2.setValue(0);
                moved = true;
                scoreEarned += box1.getValue();
                return skipped = true;
            }
            return (box2.getValue() != 0);
        }
        return false;
    }

    public boolean move(String input) {
        System.out.println("Please enter a movement");
        return moveSelect(input);
    }

    public boolean moveSelect(String input) {
        if (input.equals("w")) {
            //up();
            System.out.println("moved upwards");
            return mergeUp();
        } else if (input.equals("s")) {
            //down();
            System.out.println("moved downwards");
            return mergeDown();
        } else if (input.equals("a")) {
            //left();
            System.out.println("moved leftwards");
            return mergeLeft();
        } else if (input.equals("d")) {
            //right();
            System.out.println("moved rightwards");
            return mergeRight();
        } else {
            return false;
        }
    }

    private class Merge {
        private int intY;
        private int intX;
        private int intN;

        Merge(int y, int x, int n) {
            skipped = false;
            this.intY = y;
            this.intX = x;
            this.intN = n;
        }

        boolean horizontally() {
            Box box1 = get(4 * intY + intX);
            Box box2 = get(4 * intY + intN);

            return merge2Boxes(box1, box2);
        }

        boolean vertically() {
            Box box1 = get(4 * intY + intX);
            Box box2 = get(4 * intN + intX);

            return merge2Boxes(box1, box2);
        }
    }
}