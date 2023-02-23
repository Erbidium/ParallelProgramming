package Task4;

public class BallThread extends Thread {
    private Ball b;

    public BallThread(Ball ball){
        b = ball;
    }
    @Override
    public void run(){
        try {
            for(int i=1; i<100; i++){
                b.move();
                Thread.sleep(5);
            }
        }
        catch(InterruptedException ex){ }
    }
}
