import java.util.Scanner;
import java.awt.Color;

public class Piece {
    // Create Instance Variables
    private char character;
    private int row;
    private int col;
    private boolean isBlack;


    /**
     * Constructor.
     *
     * @param character The character representing the piece.
     * @param row       The row on the board the piece occupies.
     * @param col       The column on the board the piece occupies.
     * @param isBlack   The color of the piece.
     */
    public Piece(char character, int row, int col, boolean isBlack) {
        this.character = character;
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }

    /**
     * Determines if moving this piece is legal.
     *
     * @param board  The current state of the board.
     * @param endRow The destination row of the move.
     * @param endCol The destination column of the move.
     * @return If the piece can legally move to the provided destination on the board.
     */
    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        switch (this.character) {
            case '\u2659':
            case '\u265f':
                Pawn pawn = new Pawn(row, col, isBlack);
                return pawn.isMoveLegal(board, endRow, endCol);
            case '\u2656':
            case '\u265c':
                Rook rook = new Rook(row, col, isBlack);
                return rook.isMoveLegal(board, endRow, endCol);
            case '\u265e':
            case '\u2658':
                Knight knight = new Knight(row, col, isBlack);
                return knight.isMoveLegal(board, endRow, endCol);
            case '\u265d':
            case '\u2657':
                Bishop bishop = new Bishop(row, col, isBlack);
                return bishop.isMoveLegal(board, endRow, endCol);
            case '\u265b':
            case '\u2655':
                Queen queen = new Queen(row, col, isBlack);
                return queen.isMoveLegal(board, endRow, endCol);
            case '\u265a':
            case '\u2654':
                King king = new King(row, col, isBlack);
                return king.isMoveLegal(board, endRow, endCol);
            default:
                return false;
        }
    }

    /**
     * Sets the position of the piece.
     *
     * @param row The row to move the piece to.
     * @param col The column to move the piece to.
     */
    public void setPosition(int row, int col) {
        this.row = row;
        this.col = col;

    }

    /**
     * Return the color of the piece.
     *
     * @return The color of the piece.
     */
    public boolean getIsBlack() {
        return this.isBlack;

    }

    /**
     * Handle promotion of a pawn.
     *
     * @param row     Current row of the pawn
     * @param isBlack Color of the pawn
     */
    public void promotePawn(int row, boolean isBlack) {
        if (!isBlack && row == 0 && character == '\u2659') {
            // black pawn has reached the eighth rank, so it can be promoted.
            System.out.println("Choose a piece to promote the white pawn (Q for Queen, R for Rook, K for Knight, B for Bishop):");
            Scanner scanner = new Scanner(System.in);
            char promote = scanner.nextLine().charAt(0);
            switch (promote) {
                case 'Q':
                    this.character = '\u2655';
                    break;
                case 'R':
                    this.character = '\u2656'; // black rook
                    break;
                case 'K':
                    this.character = '\u2658'; // black knight
                    break;
                case 'B':
                    this.character = '\u2657'; // black bishop
                    break;
                default:
                    // Default to queen if the promotion choice is not recognized.
                    this.character = '\u2655'; // black queen
                    break;
            }
           // scanner.close();
        } else if (isBlack && row == 7 && character == '\u265f') {
            // Black pawn has reached the first rank, so it can be promoted.
            System.out.println("Choose a piece to promote the black pawn (q for Queen, r for Rook, k for Knight, b for Bishop):");
            Scanner scanner = new Scanner(System.in);
            char promotionChoice = scanner.nextLine().charAt(0);

            switch (promotionChoice) {
                case 'q':
                    this.character = '\u265B';
                    break;
                case 'r':
                    this.character = '\u265C'; // white rook
                    break;
                case 'k':
                    this.character = '\u265E'; // white knight
                    break;
                case 'b':
                    this.character = '\u265D'; // white bishop
                    break;
                default:
                    // Default to queen if the promotion choice is not recognized.
                    this.character = '\u265B'; // white queen
                    break;
            }

          //  scanner.close();
        }
    }


    /**
     * Returns a string representation of the piece.
     *
     * @return A string representation of the piece.
     */
    public String toString() {
        return "" + character;
    }


}
