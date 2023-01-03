package com.example.minesweeper;

public class Field {
    private int id;
    private int row;
    private int column;
    private int countBombsAround;
    private boolean isOpen = false;
    private boolean isFlag = false;
    private boolean isBomb = false;
    private boolean isBoomBomb = false;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCountBombsAround() {
        return countBombsAround;
    }

    public void setCountBombsAround(int countBombsAround) {
        this.countBombsAround = countBombsAround;
    }

    public boolean getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(boolean open) {
        isOpen = open;
    }

    public boolean getIsFlag() {
        return isFlag;
    }

    public void setIsFlag(boolean isFlag) {
        this.isFlag = isFlag;
    }

    public void setIsBomb(boolean isBomb) {
        this.isBomb = isBomb;
    }

    public boolean getIsBomb() {
        return isBomb;
    }

    public boolean getIsBoomBomb() {
        return isBoomBomb;
    }

    public void setIsBoomBomb(boolean boomBomb) {
        isBoomBomb = boomBomb;
    }

    public Field(int id, int row, int column) {
        this.id = id;
        this.row = row;
        this.column = column;
    }
}
