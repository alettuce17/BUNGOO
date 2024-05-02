public class Patterns {
    private String name; // Name of the pattern (e.g., "Blackout", "Diagonal", "X")
    private boolean[][] patternMatrix; // 5x5 boolean matrix for the pattern

    public Patterns(String name, boolean[][] patternMatrix) {
        this.name = name;
        this.patternMatrix = patternMatrix;
    }

    public String getName() {
        return name;
    }

    public boolean[][] getPatternMatrix() {
        return patternMatrix;
    }
    static Patterns createDiagonalPattern(boolean leftDiagonal) {
        boolean[][] diagonalPattern = new boolean[5][5];

        if (leftDiagonal) {
            // Left diagonal
            for (int i = 0; i < 5; i++) {
                diagonalPattern[i][i] = true;
            }
        } else {
            // Right diagonal
            for (int i = 0; i < 5; i++) {
                diagonalPattern[i][4 - i] = true;
            }
        }

        return new Patterns(leftDiagonal ? "Left Diagonal" : "Right Diagonal", diagonalPattern);
    }



}

