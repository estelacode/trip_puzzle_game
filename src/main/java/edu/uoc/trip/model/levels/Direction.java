package edu.uoc.trip.model.levels;

/**
 * Enumeración que representa las direcciones de las celdas.
 * @author Estela Madariaga
 * @version 1.0
 */
public enum Direction {
    /* Dirección arriba. */
    UP(-1,0,2),
    /* Dirección derecha. */
    RIGHT(0,1,3),
    /* Dirección abajo. */
    DOWN(1,0,0),
    /* Dirección izquierda. */
    LEFT(0,-1,1);

    
    /**
     * Row change value associated with this direction.
     */
    private final int dRow;
    /** 
     * Column change value associated with this direction. 
    */
    private final int dColumn;
    /**
     * Opposite direction of the current direction.
    */
    private final int opposite;

    /** Constructor de la enumeración. */
    Direction(int dRow, int dColumn, int opposite) {
        this.dRow = dRow;
        this.dColumn = dColumn;
        this.opposite = opposite;
    }

    /**
     * Returns a Direction enum value based on the given index.
     *
     * @param index the index of the desired Direction enum value
     * @return the Direction enum value corresponding to the given index, or null if the index is out of range
     */
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

    /**
     * Returns the row change value associated with this direction.
     *
     * @return  the row change value
     */
    public int getDRow() {
        return dRow;
    }

    /**
     * Returns the column change value associated with this direction.
     *
     * @return  the column change value
     */
    public int getDColumn() {
        return dColumn;
    }

    /**
     * Returns the opposite direction of the current direction.
     *
     * @return  the opposite direction
     */
    public Direction getOpposite() {
        return getValueByIndex(opposite);
    }
}
