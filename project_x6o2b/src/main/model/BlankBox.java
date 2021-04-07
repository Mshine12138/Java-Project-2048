package model;

class BlankBox extends Box {

    BlankBox(Board board) {
        super(board);
        this.setValue(0);
        this.board = board;
    }

}
