package org.example;

import java.util.ArrayList;

public class BallThread extends Thread {
    private ArrayList<Ball> balls;

    private BallCanvas canvas;

    public BallThread(ArrayList<Ball> balls, BallCanvas canvas){
        this.balls = balls;
        this.canvas = canvas;
    }
    @Override
    public void run(){
        try {
            for(int j=1; j<10000; j++){
                for (Ball ball : balls) {
                    ball.move();
                }
                canvas.repaint();
                Thread.sleep(5);
            }
        }
        catch(InterruptedException ex){
            System.out.println("interrupted");
        }
    }
}
