package main;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

public class TreatmentMinigame1 {
    private GamePanel gp;
    private Font font;
    private Phase phase = Phase.ONE;
    private Timer timer;
    private int timeLeft = 20;
    private int sequenceStep = 0;
    private String currentDialogue = "Poprawnie zdiagnozuj pacjenta";
    private String grade = "";
    private static final int BUTTON_WIDTH = 600;
    private static final int BUTTON_HEIGHT = 40;
    private Rectangle[] buttonBounds = new Rectangle[4];// ilość gózików
    private Rectangle restartButtonBounds;
    private boolean showRestartButton = false;
    private String[] buttonLabels = {"Migrena", "Złamanie", "Grypa","Zatrucie"};
    private boolean mousePressed = false;
    private Point mousePosition = new Point(0, 0);

    private enum Phase { ONE, TWO }

    public TreatmentMinigame1(GamePanel gp) {
        this.gp = gp;
        this.font = font;
        initializeBounds();
        setupMouseListener();
    }

    private void initializeBounds() {
        int startY = gp.screenHeight / 2;
        int spacing = 50;

        for (int i = 0; i < 4; i++) {
            int x = (gp.screenWidth - BUTTON_WIDTH) / 2;
            int y = startY + (spacing * i);
            buttonBounds[i] = new Rectangle(x, y, BUTTON_WIDTH, BUTTON_HEIGHT);
        }

        restartButtonBounds = new Rectangle(
                (gp.screenWidth - BUTTON_WIDTH) / 2,
                startY + (spacing * 4),
                BUTTON_WIDTH,
                BUTTON_HEIGHT
        );
    }

