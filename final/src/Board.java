import java.util.ArrayList;

public class Board {
    private Square[][] squares = new Square[8][8];

    public Board() {
        // Initialise the board
        initialiseBoardEmpty();

        System.out.println("Board created");
    }

    private void initialiseBoardEmpty() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                squares[i][j] = new Square(i, j);
            }
        }
    }

    public void initialiseBoardPieces(ArrayList<Piece> pieces) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                squares[i][j].setPiece(pieces.get((i * 4) + j));
            }
        }
    }

    public void printBoard() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(squares[i][j].getPiece().getName());
            }
            System.out.println();
        }
    }
}