import java.util.NoSuchElementException;

class BallsQueue {
    private BingoNumber[] balls;
    private int front;
    private int rear;
    private int size;

    // Constructor to initialize an empty queue
    public BallsQueue() {
        balls = new BingoNumber[75];
        front = 0;
        rear = -1;
        size = 0;
    }

    // Method to populate and shuffle the queue
    public void setup() {
        // Populate the queue with BingoNumber objects representing numbers between 1 and 75
        for (int i = 0; i < 75; i++) {
            balls[i] = new BingoNumber(i + 1);
        }
        // Shuffle the queue to randomize the order of the balls
        shuffle();
        // Reset front, rear, and size
        front = 0;
        rear = 74; // Adjusted to point to the last valid index
        size = 75;
    }

    // Method to shuffle the queue
// Method to shuffle the queue
    private void shuffle() {
        for (int i = balls.length - 1; i > 0; i--) {
            int j = (int) (Math.random() * (i + 1));
            if (j != i) {
                BingoNumber temp = balls[i];
                balls[i] = balls[j];
                balls[j] = temp;
            }
        }
    }


    // Method to dequeue (remove) and return the next drawn ball
    public BingoNumber dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("No more balls in the queue");
        }
        BingoNumber ball = balls[front];
        front = (front + 1) % balls.length;
        size--;
        return ball;
    }

    // Method to enqueue (add) the drawn ball back to the queue
    public void enqueue(BingoNumber ball) {
        if (isFull()) {
            throw new IllegalStateException("Queue is full");
        }
        rear = (rear + 1) % balls.length;
        balls[rear] = ball;
        // Only increment size if the queue wasn't previously full
        if (size < balls.length) {
            size++;
        }
    }

    // Method to check if the queue is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Method to check if the queue is full
    public boolean isFull() {
        return size == balls.length;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Balls Queue (For Demonstration): [");

        int index = front;
        for (int i = 0; i < size; i++) {
            sb.append(balls[index].getValue()).append(", ");
            index = (index + 1) % balls.length;
        }

        if (sb.length() > 14) {
            sb.delete(sb.length() - 2, sb.length());
        }
        sb.append("]");
        return sb.toString();
    }
}
