package tiles;

import main.GamePanel;
import javax.imageio.ImageIO;
import java.awt.*;
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

        tiles = new Tiles[10]; // How many tiles can I have in my game you can change later to add more tile to game.
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap("/maps/map2.txt");
    }

    public void getTileImage(){
        try{

            tiles[0]= new Tiles();
            tiles[0].image=ImageIO.read(getClass().getResourceAsStream("/tiles/K1.png"));
            tiles[0].collision = true;

            tiles[1]= new Tiles();
            tiles[1].image=ImageIO.read(getClass().getResourceAsStream("/tiles/K2.png"));

            tiles[2]= new Tiles();
            tiles[2].image=ImageIO.read(getClass().getResourceAsStream("/tiles/K3.png"));

            tiles[3]= new Tiles();
            tiles[3].image=ImageIO.read(getClass().getResourceAsStream("/tiles/K4.png"));

            tiles[4]= new Tiles();
            tiles[4].image=ImageIO.read(getClass().getResourceAsStream("/tiles/K5.png"));

            tiles[5]= new Tiles();
            tiles[5].image=ImageIO.read(getClass().getResourceAsStream("/tiles/D4.png"));

            tiles[6]= new Tiles();
            tiles[6].image=ImageIO.read(getClass().getResourceAsStream("/tiles/D5.png"));


        }catch(IOException e){
            e.printStackTrace();
        }
    }

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
              g2.drawImage(tiles[tilesNum].image,screenX,screenY,gp.tileSize,gp.tileSize,null);
          }

          worldCol++;


          if(worldCol == gp.maxWorldCol){
              worldCol = 0;
              worldRow++;

          }
      }
    }
}

