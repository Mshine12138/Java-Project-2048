package model;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class Box extends JLabel {
    Board board;
    private int value;

    public Box(Board board) {
        this.board = board;
        setHorizontalAlignment(SwingConstants.CENTER);
        setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.black, null));
        setOpaque(true);
        setBackground(new Color(204, 192, 178));
        setForeground(Color.white);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        if (value == 0) {
            this.setText("");
            setBackground(new Color(204, 192, 178));
        } else if (value == 2) {
            this.setText("2");
            setFont(new Font("", Font.BOLD, 60));
            setForeground(new Color(121, 111, 101));
            setBackground(new Color(238, 228, 218));
        } else if (value == 4) {
            this.setText("4");
            setFont(new Font("", Font.BOLD, 60));
            setForeground(new Color(121, 111, 101));
            setBackground(new Color(236, 224, 200));
        } else {
            setValueOver8(value);
        }

    }

    private void setValueOver8(int value) {
        if (value == 8) {
            this.setText("8");
            setFont(new Font("", Font.BOLD, 60));
            setBackground(new Color(242, 177, 121));
            setForeground(Color.white);
        } else if (value == 16) {
            this.setText("16");
            setFont(new Font("", Font.BOLD, 50));
            setBackground(new Color(245, 149, 99));
            setForeground(Color.white);
        } else if (value == 32) {
            this.setText("32");
            setFont(new Font("", Font.BOLD, 50));
            setBackground(new Color(243, 124, 94));
            setForeground(Color.white);
        } else {
            setValueOver64(value);
        }
    }

    private void setValueOver64(int value) {
        if (value == 64) {
            this.setText("64");
            setFont(new Font("", Font.BOLD, 50));
            setBackground(new Color(246, 93, 59));
            setForeground(Color.white);
        } else if (value == 128) {
            this.setText("128");
            setFont(new Font("", Font.BOLD, 40));
            setBackground(new Color(237, 204, 97));
            setForeground(Color.white);
        } else if (value == 256) {
            this.setText("256");
            setFont(new Font("", Font.BOLD, 40));
            setBackground(new Color(205, 92, 92));
            setForeground(Color.white);
        } else {
            setValueOver512(value);
        }
    }

    private void setValueOver512(int value) {
        if (value == 512) {
            this.setText("512");
            setFont(new Font("", Font.BOLD, 40));
            setBackground(new Color(205, 85, 00));
            setForeground(Color.white);
        } else if (value == 1024) {
            this.setText("1024");
            setFont(new Font("", Font.BOLD, 30));
            setBackground(new Color(238, 201, 0));
            setForeground(Color.white);
        } else if (value == 2048) {
            this.setText("2048");
            setFont(new Font("", Font.BOLD, 30));
            setBackground(new Color(255, 30, 30));
            setForeground(Color.white);
        } else if (value > 8192) {
            System.exit(0);

        }
    }

    public int getColumn() {
        return getIndex() % 4;
    }

    public int getRow() {
        return getIndex() / 4;
    }

    public int getIndex() {
        return board.indexOf(this);
    }

    //需要进一步修改
    public boolean isBlank() {
        return getValue() == 0;
    }

}
