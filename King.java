public class King {
    private int row;
    private int col;
    private boolean isBlack;
    /**
     * Constructor.
     * @param row   The current row of the pawn.
     * @param col   The current column of the pawn.
     * @param isBlack   The color of the pawn.
     */
    public King(int row, int col, boolean isBlack) {
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }

    /**
     * Checks if a move to a destination square is legal.
     * @param board     The game board.
     * @param endRow    The row of the destination square.
     * @param endCol    The column of the destination square.
     * @return True if the move to the destination square is legal, false otherwise.
     */
    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        if (!board.verifySourceAndDestination(this.row, this.col, endRow, endCol, isBlack)) {
            return false;
        }

        int rowDifference = Math.abs(endRow - this.row);
        int columnDifference = Math.abs(endCol - this.col);

        // A king can move one square in any direction (horizontally, vertically, or diagonally).
        return (rowDifference <= 1) && (columnDifference <= 1);
    }




}
