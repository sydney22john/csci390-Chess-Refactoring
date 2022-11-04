public class Square {
    private int rankIndex;
    private int fileIndex;

    public Square(int rankIndex, int fileIndex) {
        this.rankIndex = rankIndex;
        this.fileIndex = fileIndex;
    }

    public Square() {}

    public int getRankIndex() {
        return rankIndex;
    }

    public void setRankIndex(int rankIndex) {
        this.rankIndex = rankIndex;
    }

    public int getFileIndex() {
        return fileIndex;
    }

    public void setFileIndex(int fileIndex) {
        this.fileIndex = fileIndex;
    }


}
