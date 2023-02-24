package Task5;

public class Main {
    public static void main(String[] args) {
        PrintThread firstThread = new PrintThread('-');
        PrintThread secondThread = new PrintThread('|');

        firstThread.start();
        secondThread.start();
    }
}
