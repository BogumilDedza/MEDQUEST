package main;

import entity.Player;
import tiles.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    //

    final int originalTitleSize=16;//16x16 size of tile
    final int scale=3;

   public final int tileSize = originalTitleSize*scale;//size tile of 48 pixel
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize*maxScreenCol; //768 szerokosc
    public final int screenHeight = tileSize*maxScreenRow; // 576 wysokosc

    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth =tileSize * maxScreenCol;
    public final int worldHeight =tileSize * maxScreenRow;

    int FPS=60;

TileManager tileM = new TileManager(this);
KeyHandler keyH= new KeyHandler();
Thread gameThread;
public CollsionDetector collsionDetector = new CollsionDetector(this);
 public Player player = new Player(this,keyH);

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

        tileM.draw(g2);

        player.draw(g2);



        g2.dispose();
    }
}
