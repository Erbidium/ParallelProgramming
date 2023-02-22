package Task2;

public class BallThread extends Thread {
    private Ball b;
    private BounceFrame bounceFrame;

    public BallThread
    (
            Ball ball,
            BounceFrame bounceFrame
    ){
        b = ball;
        this.bounceFrame = bounceFrame;
    }
    @Override
    public void run(){
        try {
            for(int i=1; i<10000; i++){
                b.move();
                if (b.getIsInHole())
                {
                    bounceFrame.incrementBallsInHoles();
                    break;
                }
                // System.out.println("Thread name = " + Thread.currentThread().getName());
                Thread.sleep(5);
            }
        }
        catch(InterruptedException ex){ }
    }
}
