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
    public boolean isValidMove(char startChar, int startInt, char endChar, int endInt) {
        int startX = startChar - 'a';
        int startY = startInt - 1;
        int endX = endChar - 'a';
        int endY = endInt - 1;

        if (!isInBounds(endX, endY)) {
            return false;
        }

        return isValidMovePieceSpecific(startX, startY, endX, endY);
    }

    protected abstract boolean isValidMovePieceSpecific(int startX, int startY, int endX, int endY);

    protected boolean isInBounds(int x, int y) {
        return x >= 0 && x < 4 && y >= 0 && y < 4;
    }

    // Get the class name ie. specific type of piece
    public String getType() {
        return this.getClass().getSimpleName();
    }
}
