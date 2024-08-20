package edu.uoc.trip.model.levels;

public enum Direction {
    UP(-1,0,2),
    RIGHT(0,1,3),
    DOWN(1,0,0),
    LEFT(0,-1,1);

    private final int dRow;
    private final int dColumn;
    private final int opposite;

    Direction(int dRow, int dColumn, int opposite) {
        this.dRow = dRow;
        this.dColumn = dColumn;
        this.opposite = opposite;
    }

    public static Direction getValueByIndex(int index) {
        if (index == 0) {
            return UP;
        } else if (index == 1) {
            return RIGHT;
        } else if (index == 2) {
            return DOWN;
        } else if (index == 3){
            return LEFT;
        }else{
            return null;
        }
    }

    public int getDRow() {
        return dRow;
    }

    public int getDColumn() {
        return dColumn;
    }

    public Direction getOpposite() {
        return getValueByIndex(opposite);
    }
}
