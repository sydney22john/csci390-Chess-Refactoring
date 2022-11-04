public class Bishop extends GamePiece {
    public Bishop(boolean whitePiece, Square fromSquare) {
        super(whitePiece, fromSquare);
        this.pieceType = Piece.B;
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
