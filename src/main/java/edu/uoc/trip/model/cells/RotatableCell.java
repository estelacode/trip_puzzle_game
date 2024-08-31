package edu.uoc.trip.model.cells;


/**
 * Clase que representa una celda rotable en el modelo del juego. 
 * @author Estela Madariaga
 * @version 1.0
 */
public class RotatableCell extends Cell implements Rotatable {

    /**
     * Constructor de la clase RotatableCell.
     * @param row Row of the coordinate/position in which the cell is in the board.
     * @param column Column of the coordinate/position in which the cell is in the board.
     * @param type  Type of the cell.
     */
    public RotatableCell(int row, int column, CellType type){
        super(row, column, type);
    }

    /**
     * Checks if the cell is movable.
     *
     * @return  <code>true</code> if the cell is movable, <code>false</code> otherwise
     */
    @Override
    public boolean isMovable() {
        return false;
    }

    /**
     * Checks if the cell is rotatable.
     *
     * @return true if the cell is rotatable, false otherwise
     */
    @Override
    public  boolean isRotatable(){
        return true;
    }

    /**
     * Rotates the cell that invokes this method by using CellType's "next" method.
     *
     * @return         	void
     */
    public void rotate(){
          this.setType(this.getType().next());
    }
}
