package Task4;

public class BallThreadWaiting extends Thread {
    private Ball b;
    private BallThread ballToWait;

    public BallThreadWaiting(Ball ball, BallThread ballToWait){
        b = ball;
        this.ballToWait = ballToWait;
    }

    @Override
    public void run(){
        try {
            ballToWait.join();
            for(int i=1; i<100; i++){
                b.move();
                Thread.sleep(5);
            }
        }
        catch(InterruptedException ex){ }
    }
}