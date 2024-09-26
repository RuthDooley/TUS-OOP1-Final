import java.util.ArrayList;
import java.util.Collections;

public class App {
    public static void main(String[] args) throws Exception {

        /**
         * TODO:
         * 1. Create the board, randomise the pieces on the board and print the board
         */

        // 1. 
        Board board = new Board();

        ArrayList<Piece> pieces = new ArrayList<>();
        pieces.add(new Piece("white king"));
        pieces.add(new Piece("white queen"));
        pieces.add(new Piece("white bishop"));
        pieces.add(new Piece("white bishop"));
        pieces.add(new Piece("white knight"));
        pieces.add(new Piece("white knight"));
        pieces.add(new Piece("white rook"));
        pieces.add(new Piece("white rook"));
        pieces.add(new Piece("black king"));
        pieces.add(new Piece("black queen"));
        pieces.add(new Piece("black bishop"));
        pieces.add(new Piece("black bishop"));
        pieces.add(new Piece("black knight"));
        pieces.add(new Piece("black knight"));
        pieces.add(new Piece("black rook"));
        pieces.add(new Piece("black rook"));

        Collections.shuffle(pieces);
        board.initialiseBoardPieces(pieces);
        board.printBoard();


        

        // board.printBoard();

        // Randomise the position, needs to be black and white of 1 king, 1 queen, 2 rooks, 2 bishops, 2 knights


        // Start the loop for the game

        // Upon first move start the timer

        // As the user to give the position of the piece they want to move, and the position they want to move it to

        // Check if this is a valid entry 

        // Check if this is a valid move

        // Move the piece 

        // Check if game is over and if not continue
    }
}
