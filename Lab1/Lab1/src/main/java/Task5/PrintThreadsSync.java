package Task5;

public class PrintThreadsSync {
    private boolean permission = true;
    private boolean stop = false;
    private int numberOfPrintedSymbols = 0;
    public synchronized void waitAndChange(char symbolToPrint, boolean controlValue)
    {
        while (controlValue != hasPermission())
        {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        if (stop)
        {
            return;
        }
        System.out.print(symbolToPrint);
        permission = !permission;
        numberOfPrintedSymbols++;
        if (numberOfPrintedSymbols % 100 == 0)
        {
            System.out.println();
        }
        if (numberOfPrintedSymbols == 100000)
        {
            stop = true;
        }
        notifyAll();
    }

    public synchronized boolean hasPermission()
    {
        return permission;
    }

    public synchronized boolean getStop()
    {
        return stop;
    }
}
