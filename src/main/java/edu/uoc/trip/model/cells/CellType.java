package edu.uoc.trip.model.cells;

import edu.uoc.trip.model.levels.Direction;

import java.util.EnumSet;

public enum CellType {

    START('S','^',"start.png", new boolean[]{true, false, false, false}){
        @Override
        public CellType next(){
            return null;
        }
    },

    FINISH('F','v',"finish.png",new boolean[]{false, false, true, false}){
        @Override
        public CellType next(){
            return null;
        }
    },

    MOUNTAINS('M','M',"mountains.png",null){
        @Override
        public CellType next(){
            return null;
        }
    },

    RIVER('~','~',"river.png",null){
        @Override
        public CellType next(){
            return null;
        }
    },


    VERTICAL('V','\u2551',"road_vertical.png",new boolean[]{true, false, true, false}){
        @Override
        public CellType next(){
            return HORIZONTAL;
        }
    },

    HORIZONTAL('H','\u2550',"road_horizontal.png",new boolean[]{false, true, false, true}){
        @Override
        public CellType next(){
            return VERTICAL;
        }
    },

    BOTTOM_RIGHT('r','\u2554',"road_bottom_right.png",new boolean[]{false, true, true, false}){
        @Override
        public CellType next(){
            return BOTTOM_LEFT;
        }
    },

    BOTTOM_LEFT('l','\u2557',"road_bottom_left.png",new boolean[]{false, false, true, true}){
        @Override
        public CellType next(){
            return TOP_LEFT;
        }
    },

    TOP_RIGHT('R','\u255A',"road_top_right.png",new boolean[]{true, true, false, false}){
        @Override
        public CellType next(){
            return BOTTOM_RIGHT;
        }
    },

    TOP_LEFT('L','\u255D',"road_top_left.png",new boolean[]{true, false, false, true}){
        @Override
        public CellType next(){
            return TOP_RIGHT;
        }
    },

    FREE('·','\u00b7',"free.png",null){
        @Override
        public CellType next(){
            return null;
        }
    },

    ROTATABLE_VERTICAL('G','\u2503',"road_rotatable_vertical.png",new boolean[]{true, false, true, false}){
        @Override
        public CellType next(){
            return ROTATABLE_HORIZONTAL;
        }
    },

    ROTATABLE_HORIZONTAL('g','\u2501',"road_rotatable_horizontal.png",new boolean[]{false, true, false, true}){
        @Override
        public CellType next(){
            return ROTATABLE_VERTICAL;
        }
    };



    private char fileSymbol;
    private char unicodeRepresentation;
    private String imageSrc;
    private boolean[] connections;

    CellType(char fileSymbol,char unicodeRepresentation, String imageSrc,boolean[] connections){
        setFileSymbol(fileSymbol);
        setUnicodeRepresentation(unicodeRepresentation);
        setImageSrc(imageSrc);
        setConnections(connections);
    }

    public char getFileSymbol() {
        return fileSymbol;
    }

    private void setFileSymbol(char fileSymbol) {
        this.fileSymbol = fileSymbol;
    }

    public char getUnicodeRepresentation() {
        return unicodeRepresentation;
    }

    private void setUnicodeRepresentation(char unicodeRepresentation) {
        this.unicodeRepresentation = unicodeRepresentation;
    }

    public String getImageSrc() {
        return imageSrc;
    }

    private void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }

    private void setConnections(boolean[] connections) {
        this.connections = connections;
    }


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

    public static CellType map2CellType(char fileSymbol){
        for(CellType celda : CellType.values()){
            if(celda.getFileSymbol() == fileSymbol){
                return celda;
            }
        }
        return null;
    }

    public abstract CellType next();
}
