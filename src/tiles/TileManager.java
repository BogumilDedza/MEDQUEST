package tiles;

import main.GamePanel;
import main.Toolbox;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    GamePanel gp;
    public Tiles [] tiles;
     public int mapTileNum[][];

    public TileManager(GamePanel gp){
        this.gp=gp;
        tiles = new Tiles [20];                                                                                  
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap("/maps/map1.txt");
    }
//Wszystkie PNG wykorzystane do tile
    public void getTileImage(){


            setup(0,"mur",true);
            setup(1,"Trawa1",true);
            setup(2,"Trawa2",true);
            setup(3,"Trawa3",true);
            setup(4,"deska1",false);
            setup(5,"kafel1",false);
            setup(6,"deska4",false);
            setup(7,"kafel3",false);
            setup(8,"kafel2",false);
            setup(9,"deska3",false);

    }

    public void setup(int index,String imageName,boolean collision){
        Toolbox toolbox = new Toolbox();
        try{
            tiles[index] = new Tiles();
            tiles[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/"+ imageName + ".png"));
            tiles[index].image = toolbox.scaleImage(tiles[index].image,gp.tileSize,gp.tileSize);
            tiles[index].collision = collision;

        }catch(IOException e){
            e.printStackTrace();
        }

    }
    //Ładowanie mapy
    public void loadMap(String filePath){
        try{
            InputStream is =getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col =0;
            int row=0;

            while(col < gp.maxWorldCol && row < gp.maxWorldRow) {

                String line = br.readLine();

                while(col < gp.maxWorldCol){

                    String numbers[] = line.split(" ");

                    int number = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = number;
                    col++;
                }
                if(col == gp.maxWorldCol){
                    col =0;
                    row++;
                }
          }
       br.close();

        } catch (Exception e) {

        }


    }

    public void draw(Graphics2D g2){

       int worldCol = 0;
       int worldRow = 0;


      while(worldCol <gp.maxWorldCol && worldRow < gp.maxWorldRow){

          int tilesNum = mapTileNum[worldCol][worldRow];

          int worldX = worldCol * gp.tileSize;
          int worldY = worldRow * gp.tileSize;

          int screenX = worldX - gp.player.worldX + gp.player.screenX;
          int screenY = worldY - gp.player.worldY + gp.player.screenY;

          if(worldX + gp.tileSize> gp.player.worldX - gp.player.screenX && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX
          && worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY)

          {
              g2.drawImage(tiles[tilesNum].image,screenX,screenY,null);
          }

          worldCol++;

          if(worldCol == gp.maxWorldCol){
              worldCol = 0;
              worldRow++;

          }
      }
    }
}

