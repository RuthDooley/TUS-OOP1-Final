import java.util.ArrayList;
import java.util.Collections;

public class App {
    public static void main(String[] args) throws Exception {

        /**
         * TODO:
         * 1. Create the board, randomise the pieces on the board and print the board
         * 2. Create the game loop, and on first actionable start the timer
         * 3. Game logic, ask for piece location, piece destination. Confirm or deny action and reload board state.
         */

        // 1. 

        // Create the board
        Board board = new Board();

        // Create all of the pieces
        ArrayList<ChessPiece> pieces = new ArrayList<>();
        pieces.add(new King(Colour.WHITE, '\u2654'));
        pieces.add(new Queen(Colour.WHITE, '\u2655'));
        pieces.add(new Bishop(Colour.WHITE, '\u2657'));
        pieces.add(new Bishop(Colour.WHITE, '\u2657'));
        pieces.add(new Knight(Colour.WHITE, '\u2658'));
        pieces.add(new Knight(Colour.WHITE, '\u2658'));
        pieces.add(new Rook(Colour.WHITE, '\u2656'));
        pieces.add(new Rook(Colour.WHITE, '\u2656'));
        pieces.add(new King(Colour.BLACK, '\u265A'));
        pieces.add(new Queen(Colour.BLACK, '\u265B'));
        pieces.add(new Bishop(Colour.BLACK, '\u265D'));
        pieces.add(new Bishop(Colour.BLACK, '\u265D'));
        pieces.add(new Knight(Colour.BLACK, '\u265E'));
        pieces.add(new Knight(Colour.BLACK, '\u265E'));
        pieces.add(new Rook(Colour.BLACK, '\u265C'));
        pieces.add(new Rook(Colour.BLACK, '\u265C'));

        // Randomise the ordering of the pieces
        Collections.shuffle(pieces);

        // Place the pieces one after the other in the squares
        board.initialiseBoardPieces(pieces);

        // Print the game state
        board.printBoard();

        // 2.
    }
}
