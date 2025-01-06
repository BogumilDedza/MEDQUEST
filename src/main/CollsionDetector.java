package main;

import entity.Entity;

public class CollsionDetector {

    GamePanel gp;

    public CollsionDetector(GamePanel gp)
    {
    this.gp = gp;
    }

    //Rozpoznawanie Tile na Mapie
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
                entityRightCol =(entitysideRightWorldX + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 =gp.tileM.mapTileNum[entityRightCol][entityDownRow];
                if(gp.tileM.tiles[tileNum1].collision == true || gp.tileM.tiles[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
        }

    }
    // Sprawdzanie kolizji z npc
    public int checkEntity(Entity entity, Entity[] target) {
        int index = 999;

        for (int i = 0; i < target.length; i++) {
            if (target[i] != null) {

                int entityLeftWorldX = entity.worldX + entity.solidPoint.x;
                int entityRightWorldX = entity.worldX + entity.solidPoint.x + entity.solidPoint.width;
                int entityTopWorldY = entity.worldY + entity.solidPoint.y;
                int entityBottomWorldY = entity.worldY + entity.solidPoint.y + entity.solidPoint.height;


                int targetLeftWorldX = target[i].worldX + target[i].solidPoint.x;
                int targetRightWorldX = target[i].worldX + target[i].solidPoint.x + target[i].solidPoint.width;
                int targetTopWorldY = target[i].worldY + target[i].solidPoint.y;
                int targetBottomWorldY = target[i].worldY + target[i].solidPoint.y + target[i].solidPoint.height;


                int nextEntityLeftWorldX = entityLeftWorldX;
                int nextEntityRightWorldX = entityRightWorldX;
                int nextEntityTopWorldY = entityTopWorldY;
                int nextEntityBottomWorldY = entityBottomWorldY;

                switch(entity.direction) {
                    case "up": nextEntityTopWorldY -= entity.speed; break;
                    case "down": nextEntityBottomWorldY += entity.speed; break;
                    case "left": nextEntityLeftWorldX -= entity.speed; break;
                    case "right": nextEntityRightWorldX += entity.speed; break;
                }


                if (nextEntityRightWorldX > targetLeftWorldX &&
                        nextEntityLeftWorldX < targetRightWorldX &&
                        nextEntityBottomWorldY > targetTopWorldY &&
                        nextEntityTopWorldY < targetBottomWorldY) {
                    if (target[i].collisionOn) {
                        entity.collisionOn = true;
                        index = i;
                    }
                }
            }
        }
        return index;
    }
}
