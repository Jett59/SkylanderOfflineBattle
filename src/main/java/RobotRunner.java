import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.MalformedURLException;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by lthompson on 6/09/15.
 */
public class RobotRunner {
    private static JFrame gameWindow;
    private static Graphics graphics;
    static int posX = 500;
    static int posY = 500;
    static int radius = 20;
    private static ImageIcon imageIcon;

    static int posRobotX = 100;
    static int posRobotY = 100;


    public static void main(String[] args) throws InterruptedException, MalformedURLException {
        AtomicBoolean game_is_running = new AtomicBoolean(true);

        gameWindow = new JFrame("Jett Block");
        gameWindow.setBounds(0, 0, 1024, 768);
        gameWindow.setVisible(true);
        gameWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        imageIcon = new ImageIcon(RobotRunner.class.getResource("pac-man.png"));

        Thread.sleep(500);

        graphics = gameWindow.getGraphics();
        graphics.setPaintMode();

        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, gameWindow.getWidth(), gameWindow.getHeight());

        drawNew();

        gameWindow.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {
            }

            public void keyPressed(KeyEvent e) {
                blackCurrent();
                switch (e.getKeyCode()) {
                    case 81: {
                        System.exit(0);
                        break;
                    }
                    case 37: {
                        if (e.isShiftDown()) {
                            radius--;
                        } else {
                            posX -= 5;
                        }
                        break;
                    }
                    case 38: {
                        posY -= 5;
                        break;
                    }
                    case 39: {
                        if (e.isShiftDown()) {
                            radius++;
                        } else {
                            posX += 5;
                        }
                        break;
                    }
                    case 40: {
                        posY += 5;
                        break;
                    }
                }
                drawNew();

            }

            public void keyReleased(KeyEvent e) {
            }
        });

    }

    private static void blackCurrent() {
        graphics.setColor(Color.BLACK);
        graphics.fillOval(posX, posY, imageIcon.getImage().getWidth(null), imageIcon.getImage().getHeight(null));
    }

    static void drawNew(){
        graphics.drawImage(imageIcon.getImage(), posX, posY, null);
    }
}
