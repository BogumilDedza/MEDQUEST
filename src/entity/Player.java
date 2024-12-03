package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;



public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp,KeyHandler keyH){
this.gp=gp;
this.keyH=keyH;

setDefaultValues();
getPlayerImage();

    }

    public void setDefaultValues(){
        x=100;
        y=100;
        speed=4;
        direction ="down";
    }

    public void getPlayerImage(){
        try{
          up1=ImageIO.read(getClass().getResourceAsStream("/player/doc_u1.png"));
          up2=ImageIO.read(getClass().getResourceAsStream("/player/doc_up2.png"));
          up3=ImageIO.read(getClass().getResourceAsStream("/player/doc_up3.png"));
            down1=ImageIO.read(getClass().getResourceAsStream("/player/doc_down1.png"));
            down2=ImageIO.read(getClass().getResourceAsStream("/player/doc_down2.png"));
            down3=ImageIO.read(getClass().getResourceAsStream("/player/doc_down3.png"));
            left1=ImageIO.read(getClass().getResourceAsStream("/player/doc_left1.png"));
            left2=ImageIO.read(getClass().getResourceAsStream("/player/doc_left2.png"));
            left3=ImageIO.read(getClass().getResourceAsStream("/player/doc_left3.png"));
            right1=ImageIO.read(getClass().getResourceAsStream("/player/doc_right1.png"));
            right2=ImageIO.read(getClass().getResourceAsStream("/player/doc_right2.png"));
            right3=ImageIO.read(getClass().getResourceAsStream("/player/doc_right3.png"));

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void update()
    {
        if(keyH.upPressed|| keyH.downPressed|| keyH.leftPressed|| keyH.rightPressed){
            if(keyH.upPressed )
            {
                direction ="up";
                y -= speed;
            } else if (keyH.downPressed )
            {
                direction ="down";
                y += speed;
            } else if (keyH.leftPressed )
            {
                direction ="left";
                x -= speed;
            }else if (keyH.rightPressed )
            {
                direction ="right";
                x += speed;
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
        g2.drawImage(image,x,y,gp.tileSize,gp.tileSize,null);
    }
}
