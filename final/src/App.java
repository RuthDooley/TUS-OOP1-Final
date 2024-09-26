import java.util.ArrayList;
import java.util.Collections;

public class App {
    public static void main(String[] args) throws Exception {

        /**
         * TODO:
         * 1. Create the board, randomise the pieces on the board and print the board
         * 2. 
         */

        // 1. 

        // TODO, bit of tidying to be done in this
        Board board = new Board();

        ArrayList<Piece> pieces = new ArrayList<>();
        pieces.add(new Piece("white king", '\u2654'));
        pieces.add(new Piece("white queen", '\u2655'));
        pieces.add(new Piece("white bishop", '\u2657'));
        pieces.add(new Piece("white bishop", '\u2657'));
        pieces.add(new Piece("white knight", '\u2658'));
        pieces.add(new Piece("white knight", '\u2658'));
        pieces.add(new Piece("white rook", '\u2656'));
        pieces.add(new Piece("white rook", '\u2656'));
        pieces.add(new Piece("black king", '\u265A'));
        pieces.add(new Piece("black queen", '\u265B'));
        pieces.add(new Piece("black bishop", '\u265D'));
        pieces.add(new Piece("black bishop", '\u265D'));
        pieces.add(new Piece("black knight", '\u265E'));
        pieces.add(new Piece("black knight", '\u265E'));
        pieces.add(new Piece("black rook", '\u265C'));
        pieces.add(new Piece("black rook", '\u265C'));

        Collections.shuffle(pieces);
        board.initialiseBoardPieces(pieces);
        board.printBoard();


        // Start the loop for the game

        // Upon first move start the timer

        // As the user to give the position of the piece they want to move, and the position they want to move it to

        // Check if this is a valid entry 

        // Check if this is a valid move

        // Move the piece 

        // Check if game is over and if not continue
    }
}
