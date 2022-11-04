public class Rook extends GamePiece {
    public Rook(boolean whitePiece, Square fromSquare) {
        super(whitePiece, fromSquare);
        this.pieceType = Piece.R;
    }

    @Override
    public void move(Board board, Square toSquare) {
        if (isValidMove(board, toSquare)) {
            // swapping the piece location and updating current location
            board.setPiece(null, this.fromSquare);
            board.setPiece(this, toSquare);
            this.fromSquare = toSquare;
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
    }

    @Override
    public boolean isValidMove(Board board, Square toSquare) {
        if(this.fromSquare.getFileIndex() == toSquare.getFileIndex() && toSquare.getRankIndex() == this.fromSquare.getRankIndex()) {
            System.out.println("Rook must move at least 1 square.");
            return false;
        } else if(this.fromSquare.getFileIndex() == toSquare.getFileIndex()) {
            if(toSquare.getRankIndex() > this.fromSquare.getRankIndex()) {
                for(int i = this.fromSquare.getRankIndex() +1; i<= toSquare.getRankIndex(); i++) {
                    if(board.getPiece(i, this.fromSquare.getFileIndex()) != null) {
                        System.out.println("Cannot create valid path for Rook.");
                        return false;
                    }
                }
            } else {
                for(int i = this.fromSquare.getRankIndex() -1; i>= toSquare.getRankIndex(); i--) {
                    if(board.getPiece(i, this.fromSquare.getFileIndex()) != null) {
                        System.out.println("Cannot create valid path for Rook.");
                        return false;
                    }
                }
            }
        } else if(this.fromSquare.getRankIndex() == toSquare.getRankIndex()){
            if(toSquare.getFileIndex() > this.fromSquare.getFileIndex()) {
                for(int i = this.fromSquare.getFileIndex() +1; i<= toSquare.getFileIndex(); i++) {
                    if(board.getPiece(this.fromSquare.getRankIndex(), i) != null) {
                        System.out.println("Cannot create valid path for Rook.");
                        return false;
                    }
                }
            } else {
                for(int i = this.fromSquare.getFileIndex() -1; i>= this.fromSquare.getFileIndex(); i--) {
                    if(board.getPiece(this.fromSquare.getRankIndex(), i) != null) {
                        System.out.println("Cannot create valid path for Rook.");
                        return false;
                    }
                }
            }
        } else {
            System.out.println("Cannot create valid path for Rook.");
            return false;
        }
        return true;
    }
}
