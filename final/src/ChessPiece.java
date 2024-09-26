public abstract class ChessPiece implements Movable {
    protected Colour colour; 
    protected char unicode;

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

    @Override
    public abstract boolean isValidMove(int startX, int startY, int endX, int endY);

    protected boolean isInBounds(int x, int y) {
        // TODO, maybe can fit an exception in here
        return x >= 0 && x < 4 && y >= 0 && y < 4;
    }
}
