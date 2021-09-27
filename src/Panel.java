import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
public class Panel extends JPanel implements Runnable{
    static final int GAME_WIDTH = 1000;
    static final int GAME_HEIGHT = (int)(GAME_WIDTH*(0.5555));
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
    static final int BALL_SIZE = 20;
    static final int PADDLE_HEIGHT = 100;
    static final int PADDLE_WIDTH = 25;
    Thread gameThread;
    Image image;
    Graphics graphics;
    Random random;
    Paddle paddle1;
    Paddle paddle2;
    Ball ball;
    Score score;


    Panel(){
        newPaddles();
        newBall();
        score = new Score(GAME_WIDTH,GAME_HEIGHT);
        this.setFocusable(true);
        this.addKeyListener(new ActionListener());
        this.setPreferredSize(SCREEN_SIZE);

        gameThread = new Thread(this);
        gameThread.start();
    }
    public void newBall(){
        //random = new Random();
        ball = new Ball((GAME_WIDTH/2)-(BALL_SIZE/2),(GAME_WIDTH/2)-(BALL_SIZE/2) , BALL_SIZE,BALL_SIZE);
    }
    public void newPaddles(){
        paddle1 = new Paddle(0,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2), PADDLE_WIDTH, PADDLE_HEIGHT, 1);
        paddle2 = new Paddle(GAME_WIDTH-PADDLE_WIDTH, (GAME_HEIGHT/2)-(PADDLE_HEIGHT/2), PADDLE_WIDTH, PADDLE_HEIGHT, 2);

    }
    public void paint(Graphics g){
        image = createImage(getWidth(),getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image, 0,0,this);
    }
    public void draw(Graphics g){
        paddle1.draw(g);
        paddle2.draw(g);
        ball.draw(g);
        score.draw(g);
    }
    public void move(){
        paddle1.move();
        paddle2.move();
        ball.move();
    }
    public void checkCollision(){
        //one paddle collison with ball
        if(ball.intersects(paddle1)){
            ball.xSpeed = Math.abs(ball.xSpeed);
            ball.xSpeed++; // more difficulty
            if (ball.ySpeed>0)
                ball.ySpeed++;
            else
                ball.ySpeed--;
            ball.setXDirection(ball.xSpeed);
            ball.setYDirection(ball.ySpeed);
        }
        // other paddle collision with ball
        if(ball.intersects(paddle2)){
            ball.xSpeed = Math.abs(ball.xSpeed);
            ball.xSpeed++; // more difficulty
            if (ball.ySpeed>0)
                ball.ySpeed++;
            else
                ball.ySpeed--;
            ball.setXDirection(-ball.xSpeed);
            ball.setYDirection(ball.ySpeed);
        }

        if (ball.y <= 0){
            ball.setYDirection(-ball.ySpeed);
        }
        if (ball.y>= GAME_HEIGHT-BALL_SIZE){
            ball.setYDirection(-ball.ySpeed);
        }

        if(paddle1.y<=0)
            paddle1.y=0;
        if(paddle1.y >= (GAME_HEIGHT-PADDLE_HEIGHT))
            paddle1.y=GAME_HEIGHT-PADDLE_HEIGHT;
        if(paddle2.y<=0)
            paddle2.y=0;
        if(paddle2.y >= (GAME_HEIGHT-PADDLE_HEIGHT))
            paddle2.y=GAME_HEIGHT-PADDLE_HEIGHT;

        // score, new paddles and ball...
        if (ball.x<=0){
            score.player2++;
            newPaddles();
            newBall();
        }
        if (ball.x >= GAME_WIDTH-BALL_SIZE){
            score.player1++;
            newPaddles();
            newBall();
        }
    }
    public void run(){
        long lastTime = System.nanoTime();
        double ticks = 60.0;
        double ns = 1000000000/ticks;
        double delta = 0;
        while(true){
            long now = System.nanoTime();
            delta += (now - lastTime)/ns;
            lastTime = now;
            if(delta>=1){
                move();
                checkCollision();
                repaint();
                delta--;
            }
        }
    }
    public class ActionListener extends KeyAdapter{
        public void keyPressed(KeyEvent e){
        paddle1.keyPressed(e);
        paddle2.keyPressed(e);
        }
        public void keyReleased(KeyEvent e){
            paddle1.keyReleased(e);
            paddle2.keyReleased(e);
        }
    }
}
