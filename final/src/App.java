import java.util.Date;
import java.util.Scanner;

// TODO, remove all of the project spec comments
// TODO, remove all of the TODO comments

public class App {
    public static void main(String[] args) throws Exception {

        /**
         * TODO:
         * 1. Create the board, randomise the pieces on the board and print the board
         * 2. Create the game loop, and on first actionable start the timer
         * 3. Game logic, ask for piece location, piece destination. Confirm or deny action and reload board state.
         */

        // 1. 

        // Initialize the board with varargs
        Board board = new Board(
            new King(Colour.WHITE, '\u2654'),
            new Queen(Colour.WHITE, '\u2655'),
            new Bishop(Colour.WHITE, '\u2657'),
            new Bishop(Colour.WHITE, '\u2657'),
            new Knight(Colour.WHITE, '\u2658'),
            new Knight(Colour.WHITE, '\u2658'),
            new Rook(Colour.WHITE, '\u2656'),
            new Rook(Colour.WHITE, '\u2656'),
            new King(Colour.BLACK, '\u265A'),
            new Queen(Colour.BLACK, '\u265B'),
            new Bishop(Colour.BLACK, '\u265D'),
            new Bishop(Colour.BLACK, '\u265D'),
            new Knight(Colour.BLACK, '\u265E'),
            new Knight(Colour.BLACK, '\u265E'),
            new Rook(Colour.BLACK, '\u265C'),
            new Rook(Colour.BLACK, '\u265C')
        );

        // Quick shuffle of the pieces
        board.shuffle();

        // Print the game state
        board.print();

        // 2.
        Scanner scanner = new Scanner(System.in);
        Colour currentTurn = Colour.WHITE;
        Date startTime = new Date();
        while (!board.isGameOver(currentTurn)){

            //3
            // TODO, replace with string literal and correct colour
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

                // Changed the design, want to be able ot capture the white king, triggers game over
                // // e 
                // if (endPiece.getType() == "King" && endPiece.getColour() == Colour.WHITE) {
                //     throw new IllegalStateException("The selected piece " + endPiece.getUnicode() + " is the white king.");
                // }
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

        Date endTime = new Date();
        long elapsedTime = endTime.getTime() - startTime.getTime();
        long elapsedSeconds = elapsedTime / 1000;
        long elapsedMinutes = elapsedSeconds / 60;
        elapsedSeconds = elapsedSeconds % 60;

        // Print the elapsed time
        System.out.println("Time taken for the puzzle: " + elapsedMinutes + " minutes and " + elapsedSeconds + " seconds.");

        scanner.close();
    }
}


/**
 *         // Create all of the pieces
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

        Board board = new Board();

        For the logging create a baord object with no paramaters
        Store the order of the pieces in an array list or list
        place the pieces in the board object
        
 */