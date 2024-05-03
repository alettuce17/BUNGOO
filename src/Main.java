import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Patterns[] patterns = null;

        // Print the main menu
        printMainMenu();

        // Get user's choice
        int choice = scanner.nextInt();

        // Initialize patterns based on user's choice
        switch (choice) {
            case 1:
                patterns = new Patterns[]{createDoubleDiagonalPattern()};
                break;
            case 2:
                patterns = new Patterns[]{createBlackoutPattern()};
                break;
            case 3:
                patterns = createBothDiagonalPatterns();
                break;
            case 4:
                patterns = new Patterns[]{createFourCornersPattern()};
                break;
            case 5:
                patterns = createAllHorizontalPatterns();
                break;
            case 6:
                patterns = createAllVerticalPatterns();
                break;
            default:
                System.out.println("Invalid choice. Exiting...");
                System.exit(0);
        }
        BallsQueue ballsQueue = new BallsQueue();
        System.out.println(ballsQueue); // Print the ballsQueue status to demonstrate how this works

        // Play the game with selected patterns
        playBingo(patterns, ballsQueue);
    }

    // Method to print the main menu
    private static void printMainMenu() {
        System.out.println("Welcome to Bingo!");
        System.out.println("Choose the type of pattern:");
        System.out.println("1. Double Diagonal Pattern");
        System.out.println("2. Blackout Pattern");
        System.out.println("3. Both Diagonal Patterns");
        System.out.println("4. Four Corners Pattern");
        System.out.println("5. Horizontal Pattern");
        System.out.println("6. Vertical Pattern");
        System.out.print("Enter your choice: ");
    }



    private static boolean rowIsFilled(int rowIndex, boolean[][] patternMatrix) {
        for (int i = 0; i < 5; i++) {
            if (!patternMatrix[rowIndex][i]) {
                return false; // If any cell in the row is false, the row is not filled
            }
        }
        return true; // If all cells in the row are true, the row is filled
    }


    // Method to create a double diagonal pattern
    private static Patterns createDoubleDiagonalPattern() {
        boolean[][] diagonalPattern = {
                {true, false, false, false, true},
                {false, true, false, true, false},
                {false, false, true, false, false},
                {false, true, false, true, false},
                {true, false, false, false, true}
        };
        return new Patterns("Double Diagonal", diagonalPattern);
    }

    // Method to create a blackout pattern
    private static Patterns createBlackoutPattern() {
        boolean[][] blackoutPattern = {
                {true, true, true, true, true},
                {true, true, true, true, true},
                {true, true, true, true, true},
                {true, true, true, true, true},
                {true, true, true, true, true}
        };
        return new Patterns("Blackout", blackoutPattern);
    }

    // Method to create both left and right diagonal patterns
    private static Patterns[] createBothDiagonalPatterns() {
        Patterns leftDiagonal = Patterns.createDiagonalPattern(true);
        Patterns rightDiagonal = Patterns.createDiagonalPattern(false);
        return new Patterns[]{leftDiagonal, rightDiagonal};
    }
    private static Patterns[] createAllHorizontalPatterns() {
        Patterns[] horizontalPatterns = new Patterns[5];

        // Create horizontal patterns for each row
        for (int row = 0; row < 5; row++) {
            boolean[][] patternMatrix = new boolean[5][5];
            for (int i = 0; i < 5; i++) {
                patternMatrix[row][i] = true;
            }
            horizontalPatterns[row] = new Patterns("Horizontal Row " + (row + 1), patternMatrix);
        }

        return horizontalPatterns;
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

    // Method to create the four corners pattern
    private static Patterns createFourCornersPattern() {
        boolean[][] fourCornersPattern = {
                {true, false, false, false, true},
                {false, false, false, false, false},
                {false, false, false, false, false},
                {false, false, false, false, false},
                {true, false, false, false, true}
        };
        return new Patterns("Four Corners", fourCornersPattern);
    }

    private static void playBingo(Patterns[] patterns, BallsQueue ballsQueue) {
        Bungo[] players = new Bungo[4];
        List<Integer> losingPlayers = new ArrayList<>(); // Keep track of losing players

        // Initialize players
        for (int i = 0; i < players.length; i++) {
            players[i] = new Bungo();
            players[i].printCard();
        }

        int winnerIndex = -1;

        while (winnerIndex == -1) {
            BingoNumber generatedBall = ballsQueue.dequeue(); // Dequeue the next drawn ball
            System.out.println("\nGenerated Ball: " + generatedBall.getValue());

            // Check each pattern for bingo
            for (Patterns pattern : patterns) {
                for (int i = 0; i < players.length; i++) {
                    boolean hasNumber = players[i].markNumber(generatedBall.getValue());
                    if (hasNumber) {
                        System.out.println("Player " + (i + 1) + " has the number " + generatedBall.getValue());
                    } else {
                        System.out.println("Player " + (i + 1) + " does not have the number " + generatedBall.getValue());
                    }

                    if (players[i].hasBingo(pattern)) {
                        winnerIndex = i;
                        break;
                    }
                }
                // If a winner is found with this pattern, break out of the loop
                if (winnerIndex != -1) {
                    break;
                }
            }
            ballsQueue.enqueue(generatedBall); // Add the drawn ball back to the queue

        }
        System.out.println(ballsQueue); // Print the ballsQueue status for demonstration on how the balls queue work
        System.out.println("\nPlayer " + (winnerIndex + 1) + " wins with BINGO!");
        System.out.println("\nCard after marking some numbers:");

        players[winnerIndex].printCard();

        // Print the cards of the other losing players
        for (int i = 0; i < players.length; i++) {
            if (i != winnerIndex) {
                System.out.println("\nPlayer " + (i + 1) + "'s card:");
                players[i].printCard();
                losingPlayers.add(i + 1); // Add losing player to the list
            }
        }


        // Print losing players
        System.out.println("\nLosing players: " + losingPlayers);
    }


}
