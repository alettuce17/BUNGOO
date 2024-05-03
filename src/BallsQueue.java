import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.NoSuchElementException;
import java.util.List;

class BallsQueue {
    private Queue<BingoNumber> balls;

    // Constructor to initialize an empty queue
    public BallsQueue() {
        balls = new LinkedList<>();
        // Populate the queue with BingoNumber objects representing random numbers between 1 and 75
        for (int i = 1; i <= 75; i++) {
            balls.add(new BingoNumber(i));
        }
        // Shuffle the queue to randomize the order of the balls
        Collections.shuffle((List<?>) balls);
    }

    // Method to dequeue (remove) and return the next drawn ball
    public BingoNumber dequeue() {
        if (balls.isEmpty()) {
            throw new NoSuchElementException("No more balls in the queue");
        }
        return balls.remove();
    }

    // Method to enqueue (add) the drawn ball back to the queue
    public void enqueue(BingoNumber ball) {
        balls.add(ball);
    }

    // Method to check if the queue is empty
    public boolean isEmpty() {
        return balls.isEmpty();
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Balls Queue (For Demonstration): [");

        for (BingoNumber ball : this.balls) {
            sb.append(ball.getValue()).append(", ");
        }

        if (sb.length() > 14) {
            sb.delete(sb.length() - 2, sb.length());
        }
        sb.append("]");
        return sb.toString();
    }
}