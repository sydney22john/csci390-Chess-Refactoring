import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Chess {

    Board board = new Board();
    boolean playerTurnIsWhite;

    public Chess() {
        playerTurnIsWhite = true;
    }

    public void play() throws IOException {
        GameMove gameMove = new GameMove();
        while(!gameIsOver()) {
            printBoardToConsole();

            // getting the user input
            if (!gameMove.getMove(board)) continue;

            if(gameMove.isCapture()) {
                capturePiece(gameMove);
            } else {
                movePiece(gameMove);
            }
            // We are not going to worry about special moves like castling and en passant
        }
        System.out.println("Game over.");
        System.out.println("Thanks for playing!");
    }

    private void movePiece(GameMove gameMove) {
        GamePiece fromPiece = gameMove.getPiece();

        // making sure the piece is valid
        if(fromPiece == null) {
            System.out.println("Select a square with a piece.");
            return;
        }
        if (correctPlayerNotMovingTheirPiece(fromPiece)) return;

        // move the piece
        if (gameMove.getPawnPromotionPiece() == null) {
            fromPiece.move(board, gameMove.getToSquare());
        }
        else {
            ((Pawn) fromPiece).pawnMove(board, gameMove.getToSquare(), gameMove.getPawnPromotionPiece(), playerTurnIsWhite);
        }

        //Change the player's turn
        playerTurnIsWhite = !playerTurnIsWhite;
    }




    private boolean correctPlayerNotMovingTheirPiece(GamePiece fromPiece) {
        //Check that the piece is owned by the correct player.
        if(playerTurnIsWhite) {
            if(!fromPiece.isWhitePiece()) {
                System.out.println("Select a square with a white piece.");
                return true;
            }
        } else {
            if(fromPiece.isWhitePiece()) {
                System.out.println("Select a square with a black piece.");
                return true;
            }
        }
        return false;
    }

    private void capturePiece(GameMove gameMove) {
        GamePiece fromPiece = gameMove.getPiece();

        if(fromPiece == null) {
            System.out.println("Select a square with a piece.");
            return;
        }
        if (correctPlayerNotMovingTheirPiece(fromPiece)) return;

        // move the piece
        if (gameMove.getPawnPromotionPiece() == null) {
            fromPiece.capture(board, gameMove.getToSquare());
        }
        else {
            ((Pawn) fromPiece).pawnCapture(board, gameMove.getToSquare(), gameMove.getPawnPromotionPiece(), playerTurnIsWhite);
        }

        //Change the player's turn
        playerTurnIsWhite = !playerTurnIsWhite;
    }


    private boolean gameIsOver() {
        return isPositionCheckmate() || isPositionStalemate();
    }

    private boolean isPositionStalemate() {
        return false;
    }

    private boolean isPositionCheckmate() {
        return false;
    }

    private void printBoardToConsole() {
        StringBuilder sb = new StringBuilder();
        int rankNum = 8;
        for (GamePiece[] rank : board.getBoard()) {
            sb.append(rankNum + " ");
            for(GamePiece piece : rank) {
                if(piece != null) {
                    sb.append(piece);
                } else {
                    sb.append(" ");
                }
            }
            sb.append("\n");
            rankNum--;
        }
        sb.append("  abcdefgh");
        System.out.println(sb);
    }
}
