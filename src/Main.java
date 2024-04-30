import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Bungo bingo = new Bungo();
        bingo.printCard();

        Random random = new Random(); // Create a random number generator

        while (!bingo.hasBingo()) {
            int generatedNumber = random.nextInt(75) + 1; // Generate random number between 1 and 75
            bingo.markNumber(generatedNumber);

            bingo.printCard(); // Print updated card (optional)
        }

        System.out.println("BINGO! We have a winner!");

        System.out.println("\nCard after marking some numbers:");
        bingo.printCard();


    }
}
