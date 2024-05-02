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
            default:
                System.out.println("Invalid choice. Exiting...");
                System.exit(0);
        }

        // Play the game with selected patterns
        playBingo(patterns);
    }

    // Method to print the main menu
    private static void printMainMenu() {
        System.out.println("Welcome to Bingo!");
        System.out.println("Choose the type of pattern:");
        System.out.println("1. Double Diagonal Pattern");
        System.out.println("2. Blackout Pattern");
        System.out.println("3. Both Diagonal Patterns");
        System.out.println("4. Four Corners Pattern");
        System.out.print("Enter your choice: ");
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

    // Method to play the bingo game
    private static void playBingo(Patterns[] patterns) {
        Bungo[] players = new Bungo[4];
        Set<Integer> generatedNumbers = new HashSet<>();
        Random random = new Random();
        List<Integer> losingPlayers = new ArrayList<>(); // Keep track of losing players

        // Initialize players
        for (int i = 0; i < players.length; i++) {
            players[i] = new Bungo();
            players[i].printCard();
        }

        int winnerIndex = -1;

        while (winnerIndex == -1) {
            int generatedNumber;
            do {
                generatedNumber = random.nextInt(75) + 1; // Generate random number between 1 and 75
            } while (generatedNumbers.contains(generatedNumber));

            generatedNumbers.add(generatedNumber);

            System.out.println("\nGenerated number: " + generatedNumber);

            // Check each pattern for bingo
            for (Patterns pattern : patterns) {
                for (int i = 0; i < players.length; i++) {
                    boolean hasNumber = players[i].markNumber(generatedNumber);
                    if (hasNumber) {
                        System.out.println("Player " + (i + 1) + " has the number " + generatedNumber);
                    } else {
                        System.out.println("Player " + (i + 1) + " does not have the number " + generatedNumber);
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
        }

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
