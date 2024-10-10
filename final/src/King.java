public class King extends ChessPiece {

    public King(Colour colour, char unicode) {
        super(colour, unicode);
    }

    @Override
    public boolean isValidMovePieceSpecific (int startX, int startY, int endX, int endY) {    
        int deltaX = Math.abs(endX - startX);
        int deltaY = Math.abs(endY - startY);
        return deltaX <= 1 && deltaY <= 1;
    }
}