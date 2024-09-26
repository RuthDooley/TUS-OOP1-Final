public class Rook extends ChessPiece {

    public Rook(Colour colour, char unicode) {
        super(colour, unicode);
    }

    @Override
    public boolean isValidMovePieceSpecific (int startX, int startY, int endX, int endY) {    
        return isInBounds(endX, endY) && (startX == endX || startY == endY);
    }
}