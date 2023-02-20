package Task2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BounceFrame extends JFrame {
    private BallCanvas canvas;
    public static final int WIDTH = 450;
    public static final int HEIGHT = 350;
    static int holesCount = 5;
    public BounceFrame() {
        this.setSize(WIDTH, HEIGHT);
        this.setTitle("Bounce program");
        this.canvas = new BallCanvas();

        System.out.println("In Frame Thread name = " + Thread.currentThread().getName());

        Container content = this.getContentPane();
        content.add(this.canvas, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.lightGray);

        JButton buttonStart = new JButton("Start");
        JButton buttonStop = new JButton("Stop");

        JLabel labelCounter = new JLabel("In holes: 0");

        buttonStart.addActionListener(e -> {
            for(int i = 0; i < 100; i++)
            {
                Ball b = new Ball(canvas, Color.green, false);
                canvas.add(b);

                BallThread thread = new BallThread(b);
                thread.start();
                // System.out.println("Thread name = " + thread.getName());
            }

            // System.out.println("Thread name = " + thread.getName());
        });
        buttonStop.addActionListener(e -> System.exit(0));

        buttonPanel.add(buttonStart);
        buttonPanel.add(buttonStop);
        buttonPanel.add(labelCounter);

        content.add(buttonPanel, BorderLayout.SOUTH);
    }

    public void addHoles() {
        for (int i = 0; i < holesCount; i++) {
            canvas.addHole();
        }
    }
}
