import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Chess {

    Board board = new Board();
    boolean playerTurnIsWhite;

    public Chess() {
        playerTurnIsWhite = true;
    }

    public void play() throws IOException {
        GameMove gameMove = new GameMove();
        while(!gameIsOver()) {
            printBoardToConsole();

            // getting the user input
            if (!gameMove.getMove(board)) continue;

            if(gameMove.isCapture()) {
                capturePiece(gameMove);
            } else {
                movePiece(gameMove);
            }
            // We are not going to worry about special moves like castling and en passant
        }

        System.out.println("Game over.");
        System.out.println("Thanks for playing!");
    }

    private void movePiece(GameMove gameMove) {
        GamePiece fromPiece = gameMove.getPiece();

        // making sure the piece is valid
        if(fromPiece == null) {
            System.out.println("Select a square with a piece.");
            return;
        }
        if (correctPlayerNotMovingTheirPiece(fromPiece)) return;

        // move the piece
        fromPiece.move(board, gameMove.getToSquare());

        //Change the player's turn
        playerTurnIsWhite = !playerTurnIsWhite;
    }



    private void moveBishop(int toFileIndex, int toRankIndex, int fromFileIndex, int fromRankIndex) {
        if(fromFileIndex == toFileIndex || toRankIndex == fromRankIndex) {
            System.out.println("Cannot create valid path for Bishop.");
            return;
        } else if(Math.abs(fromFileIndex - toFileIndex) != Math.abs(fromRankIndex - toRankIndex)) {
            System.out.println("Cannot create valid path for Bishop.");
            return;
        } else {
            if(fromFileIndex < toFileIndex && fromRankIndex < toRankIndex) {
                for(int i = 1; i <= toFileIndex - fromFileIndex; i++) {
                    if(board.getPiece(fromRankIndex +i, fromFileIndex +i) != null) {
                        System.out.println("Cannot create valid path for Bishop.");
                        return;
                    }
                }
            } else if(fromFileIndex < toFileIndex && fromRankIndex > toRankIndex) {
                for(int i = 1; i <= toFileIndex - fromFileIndex; i++) {
                    if(board.getPiece(fromRankIndex -i, fromFileIndex +i) != null) {
                        System.out.println("Cannot create valid path for Bishop.");
                        return;
                    }
                }
            } else if(fromFileIndex > toFileIndex && fromRankIndex > toRankIndex) {
                for(int i = 1; i <= fromFileIndex - toFileIndex; i++) {
                    if(board.getPiece(fromRankIndex -i, fromFileIndex -i) != null) {
                        System.out.println("Cannot create valid path for Bishop.");
                        return;
                    }
                }
            } else if(fromFileIndex > toFileIndex && fromRankIndex < toRankIndex) {
                for(int i = 1; i <= fromFileIndex - toFileIndex; i++) {
                    if(board.getPiece(fromRankIndex +i, fromFileIndex -i) != null) {
                        System.out.println("Cannot create valid path for Bishop.");
                        return;
                    }
                }
            }
        }
    }

    private void moveRook(int toFileIndex, int toRankIndex, int fromFileIndex, int fromRankIndex) {
        validateRookMove(toFileIndex, toRankIndex, fromFileIndex, fromRankIndex);
        return;
    }

    private boolean moveKnight(int toFileIndex, int toRankIndex, int fromFileIndex, int fromRankIndex) {
        if (!((Math.abs(fromFileIndex - toFileIndex) == 2 && Math.abs(fromRankIndex - toRankIndex) == 1) || (Math.abs(fromFileIndex - toFileIndex) == 1 && Math.abs(fromRankIndex - toRankIndex) == 2))) {
            System.out.println("Invalid move for Knight.");
            return true;
        }
        return false;
    }

    private boolean moveKing(int toFileIndex, int toRankIndex, int fromFileIndex, int fromRankIndex) {
        if(fromFileIndex == toFileIndex && toRankIndex == fromRankIndex) {
            System.out.println("Cannot create valid path for King.");
            return true;
        } else if (Math.abs(fromFileIndex - toFileIndex) > 1) {
            System.out.println("Cannot create valid path for King.");
            return true;
        } else if (Math.abs(fromRankIndex - toRankIndex) > 1) {
            System.out.println("Cannot create valid path for King.");
            return true;
        } else if (board.getPiece(toRankIndex, toFileIndex) != null) {
            System.out.println("Cannot create valid path for King.");
            return true;
        }
        return false;
    }

    private boolean movePawn(int toFileIndex, int toRankIndex, int fromFileIndex, int fromRankIndex) {
        if(fromFileIndex != toFileIndex) {
            System.out.println("Cannot create valid path for Pawn.");
            return true;
        }
        if(playerTurnIsWhite) {
            if(fromRankIndex == 6) {
                int rankDelta = fromRankIndex - toRankIndex;
                if(rankDelta > 2 || rankDelta < 1) {
                    System.out.println("Cannot create valid path for Pawn.");
                    return true;
                } else if (rankDelta == 1) {
                    if(board.getPiece(toRankIndex, toFileIndex) != null) {
                        System.out.println("Cannot create valid path for Pawn.");
                        return true;
                    }
                } else if (rankDelta == 2) {
                    if(board.getPiece(toRankIndex, toFileIndex) != null || board.getPiece(toRankIndex -1, toFileIndex) != null) {
                        System.out.println("Cannot create valid path for Pawn.");
                        return true;
                    }
                }
            } else {
                int rankDelta = fromRankIndex - toRankIndex;
                if(rankDelta != 1) {
                    System.out.println("Cannot create valid path for Pawn.");
                    return true;
                } else {
                    if(board.getPiece(toRankIndex, toFileIndex) != null) {
                        System.out.println("Cannot create valid path for Pawn.");
                        return true;
                    }
                }
            }
        } else {
            if(fromRankIndex == 1) {
                int rankDelta = fromRankIndex - toRankIndex;
                if(rankDelta < -2 || rankDelta > -1) {
                    System.out.println("Cannot create valid path for Pawn.");
                    return true;
                } else if (rankDelta == -1) {
                    if(board.getPiece(toRankIndex, toFileIndex) != null) {
                        System.out.println("Cannot create valid path for Pawn.");
                        return true;
                    }
                } else if (rankDelta == -2) {
                    if(board.getPiece(toRankIndex, toFileIndex) != null || board.getPiece(toRankIndex +1, toFileIndex) != null) {
                        System.out.println("Cannot create valid path for Pawn.");
                        return true;
                    }
                }
            } else {
                int rankDelta = fromRankIndex - toRankIndex;
                if(rankDelta != -1) {
                    System.out.println("Cannot create valid path for Pawn.");
                    return true;
                } else {
                    if(board.getPiece(toRankIndex, toFileIndex) != null) {
                        System.out.println("Cannot create valid path for Pawn.");
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private GamePiece promotePawn(int toRankIndex, String pawnPromotionPiece, GamePiece fromPiece) {
        if(playerTurnIsWhite && toRankIndex == 0) {
            if(pawnPromotionPiece == null) {
                System.out.println("Pawn Promotion Piece must be specified for this pawn move.");
                return null;
            }
            if(!pawnPromotionPiece.toUpperCase().equals(pawnPromotionPiece)) {
                System.out.println("Pawn Promotion Piece must be for White. Input should be uppercase.");
                return null;
            }
            fromPiece = Piece.valueOf(pawnPromotionPiece);
        } else if(!playerTurnIsWhite && toRankIndex == 7) {
            if(pawnPromotionPiece == null) {
                System.out.println("Pawn Promotion Piece must be specified for this pawn move.");
                return null;
            }
            if(!pawnPromotionPiece.toLowerCase().equals(pawnPromotionPiece)) {
                System.out.println("Pawn Promotion Piece must be for Black. Input should be lowercase.");
                return null;
            }
            fromPiece = Piece.valueOf(pawnPromotionPiece);
        }
        return fromPiece;
    }

    private void validateRookMove(int toFileIndex, int toRankIndex, int fromFileIndex, int fromRankIndex) {
        if(fromFileIndex == toFileIndex && toRankIndex == fromRankIndex) {
            System.out.println("Rook must move at least 1 square.");
            return;
        } else if(fromFileIndex == toFileIndex) {
            if(toRankIndex > fromRankIndex) {
                for(int i = fromRankIndex +1; i<= toRankIndex; i++) {
                    if(board.getPiece(i, fromFileIndex) != null) {
                        System.out.println("Cannot create valid path for Rook.");
                        return;
                    }
                }
            } else {
                for(int i = fromRankIndex -1; i>= toRankIndex; i--) {
                    if(board.getPiece(i, fromFileIndex) != null) {
                        System.out.println("Cannot create valid path for Rook.");
                        return;
                    }
                }
            }
        } else if(fromRankIndex == toRankIndex){
            if(toFileIndex > fromFileIndex) {
                for(int i = fromFileIndex +1; i<= toFileIndex; i++) {
                    if(board.getPiece(fromRankIndex, i) != null) {
                        System.out.println("Cannot create valid path for Rook.");
                        return;
                    }
                }
            } else {
                for(int i = fromFileIndex -1; i>= fromFileIndex; i--) {
                    if(board.getPiece(fromRankIndex, i) != null) {
                        System.out.println("Cannot create valid path for Rook.");
                        return;
                    }
                }
            }
        } else {
            System.out.println("Cannot create valid path for Rook.");
            return;
        }
    }

    private boolean correctPlayerNotMovingTheirPiece(GamePiece fromPiece) {
        //Check that the piece is owned by the correct player.
        if(playerTurnIsWhite) {
            if(!fromPiece.isWhitePiece()) {
                System.out.println("Select a square with a white piece.");
                return true;
            }
        } else {
            if(fromPiece.isWhitePiece()) {
                System.out.println("Select a square with a black piece.");
                return true;
            }
        }
        return false;
    }

    // TODO: Homework - Refactor this method to use a single parameter

    private void capturePiece(GameMove gameMove) {
        GamePiece fromPiece = gameMove.getPiece();

        if(fromPiece == null) {
            System.out.println("Select a square with a piece.");
            return;
        }
        if (correctPlayerNotMovingTheirPiece(fromPiece)) return;

        Square wantedLocation = gameMove.getToSquare();
        // TODO: Homework - Create capture logic when a piece is capturing another piece
        //           Remember: Pieces can only capture opposing pieces
        //                     Pawns can only capture diagonally in front of them
        //                     We are not worrying about en passant. This is just the simple and basic moves.
        //           Use inspiration from the move method. Think about what can be refactored.
        //                     Extract method is your friend.

        // move the piece
        fromPiece.move(board, gameMove.getToSquare());

        //Change the player's turn
        playerTurnIsWhite = !playerTurnIsWhite;
    }


    private boolean gameIsOver() {
        return isPositionCheckmate() || isPositionStalemate();
    }

    private boolean isPositionStalemate() {
        return false;
    }

    private boolean isPositionCheckmate() {
        return false;
    }

    private void printBoardToConsole() {
        StringBuilder sb = new StringBuilder();
        int rankNum = 8;
        for (GamePiece[] rank : board.getBoard()) {
            sb.append(rankNum + " ");
            for(GamePiece piece : rank) {
                if(piece != null) {
                    sb.append(piece);
                } else {
                    sb.append(" ");
                }
            }
            sb.append("\n");
            rankNum--;
        }
        sb.append("  abcdefgh");
        System.out.println(sb);
    }
}
