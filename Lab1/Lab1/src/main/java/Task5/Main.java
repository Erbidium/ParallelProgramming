package Task5;

public class Main {
    public static void main(String[] args) {
        callAsyncThreads();
        //callSyncThreads();
    }

    public static void callAsyncThreads()
    {
        PrintThreadAsync firstThread = new PrintThreadAsync('-');
        PrintThreadAsync secondThread = new PrintThreadAsync('|');

        firstThread.start();
        secondThread.start();
    }

    public static void callSyncThreads()
    {
        PrintThreadsSync printThreadsSync = new PrintThreadsSync();

        PrintThread firstThread = new PrintThread('-', printThreadsSync, true);
        PrintThread secondThread = new PrintThread('|', printThreadsSync, false);

        firstThread.start();
        secondThread.start();
    }
}
