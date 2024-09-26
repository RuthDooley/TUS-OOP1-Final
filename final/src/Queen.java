public class Queen extends ChessPiece {

    public Queen(Colour colour, char unicode) {
        super(colour, unicode);
    }

    @Override
    public boolean isValidMovePieceSpecific (int startX, int startY, int endX, int endY) {    
        int deltaX = Math.abs(endX - startX);
        int deltaY = Math.abs(endY - startY);
        return isInBounds(endX, endY) && (deltaX == deltaY || startX == endX || startY == endY);
    }
}