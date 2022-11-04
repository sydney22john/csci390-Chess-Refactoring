public class Knight extends GamePiece {
    public Knight(boolean whitePiece, Square fromSquare) {
        super(whitePiece, fromSquare);
        this.pieceType = Piece.N;
    }

    @Override
    public void move(Board board, Square toSquare) {
        if (isValidMove(board, toSquare)) {
            // swapping the piece location and updating current location
            board.setPiece(null, this.fromSquare);
            board.setPiece(this, toSquare);
            this.fromSquare = toSquare;
        }
        else {
            System.out.println("Invalid move for Knight.");
        }
    }

    @Override
    public void capture(Board board, Square toSquare) {

        if (isValidMove(board, toSquare)) {
            GamePiece capturedPiece = board.getPiece(toSquare);
            if(capturedPiece.isWhitePiece() != this.isWhitePiece()) {
                // swapping the piece location and updating current location
                board.setPiece(null, this.fromSquare);
                board.setPiece(this, toSquare);
                this.fromSquare = toSquare;
            }
        }
        else {
            System.out.println("Invalid move for Knight.");
        }
    }

    @Override
    public boolean isValidMove(Board board, Square toSquare) {
        if (!((Math.abs(this.fromSquare.getFileIndex() - toSquare.getFileIndex()) == 2 && Math.abs(this.fromSquare.getRankIndex() - toSquare.getRankIndex()) == 1) || (Math.abs(this.fromSquare.getFileIndex() - toSquare.getFileIndex()) == 1 && Math.abs(this.fromSquare.getRankIndex() - toSquare.getRankIndex()) == 2))) {
            return true;
        }
        return false;
    }
}
