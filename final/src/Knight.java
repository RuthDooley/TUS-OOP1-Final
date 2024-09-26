public class Knight extends ChessPiece {

    public Knight(Colour colour, char unicode) {
        super(colour, unicode);
    }

    @Override
    public boolean isValidMovePieceSpecific (int startX, int startY, int endX, int endY) {    
        int deltaX = Math.abs(endX - startX);
        int deltaY = Math.abs(endY - startY);
        return isInBounds(endX, endY) && ((deltaX == 2 && deltaY == 1) || (deltaX == 1 && deltaY == 2));
    }
}