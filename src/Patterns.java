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

    private static Patterns createHorizontalPattern() {
        // Create a boolean matrix to represent the pattern
        boolean[][] patternMatrix = new boolean[5][5];

        // Select a random row to mark as the horizontal line
        int row = (int) (Math.random() * 5);

        // Mark all cells in the selected row as true
        for (int i = 0; i < 5; i++) {
            patternMatrix[row][i] = true;
        }

        // Create a pattern object with the generated horizontal line
        return new Patterns("Horizontal Row " + (row + 1), patternMatrix);
    }
    private static Patterns[] createAllVerticalPatterns() {
        Patterns[] verticalPatterns = new Patterns[5];

        // Create vertical patterns for each column
        for (int col = 0; col < 5; col++) {
            boolean[][] patternMatrix = new boolean[5][5];
            for (int i = 0; i < 5; i++) {
                patternMatrix[i][col] = true;
            }
            verticalPatterns[col] = new Patterns("Vertical Column " + (col + 1), patternMatrix);
        }

        return verticalPatterns;
    }



}

