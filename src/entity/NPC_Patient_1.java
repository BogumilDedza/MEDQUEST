package entity;

import main.GamePanel;

public class NPC_Patient_1 extends Entity{
    public int dialogueCount = 0;

    public NPC_Patient_1(GamePanel gp) {
        super(gp);
        direction = "down";
        getNPCImage();
        collisionOn = true;
        getDialogue();
    }
    // Ustawiony obraz NPC 2
    public void getNPCImage() {
        down1 = setup("/npc/Pacjent_2");
    }
    //Dialog ustawiony dla npc 2
    public void getDialogue() {
        dialogues[0] = "Dzień dobry doktorze.";
        dialogues[1] = "Od kilku dni mam silny ból głowy.";
        dialogues[2] = "Często też mam zawroty głowy.";
        dialogues[3] = "Proszę o diagnozę.";
        dialogues[4] = null;
    }

    // Rozmowa z NPC
    public void speak() {
        if (dialogues[dialogueIndex] == null) {
            dialogueIndex = 0;
            dialogueCount++;

                // Pokazywanie gry z wyborem po zakończonym dialogu z npc
            if (dialogueCount >= 1) {
                gp.gameState = gp.minigameState;
               gp.currentMinigame=1;
                gp.treatmentMinigame2.show();
                dialogueCount = 0;
                return;
            }
        }
        gp.ui.currentDialoge = dialogues[dialogueIndex];
        dialogueIndex++;
    }
}
