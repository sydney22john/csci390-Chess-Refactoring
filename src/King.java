public class King extends GamePiece {
    public King(boolean whitePiece, Square fromSquare) {
        super(whitePiece, fromSquare);
        this.pieceType = Piece.K;
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
            System.out.println("Cannot create valid path for King.");
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
            System.out.println("Cannot create valid path for King.");
        }
    }

    @Override
    public boolean isValidMove(Board board, Square toSquare) {
        if(this.fromSquare.getFileIndex() == toSquare.getFileIndex() && toSquare.getRankIndex() == this.fromSquare.getRankIndex()) {
            return false;
        } else if (Math.abs(this.fromSquare.getFileIndex() - toSquare.getFileIndex()) > 1) {
            return false;
        } else if (Math.abs(this.fromSquare.getRankIndex() - toSquare.getRankIndex()) > 1) {
            return false;
        } else if (board.getPiece(toSquare.getRankIndex(), toSquare.getFileIndex()) != null) {
            return false;
        }
        return true;
    }
}
