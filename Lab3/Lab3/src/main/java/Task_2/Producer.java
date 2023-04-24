package Task_2;

public class Producer implements Runnable {

    private Drop drop;

    private int[] importantInfo;

    public Producer(Drop drop, int[] importantInfo) {
        this.drop = drop;
        this.importantInfo = importantInfo;
    }

    public void run() {
        for (int message : importantInfo) {
            drop.put(message);

            System.out.format("MESSAGE SENT: %s%n", message);
        }
        drop.put(-1);
    }
}
