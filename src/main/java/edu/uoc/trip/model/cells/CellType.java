package edu.uoc.trip.model.cells;

import edu.uoc.trip.model.levels.Direction;

import java.util.EnumSet;

/**
 * Enumeración que representa los diferentes tipos de celdas en un nivel.
 * Cada tipo de celda tiene un símbolo de archivo, una representación Unicode,
 * una fuente de imagen y una serie de conexiones posibles.
 * 
 * @author Estela Madariaga
 * @version 1.0
 */
public enum CellType {

    /**
     * Tipo de celda que representa el inicio del nivel.
     */
    START('S','^',"start.png", new boolean[]{true, false, false, false}){
        @Override
        public CellType next(){
            return null;
        }
    },

    /**
     * Tipo de celda que representa el final del nivel.
     */
    FINISH('F','v',"finish.png",new boolean[]{false, false, true, false}){
        @Override
        public CellType next(){
            return null;
        }
    },

    /**
    * Tipo de celda que representa una montaña.
    */
    MOUNTAINS('M','M',"mountains.png",null){
        @Override
        public CellType next(){
            return null;
        }
    },

    /**
    * Tipo de celda que representa un río.
    */
    RIVER('~','~',"river.png",null){
        @Override
        public CellType next(){
            return null;
        }
    },

    /**
    * Tipo de celda que representa una carretera vertical.
    */
    VERTICAL('V','\u2551',"road_vertical.png",new boolean[]{true, false, true, false}){
        @Override
        public CellType next(){
            return HORIZONTAL;
        }
    },

    /**
    * Tipo de celda que representa una carretera horizontal.
    */
    HORIZONTAL('H','\u2550',"road_horizontal.png",new boolean[]{false, true, false, true}){
        @Override
        public CellType next(){
            return VERTICAL;
        }
    },

    /**
    * Tipo de celda que representa una esquina inferior derecha.
    */
    BOTTOM_RIGHT('r','\u2554',"road_bottom_right.png",new boolean[]{false, true, true, false}){
        @Override
        public CellType next(){
            return BOTTOM_LEFT;
        }
    },

    /**
    * Tipo de celda que representa una esquina inferior izquierda.
    */
    BOTTOM_LEFT('l','\u2557',"road_bottom_left.png",new boolean[]{false, false, true, true}){
        @Override
        public CellType next(){
            return TOP_LEFT;
        }
    },

    /**
    * Tipo de celda que representa una esquina superior derecha.
    */
    TOP_RIGHT('R','\u255A',"road_top_right.png",new boolean[]{true, true, false, false}){
        @Override
        public CellType next(){
            return BOTTOM_RIGHT;
        }
    },

    
    /**
    * Tipo de celda que representa una esquina superior izquierda.
    */
    TOP_LEFT('L','\u255D',"road_top_left.png",new boolean[]{true, false, false, true}){
        @Override
        public CellType next(){
            return TOP_RIGHT;
        }
    },

    /**
    * Tipo de celda que representa una celda libre.
    */
    FREE('·','\u00b7',"free.png",null){
        @Override
        public CellType next(){
            return null;
        }
    },


    /**
    * Tipo de celda que representa una carretera vertical rotatable.
    */
    ROTATABLE_VERTICAL('G','\u2503',"road_rotatable_vertical.png",new boolean[]{true, false, true, false}){
        @Override
        public CellType next(){
            return ROTATABLE_HORIZONTAL;
        }
    },

    /**
    * Tipo de celda que representa una carretera horizontal rotatable.
    */
    ROTATABLE_HORIZONTAL('g','\u2501',"road_rotatable_horizontal.png",new boolean[]{false, true, false, true}){
        @Override
        public CellType next(){
            return ROTATABLE_VERTICAL;
        }
    };

    /**
    * Símbolo de archivo para esta celda.
    */
    private char fileSymbol;

    
    /**
    * Representación Unicode para esta celda.
    */
    private char unicodeRepresentation;

    /**
    * Fuente de imagen para esta celda.
    */
    private String imageSrc;

    /**
    * Conexiones posibles para esta celda.
    */
    private boolean[] connections;

    /**
     * Constructor para esta celda.
     * 
     * @param fileSymbol símbolo de archivo para esta celda
     * @param unicodeRepresentation representación Unicode para esta celda
     * @param imageSrc fuente de imagen para esta celda
     * @param connections conexiones posibles para esta celda
     */
    CellType(char fileSymbol,char unicodeRepresentation, String imageSrc,boolean[] connections){
        setFileSymbol(fileSymbol);
        setUnicodeRepresentation(unicodeRepresentation);
        setImageSrc(imageSrc);
        setConnections(connections);
    }

    /**
     * Obtiene el símbolo de archivo para esta celda.
     * 
     * @return símbolo de archivo para esta celda
    */
    public char getFileSymbol() {
        return fileSymbol;
    }

    /**
     * Establece el símbolo de archivo para esta celda.
     * 
     * @param fileSymbol símbolo de archivo para esta celda
    */
    private void setFileSymbol(char fileSymbol) {
        this.fileSymbol = fileSymbol;
    }

    
    /**
     * Obtiene la representación Unicode para esta celda.
     * 
     * @return representación Unicode para esta celda
    */
    public char getUnicodeRepresentation() {
        return unicodeRepresentation;
    }

    /**
     * Establece la representación Unicode para esta celda.
     * 
     * @param unicodeRepresentation representación Unicode para esta celda
    */
    private void setUnicodeRepresentation(char unicodeRepresentation) {
        this.unicodeRepresentation = unicodeRepresentation;
    }

    /**
     * Obtiene la fuente de imagen para esta celda.
     * 
     * @return fuente de imagen para esta celda
     */
    public String getImageSrc() {
        return imageSrc;
    }

    /**
     * Establece la fuente de imagen para esta celda.
     * 
     * @param imageSrc fuente de imagen para esta celda
    */
    private void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }

    /**
     * Establece las conexiones posibles para esta celda.
     * 
     * @param connections conexiones posibles para esta celda
    */
    private void setConnections(boolean[] connections) {
        this.connections = connections;
    }

    /**
     * Obtiene las conexiones posibles para esta celda.
     * 
     * @return conexiones posibles para esta celda
    */
    public EnumSet<Direction> getAvailableConnections(){
        EnumSet<Direction> output = EnumSet.noneOf(Direction.class);
        if(connections != null){
            for(int i=0; i < connections.length; i++){
                if (connections[i])
                    output.add(Direction.getValueByIndex(i));
            }
        }
        return output;
    }

    /**
     * Obtiene la celda correspondiente a un símbolo de archivo.
     * 
     * @param fileSymbol símbolo de archivo
     * @return celda correspondiente al símbolo de archivo
    */
    public static CellType map2CellType(char fileSymbol){
        for(CellType celda : CellType.values()){
            if(celda.getFileSymbol() == fileSymbol){
                return celda;
            }
        }
        return null;
    }

    /**
     * Obtiene la siguiente celda en la secuencia.
     * 
     * @return siguiente celda en la secuencia
    */
    public abstract CellType next();
}
