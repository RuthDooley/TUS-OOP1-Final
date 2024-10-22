public final class King extends ChessPiece {

    public King(Colour colour, char unicode) {
        super(colour, unicode);
    }

    @Override
    protected boolean isValidMovePieceSpecific (int startX, int startY, int endX, int endY) {    
        final int deltaX = Math.abs(endX - startX);
        final int deltaY = Math.abs(endY - startY);
        return deltaX <= 1 && deltaY <= 1;
    }

    @Override
    public String describe() {
        return super.describe() + " and type: King";
    }
}