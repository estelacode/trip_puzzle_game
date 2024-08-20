package edu.uoc.trip.model.cells;

import edu.uoc.trip.model.utils.Coordinate;

public class Cell{

    private Coordinate coordinate;
    private CellType type;

    public Cell(int row, int column, CellType type){
        setType(type);
        setCoordinate(row, column);
    }

    public CellType getType(){
        return type;
    }

    protected void setType(CellType type){
        this.type = type;
    }

    public Coordinate getCoordinate(){
        return coordinate;
    }

    protected void setCoordinate(int row, int column){
        this.coordinate = new Coordinate(row, column);
    }

    public boolean isMovable(){
        return false;
    }

    public boolean isRotatable(){
        return false;
    }

    @Override
    public String toString() {
        return String.valueOf(this.getType().getUnicodeRepresentation());
    }
}