    private void setupMouseListener() {
        gp.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (gp.gameState == gp.minigameState) {
                    mousePressed = true;
                    mousePosition.setLocation(e.getX(), e.getY());
                    handleMouseClick();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                mousePressed = false;
            }
        });

        gp.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                if (gp.gameState == gp.minigameState) {
                    mousePosition.setLocation(e.getX(), e.getY());
                    gp.repaint();
                }
            }
        });
    }

    private void handleMouseClick() {
        if (showRestartButton && restartButtonBounds.contains(mousePosition)) {
            returnToGame();
            return;
        }

        if (!showRestartButton) {
            for (int i = 0; i < buttonBounds.length; i++) {
                if (buttonBounds[i].contains(mousePosition)) {
                    handleChoice(i);
                    break;
                }
            }
        }
    }

    private void handleChoice(int choice) {
        if (phase == Phase.ONE) {
            if (choice == 2) {
                startPhaseTwo();
            } else {
                currentDialogue = "Spróbuj ponownie!!";
                showRestartButton = true;
            }
        } else {
            handleSequence(buttonLabels[choice]);
        }
        gp.repaint();
    }

    private void startPhaseTwo() {
        phase = Phase.TWO;
        sequenceStep = 0;
        timeLeft = 20;
        buttonLabels = new String[]{"W przypadku poważniejszych objaw skontaktuj się z lekarzem", "Stosuj leki pomagające zwalczyć grypę", "Unikaj kontaktu z innymi, aby nie zarażać","Odpoczywaj oraz pij dużo wody w domu",}; // ilość opci do klikniecia

        updatePhaseDialog();
        startTimer();
    }

    private void updatePhaseDialog(){
        if(phase == Phase.TWO){
            currentDialogue = "Wybierz w poprawnej kolejności\nPozostały czas: "+ timeLeft + " s";
        }
    }

    private void handleSequence(String choice) {
        String[] correct = { "Odpoczywaj oraz pij dużo wody w domu", "Stosuj leki pomagające zwalczyć grypę", "Unikaj kontaktu z innymi, aby nie zarażać","W przypadku poważniejszych objaw skontaktuj się z lekarzem"};
        if (choice.equals(correct[sequenceStep])) {
            sequenceStep++;
            if (sequenceStep == correct.length) {
                stopTimer();
                grade = getGrade();
                currentDialogue = "Gratulacje Ukończyłeś Poziom 1 \nOcena: " + grade;
                showRestartButton = true;
            }else{
                updatePhaseDialog();
            }
        } else {
            stopTimer();
            sequenceStep = 0;
            currentDialogue = "Zła odpowiedź! Spróbuj ponownie!\nPozostały czas: " + timeLeft + "s";
            showRestartButton = true;
        }
    }

    private void startTimer() {
        stopTimer();
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                timeLeft--;
                if (timeLeft <= 0) {
                    stopTimer();
                    currentDialogue = "Koniec czasu!";
                    showRestartButton = true;
                }else{
                    updatePhaseDialog();
                }
                gp.repaint();
            }
        }, 1000, 1000);
    }

    private void stopTimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    private String getGrade() {
        if (timeLeft >= 16) return "A";
        if (timeLeft >= 12) return "B";
        if (timeLeft >= 8) return "C";
        if (timeLeft >= 4) return "D";
        return "E";
    }

    private void drawButton(Graphics2D g2, Rectangle bounds, String label, boolean isHovered) {
        // Draw button background
        g2.setColor(isHovered ? new Color(30, 136, 229) : new Color(0,0,0));
        g2.fill(bounds);

        // Draw button border
        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(2));
        g2.draw(bounds);

        // Draw button text
        g2.setFont(gp.ui.MyFont.deriveFont(Font.PLAIN,20));
        FontMetrics fm = g2.getFontMetrics();
        int textX = bounds.x + (bounds.width - fm.stringWidth(label))/2;
        int textY = bounds.y + (bounds.height - fm.getHeight())  + fm.getAscent();
        g2.drawString(label, textX, textY);
    }

    private void drawDialogueWindow(Graphics2D g2) {
        int width = gp.screenWidth - (32 * 4);
        int height = 32 * 6;
        int x = (gp.screenWidth - width) / 2;
        int y = 32 ;

        // Draw dialogue box
        g2.setColor(new Color(0, 0, 0, 220));
        g2.fillRoundRect(x, y, width, height, 15, 15);
        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(x, y, width, height, 15, 15);

        // Draw text
        g2.setFont(gp.ui.MyFont.deriveFont(Font.PLAIN,30));
        FontMetrics fm = g2.getFontMetrics();
        String[] lines = currentDialogue.split("\n");
        int lineHeight = fm.getHeight();
        int textY = y + (height - (lines.length * lineHeight)) / 2 + fm.getAscent();

        for (String line : lines) {
            int textX = x + (width - fm.stringWidth(line)) / 2;
            g2.drawString(line, textX, textY);
            textY += lineHeight*2;
        }
    }

    public void draw(Graphics2D g2) {

        g2.setColor(new Color(64, 64, 64, 200));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);


        drawDialogueWindow(g2);


        for (int i = 0; i < buttonBounds.length; i++) {
            boolean isHovered = buttonBounds[i].contains(mousePosition);
            drawButton(g2, buttonBounds[i], buttonLabels[i], isHovered);
        }

        // Draw restart button if needed
        if (showRestartButton) {
            boolean isHovered = restartButtonBounds.contains(mousePosition);
            drawButton(g2, restartButtonBounds, "Powrót do mapy", isHovered);
        }
    }

    public void show() {
        resetGame();
    }

    private void resetGame() {
        phase = Phase.ONE;
        sequenceStep = 0;
        timeLeft = 20;
        currentDialogue = "Poprawnie zdiagnozuj pacjenta";
        grade = "";
        showRestartButton = false;
        buttonLabels = new String[]{"Migrena", "Złamanie", "Grypa","Zatrucie"};
        stopTimer();
    }

    private void returnToGame() {
        resetGame();
        gp.gameState = gp.playState;
        gp.repaint();
    }
}