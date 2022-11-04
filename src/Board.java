public class Board {

    GamePiece[][] board = new GamePiece[8][8];

    public Board() {
        setupPieces();
    }

    public GamePiece getPiece(int rankIndex, int fileIndex) {
        return board[rankIndex][fileIndex];
    }

    public GamePiece getPiece(Square square) {
        return board[square.getRankIndex()][square.getFileIndex()];
    }

    public GamePiece[][] getBoard() {
        return board;
    }

    public void setPiece(GamePiece piece, int rankIndex, int fileIndex) {
        board[rankIndex][fileIndex] = piece;
    }

    public void setPiece(GamePiece piece, Square square) {
        board[square.getRankIndex()][square.getFileIndex()] = piece;
    }

    private void setupPieces() {
        setupBlackPieces();
        setupWhitePieces();
    }

    private void setupBlackPieces() {
        board[0][0] = new Rook(false, new Square(0, 0));
        board[0][1] = new Knight(false, new Square(0, 1));
        board[0][2] = new Bishop(false, new Square(0, 2));
        board[0][3] = new Queen(false, new Square(0, 3));
        board[0][4] = new King(false, new Square(0, 4));
        board[0][5] = new Bishop(false, new Square(0, 5));
        board[0][6] = new Knight(false, new Square(0, 6));
        board[0][7] = new Rook(false, new Square(0, 7));
        board[1][0] = new Pawn(false, new Square(1, 0));
        board[1][1] = new Pawn(false, new Square(1, 1));
        board[1][2] = new Pawn(false, new Square(1, 2));
        board[1][3] = new Pawn(false, new Square(1, 3));
        board[1][4] = new Pawn(false, new Square(1, 4));
        board[1][5] = new Pawn(false, new Square(1, 5));
        board[1][6] = new Pawn(false, new Square(1, 6));
        board[1][7] = new Pawn(false, new Square(1, 7));
    }

    private void setupWhitePieces() {
        board[6][0] = new Pawn(true, new Square(6, 0));
        board[6][1] = new Pawn(true, new Square(6, 1));
        board[6][2] = new Pawn(true, new Square(6, 2));
        board[6][3] = new Pawn(true, new Square(6, 3));
        board[6][4] = new Pawn(true, new Square(6, 4));
        board[6][5] = new Pawn(true, new Square(6, 5));
        board[6][6] = new Pawn(true, new Square(6, 6));
        board[6][7] = new Pawn(true, new Square(6, 7));
        board[7][0] = new Rook(true, new Square(7, 0));
        board[7][1] = new Knight(true, new Square(7, 1));
        board[7][2] = new Bishop(true, new Square(7, 2));
        board[7][3] = new Queen(true, new Square(7, 3));
        board[7][4] = new King(true, new Square(7, 4));
        board[7][5] = new Bishop(true, new Square(7, 5));
        board[7][6] = new Knight(true, new Square(7, 6));
        board[7][7] = new Rook(true, new Square(7, 7));
    }
}
