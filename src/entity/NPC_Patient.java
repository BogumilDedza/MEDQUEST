package entity;

import main.GamePanel;
import main.Toolbox;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;




public class NPC_Patient extends Entity {

    public int dialogueCount = 0;

    public NPC_Patient(GamePanel gp){

        super(gp);
        direction ="down";
        getNPCImage();
        collisionOn = true;
        getDialogue();
    }

    // Ustawiony obraz NPC 1
    public void getNPCImage(){
        down1=setup("/npc/Pacjent_1");
    }

    //Dialog ustawiony dla npc 1
     public void getDialogue(){
        dialogues[0]= " Cześć potrzebuje pomocy. ";
         dialogues[1]= " Podczas pieczenia ciasta \n dotknałem metalowej blachy. ";
         dialogues[2] = " Teraz starsznie boli mnie ręka. ";
         dialogues[3] = " Proszę o diagnozę. ";
         dialogues[4] = null;

     }

    // Rozmowa z NPC
     public void speak(){
         if (dialogues[dialogueIndex] == null) {
             dialogueIndex = 0;
             dialogueCount++;

             // Pokazywanie gry z wyborem po zakończonym dialogu z npc
             if (dialogueCount >= 1) { // Adjust number as needed
                 gp.gameState = gp.minigameState;
                 gp.currentMinigame=0;
                 gp.treatmentMinigame.show();
                 dialogueCount = 0;
                 return;
             }
         }
         gp.ui.currentDialoge = dialogues[dialogueIndex];
         dialogueIndex++;

     }
}
