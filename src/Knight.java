public class Knight extends GamePiece {
    public Knight(boolean whitePiece, Square fromSquare) {
        super(whitePiece, fromSquare);
        this.pieceType = Piece.N;
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
