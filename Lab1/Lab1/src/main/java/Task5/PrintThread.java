package Task5;

public class PrintThread extends Thread {
    private char symbolToPrint;

    public PrintThread(char symbolToPrint){
        this.symbolToPrint = symbolToPrint;
    }

    @Override
    public void run(){
        for(int i = 0; i < 1000; i++)
        {
            for(int j = 0; j < 100; j++)
            {
                System.out.print(symbolToPrint);
            }
            System.out.println();
        }
    }
}
