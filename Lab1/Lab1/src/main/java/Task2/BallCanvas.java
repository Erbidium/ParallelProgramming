package Task2;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class BallCanvas extends JPanel {
    public ArrayList<Ball> holes = new ArrayList<>();
    private CopyOnWriteArrayList<Ball> balls = new CopyOnWriteArrayList<>();

    public ArrayList<Ball> getHoles()
    {
        return holes;
    }

    public void add(Ball b){
        this.balls.add(b);
    }

    public synchronized void remove(Ball b)
    {
        this.balls.remove(b);
    }
    public void addHole()
    {
        var ball = new Ball(this, Color.black, true);
        holes.add(ball);
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        for (Ball b : holes) {
            b.draw(g2);
        }
        for (Ball b : balls) {
            b.draw(g2);
        }
    }
}
