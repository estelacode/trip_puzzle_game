package edu.uoc.trip.model.cells;

import edu.uoc.trip.model.utils.Coordinate;


/**
 * Clase que representa una celda movible en el modelo del juego.
 * 
 * @author Estela Madariaga
 * @version 1.0
 */
public class MovableCell extends Cell implements Movable{

    public MovableCell(int row, int column, CellType type){
        super(row, column, type);
    }

    /**
     * Checks if the cell is movable.
     *
     * @return  <code>true</code> if the cell is movable, <code>false</code> otherwise
     */
    @Override
    public boolean isMovable() {
        return true;
    }

    /**
     * Checks if the cell is rotatable.
     *
     * @return true if the cell is rotatable, false otherwise
     */
    @Override
    public  boolean isRotatable(){
        return false;
    }

    /**
     * Moves the cell that invokes this method from the current position/coordinate to the destination coordinate/position.
     *
     * @param  destination  Position of the board in which we want to move the cell that invokes this method.
     * @return void
     */
    public void move(Coordinate destination){
        setCoordinate(destination.getRow(),destination.getColumn());
    }
}
