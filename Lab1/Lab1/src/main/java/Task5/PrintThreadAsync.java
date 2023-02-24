package Task5;

public class PrintThreadAsync extends Thread {
    private final char symbolToPrint;

    public PrintThreadAsync(char symbolToPrint){
        this.symbolToPrint = symbolToPrint;
    }
    @Override
    public void run(){
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++)
            {
                System.out.print(symbolToPrint);
            }
            System.out.println();
        }
    }
}
