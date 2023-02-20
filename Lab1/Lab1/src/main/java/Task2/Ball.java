package Task2;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.Random;

import static java.awt.geom.Point2D.*;

class Ball {
    private BallCanvas canvas;
    private Color color;
    private static final int XSIZE = 20;
    private static final int YSIZE = 20;
    private int x = 0;
    private int y= 0;
    private int dx = 2;
    private int dy = 2;

    private static int borderSize = 3;


    public Ball
    (
            BallCanvas c,
            Color color,
            boolean fullRandomPosition
    ){
        this.canvas = c;
        this.color = color;

        if (fullRandomPosition)
        {
            x = new Random().nextInt(this.canvas.getWidth());
            y = new Random().nextInt(this.canvas.getHeight());
        }
        else
        {
            if(Math.random()<0.5){
                x = new Random().nextInt(this.canvas.getWidth());
                y = 0;
            }else{
                x = 0;
                y = new Random().nextInt(this.canvas.getHeight());
            }
        }
    }

    public void draw (Graphics2D g2){
        g2.setColor(Color.black);
        g2.fill(new Ellipse2D.Double
        (
            x - borderSize,
            y - borderSize,
            XSIZE + 2 * borderSize,
            YSIZE + 2 * borderSize
        ));
        g2.setColor(color);
        g2.fill(new Ellipse2D.Double(x,y,XSIZE,YSIZE));
    }

    public void move(){
        x+=dx;
        y+=dy;
        if(x<0){
            x = 0;
            dx = -dx;
        }
        if(x+XSIZE>=this.canvas.getWidth()){
            x = this.canvas.getWidth()-XSIZE;
            dx = -dx;
        }
        if(y<0){
            y=0;
            dy = -dy;
        }
        if(y+YSIZE>=this.canvas.getHeight()){
            y = this.canvas.getHeight()-YSIZE;
            dy = -dy;
        }
        this.canvas.repaint();
    }

    public int getCenterX()
    {
        return x + XSIZE;
    }

    public int getCenterY()
    {
        return y + YSIZE;
    }

    public float getRadius()
    {
        return (float)Math.max(YSIZE, XSIZE) / 2;
    }

    public boolean getIsInHole()
    {
        var holes = canvas.getHoles();
        for(Ball hole: holes)
        {
            if(intersect(hole, this))
            {
                canvas.remove(this);
                return true;
            }
        }
        return false;
    }

    private static boolean intersect(Ball ball1, Ball ball2)
    {
        return Point2D.distance
        (
            ball1.getCenterX(),
            ball1.getCenterY(),
            ball2.getCenterX(),
            ball2.getCenterY()
        ) <= ball1.getRadius() + ball2.getRadius();
    }
}
