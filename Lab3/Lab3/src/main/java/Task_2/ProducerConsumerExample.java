package Task_2;

public class ProducerConsumerExample {

    public static void main(String[] args) {
        int bufferSize = 500;

        Drop drop = new Drop(bufferSize);

        var producerData = ArrayGenerator.GenerateConsecutiveArray(5000);

        var producerThread = new Thread(new Producer(drop, producerData));
        var consumerThread = new Thread(new Consumer(drop));

        producerThread.start();
        consumerThread.start();

        try {
            producerThread.join();
            consumerThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Produced count: " + drop.getProducedMessagesCount());
        System.out.println("Consumed count: " + drop.getConsumedMessagesCount());
    }
}
