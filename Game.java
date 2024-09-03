import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Board board = new Board();


        Fen.load("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR", board);
        int count = 0;

        while (!board.isGameOver()) {
            System.out.println(board);

            System.out.println("Please pick a start row, start column, end row, end column:");
            String[] line = scanner.nextLine().split(" ");
            int startRow = Integer.parseInt(line[0]);
            int startCol =  Integer.parseInt(line[1]);
            int endRow =  Integer.parseInt(line[2]);
            int endCol =  Integer.parseInt(line[3]);

            if (count % 2 == 0) {
                if (!board.getPiece(startRow,startCol).getIsBlack() && board.movePiece(startRow, startCol, endRow, endCol)) {
                    System.out.println("Move successful.");
                    boolean colorPiece = board.getPiece(endRow, endCol).getIsBlack();
                    board.getPiece(endRow, endCol).promotePawn(endRow, colorPiece);
                    count += 1;
                } else {
                    System.out.println("Invalid move. Try again.");
                }
            } else {
                System.out.println("It's Black's turn.");
                if (board.getPiece(startRow,startCol).getIsBlack() && board.movePiece(startRow, startCol, endRow, endCol)) {
                    System.out.println("Move successful.");
                    boolean colorPiece = board.getPiece(endRow, endCol).getIsBlack();
                    board.getPiece(endRow, endCol).promotePawn(endRow, colorPiece);
                    count += 1;
                } else {
                    System.out.println("Invalid move. Try again.");
                }

            }
        }
        if (count % 2 == 0){
            System.out.println("the winner is black.");
        } else {
            System.out.println("the winner is white.");

        }

    }
}






