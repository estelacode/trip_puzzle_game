package edu.uoc.trip.model.cells;

/** 
 * Interface that represents if a cell is rotatable
 * 
 * @author Estela Madariaga
 * @version 1.0
 */
public interface Rotatable {

    /**
     * Checks if the cell is rotatable.
     * 
     * @return true if the cell is rotatable, false otherwise
     */
    boolean isRotatable();

    /**
     * Rotates the cell
     */
    void rotate();
}
