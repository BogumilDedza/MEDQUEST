package main;

import entity.Entity;
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
public KeyHandler keyH= new KeyHandler(this);
Thread gameThread;

public CollsionDetector collsionDetector = new CollsionDetector(this);
public UI ui = new UI(this);
public AssetSetter ASetter = new AssetSetter(this);
//Player
 public Player player = new Player(this,keyH);
 public Entity NPC_Patient[] = new Entity[10];
    public Entity NPC_Patient2[] = new Entity[10];
    public Entity NPC_Patient3[] = new Entity[10];

 public int gameState;
 public final int titleState = 0;
 public final int playState = 1;
 public final int pauseState = 2;
public final int dialogueState = 3;
public final int minigameState = 4;
public final int diseaseInfoState =5;
public final int endScreenState = 6;
    public TreatmentMinigame treatmentMinigame;
    public TreatmentMinigame1 treatmentMinigame2;
    public TreatmentMinigame2 treatmentMinigame3;
    public int currentMinigame =0;
    //set player defalut seting
    int playerX =100;
    int playerY=100;
    int playerSpeed =4;
    public GamePanel(){
        this.setLayout( new BorderLayout());
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        treatmentMinigame = new TreatmentMinigame(this);
        treatmentMinigame2 = new TreatmentMinigame1(this);
        treatmentMinigame3 = new TreatmentMinigame2(this);
        setupGame();
    }
    public void setupGame(){
        ASetter.setNPC();
        gameState = titleState;
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
        if(gameState==playState){
            player.update();


        }
        if(gameState==pauseState){
        //nothing
        }


    }//do tego mam problem

    public void paintComponent(Graphics g){

        super.paintComponent(g);

        Graphics2D g2 =(Graphics2D)g;

        if(gameState == minigameState){
            if(currentMinigame == 0) {
                treatmentMinigame.draw(g2);
            }
             if  (currentMinigame == 1){
                treatmentMinigame2.draw(g2);
            }
             if (currentMinigame == 2){
                treatmentMinigame3.draw(g2);
            }
        }
        //title screen
        else if (gameState == titleState){

            ui.draw(g2);

        }
        else if (gameState == endScreenState) {

            ui.drawEndScreen(g2);
        }

        else{

            tileM.draw(g2);


            for (int i = 0; i < NPC_Patient.length; i++) {
                if(NPC_Patient[i] != null) {
                    NPC_Patient[i].draw(g2);
                }
            }
            for (int i = 0; i < NPC_Patient2.length; i++) {
                if(NPC_Patient2[i] != null) {
                    NPC_Patient2[i].draw(g2);
                }
            }
            for (int i = 0; i < NPC_Patient3.length; i++) {
                if(NPC_Patient3[i] != null) {
                    NPC_Patient3[i].draw(g2);
                }
            }

            player.draw(g2);

            //rysowanie ui
            ui.draw(g2);
        }




        g2.dispose();
    }
}
