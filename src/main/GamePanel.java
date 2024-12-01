package main;

import entity.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    //

    final int originalTitleSize=16;//16x16 size of tile
    final int scale=3;

   public final int tileSize = originalTitleSize*scale;//size tile of 48 pixel
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize*maxScreenCol; //768 szerokosc
    final int screenHeight = tileSize*maxScreenRow; // 576 wysokosc

    int FPS=60;

KeyHandler keyH= new KeyHandler();
Thread gameThread;
Player player = new Player(this,keyH);

    //set player defalut seting
    int playerX =100;
    int playerY=100;
    int playerSpeed =4;
    public GamePanel(){

        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
this.addKeyListener(keyH);
this.setFocusable(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval =1000000000/FPS;
                double delta=0;
                long lastTime =System.nanoTime();
                long currentTime;
        while(gameThread !=null){

            currentTime = System.nanoTime();

            delta += (currentTime-lastTime)/drawInterval;

            lastTime= currentTime;

            if(delta >=1){
                update();
                repaint();
                delta--;
            }

        }

    }

    public void update (){
        player.update();

    }
    public void paintComponent(Graphics g){

        super.paintComponent(g);

        Graphics2D g2 =(Graphics2D)g;

player.draw(g2);

        g2.dispose();
    }
}
