import java.util.Random;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Board {
    private final Square[][] squares = new Square[4][4];

    public Board() {
        initialiseBoardEmpty();
    }

    public Board(ChessPiece... pieces) {
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

    // PROJECT SPEC: VARARGS
    // Init the square objects of the board with piece objects using varargs, null if no piece
    public void initialiseBoardPieces(ChessPiece... pieces) {
        int pieceIndex = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (pieceIndex < pieces.length) {
                    squares[i][j].setPiece(pieces[pieceIndex]);
                } else {
                    squares[i][j].setPiece(null);
                }
                pieceIndex++;
            }
        }
    }

    // Using the pice objects of the square objects, print the baord state
    public void print() {
        System.out.println("    (a)(b)(c)(d)");
        for (int i = 0; i < 4; i++) {
            // TODO, replace with string literal
            System.out.print("(" + (i + 1) + ") ");
            
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

    // Board string for file writer
    public String string() {
        final StringBuilder boardString = new StringBuilder();
        boardString.append("    (a)(b)(c)(d)\n");

        IntStream.range(0, 4).forEach(i -> {
            boardString.append("(").append(i + 1).append(") ");
            String row = IntStream.range(0, 4)
                                  .mapToObj(j -> squares[i][j].getPiece() == null ? "[ ]" : "[" + squares[i][j].getPiece().getUnicode() + "]")
                                  .collect(Collectors.joining());
            boardString.append(row).append("\n");
        });

        return boardString.toString();
    }

    // Fisher-Yates shuffle for the pieces on the board, complexity O(n)
    public void shuffle() {
        final Random rand = new Random();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int randomI = rand.nextInt(4);
                int randomJ = rand.nextInt(4);

                // Swap pieces
                ChessPiece temp = squares[i][j].getPiece();
                squares[i][j].setPiece(squares[randomI][randomJ].getPiece());
                squares[randomI][randomJ].setPiece(temp);
            }
        }
    }

    /*
    * Game lost when:
    * - No white king is on the board
    * - No valid move for the current turn colour
    * 
    * Game won when:
    * - White king is the last remaining piece
    * 
    * Return true when game is over, return false when game is not over
    */
    public boolean isGameOver(Colour currentTurn) {
        boolean hasWhiteKing = false;
        boolean hasMovablePiece = false;
        boolean hasWhiteKingOnly = true;

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
                    // If white king is on the board, continue
                    if (!hasWhiteKing && piece instanceof King && piece.getColour() == Colour.WHITE) {
                        System.out.println("* White king on the board: " + piece.getUnicode());
                        hasWhiteKing = true;
                    }
                    
                    // Check if the piece is not the white queen
                    if (hasWhiteKingOnly && !(piece instanceof King && piece.getColour() == Colour.WHITE)) {
                        hasWhiteKingOnly = false;
                    }
                }
            }
        }
        System.out.println("----- Checking game over conditions end -----");

        // Game lost when no white queen is on the board
        if (!hasWhiteKing) {
            System.out.println("Game lost, no white queen on the board.");
            return true;
        }

        // Game lost when no valid move for the current turn colour
        if (!hasMovablePiece) {
            System.out.println("Game lost, no valid move for the current turn colour.");
            return true;
        }

        // Game won when white queen is the last remaining piece
        if (hasWhiteKingOnly) {
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

    // PROJECT SPEC: OVERLOADING
    public Square getSquare (int x, int y){
        return squares[y][x];
    }

    public ArrayList<ChessPiece> getPieces() {
        final ArrayList<ChessPiece> pieces = new ArrayList<>();
        for (int row = 0; row < squares.length; row++) {
            for (int col = 0; col < squares[row].length; col++) {
                ChessPiece piece = squares[row][col].getPiece();
                if (piece != null) {
                    pieces.add(piece);
                }
            }
        }
        return pieces;
    }
}
