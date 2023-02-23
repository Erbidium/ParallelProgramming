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

        JButton buttonStart = new JButton("Start");
        JButton buttonStop = new JButton("Stop");

        JEditorPane redBallsNumberEditorPane = new JEditorPane();
        JEditorPane blueBallsNumberEditorPane = new JEditorPane();

        blueBallsNumberEditorPane.setText("0");
        redBallsNumberEditorPane.setText("0");

        buttonStart.addActionListener(e -> {
            int redBallsNumber = Integer.parseInt(redBallsNumberEditorPane.getText());

            for(int i = 0; i < redBallsNumber; i++)
            {
                Ball b = new Ball(canvas, Color.red);
                canvas.add(b);

                BallThread thread = new BallThread(b);
                thread.setPriority(8);
                thread.start();
            }

            int blueBallsNumber = Integer.parseInt(blueBallsNumberEditorPane.getText());

            for(int i = 0; i < blueBallsNumber; i++)
            {
                Ball b = new Ball(canvas, Color.blue);
                canvas.add(b);

                BallThread thread = new BallThread(b);
                thread.setPriority(2);
                thread.start();
            }
        });
        buttonStop.addActionListener(e -> System.exit(0));

        buttonPanel.add(buttonStart);
        buttonPanel.add(buttonStop);
        buttonPanel.add(redBallsNumberEditorPane);
        buttonPanel.add(blueBallsNumberEditorPane);

        content.add(buttonPanel, BorderLayout.SOUTH);
    }
}
