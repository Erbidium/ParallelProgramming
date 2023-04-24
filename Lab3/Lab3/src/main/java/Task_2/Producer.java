package Task_2;

import java.util.Random;

public class Producer implements Runnable {

    private Drop drop;

    private int[] importantInfo;

    public Producer(Drop drop, int[] importantInfo) {
        this.drop = drop;
        this.importantInfo = importantInfo;
    }

    public void run() {
        for (int i = 0; i < importantInfo.length; i++) {
            drop.put(importantInfo[i]);

            System.out.format("MESSAGE SENT: %s%n", importantInfo[i]);
        }
        drop.put(-1);
    }
}
