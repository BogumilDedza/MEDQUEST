package main;

import entity.NPC_Patient;
import entity.NPC_Patient_1;
import entity.NPC_Patient_2;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp=gp;
    }

    public void setObject(){


    }
  //Ustawianie Pozycji Pacjentów
    public void setNPC(){
        gp.NPC_Patient[0]= new NPC_Patient(gp);
        gp.NPC_Patient[0].worldX =gp.tileSize*21;
        gp.NPC_Patient[0].worldY =gp.tileSize*21;

        gp.NPC_Patient2[0] = new NPC_Patient_1(gp);
        gp.NPC_Patient2[0].worldX = gp.tileSize * 23; // Different position
        gp.NPC_Patient2[0].worldY = gp.tileSize * 23;

        gp.NPC_Patient3[0] = new NPC_Patient_2(gp);
        gp.NPC_Patient3[0].worldX = gp.tileSize * 27; // Different position
        gp.NPC_Patient3[0].worldY = gp.tileSize * 27;
    }
}
