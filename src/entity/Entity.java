package entity;

import main.GamePanel;
import main.Toolbox;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Entity {

    public GamePanel gp;

    public int worldX,worldY;
    public int speed;

    public BufferedImage up1,up2,up3,down1,down2,down3,left1,left2,left3,right1,right2,right3;
    public String direction;

    public int spriteCounter=0;
    public int spriteNum=1;

    public Rectangle solidPoint = new Rectangle( 0,0,48,48);
    public int solidAreaX =8,solidAreaY =16;
    public boolean collisionOn = false;

    public String dialogues[] = new String[20];
    public int dialogueIndex = 0;
    public Entity(GamePanel gp) {
        this.gp = gp;
    }

    //Rozmowa z NPC
    public void speak(){
        if(dialogues[dialogueIndex]==null){
            dialogueIndex = 0;
        }
        gp.ui.currentDialoge = dialogues[dialogueIndex];
        dialogueIndex++;
    };

    //Renderowanie NPC
    public void draw(Graphics2D g2) {

        BufferedImage image = down1; // Podstawowa pozycja npc

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;


        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }


 //Pobieranie Obrazu Postaci
    public BufferedImage setup(String imagePath) {
        Toolbox toolbox = new Toolbox();
        BufferedImage Image = null;

        try {
            Image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
            Image = toolbox.scaleImage(Image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Image;
    }
}
