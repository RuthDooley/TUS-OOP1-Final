public class Square {
    private int x; 
    private int y; 
    private Piece piece;

    public Square(int x, int y) {
        this.x = x;
        this.y = y;
        // System.out.println("Square created at " + x + ", " + y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public Piece getPiece() {
        return piece;
    }
}