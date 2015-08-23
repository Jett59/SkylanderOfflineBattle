import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import javax.swing.*;
import java.io.IOException;
import java.util.Vector;

/**
 * Created by lthompson on 23/08/15.
 */
public class BattleWindow {
    private final Vector<String> playerList;
    private final String skylanders = readResource("skylanders.txt");
    private final JFrame window;

    public BattleWindow(Vector<String> playerList){
        this.playerList = playerList;
        this.window = new JFrame("Battle!");

        this.window.setSize(600, 400);

        setupBattleWindow();
    }

    private void setupBattleWindow() {
        window.add(new JList<String>(skylanders.split("\n")));
    }

    public void display(){
        window.setVisible(true);
    }

    private static String readResource(String resourceName) {
        try {
            return Resources.toString(Resources.getResource(resourceName), Charsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }


}
