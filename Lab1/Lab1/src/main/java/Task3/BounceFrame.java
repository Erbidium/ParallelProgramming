package Task3;

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
        JButton buttonRed = new JButton("Red");
        JButton buttonBlue = new JButton("Blue");
        JButton buttonStop = new JButton("Stop");

        JPanel experimentsPanel = new JPanel();
        experimentsPanel.setBackground(Color.lightGray);
        JButton experiment1 = new JButton(("1R 1B"));
        JButton experiment2 = new JButton(("1R 10B"));
        JButton experiment3 = new JButton(("1R 100B"));
        JButton experiment4 = new JButton(("1R 1000B"));
        JButton experiment5 = new JButton(("1R 3000B"));

        buttonRed.addActionListener(e -> {
            for(int i = 0; i < 100; i++)
            {
                Ball b = new Ball(canvas, Color.red);
                canvas.add(b);

                BallThread thread = new BallThread(b);
                thread.setPriority(8);
                thread.start();
            }
        });

        buttonBlue.addActionListener(e -> {
            for(int i = 0; i < 100; i++)
            {
                Ball b = new Ball(canvas, Color.blue);
                canvas.add(b);

                BallThread thread = new BallThread(b);
                thread.setPriority(4);
                thread.start();
            }
        });

        buttonStop.addActionListener(e -> System.exit(0));

        experiment1.addActionListener(e -> {
            Ball redBall = new Ball(canvas, Color.red);
            canvas.add(redBall);

            BallThread redBallThread = new BallThread(redBall);
            redBallThread.setPriority(8);
            redBallThread.start();

            Ball blueBall = new Ball(canvas, Color.blue);
            canvas.add(blueBall);

            BallThread blueBallThread = new BallThread(redBall);
            blueBallThread.setPriority(4);
            blueBallThread.start();
        });

        buttonPanel.add(buttonRed);
        buttonPanel.add(buttonBlue);
        buttonPanel.add(buttonStop);

        experimentsPanel.add(experiment1);
        experimentsPanel.add(experiment2);
        experimentsPanel.add(experiment3);
        experimentsPanel.add(experiment4);
        experimentsPanel.add(experiment5);

        content.add(buttonPanel, BorderLayout.SOUTH);
        content.add(experimentsPanel, BorderLayout.NORTH);
    }
}
