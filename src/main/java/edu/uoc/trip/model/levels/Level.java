package edu.uoc.trip.model.levels;

import edu.uoc.trip.model.cells.*;
import edu.uoc.trip.model.utils.Coordinate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * Class that represents each level of the game.
 *
 * @author David García-Solórzano
 * @version 1.0
 */
public class Level {

    /**
     * Size of the board, i.e. size x size.
     */
    private int size;

    /**
     * Difficulty of the level
     */
    private LevelDifficulty difficulty;

    /**
     * Representation of the board.
     */
    private Cell[][] board;

    /**
     * Number of moves that the player has made so far.
     */
    private int numMoves = 0;

    /**
     * Minimum value that must be assigned to the attribute "size".
     */
    private static final int MINIMUM_BOARD_SIZE = 3;

    /**
     * Constructor
     *
     * @param fileName Name of the file that contains level's data.
     * @throws LevelException When there is any error while parsing the file.
     */
    public Level(String fileName) throws LevelException {
        setNumMoves(0);
        parse(fileName);
    }

    public int getSize(){
        return size;
    }

    private void setSize(int size) throws LevelException {
        if(size < 3){
            throw new LevelException(LevelException.ERROR_BOARD_SIZE);
        }else{
            this.size = size;
        }
    }

    public LevelDifficulty getDifficulty() {
        return difficulty;
    }

    private void setDifficulty(LevelDifficulty difficulty){
        this.difficulty = difficulty;
    }

    public int getNumMoves(){
        return numMoves;
    }

    private void setNumMoves(int numMoves){
        this.numMoves = numMoves;

    }
    /**
     * Parses/Reads level's data from the given file.<br/>
     * It also checks which the board's requirements are met.
     *
     * @param fileName Name of the file that contains level's data.
     * @throws LevelException When there is any error while parsing the file
     * or some board's requirement is not satisfied.
     */
    private void parse(String fileName) throws LevelException{
        boolean isStarting = false;
        boolean isFinish = false;
        String line;

        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = Objects.requireNonNull(classLoader.getResourceAsStream(fileName));

        try(InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(streamReader)){

            line = getFirstNonEmptyLine(reader);

            if (line  != null) {
                setSize(Integer.parseInt(line));
            }

            line = getFirstNonEmptyLine(reader);

            if (line != null) {
                setDifficulty(LevelDifficulty.valueOf(line));
            }

            board = new Cell[getSize()][getSize()];

            for (int row = 0; row < getSize(); row++) {
                char[] rowChar = Objects.requireNonNull(getFirstNonEmptyLine(reader)).toCharArray();
                for (int column = 0; column < getSize(); column++) {
                    board[row][column] = CellFactory.getCellInstance(row, column,
                            Objects.requireNonNull(CellType.map2CellType(rowChar[column])));
                }
            }

        }catch (IllegalArgumentException | IOException e){
            throw new LevelException(LevelException.ERROR_PARSING_LEVEL_FILE);
        }

        //Check if there is one starting cell, one finish cell and, at least, any other type of cell.
        for(var j =0; j<getSize(); j++){

            if(getCell(new Coordinate(getSize()-1,j)).getType() == CellType.START){
                isStarting = true;
            }

            if(getCell(new Coordinate(0,j)).getType() == CellType.FINISH){
                isFinish = true;
            }
        }

        //Checks if there are more than one starting cell
        if(Stream.of(board).flatMap(Arrays::stream).filter(x -> x.getType() == CellType.START).count()>1){
            throw new LevelException(LevelException.ERROR_PARSING_LEVEL_FILE);
        }

        //Checks if there are more than one finish cell
        if(Stream.of(board).flatMap(Arrays::stream).filter(x -> x.getType() == CellType.FINISH).count()>1){
            throw new LevelException(LevelException.ERROR_PARSING_LEVEL_FILE);
        }

        if(!isStarting){
            throw new LevelException(LevelException.ERROR_NO_STARTING);
        }

        if(!isFinish){
            throw new LevelException(LevelException.ERROR_NO_FINISH);
        }

        //Checks if there is one road (i.e. movable or rotatable cell) at least.
        if(Stream.of(board).flatMap(Arrays::stream).noneMatch(x -> x.isMovable() || x.isRotatable())){
            throw new LevelException(LevelException.ERROR_NO_ROAD);
        }

    }

