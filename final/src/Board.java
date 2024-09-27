import java.util.ArrayList;

public class Board {
    private Square[][] squares = new Square[4][4];

    public Board() {
        initialiseBoardEmpty();
    }

    public Board(ArrayList<ChessPiece> pieces) {
        this();
        this.initialiseBoardPieces(pieces);
    }

    // Init board with square objects
    private void initialiseBoardEmpty() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                squares[i][j] = new Square(i, j);
            }
        }
    }

    // Init the square objects of the board with piece objects
    public void initialiseBoardPieces(ArrayList<ChessPiece> pieces) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                squares[i][j].setPiece(pieces.get((i * 4) + j));
            }
        }
    }

    // Using the pice objects of the square objects, print the baord state
    public void print() {
        System.out.println("    (a)(b)(c)(d)");
        for (int i = 0; i < 4; i++) {
            // TODO, replace with string literal
            System.out.print("(" + (i + 1) + ") ");

            // TOOD, handelling when there is no piece on the square
            for (int j = 0; j < 4; j++) {
                if (squares[i][j].getPiece() == null) {
                    System.out.print("[ ]");
                } else {
                    System.out.print("[" + squares[i][j].getPiece().getUnicode() + "]");
                }
            }
            System.out.println();
        }
    }

    // Game over when only the white queen exists
    public boolean isGameOver (){
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                // TODO, give condition if the piece is null
                if (squares[i][j].getPiece().getType() != "Queen" && squares[i][j].getPiece().getColour() != Colour.WHITE) {
                    return false;
                }
            }
        }
        return true;
    }

    public void movePiece (Square startSquare, Square endSquare){
        endSquare.setPiece(startSquare.getPiece());
        startSquare.setPiece(null);
    }

    public Square getSquare (char boardChar, int boardInt){
        return squares[boardInt - 1][boardChar - 'a'];
    }
}
