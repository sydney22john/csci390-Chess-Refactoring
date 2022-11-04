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

    public void pawnMove(Board board, Square toSquare, String pawnPromotionPiece, boolean playerTurnIsWhite) {
        this.move(board, toSquare);
        GamePiece promotion = this.promotePawn(board, toSquare, pawnPromotionPiece, playerTurnIsWhite);
        if (promotion != null) {
            board.setPiece(promotion, promotion.fromSquare);
        }
    }

    public void pawnCapture(Board board, Square toSquare, String pawnPromotionPiece, boolean playerTurnIsWhite) {
        this.capture(board, toSquare);
        GamePiece promotion = this.promotePawn(board, toSquare, pawnPromotionPiece, playerTurnIsWhite);
        if (promotion != null) {
            board.setPiece(promotion, promotion.fromSquare);
        }
    }

    private GamePiece promotePawn(Board board, Square toSquare, String pawnPromotionPiece, boolean playerTurnIsWhite) {
        GamePiece fromPiece = null;
        if(playerTurnIsWhite && toSquare.getRankIndex() == 0) {
            if(pawnPromotionPiece == null) {
                System.out.println("Pawn Promotion Piece must be specified for this pawn move.");
                return null;
            }
            if(!pawnPromotionPiece.toUpperCase().equals(pawnPromotionPiece)) {
                System.out.println("Pawn Promotion Piece must be for White. Input should be uppercase.");
                return null;
            }
            fromPiece = getPromotionPiece(Piece.valueOf(pawnPromotionPiece));
        } else if(!playerTurnIsWhite && toSquare.getRankIndex() == 7) {
            if(pawnPromotionPiece == null) {
                System.out.println("Pawn Promotion Piece must be specified for this pawn move.");
                return null;
            }
            if(!pawnPromotionPiece.toLowerCase().equals(pawnPromotionPiece)) {
                System.out.println("Pawn Promotion Piece must be for Black. Input should be lowercase.");
                return null;
            }
            fromPiece = getPromotionPiece(Piece.valueOf(pawnPromotionPiece));
        }
        return fromPiece;
    }

    private GamePiece getPromotionPiece(Piece pieceType) {
        switch(pieceType) {
            case B:
                return new Bishop(this.whitePiece, this.fromSquare);
            case Q:
                return new Queen(this.whitePiece, this.fromSquare);
            case R:
                return new Rook(this.whitePiece, this.fromSquare);
            case N:
                return new Knight(this.whitePiece, this.fromSquare);
        }
        return null;
    }
}
