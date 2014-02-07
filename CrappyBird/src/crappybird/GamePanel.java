package crappybird;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import static javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

/**
 * JPanel to display a Game.
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public class GamePanel extends JPanel implements Runnable {

    /**
     * Game using the Game Panel.
     */
    private Game game;

    @Override
    public void run() {
        final char[] keyChars = {' ', 'w'};
        final String[] keyStrings = {"UP"};

        setupKeyActions("moveUp", -2, keyChars, keyStrings);

        game = new Game(this);
        game.start();
        try {
            if (game.getThread().isAlive()) {
                game.getThread().join();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(MainMenuGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(this, "Points: " + game.getPoints(), "Points", JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * Put actions and key inputs.
     *
     * @param actionName    name of action to be put on the ActionMap
     * @param stepY         how far the object moves up every step
     * @param keysChars     chars to be put on the InputMap
     * @param keysStrings   strings to be put on the InputMap
     */
    private void setupKeyActions(final String actionName, final int stepY, final char[] keysChars, final String[] keysStrings) {
        final Action action = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(actionName); // TODO remove
                game.getBird().setStepY(stepY);
            }
        };

        this.getActionMap().put(actionName, action);

        for (char key : keysChars) {
            this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(key),
                    actionName);
        }

        for (String key : keysStrings) {
            this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(key),
                    actionName);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (game == null) {
            return; // TODO find a better solution
        }

        final Graphics2D g2 = (Graphics2D) g;
        for (Rectangle rect : game.getObstacles()) {
            g2.fill(rect);
        }

        g2.setColor(game.getBird().getColor());
        g2.fill(game.getBird());
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(640, 480);
    }
}