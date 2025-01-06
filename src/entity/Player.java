package entity;

import main.GamePanel;
import main.KeyHandler;
import main.Toolbox;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;



public class Player extends Entity{

    KeyHandler keyH;

    public final int screenX;
    public final int screenY;


    public Player(GamePanel gp,KeyHandler keyH){

        super(gp);


        this.keyH=keyH;

        screenX =gp.screenWidth/2 - (gp.tileSize/2);
        screenY =gp.screenHeight/2 - (gp.tileSize/2);

        solidPoint = new Rectangle(8,12,32,32); // wykrywanie kolizji na postac nakladamy niewidzialny kwadrat ktory wykrywa nam czy postac zderzyła się z panelem.


    setDefaultValues();
    getPlayerImage();

    }

    //Pozycja Podstawowa NPC
    public void setDefaultValues(){
        worldX=gp.tileSize * 23;
        worldY=gp.tileSize * 21;
        speed=4;
        direction ="down";
    }

    // Sprity Gracza
    public void getPlayerImage(){
        up1=setup("/player/doc_u1");
        up2=setup("/player/doc_up2");
        up3=setup("/player/doc_up3");
        down1=setup("/player/doc_down1");
        down2=setup("/player/doc_down2");
        down3=setup("/player/doc_down3");
        left1=setup("/player/doc_left1");
        left2=setup("/player/doc_left2");
        left3=setup("/player/doc_left3");
        right1=setup("/player/doc_right1");
        right2=setup("/player/doc_right2");
        right3=setup("/player/doc_right3");
    }



    public void update()
    {
        if(keyH.upPressed|| keyH.downPressed|| keyH.leftPressed|| keyH.rightPressed){
            if(keyH.upPressed )
            {
                direction ="up";
            }
            else if (keyH.downPressed )
            {
                direction ="down";
            }
            else if (keyH.leftPressed )
            {
                direction ="left";
            }
            else if (keyH.rightPressed )
            {
                direction ="right";
            }

            // sprawdzenie kolizji gracza
            collisionOn = false;
            gp.collsionDetector.checkTile(this);

            // sprawdzanie kolizji z pacjentem

            int npcIndex =gp.collsionDetector.checkEntity(this,gp.NPC_Patient);
            TouchNpc(npcIndex);

            int npc1Index = gp.collsionDetector.checkEntity(this, gp.NPC_Patient2);
            TouchNpc1(npc1Index);

            int npc2Index = gp.collsionDetector.checkEntity(this, gp.NPC_Patient3);
            TouchNpc2(npc2Index);

            if(collisionOn == false){
                switch(direction){
                    case "up":
                        worldY -= speed;
                    break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;


                }
            }



            spriteCounter++;
            if(spriteCounter>10)
            {
                spriteNum++;
                if(spriteNum>3)
                {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        } else {
            spriteNum=2;
        }


    }

    //Wykrywanie kolizji z NPC w celu rozmowy
    public void TouchNpc1(int i) {
        if(i != 999) {
            if(gp.keyH.ePressed) {
                gp.gameState = gp.dialogueState;
                gp.NPC_Patient2[i].speak();
            }
        }
    }
    //Wykrywanie kolizji z NPC w celu rozmowy
    public void TouchNpc2(int i) {
        if(i != 999) {
            if(gp.keyH.ePressed) {
                gp.gameState = gp.dialogueState;
                gp.NPC_Patient3[i].speak();
            }
        }
    }
    //Wykrywanie kolizji z NPC w celu rozmowy
    public void TouchNpc(int i){
        if(i != 999){
            if(gp.keyH.ePressed ) {

                    gp.gameState = gp.dialogueState;
                    gp.NPC_Patient[i].speak();
                    gp.keyH.ePressed = false;
            }
        }

    }

    // Aktualizacja pozycji Gracza
    public void updatePosition() {



        if(worldX < screenX) {
            worldX = screenX;
        }


        if(worldX > gp.tileSize * gp.maxWorldCol - screenX) {
            worldX = gp.tileSize * gp.maxWorldCol - screenX;
        }


        if(worldY < screenY) {
            worldY = screenY;
        }


        if(worldY > gp.tileSize * gp.maxWorldRow - screenY) {
            worldY = gp.tileSize * gp.maxWorldRow - screenY;
        }
    }


//Renderowanie Gracza na Planszy
    public void draw(Graphics2D g2)
    {


        BufferedImage image =null;

        switch(direction){
            case "up":
                if(spriteNum==1)
                {
                    image=up1;
                }
                if(spriteNum==2)
                {
                    image=up2;
                }
                if(spriteNum==3)
                {
                    image=up3;
                }
                break;
            case "down":
                if(spriteNum==1)
                {
                    image=down1;
                }
                if(spriteNum==2)
                {
                    image=down2;
                }
                if(spriteNum==3)
                {
                    image=down3;
                }
                break;
            case "left":
                if(spriteNum==1)
                {
                    image=left1;
                }
                if(spriteNum==2)
                {
                    image=left2;
                }
                if(spriteNum==3)
                {
                    image=left3;
                }
                break;
            case "right":
                if(spriteNum==1)
                {
                    image=right1;
                }
                if(spriteNum==2)
                {
                    image=right2;
                }
                if(spriteNum==3)
                {
                    image=right3;
                }
                break;
        }
        g2.drawImage(image,screenX,screenY,null);
    }
}
