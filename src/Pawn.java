public class Pawn extends GamePiece implements Moves {

    public Pawn(boolean whitePiece, Square fromSquare) {
        super(whitePiece, fromSquare);
        this.pieceType = Piece.P;
    }

    @Override
    public void move(Board board, Square toSquare) {
        if (isValidMove(board, toSquare)) {
            board.setPiece(null, this.fromSquare);
            board.setPiece(this, toSquare);
            this.fromSquare = toSquare;
        } else {
            System.out.println("Cannot create valid path for Pawn.");
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
            System.out.println("Cannot create valid path for Pawn.");
        }
    }

    @Override
    public boolean isValidMove(Board board, Square toSquare) {
        if (this.fromSquare.getFileIndex() != toSquare.getFileIndex()) {
            return false;
        }
        if (this.isWhitePiece()) {
            if (this.fromSquare.getRankIndex() == 6) {
                int rankDelta = this.fromSquare.getRankIndex() - toSquare.getRankIndex();
                if (rankDelta > 2 || rankDelta < 1) {
                    return false;
                } else if (rankDelta == 1) {
                    if (board.getPiece(toSquare.getRankIndex(), toSquare.getFileIndex()) != null) {
                        return false;
                    }
                } else if (rankDelta == 2) {
                    if (board.getPiece(toSquare.getRankIndex(), toSquare.getFileIndex()) != null || board.getPiece(toSquare.getRankIndex() - 1, toSquare.getFileIndex()) != null) {
                        return false;
                    }
                }
            } else {
                int rankDelta = this.fromSquare.getRankIndex() - toSquare.getRankIndex();
                if (rankDelta != 1) {
                    return false;
                } else {
                    if (board.getPiece(toSquare.getRankIndex(), toSquare.getFileIndex()) != null) {
                        return false;
                    }
                }
            }
        } else {
            if (this.fromSquare.getRankIndex() == 1) {
                int rankDelta = this.fromSquare.getRankIndex() - toSquare.getRankIndex();
                if (rankDelta < -2 || rankDelta > -1) {
                    return false;
                } else if (rankDelta == -1) {
                    if (board.getPiece(toSquare.getRankIndex(), toSquare.getFileIndex()) != null) {
                        return false;
                    }
                } else if (rankDelta == -2) {
                    if (board.getPiece(toSquare.getRankIndex(), toSquare.getFileIndex()) != null || board.getPiece(toSquare.getRankIndex() + 1, toSquare.getFileIndex()) != null) {
                        return false;
                    }
                }
            } else {
                int rankDelta = this.fromSquare.getRankIndex() - toSquare.getRankIndex();
                if (rankDelta != -1) {
                    return false;
                } else {
                    if (board.getPiece(toSquare.getRankIndex(), toSquare.getFileIndex()) != null) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

//    private GamePiece promotePawn(int toRankIndex, String pawnPromotionPiece, GamePiece fromPiece) {
//        if(playerTurnIsWhite && toRankIndex == 0) {
//            if(pawnPromotionPiece == null) {
//                System.out.println("Pawn Promotion Piece must be specified for this pawn move.");
//                return null;
//            }
//            if(!pawnPromotionPiece.toUpperCase().equals(pawnPromotionPiece)) {
//                System.out.println("Pawn Promotion Piece must be for White. Input should be uppercase.");
//                return null;
//            }
//            fromPiece = Piece.valueOf(pawnPromotionPiece);
//        } else if(!playerTurnIsWhite && toRankIndex == 7) {
//            if(pawnPromotionPiece == null) {
//                System.out.println("Pawn Promotion Piece must be specified for this pawn move.");
//                return null;
//            }
//            if(!pawnPromotionPiece.toLowerCase().equals(pawnPromotionPiece)) {
//                System.out.println("Pawn Promotion Piece must be for Black. Input should be lowercase.");
//                return null;
//            }
//            fromPiece = Piece.valueOf(pawnPromotionPiece);
//        }
//        return fromPiece;
//    }
}
