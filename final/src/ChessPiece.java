public abstract sealed class ChessPiece implements Movable permits King, Queen, Bishop, Knight, Rook {
    private final Colour colour; 
    private final char unicode;

    public ChessPiece(Colour colour, char unicode) {
        this.colour = colour;
        this.unicode = unicode;
    }

    public Colour getColour() {
        return colour;
    }

    public char getUnicode (){
        return unicode;
    }

    // PROJECT SEPC: OVERLOADING
    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY) {
        return isValidMovePieceSpecific(startX, startY, endX, endY);
    }

    public boolean isValidMove(char startChar, int startInt, char endChar, int endInt) {
        final int startX = startChar - 'a';
        final int startY = startInt - 1;
        final int endX = endChar - 'a';
        final int endY = endInt - 1;
    
        return isValidMove(startX, startY, endX, endY);
    }

    protected abstract boolean isValidMovePieceSpecific(int startX, int startY, int endX, int endY);

    // Get the class name ie. specific type of piece
    public String getType() {
        return this.getClass().getSimpleName();
    }

    public String describe() {
        return "ChessPiece of colour: " + colour;
    }
}
