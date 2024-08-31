package edu.uoc.trip.model.utils;

import java.util.Objects;

/** 
 * Clase Coordinate que representa la coordenada de una celda.
 * @author Estela Madariaga
 * @version 1.0
 */
public class Coordinate {
    /**
     * Fila de la coordenada.
     */
    private int row;
    /**
     * Columna de la coordenada.
     */
    private int column;

    /**
     * Constructor de la clase Coordinate.
     */
    public Coordinate(int row, int column){
        setRow(row);
        setColumn(column);

    }

    /**
     * Returns the row of the coordinate.
     *
     * @return the row value
     */
    public int getRow() {
        return row;
    }

    /**
     * Sets the row of the coordinate.
     *
     * @param row the new row value
     */
    private void setRow(int row) {
        this.row = row;
    }

    /**
     * Returns the column of the coordinate.
     *
     * @return  the column value
     */
    public int getColumn() {
        return column;
    }

    /**
     * Sets the column of the coordinate.
     *
     * @param column the new column value
     */
    private void setColumn(int column) {
        this.column = column;
    }

    /**
     * Checks if this Coordinate object is equal to another object. Two Coordinate objects are considered equal if they have the same row and column values.
     *
     * @param  obj  the object to compare with
     * @return      true if this object is equal to the obj argument, false otherwise
     */
    @Override
    public boolean equals(Object obj){
        if(obj == this) // Comprarar referencias
            return true;
        if(!(obj instanceof Coordinate))// Comprobar que el parametro obj es de la clase Coordinate.
            return false;

        Coordinate other = (Coordinate) obj;
        if(this.row == other.row  && this.column == other.column){
            return true;
        }
        return false;
    }

    /**
     * Returns the hash code value for this Coordinate object.
     *
     * @return  a hash code value for this object
     */
    @Override
    public int hashCode(){
        return  Objects.hash(row, column);
    }

    /**
     * Returns a string representation of the Coordinate object.
     *
     * @return  a string in the format "(row, column)"
     */
    @Override
    public String toString() {
        return "("+ this.row + "," + this.column + ")";
    }
}
