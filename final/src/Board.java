import java.util.ArrayList;

public class Board {
    private Square[][] squares = new Square[8][8];

    public Board() {
        initialiseBoardEmpty();
    }

    private void initialiseBoardEmpty() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                squares[i][j] = new Square(i, j);
            }
        }
    }

    public void initialiseBoardPieces(ArrayList<ChessPiece> pieces) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                squares[i][j].setPiece(pieces.get((i * 4) + j));
            }
        }
    }

    public void printBoard() {
        System.out.println("    (a)(b)(c)(d)");
        for (int i = 0; i < 4; i++) {
            // TODO, replace with string literal
            System.out.print("(" + (i + 1) + ") ");

            // TOOD, handelling when there is no piece on the square
            for (int j = 0; j < 4; j++) {
                System.out.print("[" + squares[i][j].getPiece().getUnicode() + "]");
            }
            System.out.println();
        }
    }
}