package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    GamePanel gp;
public boolean upPressed,downPressed,leftPressed,rightPressed,ePressed;

public KeyHandler(GamePanel gp){
    this.gp =gp;
}
    @Override
    public void keyTyped(KeyEvent e) {}


    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();


        if(gp.gameState == gp.titleState){
            if(gp.ui.TitleScreenState == 0) {
                // Main menu controls
                if(code == KeyEvent.VK_W){
                    gp.ui.commandNum--;
                    if(gp.ui.commandNum < 0){
                        gp.ui.commandNum = 4;
                    }
                }
                if(code == KeyEvent.VK_S){
                    gp.ui.commandNum++;
                    if(gp.ui.commandNum > 4){
                        gp.ui.commandNum = 0;
                    }
                }
                if(code == KeyEvent.VK_ENTER){
                    if(gp.ui.commandNum == 0){
                        gp.player.setDefaultValues();
                        gp.setupGame();
                        gp.gameState = gp.playState;
                    }
                    if(gp.ui.commandNum == 1){
                        gp.gameState = gp.playState;
                    }
                    if(gp.ui.commandNum == 2){
                        gp.ui.TitleScreenState = 1;
                        gp.ui.commandNum = 0;
                    }
                    if(gp.ui.commandNum == 3){
                        gp.ui.TitleScreenState = 2;
                        gp.ui.commandNum = 0;
                    }
                    if(gp.ui.commandNum == 4){
                        System.exit(0);
                    }
                }
            }
            else if(gp.ui.TitleScreenState == 1) {
                // Game info screen controls
                if(code == KeyEvent.VK_ENTER){
                    gp.ui.TitleScreenState = 0;
                    gp.ui.commandNum = 2; // Return to "INFORMACJE O GRZE" position
                }
            }
            else if(gp.ui.TitleScreenState == 2) {
                // Game info screen controls
                if(code == KeyEvent.VK_ENTER){
                    gp.ui.TitleScreenState = 0;
                    gp.ui.commandNum = 3; // Return to "INFORMACJE O CHOROBACH" position
                }
                if(code == KeyEvent.VK_A){
                    gp.ui.previousDiseasePage();

                }
                if(code == KeyEvent.VK_D){
                    gp.ui.nextDiseasePage();
                }
            }

        }
        //play state
        if(gp.gameState == gp.playState){
            if(code == KeyEvent.VK_W){
                upPressed =true;
            }
            if(code == KeyEvent.VK_S){
                downPressed =true;
            }
            if(code == KeyEvent.VK_A){
                leftPressed =true;
            }
            if(code == KeyEvent.VK_D){
                rightPressed = true;
            }
            if(code == KeyEvent.VK_P){
               gp.gameState = gp.pauseState;

            }
            if(code == KeyEvent.VK_E){
                ePressed = true;

            }
            if (code == KeyEvent.VK_ESCAPE) {
               gp.gameState=gp.titleState;
               gp.ui.TitleScreenState=0;
               gp.ui.commandNum=0;
            }
        }
        //pause state
        else if(gp.gameState == gp.pauseState){
            if(code == KeyEvent.VK_P){
                gp.gameState = gp.playState;

            }
            if (code == KeyEvent.VK_ESCAPE) {
                gp.gameState=gp.titleState;
                gp.ui.TitleScreenState=0;
                gp.ui.commandNum=0;
            }
        }
        else if (gp.gameState == gp.dialogueState){
            if(code == KeyEvent.VK_E){
                gp.gameState = gp.playState;

            }  if (code == KeyEvent.VK_ESCAPE) {
                gp.gameState=gp.titleState;
                gp.ui.TitleScreenState=0;
                gp.ui.commandNum=0;
            }
        }
        else if (gp.gameState == gp.minigameState){
            if(code == KeyEvent.VK_E){
                gp.gameState = gp.playState;

            }  if (code == KeyEvent.VK_ESCAPE) {
                gp.gameState=gp.titleState;
                gp.ui.TitleScreenState=0;
                gp.ui.commandNum=0;
            }
        }

        else if (gp.gameState == gp.endScreenState) {
            if (code == KeyEvent.VK_ENTER) {
                gp.gameState = gp.playState; // Return to the game map
            }
            if (code == KeyEvent.VK_ESCAPE) {
                gp.gameState = gp.titleState; // Return to main menu
            }
        }
        // dialogue state


    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W){
            upPressed = false;
        }
        if(code == KeyEvent.VK_S){
            downPressed = false;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = false;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = false;
        }
        if(code == KeyEvent.VK_E) {
            ePressed = false;
        }
    }
    private void returnToTitle() {
        gp.gameState = gp.titleState;
        gp.ui.TitleScreenState = 0;
        gp.ui.commandNum = 0;
        // Reset player position
        gp.player.setDefaultValues();
        // Reset other game states as needed
        gp.setupGame();
    }


}
