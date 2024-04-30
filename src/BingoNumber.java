
class BingoNumber {
    private int value;
    private boolean marked;

    public BingoNumber(int value) {
        this.value = value;
        this.marked = false;
    }

    public int getValue() {
        return value;
    }

    public boolean isMarked() {
        return marked;
    }

    public void setMarked(boolean marked) {
        this.marked = marked;
    }
}