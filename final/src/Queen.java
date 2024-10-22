public final class Queen extends ChessPiece {

    public Queen(Colour colour, char unicode) {
        super(colour, unicode);
    }

    @Override
    protected boolean isValidMovePieceSpecific (int startX, int startY, int endX, int endY) {    
        final int deltaX = Math.abs(endX - startX);
        final int deltaY = Math.abs(endY - startY);
        return deltaX == deltaY || startX == endX || startY == endY;
    }

    @Override
    public String describe() {
        return super.describe() + " and type: Queen";
    }
}