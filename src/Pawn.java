public class Pawn extends GamePiece {

    public Pawn() {
        super();
        pieceType = Piece.p;
    }

    @Override
    public void move() {

    }

    @Override
    public void capture() {

    }

    @Override
    public boolean isValidMove() {
        return false;
    }
}
