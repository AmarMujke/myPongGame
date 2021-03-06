import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
public class Ball extends Rectangle{
    Random random;
    int xSpeed;
    int ySpeed;
    int initialBallSpeed = 2;
    Ball(int x, int y, int width, int height){
        super(x,y,width,height);
        random = new Random();
        int randomXDirection = random.nextInt(2);
        if(randomXDirection==0)
            randomXDirection--;
        setXDirection(randomXDirection*initialBallSpeed);
        int randomYDirection = random.nextInt(2);
        if(randomYDirection==0)
            randomYDirection--;
        setYDirection(randomYDirection*initialBallSpeed);

    }
    public void setXDirection(int randomXDirection){
        xSpeed = randomXDirection;
    }
    public void setYDirection(int randomYDirection){
        ySpeed = randomYDirection;
    }
    public void move(){
        x += xSpeed;
        y += ySpeed;
    }
    public void draw(Graphics g){
        g.setColor(Color.white);
        g.fillOval(x,y,height,width);
    }

}
