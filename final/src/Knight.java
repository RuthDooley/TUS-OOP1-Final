public final class Knight extends ChessPiece {

    public Knight(Colour colour, char unicode) {
        super(colour, unicode);
    }

    @Override
    protected boolean isValidMovePieceSpecific (int startX, int startY, int endX, int endY) {    
        final int deltaX = Math.abs(endX - startX);
        final int deltaY = Math.abs(endY - startY);
        return (deltaX == 2 && deltaY == 1) || (deltaX == 1 && deltaY == 2);
    }

    @Override
    public String describe() {
        return super.describe() + " and type: Knight";
    }
}