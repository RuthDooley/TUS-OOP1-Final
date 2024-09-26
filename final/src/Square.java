public class Square {
    private int x; 
    private int y; 
    private ChessPiece piece;

    public Square(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setPiece(ChessPiece piece) {
        this.piece = piece;
    }

    public ChessPiece getPiece() {
        return piece;
    }
}