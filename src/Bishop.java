public class Bishop extends GamePiece {
    public Bishop(boolean whitePiece, Square fromSquare) {
        super(whitePiece, fromSquare);
        this.pieceType = Piece.B;
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
            System.out.println("Cannot create valid path for Bishop.");
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
            System.out.println("Cannot create valid path for Bishop.");
        }
    }

    @Override
    public boolean isValidMove(Board board, Square toSquare) {
        if(this.fromSquare.getFileIndex() == toSquare.getFileIndex() || toSquare.getRankIndex() == this.fromSquare.getRankIndex()) {
            return false;
        } else if(Math.abs(this.fromSquare.getFileIndex() - toSquare.getFileIndex()) != Math.abs(this.fromSquare.getRankIndex() - toSquare.getRankIndex())) {
            return false;
        } else {
            if(this.fromSquare.getFileIndex() < toSquare.getFileIndex() && this.fromSquare.getRankIndex() < toSquare.getRankIndex()) {
                for(int i = 1; i <= toSquare.getFileIndex() - this.fromSquare.getFileIndex(); i++) {
                    if(board.getPiece(this.fromSquare.getRankIndex() +i, this.fromSquare.getFileIndex() +i) != null) {
                        return false;
                    }
                }
            } else if(this.fromSquare.getFileIndex() < toSquare.getFileIndex() && this.fromSquare.getRankIndex() > toSquare.getRankIndex()) {
                for(int i = 1; i <= toSquare.getFileIndex() - this.fromSquare.getFileIndex(); i++) {
                    if(board.getPiece(this.fromSquare.getRankIndex() -i, this.fromSquare.getFileIndex() +i) != null) {
                        return false;
                    }
                }
            } else if(this.fromSquare.getFileIndex() > toSquare.getFileIndex() && this.fromSquare.getRankIndex() > toSquare.getRankIndex()) {
                for(int i = 1; i <= this.fromSquare.getFileIndex() - toSquare.getFileIndex(); i++) {
                    if(board.getPiece(this.fromSquare.getRankIndex() -i, this.fromSquare.getFileIndex() -i) != null) {
                        return false;
                    }
                }
            } else if(this.fromSquare.getFileIndex() > toSquare.getFileIndex() && this.fromSquare.getRankIndex() < toSquare.getRankIndex()) {
                for(int i = 1; i <= this.fromSquare.getFileIndex() - toSquare.getFileIndex(); i++) {
                    if(board.getPiece(this.fromSquare.getRankIndex() +i, this.fromSquare.getFileIndex() -i) != null) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
