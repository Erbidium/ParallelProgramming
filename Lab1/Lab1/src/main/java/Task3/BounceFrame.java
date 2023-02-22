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

        buttonRed.addActionListener(e -> {
            for(int i = 0; i < 100; i++)
            {
                Ball b = new Ball(canvas, Color.red);
                canvas.add(b);

                BallThread thread = new BallThread(b);
                thread.start();
            }
        });

        buttonBlue.addActionListener(e -> {
            for(int i = 0; i < 100; i++)
            {
                Ball b = new Ball(canvas, Color.blue);
                canvas.add(b);

                BallThread thread = new BallThread(b);
                thread.start();
            }
        });

        buttonStop.addActionListener(e -> System.exit(0));

        buttonPanel.add(buttonRed);
        buttonPanel.add(buttonBlue);
        buttonPanel.add(buttonStop);

        content.add(buttonPanel, BorderLayout.SOUTH);
    }
}
