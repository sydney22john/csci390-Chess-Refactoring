import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameMove {
    private BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
    private GamePiece piece;
    private Square toSquare;
    private boolean capture;
    private String pawnPromotionPiece;


    public boolean getMove(Board board) throws IOException {
        // We'll be doing a simpler notation for our chess game. Notation will be a 5 or 6 character length. Form
        //       will take the shape of {a-h}{1-8}(\-x}{a-h}{1-8}{rnbqRNBQ}.
        //       First character is the from file
        //       Second character is the from rank
        //       Third character is - for move, x for capture
        //       Forth character is the to file
        //       Fifth character is the to rank
        //       Sixth character is the promotion of a pawn to a piece type. This is optional
        String move = inputReader.readLine();
        Pattern movePattern = Pattern.compile("^[a-h][1-8][-x][a-h][1-8][rnbqRNBQ]{0,1}");
        Matcher moveMatcher = movePattern.matcher(move);
        if(!moveMatcher.find()) {
            //Move is invalid;
            System.out.println("Move is invalid. Please input a valid move.");
            return false;
        }
        piece = board.getPiece(calcFileIndex(move.charAt(0)), calcRankIndex(Integer.valueOf(move.substring(1,2))));

        boolean capture = (move.charAt(2) == 'x');

        toSquare.setFileIndex(calcFileIndex(move.charAt(3)));
        toSquare.setRankIndex(calcRankIndex(Integer.valueOf(move.substring(4,5))));

        pawnPromotionPiece = null;
        if(move.length() == 6) {
            pawnPromotionPiece = move.substring(5,6);
        }
        return true;
    }

    public GamePiece getPiece() {
        return piece;
    }

    public Square getToSquare() {
        return toSquare;
    }

    public String getPawnPromotionPiece() {
        return pawnPromotionPiece;
    }

    public boolean isCapture() {
        return capture;
    }

    private static int calcFileIndex(Character file) {
        // Files are associated as follows: a->7, b->6, c->5, d->4, e->3, f->2, g->1, h->0
        int charValue = file;
        if (file <= 96 || file >= 105) {
            throw new IllegalArgumentException("File Character '" + file + "' is invalid.");
        }
        // calc file index with this formula
        return Math.abs(97 - file);
    }

    private static int calcRankIndex(int rankNumber) {
        // Ranks are associated as follows: 1->7, 2->6, 3->5, 4->4, 5->3, 6->2, 7->1, 8->0
        if (rankNumber <= 0 || rankNumber >= 9) {
            throw new IllegalArgumentException("Rank Value '" + rankNumber + "' is invalid.");
        }
        // We can calculate rank with this formula
        return 8 - rankNumber;
    }
}
