package edu.uoc.trip.model.levels;
/**
 * Class that represents an exception which is related to the Level class.
 * @author Estela Madariaga
 * @version 1.0
 */
public class LevelException extends Exception {
    /**
     * Constants that represent the messages of the exception.
    */
    public static final String ERROR_PARSING_LEVEL_FILE = "[ERROR] There was an error while loading the current level file!!";
    public static final String ERROR_BOARD_SIZE = "[ERROR] Board's size must be greater than 2!!";
    public static final String ERROR_COORDINATE = "[ERROR] This coordinate is incorrect!!";
    public static final String ERROR_NO_STARTING = "[ERROR] This level does not have any starting cell!!";
    public static final String ERROR_NO_FINISH = "[ERROR] This level does not have any finish cell!!";
    public static final String ERROR_NO_ROAD = "[ERROR] This level does not have any road!!";
    public static final String ERROR_NO_MOVABLE_CELL = "[ERROR] You have chosen a static cell!";
    public static final String ERROR_NO_ROTATABLE_CELL = "[ERROR] You have chosen a non-rotatable cell!";

    /**
     * Constructor that sets the message of the exception.
     * @param msg Message of the exception
     */
    public LevelException(String msg){
        super(msg);
    }
}
