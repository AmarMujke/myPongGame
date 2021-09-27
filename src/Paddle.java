import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
public class Paddle extends Rectangle{
    int id;
    int ySpeed;
    int moveSpeed = 10;

    Paddle(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int id){
        super(x,y,PADDLE_WIDTH,PADDLE_HEIGHT);
        this.id = id;
    }
        public void keyPressed(KeyEvent e){
            switch (id){
                case 1:
                    if(e.getKeyCode()==KeyEvent.VK_W){
                        setYDirection(-moveSpeed);

                    } if(e.getKeyCode()==KeyEvent.VK_S){
                    setYDirection(moveSpeed);

                } break;
                    case 2:
                    if(e.getKeyCode()==KeyEvent.VK_UP){
                        setYDirection(-moveSpeed);

                    } if(e.getKeyCode()==KeyEvent.VK_DOWN){
                        setYDirection(moveSpeed);

                    }
                break;
            }
        }
        public void keyReleased(KeyEvent e){
            switch (id){
                case 1:
                    if(e.getKeyCode()==KeyEvent.VK_W){
                        setYDirection(0);

                    } if(e.getKeyCode()==KeyEvent.VK_S){
                    setYDirection(0);

                } break;
                case 2:
                    if(e.getKeyCode()==KeyEvent.VK_UP){
                        setYDirection(0);

                    } if(e.getKeyCode()==KeyEvent.VK_DOWN){
                    setYDirection(0);

                }
                    break;
            }
        }
        public void setYDirection(int yDirection){
            ySpeed = yDirection;
        }
        public void move(){
            y=y+ySpeed;
        }
        public void draw(Graphics g){
            if(id == 1)
                g.setColor(Color.blue);
            else
                g.setColor(Color.red);
            g.fillRect(x,y,width,height);
        }
}

