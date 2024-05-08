import java.util.Scanner;


public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        BingoCard[] players = new BingoCard[4];


        String[] playerNames = {"Player 1", "Player 2", "Player 3", "Player 4"};
        for (int i = 0; i < players.length; i++) {
            players[i] = new BingoCard(playerNames[i]);
        }

        do {

            Patterns[] patterns = null;

            for (BingoCard player : players) {
                player.setCard(player.generateCard());
            }
            // Print the main menu
            printMainMenu();

            // Get user's choice
            int choice = getUserChoice(scanner);

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
                case 7:
                    patterns = new Patterns[]{createGPattern()};
                    break;
                case 8:
                    String asciiExit = """
                                                                   
                                      ██████████████████                                                                                      
                                  ████                  ████                                                                                  
                                ██                          ██                                                                                
                                ██                          ██                                                                                
                              ██░░                          ░░▓▓                                                                              
                          ░░  ██  ░░░░                        ██                                                                              
                          ░░░░░░░░░░░░░░░░          ▓▓▓▓▓▓    ██                                                                              
                        ░░░░░░░░░░░░░░░░░░░░        ██████    ██                                                                              
                            ░░██░░  ░░░░░░    ██    ██████    ██                                                                              
                              ░░▓▓  ░░      ▓▓████          ▓▓                                                                                
                              ████  ██                  ██  ████        ████████                      ██  ██                                  
                              ██    ██████████████████████    ██        ██                            ██  ██                                  
                              ██      ██  ██  ██  ██  ██      ██        ██  ▓▓▓▓  ▓▓▓▓▓▓  ██▓▓▓▓  ▓▓▓▓██  ██▓▓▓▓  ▓▓  ██  ▓▓▓▓▓▓              
                              ▒▒████    ██████████████    ████▒▒        ██    ██  ██  ██  ██  ██  ██  ██  ██  ██  ██  ██  ██  ██              
                            ▒▒▒▒▒▒▒▒████      ▓▓  ▓▓  ████▒▒▒▒▒▒▒▒      ████████  ██████  ██████  ██████  ██████  ██████  ██████              
                          ░░▒▒██▒▒▒▒▓▓▓▓██████████████▓▓▓▓▒▒▒▒██▒▒                                                    ██  ██                  
                            ░░░░██████▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒██████  ██      ████████████████████████████████████████  ██████  ██████    ██  ██  ██
                          ░░░░  ██  ▓▓██████▒▒▒▒▒▒██████    ██  ██▓▓                                                                          
                          ██      ██▓▓▓▓▓▓    ██    ██    ██      ██░░                                                                        
                        ██    ████  ████▓▓▓▓▓▓      ██████  ████  ░░░░                                                                        
                        ██        ██    ██▓▓▓▓▓▓  ████    ██        ░░░░                                                                      
                        ██          ██  ██    ▓▓▓▓  ██  ██          ██                                                                        
                          ██      ██    ████████▓▓▓▓▓▓▓▓  ██      ██                                                                          
                            ▓▓▓▓  ██    ████████████▓▓    ██  ▓▓▓▓                                                                            
                              ██████    ██████████████    ██████                                                                              
                              ██████████████████████████████████                                                                              
                            ██████████████████████████████████████                                                                            
                            ██████████████████  ██████████████████                                                                            
                              ██████████████      ██████████████                                                                              
                          ██████          ██      ██          ██████                                                                          
                          ██            ████      ████            ██                                                                          
                            ▓▓▓▓▓▓▓▓▓▓▓▓              ▓▓▓▓▓▓▓▓▓▓▓▓                                                                            
                      
                            """;
                    System.out.println(asciiExit);
                    System.exit(0);
                default:
                    System.out.println("Invalid choice.");
            }

            if (choice >= 1 && choice <= 7) { // Play game if choice is valid pattern
                BallsQueue ballsQueue = new BallsQueue();
                ballsQueue.setup();

                playBingo(patterns, ballsQueue,players);
            }

        } while (!askToQuit(scanner)); // Continue playing until the user wants to quit
    }

    private static boolean askToQuit(Scanner scanner) {
        String input;
        do {

            String human = """

_____________________________________________________________________________________________________________________________________________________________
                                                                                                                                                            |
██████╗  ██████╗     ██╗   ██╗ ██████╗ ██╗   ██╗    ██╗    ██╗ █████╗ ███╗   ██╗████████╗    ████████╗ ██████╗      ██████╗ ██╗   ██╗██╗████████╗██████╗    |
██╔══██╗██╔═══██╗    ╚██╗ ██╔╝██╔═══██╗██║   ██║    ██║    ██║██╔══██╗████╗  ██║╚══██╔══╝    ╚══██╔══╝██╔═══██╗    ██╔═══██╗██║   ██║██║╚══██╔══╝╚════██╗   |
██║  ██║██║   ██║     ╚████╔╝ ██║   ██║██║   ██║    ██║ █╗ ██║███████║██╔██╗ ██║   ██║          ██║   ██║   ██║    ██║   ██║██║   ██║██║   ██║     ▄███╔╝   |
██║  ██║██║   ██║      ╚██╔╝  ██║   ██║██║   ██║    ██║███╗██║██╔══██║██║╚██╗██║   ██║          ██║   ██║   ██║    ██║▄▄ ██║██║   ██║██║   ██║     ▀▀══╝    | 
██████╔╝╚██████╔╝       ██║   ╚██████╔╝╚██████╔╝    ╚███╔███╔╝██║  ██║██║ ╚████║   ██║          ██║   ╚██████╔╝    ╚██████╔╝╚██████╔╝██║   ██║     ██╗      |
╚═════╝  ╚═════╝        ╚═╝    ╚═════╝  ╚═════╝      ╚══╝╚══╝ ╚═╝  ╚═╝╚═╝  ╚═══╝   ╚═╝          ╚═╝    ╚═════╝      ╚══▀▀═╝  ╚═════╝ ╚═╝   ╚═╝     ╚═╝      |
                                                                                                                                                            |
                                                                                                                                                            |
                                                                                                                                                            |
                ██╗██╗   ██╗██╗         ██╗   ██╗███████╗███████╗                                                                                           |
                ██╔╝╚██╗ ██╔╝╚██╗        ╚██╗ ██╔╝██╔════╝██╔════╝                                                                                          | 
               ██╔╝  ╚████╔╝  ╚██╗        ╚████╔╝ █████╗  ███████╗                                                                                          |
               ╚██╗   ╚██╔╝   ██╔╝         ╚██╔╝  ██╔══╝  ╚════██║                                                                                          |
                ╚██╗   ██║   ██╔╝ ██╗       ██║   ███████╗███████║                                                                                          |     
                 ╚═╝   ╚═╝   ╚═╝  ╚═╝       ╚═╝   ╚══════╝╚══════╝                                                                                          |
                                                                                                                                                            |
                 ██╗███╗   ██╗██╗         ███╗   ██╗ ██████╗                                                                                                |
                ██╔╝████╗  ██║╚██╗        ████╗  ██║██╔═══██╗                                                                                               |
               ██╔╝ ██╔██╗ ██║ ╚██╗       ██╔██╗ ██║██║   ██║                                                                                               |
               ╚██╗ ██║╚██╗██║ ██╔╝       ██║╚██╗██║██║   ██║                                                                                               |
                ╚██╗██║ ╚████║██╔╝ ██╗    ██║ ╚████║╚██████╔╝                                                                                               |
                 ╚═╝╚═╝  ╚═══╝╚═╝  ╚═╝    ╚═╝  ╚═══╝ ╚═════╝                                                                                                |
____________________________________________________________________________________________________________________________________________________________|                          
                    """;

            System.out.println(human);
            input = scanner.nextLine().toLowerCase();
            if(!input.equalsIgnoreCase("y")&&!input.equalsIgnoreCase("n")){
                System.out.println("Please enter valid input (y/n)");
            }
        }while (!input.equalsIgnoreCase("y")&&!input.equalsIgnoreCase("n"));
        return input.equals("y");
    }

    private static int getUserChoice(Scanner scanner) {
        int choice = 0;
        boolean validInput = false;
        while (!validInput) {
            if(scanner.hasNextLine()){
                String strChoice = scanner.nextLine().replaceAll("0"," ");
                try {
                    choice = Integer.parseInt(strChoice);
                    if (choice < 1 || choice > 8){
                        System.out.println("Invalid choice. Please enter a number between 1 and 7.");
                        System.out.print("Enter your choice: ");
                    } else {
                        validInput = true;
                        break;
                    }

                } catch (NumberFormatException e) {
                    System.out.println("Invalid input / There's no input. Please enter a number.");
                    // scanner.nextLine(); // Clear the invalid input
                    System.out.print("Enter your choice: ");
                }
            }
        }
        return choice;
    }

    // Method to print the main menu
    private static void printMainMenu() {

        String textAscii = """

    ██╗    ██╗███████╗██╗      ██████╗ ██████╗ ███╗   ███╗███████╗    ████████╗ ██████╗     ██████╗ ██╗███╗   ██╗ ██████╗  ██████╗ ██╗
    ██║    ██║██╔════╝██║     ██╔════╝██╔═══██╗████╗ ████║██╔════╝    ╚══██╔══╝██╔═══██╗    ██╔══██╗██║████╗  ██║██╔════╝ ██╔═══██╗██║
    ██║ █╗ ██║█████╗  ██║     ██║     ██║   ██║██╔████╔██║█████╗         ██║   ██║   ██║    ██████╔╝██║██╔██╗ ██║██║  ███╗██║   ██║██║
    ██║███╗██║██╔══╝  ██║     ██║     ██║   ██║██║╚██╔╝██║██╔══╝         ██║   ██║   ██║    ██╔══██╗██║██║╚██╗██║██║   ██║██║   ██║╚═╝
    ╚███╔███╔╝███████╗███████╗╚██████╗╚██████╔╝██║ ╚═╝ ██║███████╗       ██║   ╚██████╔╝    ██████╔╝██║██║ ╚████║╚██████╔╝╚██████╔╝██╗
    ╚══╝╚══╝ ╚══════╝╚══════╝ ╚═════╝ ╚═════╝ ╚═╝     ╚═╝╚══════╝       ╚═╝    ╚═════╝     ╚═════╝ ╚═╝╚═╝  ╚═══╝ ╚═════╝  ╚═════╝ ╚═╝
                                                                                                                                  
                
 __| |____________________________________________| |__
 (__   ____________________________________________   __)
    | |                                            | |
    | |        Choose the type of pattern          | |
    | |        -> 1. X Pattern                     | |
    | |        -> 2. Blackout Pattern              | |
    | |        -> 3. Diagonal Patterns             | |
    | |        -> 4. Four Corners Pattern          | |
    | |        -> 5. Horizontal Pattern            | |     
    | |        -> 6. Vertical Pattern              | |
    | |        -> 7. G Pattern                     | |
    | |        -> 8. Exit                          | |
  __| |____________________________________________| |__
 (__   ____________________________________________   __)
    | |                                            | |
 
                """;
        System.out.println(textAscii);
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
        return new Patterns("X Pattern", diagonalPattern);
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
            horizontalPatterns[row] = new Patterns("Horizontal Row ", patternMatrix);
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
            verticalPatterns[col] = new Patterns("Vertical Column ", patternMatrix);
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

    private static Patterns createGPattern() {
        boolean[][] gPattern = {
                {true, true, true, true, true},
                {true, false, false, false, false},
                {true, false, true, true, true},
                {true, false, false, false, true},
                {true, true, true, true, true}
        };
        return new Patterns("G Pattern", gPattern);
    }

    private static void playBingo(Patterns[] patterns, BallsQueue ballsQueue, BingoCard[] players) {

        BingoCard[] winningPlayersForPattern = new BingoCard[players.length]; // Keep track of winning players for this pattern

        for (int i = 0; i < players.length; i++) {
            System.out.println(players[i].getName() + "'s card");
            players[i].printCard();
            System.out.println();
        }

        String[] announcedNumbers = new String[1000]; // Track announced numbers
        int announcedIndex = 0;

        int currentPlayerIndex = 0;
        while (winningPlayersForPattern[currentPlayerIndex] == null) {
            BingoNumber generatedBall = ballsQueue.dequeue(); // Dequeue the next drawn ball
            if (winningPlayersFound(winningPlayersForPattern)) {
                ballsQueue.enqueue(generatedBall); // Add the drawn ball back to the queue
                break;
            }

            System.out.println();
            System.out.println("Please enter to generate a ball");
            scanner.nextLine();

            System.out.println("\n+---------------------------------------+");
            System.out.println("| Player " + (currentPlayerIndex + 1) + "'s Generated Ball: " + generatedBall.getValue() + addSpaces(11 - String.valueOf(generatedBall.getValue()).length()) + "|");
            System.out.println("+---------------------------------------+");
            currentPlayerIndex = (currentPlayerIndex + 1) % players.length;

            // Check each pattern for bingo
            for (Patterns pattern : patterns) {

                for (int i = 0; i < players.length; i++) {

                    boolean hasNumber = players[i].markNumber(generatedBall.getValue());
                    String message;
                    if (hasNumber) {
                        message = players[i].getName() + " has the number " + generatedBall.getValue();
                    } else {
                        message = players[i].getName() + " does not have the number " + generatedBall.getValue();
                    }


                    // Print the message only if it hasn't been announced before
                    if (!containsString(announcedNumbers, message)) {
                        announcedNumbers[announcedIndex++] = message;
                        System.out.println(message);
                    }

                    if (players[i].hasBingo(pattern)) {
                        winningPlayersForPattern[i] = players[i];
                    }

                }

                for (BingoCard player : players) {
                    System.out.println("\n" + player.getName() + "'s card:");
                    player.printCard();
                    System.out.println();
                }


                // If there are winning players for this pattern, add them to the winners list
                if (winningPlayersFound(winningPlayersForPattern)) {
                    System.out.println();
                    System.out.println("Pattern: " + pattern.getName());
                    for (BingoCard winningPlayer : winningPlayersForPattern) {
                        if (winningPlayer != null) {
                            System.out.println("Player " + winningPlayer.getName() + " wins with BINGO!");
                            System.out.println("\n" + winningPlayer.getName() + "'s card:");
                            winningPlayer.printCard();
                            winningPlayer.addWin();
                            System.out.println();
                        }
                    }
                    break; // Exit the loop if a winner is found for this pattern
                }

            }


            ballsQueue.enqueue(generatedBall); // Add the drawn ball back to the queue

        }

        System.out.println();

        // Print losing players
        System.out.println("\n+------------------+");
        System.out.println("| Losing players:  |");
        System.out.println("+------------------+");
        for (BingoCard player : players) {
            if (!containsBingoCard(winningPlayersForPattern, player)) {
                System.out.println("| " + player.getName() + addSpaces(16 - player.getName().length()) + " |");
            }
        }
        System.out.println("+------------------+");

        // Print the cards of the other losing players
        for (BingoCard player : players) {
            if (!containsBingoCard(winningPlayersForPattern, player)) {
                System.out.println("\n" + player.getName() + "'s card:");
                player.printCard();
                System.out.println();
            }
        }

        printLeaderboard(players);
    }

    private static boolean containsString(String[] array, String value) {
        for (String s : array) {
            if (s != null && s.equals(value)) {
                return true;
            }
        }
        return false;
    }

    private static boolean containsBingoCard(BingoCard[] array, BingoCard value) {
        for (BingoCard card : array) {
            if (card != null && card.equals(value)) {
                return true;
            }
        }
        return false;
    }

    private static boolean winningPlayersFound(BingoCard[] array) {
        for (BingoCard card : array) {
            if (card != null) {
                return true;
            }
        }
        return false;
    }
    public static void printLeaderboard(BingoCard[] players) {
        // Sort players based on wins using bubble sort algorithm
        for (int i = 0; i < players.length - 1; i++) {
            for (int j = 0; j < players.length - i - 1; j++) {
                if (players[j].getWins() < players[j + 1].getWins()) {
                    // Swap players if they are out of order
                    BingoCard temp = players[j];
                    players[j] = players[j + 1];
                    players[j + 1] = temp;
                }
            }
        }

        // Print leaderboard
        System.out.println("Point Leaderboards:");
        int rank = 1; // Start with rank 1
        int prevWins = players[0].getWins(); // Initialize with wins of the first player
        for (int i = 0; i < players.length; i++) {
            // Check if current player has fewer wins than the previous player
            if (i > 0 && players[i].getWins() < prevWins) {
                rank++; // Increment rank
            }
            // Update previous wins for next iteration
            prevWins = players[i].getWins();
            // Print player information
            System.out.println("Rank " + rank + ": " + players[i].getName() + " - Wins: " + players[i].getWins());
        }
    }


    public static String addSpaces(int count) {
        StringBuilder spaces = new StringBuilder();
        for (int i = 0; i < count; i++) {
            spaces.append(" ");
        }
        return spaces.toString();
    }




}