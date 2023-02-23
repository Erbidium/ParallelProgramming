package Task4;

import javax.swing.*;
import java.awt.*;

public class BounceFrame extends JFrame {
    private BallCanvas canvas;
    public static final int WIDTH = 450;
    public static final int HEIGHT = 350;
    public BounceFrame() {
        this.setSize(WIDTH, HEIGHT);
        this.setTitle("Bounce program");
        this.canvas = new BallCanvas();
        System.out.println("In Frame Thread name = " + Thread.currentThread().getName());
        Container content = this.getContentPane();
        content.add(this.canvas, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.lightGray);

        JButton buttonAddBallsPair = new JButton("Add pair");
        JButton buttonStop = new JButton("Stop");

        buttonAddBallsPair.addActionListener(e -> {
            Ball redBall = new Ball(canvas, Color.red);
            canvas.add(redBall);

            BallThread redBallThread = new BallThread(redBall);
            redBallThread.start();

            Ball blueBall = new Ball(canvas, Color.blue);
            canvas.add(blueBall);

            BallThreadWaiting thread = new BallThreadWaiting(blueBall, redBallThread);
            thread.start();
        });
        buttonStop.addActionListener(e -> System.exit(0));

        buttonPanel.add(buttonAddBallsPair);
        buttonPanel.add(buttonStop);

        content.add(buttonPanel, BorderLayout.SOUTH);
    }
}
