public class Board {

    // Instance variables
    private Piece[][] board;

    public Board() {
        board = new Piece[8][8]; //initializes an empty 8x8 chess board


    }

    public Piece getPiece(int row, int col) {
        return board[row][col]; // gets the piece on the board and returns it.
    }

    public void setPiece(int row, int col, Piece piece) {
        board[row][col] = piece; //assigns piece to the specified location.

    }

    // Game functionality methods


    public boolean movePiece(int startRow, int startCol, int endRow, int endCol) {

        Piece myPiece = getPiece(startRow, startCol);

        if (myPiece.isMoveLegal(this, endRow, endCol)) {
            board[endRow][endCol] = myPiece;
            board[startRow][startCol] = null;
            myPiece.setPosition(endRow, endCol);

            return true; // Return true to show a legal move
        } else {
            return false; // Return false if the move is not legal
        }
    }

    //TODO:
    // Determines whether the game has been ended, i.e., if one player's King
    // has been captured.
    public boolean isGameOver() {
        boolean whiteKing = false;
        boolean blackKing = false;

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = board[row][col];

                if (piece != null && piece.toString().equals("\u2654")) {
                    whiteKing = true;
                }

                if (piece != null && piece.toString().equals("\u265a")) {
                    blackKing = true;

                    if ((whiteKing) && (blackKing)) ;
                    {
                        return false;
                    }
                }
            }
        }
        return true;

    }


    // Constructs a String that represents the Board object's 2D array.
    // Returns the fully constructed String.
    public String toString() {
        StringBuilder out = new StringBuilder();
        out.append(" ");
        for (int i = 0; i < 8; i++) {
            out.append(" ");
            out.append(i);
        }
        out.append('\n');
        for (int i = 0; i < board.length; i++) {
            out.append(i);
            out.append("|");
            for (int j = 0; j < board[0].length; j++) {
                out.append(board[i][j] == null ? "\u2001|" : board[i][j] + "|");
            }
            out.append("\n");
        }
        return out.toString();
    }

    public void clear() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                board[row][col] = null;

            }
        }
    }


    public boolean verifySourceAndDestination(int startRow, int startCol, int endRow, int endCol, boolean isBlack) {
        if (startRow < 8 && startRow >= 0 && startCol < 8 && startCol >= 0 &&
                endRow < 8 && endRow >= 0 && endCol < 8 && endCol >= 0) {
            if (board[startRow][startCol] != null) {
                if (board[startRow][startCol].getIsBlack() == isBlack) {
                    if (board[endRow][endCol] == null || board[endRow][endCol].getIsBlack() != isBlack) {
                        return true;
                    }
                }
            }

        }
        return false;
    }


    public boolean verifyAdjacent(int startRow, int startCol, int endRow, int endCol) {
        int rowDiff = Math.abs(endRow - startRow);
        int colDiff = Math.abs(endCol - startCol);
        return rowDiff <= 1 && colDiff <= 1;
    }

    public boolean verifyHorizontal(int startRow, int startCol, int endRow, int endCol) {
        if (startRow != endRow) {
            return false;
        }
        int colLeft = 0;
        int colRight = 0;
        if (startCol < endCol) {
            colRight = 1;
        } else {
            colLeft = -1;
        }
        if (startCol < endCol) {
            for (int col = startCol + colRight; col < endCol; col += colRight) {
                if (board[startRow][col] != null) {
                    return false;
                }
            }
        }
        if (startCol > endCol) {
            for (int col = startCol + colLeft; col > endCol; col += colLeft) {
                if (board[startRow][col] != null) {
                    return false;
                }
            }
        }
        return true;
    }


    public boolean verifyVertical(int startRow, int startCol, int endRow, int endCol) {
        if (startCol != endCol) {
            return false;
        }
        int rowUp = 0;;
        int rowDown = 0;
        if (startRow < endRow) {
            rowUp = 1;
        } else {
            rowDown = -1;
        }
        if (startRow < endRow) {
            for (int row = startRow + rowUp; row < endRow; row += rowUp) {
                if (board[row][startCol] != null) {
                    return false;
                }
            }
        }
        if (startRow > endRow) {
            for (int row = startRow + rowDown; row > endRow; row += rowDown) {
                if (board[row][startCol] != null) {
                    return false;
                }
            }
        }
        return true;
    }


    public boolean verifyDiagonal(int startRow, int startCol, int endRow, int endCol) {
        if (startRow == endRow && startCol == endCol) {
            return true;
        }
        int rowDifference = endRow - startRow;
        int colDifference = endCol - startCol;
        if (Math.abs(rowDifference) != Math.abs(colDifference)) {
            return false;
        }
        int rowIncrement = 0;
        int colIncrement = 0;
        if (rowDifference > 0) {
            rowIncrement = 1;
        } else {
            rowIncrement = -1;
        }
        if (colDifference > 0) {
            colIncrement = 1;
        } else {
            colIncrement = -1;
        }
        int currRow = startRow + rowIncrement;
        int currCol = startCol + colIncrement;

        while (currRow != endRow || currCol != endCol) {
            if (board[currRow][currCol] != null) {
                return false;
            }
            currRow += rowIncrement;
            currCol += colIncrement;
        }
        return true;
    }
}
