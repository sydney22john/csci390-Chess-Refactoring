public interface Moves {
    public void move(Board board, Square toSquare);
    public void capture(Board board, Square toSquare);
    public boolean isValidMove(Board board, Square toSquare);
}
