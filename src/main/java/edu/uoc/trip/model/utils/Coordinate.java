package edu.uoc.trip.model.utils;

import java.util.Objects;

public class Coordinate {
    private int row;
    private int column;

    public Coordinate(int row, int column){
        setRow(row);
        setColumn(column);

    }

    public int getRow() {
        return row;
    }

    private void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    private void setColumn(int column) {
        this.column = column;
    }

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

    @Override
    public int hashCode(){
        return  Objects.hash(row, column);
    }

    @Override
    public String toString() {
        return "("+ this.row + "," + this.column + ")";
    }
}