    /**
     * This a helper method for {@link #parse(String fileName)} which returns
     * the first non-empty and non-comment line from the reader.
     *
     * @param br BufferedReader object to read from.
     * @return First line that is a parsable line, or {@code null} there are no lines to read.
     * @throws IOException if the reader fails to read a line.
     */
    private String getFirstNonEmptyLine(final BufferedReader br) throws IOException {
        do {

            String s = br.readLine();

            if (s == null) {
                return null;
            }
            if (s.isBlank() || s.startsWith("#")) {
                continue;
            }

            return s;
        } while (true);
    }


    private boolean validatePosition(Coordinate coord){
        if (coord == null){
            return false;
        }
        return (coord.getRow()>=0 && coord.getRow()< size && coord.getColumn()>=0 && coord.getColumn()< size);
    }

    public Cell getCell(Coordinate coord) throws LevelException {
       if(validatePosition(coord)){
           return board[coord.getRow()][coord.getColumn()];
       }else{
           throw  new LevelException(LevelException.ERROR_COORDINATE);
       }
    }

    private void setCell(Coordinate coord, Cell cell) throws LevelException {
        if (cell == null){
            throw new LevelException(LevelException.ERROR_COORDINATE);
        }
        if(validatePosition(coord)){
            board[coord.getRow()][coord.getColumn()] = cell;
        }else{
            throw new LevelException(LevelException.ERROR_COORDINATE);
        }
    }

    public void swapCells(Coordinate firstCoord, Coordinate secondCoord) throws LevelException {

        Cell cell1 = board[firstCoord.getRow()][firstCoord.getColumn()];
        Cell cell2 = board[secondCoord.getRow()][secondCoord.getColumn()];
        if (cell1.isMovable() && cell2.isMovable()) {

            MovableCell cellMov1 = (MovableCell)  cell1;
            cellMov1.move(secondCoord);

            MovableCell cellMov2 = (MovableCell)  cell2;
            cellMov2.move(firstCoord);

            setCell(firstCoord,cellMov2);
            setCell(secondCoord,cellMov1);
            numMoves += 1;
        } else {
            throw new LevelException(LevelException.ERROR_NO_MOVABLE_CELL);
        }
    }


    public void rotateCell(Coordinate coord) throws LevelException {
        Cell cell = board[coord.getRow()][coord.getColumn()];
        if(cell.isRotatable()){
            RotatableCell cellRot = (RotatableCell) cell;
            cellRot.rotate();
            numMoves +=1;
        }else{
            throw new LevelException(LevelException.ERROR_NO_ROTATABLE_CELL);
        }
    }

