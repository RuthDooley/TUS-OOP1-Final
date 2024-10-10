import java.util.Date;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.util.function.Predicate;

// TODO, remove all of the project spec comments
// TODO, remove all of the TODO comments

public class App {
    public static void main(String[] args) throws Exception {

        /**
         * 1. Create the board, randomise the pieces on the board and print the board
         * 2. Create the game loop, and on first actionable start the timer
         * 3. Game logic, ask for piece location, piece destination. Confirm or deny action and reload board state.
         */

        // 1. 

        // Initialize the board with varargs
        final Board board = new Board(
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

        // Report setup, array list of the pieces
        final ArrayList<ChessPiece> pieceLog = board.getPieces();
        final ArrayList<String> movesLog = new ArrayList<>();

        final Predicate<String> isBoardPosition = "[a-d][1-4]"::matches;

        // 2.
        var scanner = new Scanner(System.in); // Scanner scanner = new Scanner(System.in);
        var startTime = new Date(); // Date startTime = new Date();
        Colour currentTurn = Colour.WHITE;
        while (!board.isGameOver(currentTurn)){

            // Print the game state
            board.print();

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
                if (!isBoardPosition.test(startPosition)) {
                    throw new IllegalArgumentException("Input " + startPosition + " is not the form: 1 letter (a-d) and 1 number (1-4).");
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
                if (!isBoardPosition.test(endPosition)) {
                    throw new IllegalArgumentException("Input " + endPosition + " is not the form: 1 letter (a-d) and 1 number (1-4).");
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

                // Changed the design, want to be able to capture the white king, triggers game over
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

            movesLog.add(startPosition + " " + endPosition);
            currentTurn = (currentTurn == Colour.WHITE) ? Colour.BLACK : Colour.WHITE;
        }

        var endTime = new Date(); // Date endTime = new Date();
        long elapsedTime = endTime.getTime() - startTime.getTime();
        long elapsedSeconds = elapsedTime / 1000;
        long elapsedMinutes = elapsedSeconds / 60;
        elapsedSeconds = elapsedSeconds % 60;

        // Print the elapsed time
        System.out.println("Time taken for the puzzle: " + elapsedMinutes + " minutes and " + elapsedSeconds + " seconds.");

        scanner.close();

        logGame(pieceLog, movesLog);
    }

    private static void logGame(ArrayList<ChessPiece> pieces, ArrayList<String> moves) {
        // Create a new board with the initial positions
        final Board boardLog = new Board();
    
        // Set the pieces on the board
        int index = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (index < pieces.size()) {
                    boardLog.getSquare(j, i).setPiece(pieces.get(index));
                    index++;
                }
            }
        }
    
        try (var writer = new BufferedWriter(new FileWriter("game_replay.txt"))) {
            writer.write(boardLog.string() + "\n");
    
            for (String move : moves) {
                String[] positions = move.split(" ");
                String startPosition = positions[0];
                String endPosition = positions[1];
    
                int startFile = startPosition.charAt(0) - 'a';
                int startRank = Character.getNumericValue(startPosition.charAt(1)) - 1;
                int endFile = endPosition.charAt(0) - 'a';
                int endRank = Character.getNumericValue(endPosition.charAt(1)) - 1;
    
                Square startSquare = boardLog.getSquare(startFile, startRank);
                Square endSquare = boardLog.getSquare(endFile, endRank);
    
                boardLog.movePiece(startSquare, endSquare);
    
                writer.write(boardLog.string() + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}