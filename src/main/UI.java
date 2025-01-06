package main;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

public class UI {
GamePanel gp;
Graphics2D g2;
 Font MyFont;
public boolean messageOn = false;
public String message = "";
int messageCounter = 0;
public String currentDialoge ="";
public int commandNum =0;
public int TitleScreenState =0; // 0: first screen, 1 second screen,2 third screen
private int currentDiseasePage =0;
private final int TotalDiseasePages=3;// ilosc stron z chorobami


public UI(GamePanel gp){
    this.gp = gp;

    InputStream is = getClass().getResourceAsStream("/Font/IllusionBook-Regular.ttf");
    try {
        MyFont= Font.createFont(Font.TRUETYPE_FONT,is);
    } catch (FontFormatException e) {
        throw new RuntimeException(e);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }

}

public void showMessage(String text){
    message=text;
    messageOn=true;
}
public void draw(Graphics2D g2){
    this.g2 =g2;

    g2.setFont(MyFont);
    g2.setColor(Color.black);

    if(gp.gameState == gp.titleState){
       if(TitleScreenState == 0)
       {
           drawTitleScreen();
       }
       else if(TitleScreenState == 1)
       {
           drawGameInfoScreen();
       }
       else if(TitleScreenState == 2)
       {
           drawDiseaseInfoScreen();
       }


    }

    if(gp.gameState == gp.playState){
        //do play state stuff
    }
    if(gp.gameState == gp.pauseState){
    drawPause();
    }
    if(gp.gameState == gp.dialogueState){
        drawDialogueScreen();
    }
}
// mozesz skopiowac ta funkcje dla informacji do chorob //
public void drawGameInfoScreen() {
    g2.setColor(new Color(64, 64, 64));
    g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

    // Title
    g2.setFont(g2.getFont().deriveFont(Font.BOLD, 64F));
    String text = "Jak grać?";
    int x = getXforPause(text);
    int y = gp.tileSize * 2;
    drawTextWithShadow(text, x, y);

    // Game Information Text
    g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28F));
    y += gp.tileSize;

    text = "Sterowanie:";
    x = gp.tileSize;
    y += gp.tileSize;
    drawTextWithShadow(text, x, y);

    text = "W/S/A/D - Poruszanie się postacią";
    y += 40;
    drawTextWithShadow(text, x, y);

    text = "E - Interakcja z pacjentem";
    y += 40;
    drawTextWithShadow(text, x, y);

    text = "P - Pauza";
    y += 40;
    drawTextWithShadow(text, x, y);

    text = "ESCAPE - Powrót do menu głównego w trakcie gry";
    y += 40;
    drawTextWithShadow(text, x, y);

    text = "Cel gry:";
    y += gp.tileSize;
    drawTextWithShadow(text, x, y);

    text = "Diagnozuj i lecz pacjentów w szpitalu.";
    y += 40;
    drawTextWithShadow(text, x, y);

    text = "Rozmawiaj z pacjentami aby poznać ich dolegliwości.";
    y += 40;
    drawTextWithShadow(text, x, y);

    // Return Button
    g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));
    text = "Powrót do Menu Głównego";
    x = getXforPause(text);
    y = gp.screenHeight - gp.tileSize;
    drawTextWithShadow(text, x, y);

    if(commandNum == 0) {
        drawTextWithShadow(">", x - gp.tileSize, y);
    }
}

    // tutaj do zmiany informacje na temat chorob oraz nawigacja
    public void drawDiseaseInfoScreen() {
        g2.setColor(new Color(64, 64, 64));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        // Title
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 64F));
        String text = "Informacje o chorobach";
        int x = getXforPause(text);
        int y = gp.tileSize * 3/2;
        drawTextWithShadow(text, x, y);

        // Disease Information
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 22F));
        x = gp.tileSize;
        y += gp.tileSize * 2;

        switch(currentDiseasePage) {
            case 0:
                drawDiseaseOneWithShadow(x, y);
                break;
            case 1:
                drawDiseaseTwoWithShadow(x, y);
                break;
            case 2:
                drawDiseaseThreeWithShadow(x, y);
                break;
        }

        // Navigation Controls
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 24F));
        text = "<- A";
        drawTextWithShadow(text, gp.tileSize, gp.screenHeight - gp.tileSize * 2);

        text = "D ->";
        drawTextWithShadow(text, gp.screenWidth - gp.tileSize * 3, gp.screenHeight - gp.tileSize * 2);

        text = "Strona " + (currentDiseasePage + 1) + "/" + TotalDiseasePages;
        x = getXforPause(text);
        drawTextWithShadow(text, x, gp.screenHeight - gp.tileSize * 2);

        // Return Button
        text = "Powrót do Menu Głównego";
        x = getXforPause(text);
        y = gp.screenHeight - gp.tileSize;
        drawTextWithShadow(text, x, y);

        if(commandNum == 0) {
            drawTextWithShadow(">", x - gp.tileSize, y);
        }
    }




    // Updated disease information methods with shadows
    private void drawDiseaseOneWithShadow(int x, int y) {
        y-=50;
        x-=40;

        String[] lines = {
                "OPARZENIE to uszkodzenie tkanek, do którego dochodzi w wyniku",
                "działania energii - wysokiej temperatury,chemicznych lub prądu.",
                "Wyróżnia się trzy stopnie oparzeń (I, II, III),różniące się głębokością",
                "uszkodzeń.",
                "Jak postępować?",
                "1. Schładzaj miejsce letnią wodą przez 10–20 minut.",
                "2. Nałóż jałowy, luźny opatrunek.",
                "3. Unikaj stosowania tłuszczy i przebijania pęcherzy.",
                "4. Skontaktuj się z lekarzem w przypadku poważniejszych oparzeń II,III stopnia."
        };

        for (String line : lines) {
            drawTextWithShadow(line, x, y);
            y += 40;
        }
    }

    private void drawDiseaseTwoWithShadow(int x, int y) {
        y-=50;
        x-=40;

        String[] lines = {
                "Grypa to wirusowa choroba zakaźna układu oddechowego z objawami gorączki,",
                "kaszlu, bólu mięśni i osłabienia.",
                "Jak postępować?",
                "1. Odpoczywaj w domu i pij dużo płynów.",
                "2. Stosuj leki przeciwgorączkowe i objawowe.",
                "3. Unikaj kontaktu z innymi, aby nie zarażać.",
                "4. Skonsultuj się z lekarzem przy ciężkich objawach lub w grupie ryzyka."
        };

        for (String line : lines) {
            drawTextWithShadow(line, x, y);
            y += 40;
        }
    }

    private void drawDiseaseThreeWithShadow(int x, int y) {
        y-=50;
        x-=40;

        String[] lines = {
                "Złamanie to przerwanie ciągłości kości, spowodowane urazem mechanicznym.",
                "Jak postępować?",
                "1. Ocena stanu pacjenta. Zbadanie miejsca urazu, ocena objawów.",
                "2. Diagnostyka obrazowa.Wykonanie RTG w celu potwierdzenia złamania i",
                "określenia jego typu oraz dokłądnego miejsca.",
                "3. Stabilizacja.",
                "4. Zaopatrzenie rany w przypadku złamań otwartych. Oczyszczenie",
                "i zaopatrzenie rany.",
                "5. Plan leczenia i rehabilitacja. Określenie dalszego leczenia."
        };

        for (String line : lines) {
            drawTextWithShadow(line, x, y);
            y += 40;
        }
    }

    private void drawTextWithShadow(String text, int x, int y) {
        g2.setColor(Color.black);
        g2.drawString(text, x + 3, y + 3);
        g2.setColor(Color.white);
        g2.drawString(text, x, y);
    }
    public void drawTitleScreen(){

    if(TitleScreenState ==0)
    {
        g2.setColor(new Color(64, 64, 64));
        g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);
        //Title Name
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,96));
        String text = " Med-Quest";
        int x = getXforPause(text) - gp.tileSize/2 ;
        int y = gp.tileSize * 3;

        //shadow text
        g2.setColor(Color.black);
        g2.drawString(text,x+5,y+5);

        //main color
        g2.setColor(Color.white);
        g2.drawString(text,x,y);

        // draw doctor on screen
        x=gp.screenWidth/2 - (gp.tileSize*2)/2;
        y += gp.tileSize*2;
        y -= gp.tileSize;

        g2.drawImage(gp.player.down2,x,y,gp.tileSize*2,gp.tileSize * 2,null);
        //dodaj pacjentów


        //Menu
        g2.setFont(g2.getFont().deriveFont(Font.TRUETYPE_FONT,36f));

        text = "NOWA GRA";
        x = getXforPause(text) - gp.tileSize/2 ;
        y += gp.tileSize * 3;

        g2.setColor(Color.black);
        g2.drawString(text,x+3,y+3);

        //main color
        g2.setColor(Color.white);
        g2.drawString(text,x,y);

        if(commandNum == 0){
            g2.drawString(">",x-gp.tileSize,y);
        }

        text = "WZNÓW GRĘ";
        x = getXforPause(text) - gp.tileSize/2 ;
        y += gp.tileSize ;

        g2.setColor(Color.black);
        g2.drawString(text,x+3,y+3);

        //main color
        g2.setColor(Color.white);
        g2.drawString(text,x,y);

        if(commandNum == 1){
            g2.drawString(">",x-gp.tileSize,y);
        }



        text = "INFORMACJE O GRZE";
        x = getXforPause(text) - gp.tileSize/2 ;
        y += gp.tileSize ;

        g2.setColor(Color.black);
        g2.drawString(text,x+3,y+3);

        //main color
        g2.setColor(Color.white);
        g2.drawString(text,x,y);

        if(commandNum == 2){
            g2.drawString(">",x-gp.tileSize,y);
        }

        text = "INFORMACJE O CHOROBACH";
        x = getXforPause(text) - gp.tileSize/2 ;
        y += gp.tileSize ;

        g2.setColor(Color.black);
        g2.drawString(text,x+3,y+3);

        //main color
        g2.setColor(Color.white);
        g2.drawString(text,x,y);

        if(commandNum == 3){
            g2.drawString(">",x-gp.tileSize,y);
        }

        text = "WYJŚCIE";
        x = getXforPause(text) - gp.tileSize/2 ;
        y += gp.tileSize ;
        g2.setColor(Color.black);
        g2.drawString(text,x+3,y+3);

        //main color
        g2.setColor(Color.white);
        g2.drawString(text,x,y);

        if(commandNum == 4){
            g2.drawString(">",x-gp.tileSize,y);
        }
    }

}
    public void drawEndScreen(Graphics2D g2) {

        g2.setColor(new Color(64, 64, 64, 200));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);


        g2.setFont(MyFont.deriveFont(Font.PLAIN, 30F));
        g2.setColor(Color.WHITE);

        // Center the title
        String title = "Gratulacje!";
        int titleX = getCenteredX(g2, title);
        int titleY = gp.tileSize * 3;
        g2.drawString(title, titleX, titleY);

        // Center the message
        String message = "Ukończyłeś Grę!";
        int messageX = getCenteredX(g2, message);
        int messageY = titleY + gp.tileSize * 2;
        g2.drawString(message, messageX, messageY);

        // Center the footer button text
        String button = "Wykonana przez Bogumił Dędza 193610";
        int buttonX = getCenteredX(g2, button);
        int buttonY = gp.screenHeight - gp.tileSize * 2;
        g2.drawString(button, buttonX, buttonY);
    }



    private int getCenteredX(Graphics2D g2, String text) {
        FontMetrics metrics = g2.getFontMetrics();
        int textWidth = metrics.stringWidth(text);
        return (gp.screenWidth - textWidth) / 2;
    }


    public void drawDialogueScreen(){

    // okno dialogowe
    int x = gp.tileSize * 2;
    int y = gp.tileSize / 2;
    int width = gp.screenWidth - (gp.tileSize*4);
    int height = gp.tileSize * 4;

    drawSWindow(x,y,width,height);

    g2.setFont(g2.getFont().deriveFont(Font.PLAIN,30F));
    x += gp.tileSize;
    y +=(gp.tileSize)*(2);

    for(String line: currentDialoge.split("\n")){
        g2.drawString(line,x,y);
        y += 40;
    }


}
public void drawSWindow(int x,int y,int width,int height){
    Color c = new Color(0,0,0,220);
    g2.setColor(c);
    g2.fillRoundRect(x,y,width,height,40,40);

    c = new Color(255,255,255);
    g2.setColor(c);
    g2.setStroke(new BasicStroke(5));
    g2.drawRoundRect(x+5,y+5,width-10,height-10,30,30 );
}

public void drawPause(){
    g2.setFont(g2.getFont().deriveFont(Font.HANGING_BASELINE,80));
    String text = "Pause";
    int x = getXforPause(text);
    int y = gp.screenHeight/2;

    g2.drawString(text,x,y);
}

public int getXforPause(String text){
    int length = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
    int x = gp.screenWidth/2 - length/2;
    return x;
}

public void previousDiseasePage(){
    currentDiseasePage--;
    if(currentDiseasePage<0){
        currentDiseasePage =TotalDiseasePages-1;
    }
}

public void  nextDiseasePage(){
    currentDiseasePage++;
    if(currentDiseasePage >= TotalDiseasePages){
        currentDiseasePage = 0;
    }

    }

}
