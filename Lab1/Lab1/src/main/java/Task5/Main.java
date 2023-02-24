package Task5;

public class Main {
    public static void main(String[] args) {
        PrintThreadsSync printThreadsSync = new PrintThreadsSync();

        PrintThread firstThread = new PrintThread('-', printThreadsSync, true);
        PrintThread secondThread = new PrintThread('|', printThreadsSync, false);

        firstThread.start();
        secondThread.start();
     }
}
