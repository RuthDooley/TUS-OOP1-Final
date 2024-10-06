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

/*
 * Game lost when:
 * - No white queen is on the board
 * - No valid move for the current turn colour
 * 
 * Game won when:
 * - White queen is the last remaining piece
 * 
 * Return true when game is over, return false when game is not over
 */
public boolean isGameOver(Colour currentTurn) {
    boolean hasWhiteQueen = false;
    boolean hasMovablePiece = false;
    boolean hasWhiteQueenOnly = true;

    System.out.println("----- Checking game over conditions start -----");

    for (int i = 0; i < 4; i++) {
        for (int j = 0; j < 4; j++) {
            ChessPiece piece = squares[i][j].getPiece();
            if (piece != null) {
                if (!hasMovablePiece && piece.getColour() == currentTurn) {
                    System.out.println("\tChecking piece: " + piece.getUnicode());

                    /*
                     * Traverse the board.
                     * If there is a square with a piece on it that:
                     * - Is opposite colour to the main piece
                     * - Valid move then valid move exists
                     */
                    for (int x = 0; x < 4; x++) {
                        for (int y = 0; y < 4; y++) {
                            ChessPiece targetPiece = squares[x][y].getPiece();
                            if (!hasMovablePiece && (targetPiece != null && targetPiece.getColour() != currentTurn && piece.isValidMove(i, j, x, y))) {
                                System.out.println("* Valid move: " + piece.getUnicode() + " from " + (char) ('a' + j) + (i + 1) + " to " + (char) ('a' + y) + (x + 1));
                                hasMovablePiece = true;
                            }
                        }
                    }
                }
                // If white queen is on the board, continue
                if (!hasWhiteQueen && piece instanceof Queen && piece.getColour() == Colour.WHITE) {
                    System.out.println("* White queen on the board: " + piece.getUnicode());
                    hasWhiteQueen = true;
                }
                
                // Check if the piece is not the white queen
                if (hasWhiteQueenOnly && !(piece instanceof Queen && piece.getColour() == Colour.WHITE)) {
                    hasWhiteQueenOnly = false;
                }
            }
        }
    }
    System.out.println("----- Checking game over conditions end -----");

    // Game lost when no white queen is on the board
    if (!hasWhiteQueen) {
        System.out.println("Game lost, no white queen on the board.");
        return true;
    }

    // Game lost when no valid move for the current turn colour
    if (!hasMovablePiece) {
        System.out.println("Game lost, no valid move for the current turn colour.");
        return true;
    }

    // Game won when white queen is the last remaining piece
    if (hasWhiteQueenOnly) {
        System.out.println("Game won, white queen is the last remaining piece.");
        return true;
    }

    return false;
}

    public void movePiece (Square startSquare, Square endSquare){
        endSquare.setPiece(startSquare.getPiece());
        startSquare.setPiece(null);
    }

    public Square getSquare (char boardChar, int boardInt){
        return squares[boardInt - 1][boardChar - 'a'];
    }
}
