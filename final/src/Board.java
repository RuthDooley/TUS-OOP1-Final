public class Board {
    private Square[][] squares = new Square[8][8];

    public Board() {
        initializeBoard();
        System.out.println("Board created");
    }

    private void initializeBoard() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                squares[i][j] = new Square(i, j);
            }
        }
    }

    public void printBoard() {
        // TODO, replace with the actual piece on the square
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(squares[i][j].getX() + ", " + squares[i][j].getY() + " ");
            }
            System.out.println();
        }
    }
}