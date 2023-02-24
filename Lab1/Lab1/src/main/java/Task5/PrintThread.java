package Task5;

public class PrintThread extends Thread {
    private final char symbolToPrint;
    private final PrintThreadsSync printThreadsSync;
    private final boolean controlValue;

    public PrintThread
    (
        char symbolToPrint,
        PrintThreadsSync printThreadsSync,
        boolean controlValue
    ){
        this.symbolToPrint = symbolToPrint;
        this.printThreadsSync = printThreadsSync;
        this.controlValue = controlValue;
    }

    @Override
    public void run(){
        do {
            printThreadsSync.waitAndChange
            (
                symbolToPrint,
                controlValue
            );
        } while (!printThreadsSync.getStop());
    }
}
