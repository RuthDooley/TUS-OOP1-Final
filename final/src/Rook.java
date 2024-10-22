public final class Rook extends ChessPiece {

    public Rook(Colour colour, char unicode) {
        super(colour, unicode);
    }

    @Override
    protected boolean isValidMovePieceSpecific (int startX, int startY, int endX, int endY) {    
        return startX == endX || startY == endY;
    }

    @Override
    public String describe() {
        return super.describe() + " and type: Rook";
    }
}