    public boolean isSolved(){
        // find s
        Cell start = null;
        int row = size-1;
        for (int col = 0; col < size; col++){
            Cell cell =  board[row][col];
            if (cell.getType().getFileSymbol()=='S'){
                start = cell;
            }
        }

        //condiciones de salida: itera en el bucle mientras haya direcciones a explorar y no encuentre la meta
        boolean options = true;
        int nextRow;
        int nextCol;

        Cell current = start;
        Cell previous = null;

        while(options) {

            EnumSet<Direction> directions = current.getType().getAvailableConnections();
            options = false;
            for (Direction direction : directions) {
                nextRow = current.getCoordinate().getRow() + direction.getDRow();
                nextCol = current.getCoordinate().getColumn() + direction.getDColumn();
                //valida que la cordenada de la celda que quiero explorar esta en el tablero
                if (validatePosition(new Coordinate(nextRow, nextCol))) {
                    Cell nextCell = board[nextRow][nextCol];
                    //comprueba que puedo ir desde la cell a la nextcell en esa direccion
                    if (!nextCell.equals(previous) && checkPath(current, nextCell, direction)) {
                        if (nextCell.getType().getFileSymbol() == 'F') {
                            return true;
                        } else {
                            //en previus guardo de donde vengo para comprobar que no vuelvo atras
                            previous = current;
                            current = nextCell;
                            options = true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean checkPath(Cell current, Cell next, Direction direction){
         if ( current.getType().getFileSymbol() =='S' && direction == Direction.UP
                 && Arrays.asList('F', 'V','l','r','G').contains(next.getType().getFileSymbol())){
             return true;
         }

        if( current.getType().getFileSymbol() == 'l' && direction == Direction.DOWN
                && Arrays.asList('F', 'G','V','R','L').contains(next.getType().getFileSymbol())){
            return true;
        }

         if( current.getType().getFileSymbol() == 'l' && direction == Direction.LEFT
                 && Arrays.asList('F', 'R','r','g','H').contains(next.getType().getFileSymbol())){
             return true;
         }

         if(current.getType().getFileSymbol() =='R' && direction == Direction.UP
                 && Arrays.asList('F','V','l','r','G').contains(next.getType().getFileSymbol())){
            return true;
        }

        if(current.getType().getFileSymbol() =='R' && direction == Direction.RIGHT
                && Arrays.asList('F','l','L','H','g').contains(next.getType().getFileSymbol())){
            return true;
        }

        if(current.getType().getFileSymbol() =='L' && direction == Direction.UP
                && Arrays.asList('F','l','r','V','G').contains(next.getType().getFileSymbol())){
            return true;
        }

        if(current.getType().getFileSymbol() =='L' && direction == Direction.LEFT
                && Arrays.asList('F','r','H','g','R').contains(next.getType().getFileSymbol())){
            return true;
        }

        if((current.getType().getFileSymbol() =='H' ||current.getType().getFileSymbol() =='g') && (direction == Direction.RIGHT)
                && Arrays.asList('F','L','l','g','H').contains(next.getType().getFileSymbol())){
            return true;
        }

        if((current.getType().getFileSymbol() =='H' ||current.getType().getFileSymbol() =='g') && (direction == Direction.LEFT)
                && Arrays.asList('F','R','r','g','H').contains(next.getType().getFileSymbol())){
            return true;
        }


        if((current.getType().getFileSymbol() =='V' ||current.getType().getFileSymbol() =='G') && (direction == Direction.UP)
                && Arrays.asList('F','l','r','G','V').contains(next.getType().getFileSymbol())){
            return true;
        }

        if((current.getType().getFileSymbol() =='V' ||current.getType().getFileSymbol() =='G') && (direction == Direction.DOWN)
                && Arrays.asList('F','L','R','G','V').contains(next.getType().getFileSymbol())){
            return true;
        }

        if((current.getType().getFileSymbol() =='r') && (direction == Direction.RIGHT)
                && Arrays.asList('F','L','g','H','l').contains(next.getType().getFileSymbol())){
            return true;
        }

        return (current.getType().getFileSymbol() == 'r') && (direction == Direction.DOWN)
                && Arrays.asList('F', 'G', 'V', 'L', 'R').contains(next.getType().getFileSymbol());
    }
    @Override
    public String toString() {

        StringBuilder output = new StringBuilder();

        for(int row=0; row < getSize(); row++) {
            output.append(row + 1);
        }
        output.append(System.lineSeparator());

        char letra ='a';
        for(int row=0; row < getSize(); row++) {
            output.append(letra).append("|");
            letra +=1;
            for (int col = 0; col < getSize(); col++) {
                Cell cell = board[row][col];
                output.append(cell.getType().getUnicodeRepresentation());
            }
            output.append(System.lineSeparator());
        }
        return output.toString();
    }
}
