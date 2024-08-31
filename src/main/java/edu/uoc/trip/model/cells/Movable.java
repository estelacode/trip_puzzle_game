package edu.uoc.trip.model.cells;

import edu.uoc.trip.model.utils.Coordinate;

/**
 * Interfaz que representa si una celda se puede mover.
 * 
 * @author Estela Madariaga
 * @version 1.0
 */
public interface Movable {

    /**
     * Determina si la celda puede moverse.
     * 
     * @return <code>true</code> si la celda puede moverse, <code>false</code> en caso contrario.
     */
    boolean isMovable();

    /**
     * Movimiento de la celda.
     * @param destination coordenada de destino
    */
    void move(Coordinate destination);
}
