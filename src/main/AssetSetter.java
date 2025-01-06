package main;

import entity.NPC_Patient_1;
import entity.NPC_Patient_2;
import entity.NPC_Patient_3;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp=gp;
    }

    public void setObject(){}

  //Ustawianie Pozycji Pacjentów
    public void setNPC(){
        //level 1 npc position
        gp.NPC_Patient2[0] = new NPC_Patient_2(gp);
        gp.NPC_Patient2[0].worldX = gp.tileSize * 22; // Different position
        gp.NPC_Patient2[0].worldY = gp.tileSize * 14;
        //level 2 npc position
        gp.NPC_Patient[0]= new NPC_Patient_1(gp);
        gp.NPC_Patient[0].worldX =gp.tileSize*22;
        gp.NPC_Patient[0].worldY =gp.tileSize*30;
        //level 3 npc position
        gp.NPC_Patient3[0] = new NPC_Patient_3(gp);
        gp.NPC_Patient3[0].worldX = gp.tileSize * 23; // Different position
        gp.NPC_Patient3[0].worldY = gp.tileSize * 40;
    }
}
