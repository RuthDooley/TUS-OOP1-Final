import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner;

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
        board.print();

        // 2.
        Scanner scanner = new Scanner(System.in);
        Colour currentTurn = Colour.WHITE;
        Date now = new Date();
        while (!board.isGameOver()){

            //3
            // TODO, replace with string literal
            System.out.println("Select the square of the white piece you wish to move: ");
            String startPosition = scanner.nextLine();

            /**
             * Following exceptions:
             * a. Check that the input is in the form one letter and one number
             * b. The letter needs to one of a - d inclusive and the number needs to be one of 1 - 4 inclusive
             * c. The must be a piece on the square that corresponds with that input
             * d. The piece that is seleceted must be the same colour as the turn
             */
            char startChar;
            int startInt;
            Square startSquare;
            ChessPiece startPiece;
            try {
                // a & b
                if (!startPosition.matches("[a-d][1-4]")) {
                    throw new IllegalArgumentException("Input " + startPosition + " is not the form: 1 letter and 1 number.");
                }

                startChar = startPosition.charAt(0);
                startInt = Character.getNumericValue(startPosition.charAt(1));
                // c
                startSquare = board.getSquare(startChar, startInt);
                if (startSquare.isEmpty()) {
                    throw new IllegalStateException("There is no piece on square " + startPosition + ".");
                }

                // d
                startPiece = startSquare.getPiece();
                if (startPiece.getColour() != currentTurn) {
                    throw new IllegalStateException("The selected piece " + startPiece.getUnicode() + " is not the same colour as the current turn.");
                }
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println(e.getMessage());
                continue;
            }

            // TODO, replace with string literal
            System.out.println("Choose the destination square to move " + startPiece.getUnicode());
            String endPosition = scanner.nextLine();

            /**
             * Following exceptions:
             * a. Check that the input is in the form one letter and one number
             * b. The letter needs to one of a - d inclusive and the number needs to be one of 1 - 4 inclusive
             * c. The must be a piece on the square that corresponds with that input
             * d. The piece that is seleceted must be the opposite colour of the turn
             * e. The piece must not be the white king, as this must remain on the board to win
             */
            char endChar;
            int endInt;
            Square endSquare;
            ChessPiece endPiece;
            try {
                // a & b
                if (!endPosition.matches("[a-d][1-4]")) {
                    throw new IllegalArgumentException("Input " + endPosition + " is not the form: 1 letter and 1 number.");
                }

                endChar = endPosition.charAt(0);
                endInt = Character.getNumericValue(endPosition.charAt(1));
                // c
                endSquare = board.getSquare(endChar, endInt);
                if (endSquare.isEmpty()) {
                    throw new IllegalStateException("There is no piece on square " + endSquare + ".");
                }

                // d
                endPiece = endSquare.getPiece();
                if (endPiece.getColour() == currentTurn) {
                    throw new IllegalStateException("The selected piece " + endPiece.getUnicode() + " is the same colour as the current turn.");
                }

                // e 
                if (endPiece.getType() == "King" && endPiece.getColour() == Colour.WHITE) {
                    throw new IllegalStateException("The selected piece " + endPiece.getUnicode() + " is the white king.");
                }
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println(e.getMessage());
                continue;
            }

            // Check if the move is a valid move
            if (startPiece.isValidMove(startChar, startInt, endChar, endInt)){
                System.out.println("Valid move");
                board.movePiece(startSquare, endSquare);
            } else {
                System.out.println("Invalid move");
                continue;
            }

            board.print();
            currentTurn = (currentTurn == Colour.WHITE) ? Colour.BLACK : Colour.WHITE;
        }
        scanner.close();
    }
}
