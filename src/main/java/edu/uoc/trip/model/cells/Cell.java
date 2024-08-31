package edu.uoc.trip.model.cells;

import edu.uoc.trip.model.utils.Coordinate;
/**
 * Clase que representa una celda en el modelo del juego.
 * 
 * @author Estela Madariaga
 * @version 1.0
 */
public class Cell {

    /**
     * Coordenada de la celda.
     */
    private Coordinate coordinate;

    /**
     * Tipo de celda.
     */
    private CellType type;

    /**
     * Constructor de la clase Cell.
     * 
     * @param row    Fila de la celda.
     * @param column Columna de la celda.
     * @param type   Tipo de celda.
     */
    public Cell(int row, int column, CellType type) {
        setType(type);
        setCoordinate(row, column);
    }

    /**
     * Obtiene el tipo de celda.
     * 
     * @return Tipo de celda.
     */
    public CellType getType() {
        return type;
    }

    /**
     * Establece el tipo de celda.
     * 
     * @param type Tipo de celda.
     */
    protected void setType(CellType type) {
        this.type = type;
    }

    /**
     * Obtiene la coordenada de la celda.
     * 
     * @return Coordenada de la celda.
     */
    public Coordinate getCoordinate() {
        return coordinate;
    }

    /**
     * Establece la coordenada de la celda.
     * 
     * @param row    Fila de la celda.
     * @param column Columna de la celda.
     */
    protected void setCoordinate(int row, int column) {
        this.coordinate = new Coordinate(row, column);
    }

    /**
     * Indica si la celda es movible.
     * 
     * @return true si la celda es movible, false en caso contrario.
     */
    public boolean isMovable() {
        return false;
    }

    /**
     * Indica si la celda es rotatable.
     * 
     * @return true si la celda es rotatable, false en caso contrario.
     */
    public boolean isRotatable() {
        return false;
    }

    /**
     * Devuelve una representación en cadena de la celda.
     * 
     * @return Representación en cadena de la celda.
     */
    @Override
    public String toString() {
        return String.valueOf(this.getType().getUnicodeRepresentation());
    }
}