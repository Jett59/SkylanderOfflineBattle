

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Arrays;
import java.util.Vector;
import java.util.prefs.Preferences;

/**
 * User: luke
 * Date: 7/05/13
 * Time: 6:50 AM
 */
public class EntryPoint {
    static Preferences prefs = Preferences.userNodeForPackage(EntryPoint.class);

    public static void main(String[] args) throws IOException {
        final JFrame battleWindow = new JFrame("Skylander Offline Battle!");
        battleWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        battleWindow.setSize(new Dimension(640, 480));
        battleWindow.setBackground(Color.BLACK);

        final Box verticalBox = Box.createVerticalBox();

        addPlayerList(battleWindow, verticalBox);

        battleWindow.add(verticalBox);

        battleWindow.setVisible(true);
    }

    private static void addPlayerList(final JFrame battleWindow, Box verticalBox) {

        final Vector<String> playerList = new Vector<String>(Arrays.asList(prefs.get("players", "").split(",")));

        final JList playerListComponent = new JList(playerList);

        playerListComponent.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {

            }

            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 8) {
                    final int selectedIndex = playerListComponent.getSelectedIndex();
                    playerListComponent.clearSelection();
                    playerList.removeElementAt(selectedIndex);
                    playerListComponent.revalidate();
                    savePlayerList(playerList);
                }
            }

            public void keyReleased(KeyEvent e) {

            }
        });

        final JScrollPane listPane = new JScrollPane(playerListComponent);
        verticalBox.add(listPane);

        final Box horizontalBox = Box.createHorizontalBox();
        JButton addPlayerButton = new JButton("Add Player");
        addPlayerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String result = JOptionPane.showInputDialog(
                        battleWindow,
                        "Enter player name",
                        "Create New Player",
                        JOptionPane.PLAIN_MESSAGE);

                playerList.add(result);
                playerListComponent.updateUI();

                savePlayerList(playerList);

            }
        });
        horizontalBox.add(addPlayerButton);
        JButton playButton = new JButton("Play!");
        playButton.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                BattleWindow Skylanders = new BattleWindow(playerList);
                Skylanders.display();
            }
        });
        horizontalBox.add(playButton);
        verticalBox.add(horizontalBox);
    }

    private static void savePlayerList(Vector<String> playerList) {
        StringBuilder players = new StringBuilder();
        for (String player : playerList) {
            if (players.length()>0) {
                players.append(",");
            }
            players.append(player);
        }
        prefs.put("players", players.toString());
    }
}
