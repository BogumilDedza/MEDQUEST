package entity;

import main.GamePanel;

public class NPC_Patient_3 extends Entity{
    public int dialogueCount = 0;

    public NPC_Patient_3(GamePanel gp) {
        super(gp);
        direction = "down";
        getNPCImage();
        collisionOn = true;
        getDialogue();
    }

    // Ustawiony obraz NPC 2
    public void getNPCImage() {
        down1 = setup("/npc/Pacjent_3");
    }

    //Dialog ustawiony dla npc 3
    public void getDialogue() {
        dialogues[0] = "Dzień dobry doktorze.";
        dialogues[1] = "Dzisiaj jechałem na rowerze.";
        dialogues[2] = "W trakcie jazdy upadłęm na drogę. ";
        dialogues[3] = "W tamtym momencie poczułem ból \n w nodzę. ";
        dialogues[4] = "Sądzę że może być złamana.";
        dialogues[5] = "Proszę o diagnozę.";
        dialogues[6] = null;
    }

    // Rozmowa z NPC
    public void speak() {
        if (dialogues[dialogueIndex] == null) {
            dialogueIndex = 0;
            dialogueCount++;

            // Pokazywanie gry z wyborem po zakończonym dialogu z npc
            if (dialogueCount >= 1) {
                gp.gameState = gp.minigameState;
                gp.currentMinigame=2;
                gp.treatmentMinigame3.show();
                dialogueCount = 0;
                return;
            }
        }
        gp.ui.currentDialoge = dialogues[dialogueIndex];
        dialogueIndex++;
    }
}
