public class GamePiece implements Moves {
    protected Piece pieceType;
    protected Square fromSquare;
    protected boolean whitePiece;

    public GamePiece(boolean whitePiece, Square fromSquare) {
        this.fromSquare = fromSquare;
        this.whitePiece = whitePiece;
    }

    public Piece getPieceType() {
        return pieceType;
    }

    public void setPieceType(Piece pieceType) {
        this.pieceType = pieceType;
    }

    public boolean isWhitePiece() {
        return whitePiece;
    }

    public void setWhitePiece(boolean whitePiece) {
        this.whitePiece = whitePiece;
    }

    @Override
    public void move(Board board, Square toSquare) {

    }

    @Override
    public void capture(Board board, Square toSquare) {

    }

    @Override
    public boolean isValidMove(Board board, Square toSquare) {
        return false;
    }
}
