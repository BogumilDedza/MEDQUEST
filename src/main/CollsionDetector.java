package main;

import entity.Entity;

public class CollsionDetector {

    GamePanel gp;

    public CollsionDetector(GamePanel gp)
    {
    this.gp = gp;
    }

    public void checkTile(Entity entity){

        int entitysideLeftWorldX = entity.worldX + entity.solidPoint.x;
        int entitysideRightWorldX = entity.worldX + entity.solidPoint.x + entity.solidPoint.width;
        int entitysideUpWorldY = entity.worldY + entity.solidPoint.y;
        int entitysideDownWorldY = entity.worldY + entity.solidPoint.y+ entity.solidPoint.height;

        int entityLeftCol = entitysideLeftWorldX/gp.tileSize;
        int entityRightCol = entitysideRightWorldX/gp.tileSize;
        int entityTopRow = entitysideUpWorldY/gp.tileSize;
        int entityDownRow= entitysideDownWorldY/gp.tileSize;

        int tileNum1, tileNum2;

        switch(entity.direction){
            case "up":
                entityTopRow =(entitysideUpWorldY - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 =gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                if(gp.tileM.tiles[tileNum1].collision == true || gp.tileM.tiles[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityDownRow =(entitysideDownWorldY + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityDownRow];
                tileNum2 =gp.tileM.mapTileNum[entityRightCol][entityDownRow];
                if(gp.tileM.tiles[tileNum1].collision == true || gp.tileM.tiles[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol =(entitysideLeftWorldX - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 =gp.tileM.mapTileNum[entityLeftCol][entityDownRow];
                if(gp.tileM.tiles[tileNum1].collision == true || gp.tileM.tiles[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol =(entitysideUpWorldY + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 =gp.tileM.mapTileNum[entityRightCol][entityDownRow];
                if(gp.tileM.tiles[tileNum1].collision == true || gp.tileM.tiles[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
        }

    }
}
